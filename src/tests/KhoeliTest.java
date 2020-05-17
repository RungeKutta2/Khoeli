package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import main.Khoeli;
import models.Adventure;
import models.Connection;
import models.Directions;
import models.Genders;
import models.Numbers;
import models.Place;
import models.Playable;
import models.Settings;
import models.Trigger;
import models.Types;
import models.Endgame;
import models.Item;
import models.Location;
import models.NonPlayable;

class KhoeliTest {

	static private Khoeli khoeli;
	private static Item espadaVieja;
	private static NonPlayable neneTerrorifico;
	private static NonPlayable npcMudo;
	private static Location baño;
	private static String endgameDescription = "Llegaste al bar, empezaste a festejar con tus amigos, pero como estaban todos deprimidos por la evaluación del primer cuatrimeste murieron por un coma alcoholico. Fin. Beber con moderación. Si bebiste no conduzcas.";
	static Location mercia;
	static Location inicial;
	static Location escuela;
	static Place sueloDeDescampado;

	@BeforeAll
	static void createAdventure() {
		khoeli = new Khoeli();
		Adventure selectedAdventure = new Adventure();
		Endgame endgames = new Endgame("location", "move", "bar", endgameDescription);
		espadaVieja = new Item("espada_vieja", "espada vieja", Genders.FEMALE, Numbers.SINGULAR, new ArrayList<String>(){{ add("use"); }},
				"parece que esta a punto de romperse", new Trigger[] {});
		neneTerrorifico = new NonPlayable("nene_terrorifico", "nene terrorifico",
				"te mira esperando algo y hay un dragon al lado sospechoso(espada) ;)", Genders.MALE,
				new Trigger[] { new Trigger(Types.ITEM, "espada_vieja", "gracias ahora puedo matar a mis enemigos :)",
						"remove") },
				"matar,matar,matar");
		npcMudo = new NonPlayable("npc_mudo", "npc mudo", "no habla xd !!!1", Genders.MALE,
				new Trigger[] { new Trigger(Types.ACTION, "espada_vieja", "", "remove") }, "");
		escuela =new Location("escuela", Genders.MALE, Numbers.SINGULAR,
				"Estás en una escuela. Al este hay un baño", new ArrayList<Place>() {
			{
				add(new Place("suelo", Genders.MALE, Numbers.SINGULAR, new ArrayList<String>()));
			}
		}, new String[] {},
		new Connection[] { new Connection(Directions.NORTH, "descampado", ""),
				new Connection(Directions.EAST, "baño", "") });
		baño = new Location("baño", Genders.MALE, Numbers.SINGULAR, "Estás en un baño. Está sucio.",
				new ArrayList<Place>() {
					{
						add(new Place("suelo", Genders.MALE, Numbers.SINGULAR, new ArrayList<String>() {
							{
								add("juguetes");
							}
						}));
					}
				}, new String[] { "nene_terrorifico" },
				new Connection[] { new Connection(Directions.WEST, "escuela", "nene_terrofico") });
		sueloDeDescampado = new Place("suelo", Genders.MALE, Numbers.SINGULAR, new ArrayList<String>() {
			{
				add("espada_vieja");
			}
		});
		mercia = new Location("bar", Genders.MALE, Numbers.SINGULAR, "Estás en Mercia. Ves a todos tus amigos.",
				new ArrayList<Place>() {
					{
						add(new Place("barra", Genders.FEMALE, Numbers.SINGULAR, new ArrayList<String>()));
					}
				}, new String[] {}, new Connection[] { new Connection(Directions.SOUTH, "descampado", "") });
		inicial = new Location("descampado", Genders.MALE, Numbers.SINGULAR,
				"Estás en un descampado, al norte hay un bar, al sur hay una escuela y al este hay un gimnasio.",
				new ArrayList<Place>() {
					{
						add(sueloDeDescampado);
					}
				}, new String[] {},
				new Connection[] { new Connection(Directions.NORTH, "bar", ""),
						new Connection(Directions.SOUTH, "escuela", ""),
						new Connection(Directions.EAST, "gimnasio", ""), });
		Item[] items = new Item[] { espadaVieja, new Item("juguetes", "juguetes", Genders.MALE, Numbers.PLURAL,
				new ArrayList<String>(), "son solo juguetes, nada que ver aqui", new Trigger[] {}) };
		Location[] locations = new Location[] { inicial, mercia, escuela,
				new Location("gimnasio", Genders.MALE, Numbers.SINGULAR,
						"Estás en un gimnasio, está lleno de máquinas que no sabés usar.", new ArrayList<Place>() {
							{
								new Place("suelo", Genders.MALE, Numbers.SINGULAR, new ArrayList<String>());
							}
						}, new String[] {}, new Connection[] { new Connection(Directions.WEST, "descampado", "") }),
				baño };

		NonPlayable[] npcs = new NonPlayable[] { neneTerrorifico };

		Playable mainCharacter = new Playable("Tigri", Genders.MALE);

		Settings settings = new Settings("Bienvenido!", mainCharacter);

		selectedAdventure.setEndgames(new Endgame[] { endgames });
		selectedAdventure.setItems(items);
		selectedAdventure.setLocations(locations);
		selectedAdventure.setNpcs(npcs);
		selectedAdventure.setSettings(settings);
		khoeli.setSelectedAdventure(selectedAdventure);
	}

	@Test
	void moveToExistingLocation() {
		String expected = "Estás en una escuela. Al este hay un baño";
		String result = khoeli.move(Directions.SOUTH);
		assertEquals(expected, result);
		assertEquals(escuela, khoeli.getCurrentLocation());
	}

	@Test
	void moveToNonExistingLocation() {
		String expected = "No hay nada al oeste";
		String result = khoeli.move(Directions.WEST);
		assertEquals(expected, result);
		assertEquals(inicial, khoeli.getCurrentLocation());
	}

	@Test
	void pickUpExistingItem() {
		assertEquals("Juntaste espada_vieja", khoeli.pickUp("espada_vieja", "suelo"));
		assertFalse(sueloDeDescampado.getItems().contains("espada_vieja"));
	}

	@Test
	void pickUpNonExistingItem() {
		assertEquals("No hay llaves en suelo", khoeli.pickUp("llaves", "suelo"));
	}

	@Test
	void lookAtExistingItem() {
		assertEquals("parece que esta a punto de romperse", khoeli.lookAt(espadaVieja));
	}

	@Test
	void lookAtExistingNpc() {
		assertEquals("te mira esperando algo y hay un dragon al lado sospechoso(espada) ;)",
				khoeli.lookAt(neneTerrorifico));
	}

	@Test
	void lookAtExistingLocation() {
		assertEquals("Estás en un baño. Está sucio.", khoeli.lookAt(baño));
	}

	@Test
	void talkToNpc() {
		assertEquals("matar,matar,matar", khoeli.talkTo(neneTerrorifico));
	}

	@Test
	void talkToNpcMudo() {
		assertEquals("No tiene nada que decir", khoeli.talkTo(npcMudo));
	}

	@Test
	void customizeCharacter() {
		khoeli.getSelectedAdventure().customizeCharacter("Manchita", Genders.FEMALE);
		assertEquals(Genders.FEMALE, khoeli.getSelectedAdventure().getSettings().getCharacter().getGender());
		assertEquals("Manchita", khoeli.getSelectedAdventure().getSettings().getCharacter().getName());
	}
	@Test
	void testEndgame() {
		String asd = khoeli.move(Directions.NORTH);
		assertEquals(mercia, khoeli.getCurrentLocation());
		assertEquals(endgameDescription, asd);
	}
}
