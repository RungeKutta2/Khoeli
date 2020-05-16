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
import models.Endgame;
import models.Item;
import models.Location;
import models.NonPlayable;

class KhoeliTest {

	static private Khoeli khoeli;
	private static String endgameDescription = "Llegaste al bar, empezaste a festejar con tus amigos, pero como estaban todos deprimidos por la evaluación del primer cuatrimeste murieron por un coma alcoholico. Fin. Beber con moderación. Si bebiste no conduzcas.";
	static Location mercia;
	static Location inicial;
	static Place sueloDeDescampado;
	@BeforeAll
	static void createAdventure() {
		khoeli = new Khoeli();
		Adventure selectedAdventure = new Adventure();
		Endgame endgames = new Endgame("location", "move", "bar", endgameDescription);
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
		Item[] items = new Item[] {
				new Item("espada_vieja", "espada vieja", Genders.FEMALE, Numbers.SINGULAR, new String[] { "use" }),
				new Item("juguetes", "juguetes", Genders.MALE, Numbers.PLURAL, new String[] {}) };
		Location[] locations = new Location[] { inicial, mercia, new Location("escuela", Genders.MALE, Numbers.SINGULAR,
				"Estás en una escuela. Al este hay un baño", new ArrayList<Place>() {
					{
						add(new Place("suelo", Genders.MALE, Numbers.SINGULAR, new ArrayList<String>()));
					}
				}, new String[] {},
				new Connection[] { new Connection(Directions.NORTH, "descampado", ""),
						new Connection(Directions.EAST, "baño", "") }),
				new Location("gimnasio", Genders.MALE, Numbers.SINGULAR,
						"Estás en un gimnasio, está lleno de máquinas que no sabés usar.", new ArrayList<Place>() {
							{
								new Place("suelo", Genders.MALE, Numbers.SINGULAR, new ArrayList<String>());
							}
						}, new String[] {}, new Connection[] { new Connection(Directions.WEST, "descampado", "") }),
				new Location("baño", Genders.MALE, Numbers.SINGULAR, "Estás en un baño. Está sucio.",
						new ArrayList<Place>() {
							{
								add(new Place("suelo", Genders.MALE, Numbers.SINGULAR, new ArrayList<String>() {
									{
										add("juguetes");
									}
								}));
							}
						}, new String[] { "nene_terrorifico" },
						new Connection[] { new Connection(Directions.WEST, "escuela", "nene_terrofico") }) };

		NonPlayable[] npcs = new NonPlayable[] { new NonPlayable("oficial_de_policia", "", Genders.MALE),
				new NonPlayable("nene_terrorifico", "", Genders.MALE) };

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
		String expected = "Estás en Mercia. Ves a todos tus amigos.";
		String result = khoeli.move(Directions.NORTH);
		assertEquals(expected, result);
		assertEquals(mercia, khoeli.getCurrentLocation());
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

	void pickUpNonExistingItem() {

	}
}
