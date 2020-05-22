package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
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

	private Khoeli khoeli;
	private  Item espadaVieja;
	private NonPlayable neneTerrorifico;
	private NonPlayable npcMudo;
	private Location ba�o;
	private String endgameDescription = "Llegaste al bar, empezaste a festejar con tus amigos, pero como estaban todos deprimidos por la evaluaci�n del primer cuatrimeste murieron por un coma alcoholico. Fin. Beber con moderaci�n. Si bebiste no conduzcas.";
	 Location mercia;
	Location inicial;
	Location escuela;
	Place sueloDeDescampado;
	private Item caramelo;
	private Item piedra;
	@BeforeEach
	void createAdventure() {
		khoeli = new Khoeli();
		Adventure selectedAdventure = new Adventure();
		Endgame endgames = new Endgame("location", "move", "bar", endgameDescription);
		espadaVieja = new Item("espada_vieja", "espada vieja", Genders.FEMALE, Numbers.SINGULAR, new ArrayList<String>(){{ add("use"); }},
				"parece que esta a punto de romperse", new Trigger[] {new Trigger(Types.ITEM, "piedra","La espada ahora est� afilada","delete;piedra")});
		piedra = new Item("piedra", "piedra", Genders.FEMALE, Numbers.SINGULAR, new ArrayList<String>(){{ add("use"); }},
				"se puede afilar una espada con esto", new Trigger[] {});
		caramelo = new Item("caramelo", "caramelo", Genders.MALE, Numbers.SINGULAR, new ArrayList<String>(){{ add("use"); }},
				"dulce, rico.", new Trigger[] {});
		neneTerrorifico = new NonPlayable("nene_terrorifico", "nene terrorifico",
				"te mira esperando algo y hay un dragon al lado sospechoso(espada) ;)", Genders.MALE,
				new Trigger[] { new Trigger(Types.ITEM, "espada_vieja", "gracias ahora puedo matar a mis enemigos :)",
						"remove"), new Trigger(Types.ITEM, "caramelo","No quiero eso" ,"changeDescription;Ahora parece m�s enojado") },
				"matar,matar,matar");
		npcMudo = new NonPlayable("npc_mudo", "npc mudo", "no habla xd !!!1", Genders.MALE,
				new Trigger[] { new Trigger(Types.ACTION, "espada_vieja", "", "remove") }, "");
		escuela =new Location("escuela", Genders.MALE, Numbers.SINGULAR,
				"Est�s en una escuela. Al este hay un ba�o", new ArrayList<Place>() {
			{
				add(new Place("suelo", Genders.MALE, Numbers.SINGULAR, new ArrayList<String>()));
			}
		}, new String[] {},
		new Connection[] { new Connection(Directions.NORTH, "descampado", ""),
				new Connection(Directions.EAST, "ba�o", "") });
		ba�o = new Location("ba�o", Genders.MALE, Numbers.SINGULAR, "Est�s en un ba�o. Est� sucio.",
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
		mercia = new Location("bar", Genders.MALE, Numbers.SINGULAR, "Est�s en Mercia. Ves a todos tus amigos.",
				new ArrayList<Place>() {
					{
						add(new Place("barra", Genders.FEMALE, Numbers.SINGULAR, new ArrayList<String>()));
					}
				}, new String[] {}, new Connection[] { new Connection(Directions.SOUTH, "descampado", "") });
		inicial = new Location("descampado", Genders.MALE, Numbers.SINGULAR,
				"Est�s en un descampado, al norte hay un bar, al sur hay una escuela y al este hay un gimnasio.",
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
						"Est�s en un gimnasio, est� lleno de m�quinas que no sab�s usar.", new ArrayList<Place>() {
							{
								new Place("suelo", Genders.MALE, Numbers.SINGULAR, new ArrayList<String>());
							}
						}, new String[] {}, new Connection[] { new Connection(Directions.WEST, "descampado", "") }),
				ba�o };

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
		String expected = "Est�s en una escuela. Al este hay un ba�o";
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
		assertEquals("Est�s en un ba�o. Est� sucio.", khoeli.lookAt(ba�o));
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
		String locationString = khoeli.move(Directions.NORTH);
		assertEquals(mercia, khoeli.getCurrentLocation());
		assertEquals(endgameDescription, locationString);
	}
	
	@Test
	void testUseItemOnNpc() {
		assertEquals("gracias ahora puedo matar a mis enemigos :)",khoeli.use(espadaVieja, neneTerrorifico));
	}
	
	@Test
	void testUseItemOnItem() {
		khoeli.getSelectedAdventure().getSettings().getCharacter().getItems().add("piedra");
		assertTrue(khoeli.getSelectedAdventure().getSettings().getCharacter().getItems().contains("piedra"));
		assertEquals("La espada ahora est� afilada",khoeli.use(piedra, espadaVieja));
		assertFalse(khoeli.getSelectedAdventure().getSettings().getCharacter().getItems().contains("piedra"));
	}
	
	@Test
	void testChangeDescription() {
	assertEquals("No quiero eso", khoeli.use(caramelo, neneTerrorifico));
	assertEquals("Ahora parece m�s enojado",neneTerrorifico.getDescription());
	}
	//falta probar que agregue un item al inventario.
	//falta probar que cambie descripcion.
}
