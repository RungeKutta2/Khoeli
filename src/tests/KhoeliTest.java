package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

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

//	private Khoeli khoeli;
//	private  Item espadaVieja;
//	private NonPlayable neneTerrorifico;
//	private NonPlayable npcMudo;
//	private Location baño;
//	 Location mercia;
//	Location inicial;
//	Location escuela;
//	Place sueloDeDescampado;
//	private Item caramelo;
//	private Item piedra;
//	@BeforeEach
//	void createAdventure() {
//		khoeli = new Khoeli();
//		Adventure selectedAdventure = new Adventure();
//		Endgame endgames = new Endgame("location", "move", "bar", endgameDescription);
//		ArrayList<Trigger> triggerEspada = new ArrayList<Trigger>();
//		triggerEspada.add(new Trigger(Types.ITEM, "espada_vieja", "gracias ahora puedo matar a mis enemigos :)",
//						"remove"));
//		ArrayList<Trigger> triggerVacio = new ArrayList<Trigger>();
//		espadaVieja = new Item("espada_vieja", "espada vieja", Genders.FEMALE, Numbers.SINGULAR, new ArrayList<String>(){{ add("use"); }},
//				"parece que esta a punto de romperse", triggerEspada);
//		piedra = new Item("piedra", "piedra", Genders.FEMALE, Numbers.SINGULAR, new ArrayList<String>(){{ add("use"); }},
//				"se puede afilar una espada con esto", triggerVacio);
//		caramelo = new Item("caramelo", "caramelo", Genders.MALE, Numbers.SINGULAR, new ArrayList<String>(){{ add("use"); }},
//				"dulce, rico.", triggerVacio);
//		neneTerrorifico = new NonPlayable("nene_terrorifico", "nene terrorifico",
//				"te mira esperando algo y hay un dragon al lado sospechoso(espada) ;)", Genders.MALE,
//				new Trigger[] { new Trigger(Types.ITEM, "espada_vieja", "gracias ahora puedo matar a mis enemigos :)",
//						"remove"), new Trigger(Types.ITEM, "caramelo","No quiero eso" ,"changeDescription;Ahora parece más enojado") },
//				"matar,matar,matar");
//		npcMudo = new NonPlayable("npc_mudo", "npc mudo", "no habla xd !!!1", Genders.MALE,
//				new Trigger[] { new Trigger(Types.ACTION, "espada_vieja", "", "remove") }, "");
//		escuela =new Location("escuela", Genders.MALE, Numbers.SINGULAR,
//				"Estás en una escuela. Al este hay un baño", new ArrayList<Place>() {
//			{
//				add(new Place("suelo", Genders.MALE, Numbers.SINGULAR, new ArrayList<String>()));
//			}
//		}, new String[] {},
//		new Connection[] { new Connection(Directions.NORTH, "descampado", ""),
//				new Connection(Directions.EAST, "baño", "") });
//		baño = new Location("baño", Genders.MALE, Numbers.SINGULAR, "Estás en un baño. Está sucio.",
//				new ArrayList<Place>() {
//					{
//						add(new Place("suelo", Genders.MALE, Numbers.SINGULAR, new ArrayList<String>() {
//							{
//								add("juguetes");
//							}
//						}));
//					}
//				}, new String[] { "nene_terrorifico" },
//				new Connection[] { new Connection(Directions.WEST, "escuela", "nene_terrofico") });
//		sueloDeDescampado = new Place("suelo", Genders.MALE, Numbers.SINGULAR, new ArrayList<String>() {
//			{
//				add("espada_vieja");
//			}
//		});
//		mercia = new Location("bar", Genders.MALE, Numbers.SINGULAR, "Estás en Mercia. Ves a todos tus amigos.",
//				new ArrayList<Place>() {
//					{
//						add(new Place("barra", Genders.FEMALE, Numbers.SINGULAR, new ArrayList<String>()));
//					}
//				}, new String[] {}, new Connection[] { new Connection(Directions.SOUTH, "descampado", "") });
//		inicial = new Location("descampado", Genders.MALE, Numbers.SINGULAR,
//				"Estás en un descampado, al norte hay un bar, al sur hay una escuela y al este hay un gimnasio.",
//				new ArrayList<Place>() {
//					{
//						add(sueloDeDescampado);
//					}
//				}, new String[] {},
//				new Connection[] { new Connection(Directions.NORTH, "bar", ""),
//						new Connection(Directions.SOUTH, "escuela", ""),
//						new Connection(Directions.EAST, "gimnasio", ""), });
//		Item[] items = new Item[] { espadaVieja, new Item("juguetes", "juguetes", Genders.MALE, Numbers.PLURAL,
//				new ArrayList<String>(), "son solo juguetes, nada que ver aqui", new Trigger[] {}) };
//		Location[] locations = new Location[] { inicial, mercia, escuela,
//				new Location("gimnasio", Genders.MALE, Numbers.SINGULAR,
//						"Estás en un gimnasio, está lleno de máquinas que no sabés usar.", new ArrayList<Place>() {
//							{
//								new Place("suelo", Genders.MALE, Numbers.SINGULAR, new ArrayList<String>());
//							}
//						}, new String[] {}, new Connection[] { new Connection(Directions.WEST, "descampado", "") }),
//				baño };
//
//		NonPlayable[] npcs = new NonPlayable[] { neneTerrorifico };
//
//		Playable mainCharacter = new Playable("Tigri", Genders.MALE);
//
//		Settings settings = new Settings("Bienvenido!", mainCharacter);
//
//		selectedAdventure.setEndgames(new Endgame[] { endgames });
//		selectedAdventure.setItems(items);
//		selectedAdventure.setLocations(locations);
//		selectedAdventure.setNpcs(npcs);
//		selectedAdventure.setSettings(settings);
//		khoeli.setSelectedAdventure(selectedAdventure);
//	}

	@Test
	void moveToExistingLocation() {
		Khoeli khoeli = new Khoeli();
		Playable mainCharacter = new Playable("Tigri", Genders.MALE);
		ArrayList<Place> places = new ArrayList<Place>();
		ArrayList<String> items = new ArrayList<String>();
		ArrayList<Connection> connectionsInicial = new ArrayList<Connection>();
		ArrayList<Connection> connectionsSur = new ArrayList<Connection>();
		ArrayList<Endgame> endGames = new ArrayList<Endgame>();
		Adventure selectedAdventure = new Adventure();
		selectedAdventure.setEndgames(endGames);
		ArrayList<Item> adventureItems = new ArrayList<Item>();
		selectedAdventure.setItems(adventureItems);
		ArrayList<Location> locations = new ArrayList<Location>();
		ArrayList<NonPlayable> npcs = new ArrayList<NonPlayable>();
		Settings settings = new Settings("Bienvenido!", mainCharacter);
		connectionsInicial.add(new Connection(Directions.SOUTH, "escuela", null));
		connectionsSur.add(new Connection(Directions.NORTH, "descampado", null));
		Location sur = new Location("escuela", Genders.FEMALE, Numbers.SINGULAR,
				"Estás en una escuela. Al este hay un baño", places, new ArrayList<String>(), connectionsSur);

		Location inicial = new Location("descampado", Genders.MALE, Numbers.SINGULAR,
				"Estás en un descampado, al sur hay una escuela y en el suelo hay una espada vieja", places,
				new ArrayList<String>(), connectionsInicial);
		locations.add(inicial);
		locations.add(sur);
		selectedAdventure.setLocations(locations);
		selectedAdventure.setNpcs(npcs);
		selectedAdventure.setSettings(settings);
		khoeli.setSelectedAdventure(selectedAdventure);

		// una location al sur y setear esa location

		places.add(new Place("suelo descampado", Genders.MALE, Numbers.SINGULAR, items));
		items.add("espada_vieja");

		String expected = "Estás en una escuela. Al este hay un baño";
		String result = khoeli.move(Directions.SOUTH);
		assertEquals(expected, result);
		assertEquals(sur, khoeli.getCurrentLocation());
	}

	@Test
	void moveToNonExistingLocation() {
		Khoeli khoeli = new Khoeli();
		Adventure selectedAdventure = new Adventure();
		Location inicial = new Location("descampado", Genders.MALE, Numbers.SINGULAR,
				"Estás en un descampado, al sur hay una escuela y en el suelo hay una espada vieja",
				new ArrayList<Place>(), new ArrayList<String>(), new ArrayList<Connection>());

		ArrayList<Location> locations = new ArrayList<Location>();
		locations.add(inicial);
		selectedAdventure.setLocations(locations);
		selectedAdventure.setNpcs(new ArrayList<NonPlayable>());
		Playable mainCharacter = new Playable("Tigri", Genders.MALE);
		Settings settings = new Settings("Bienvenido!", mainCharacter);
		selectedAdventure.setSettings(settings);
		khoeli.setSelectedAdventure(selectedAdventure);
		String expected = "No hay nada al oeste";
		String result = khoeli.move(Directions.WEST);
		assertEquals(expected, result);
		assertEquals(inicial, khoeli.getCurrentLocation());
	}

	@Test
	void pickUpExistingItem() {
		Khoeli khoeli = new Khoeli();
		Playable mainCharacter = new Playable("Tigri", Genders.MALE);
		ArrayList<Place> places = new ArrayList<Place>();
		ArrayList<String> items = new ArrayList<String>();
		ArrayList<Connection> connectionsInicial = new ArrayList<Connection>();
		ArrayList<Connection> connectionsSur = new ArrayList<Connection>();
		ArrayList<Endgame> endGames = new ArrayList<Endgame>();
		Adventure selectedAdventure = new Adventure();
		selectedAdventure.setEndgames(endGames);
		ArrayList<Item> adventureItems = new ArrayList<Item>();
		selectedAdventure.setItems(adventureItems);
		ArrayList<Location> locations = new ArrayList<Location>();
		ArrayList<NonPlayable> npcs = new ArrayList<NonPlayable>();
		Settings settings = new Settings("Bienvenido!", mainCharacter);
		connectionsInicial.add(new Connection(Directions.SOUTH, "escuela", null));
		connectionsSur.add(new Connection(Directions.NORTH, "descampado", null));
		Location sur = new Location("escuela", Genders.FEMALE, Numbers.SINGULAR,
				"Estás en una escuela. Al este hay un baño", places, new ArrayList<String>(), connectionsSur);

		Location inicial = new Location("descampado", Genders.MALE, Numbers.SINGULAR,
				"Estás en un descampado, al sur hay una escuela y en el suelo hay una espada vieja", places,
				new ArrayList<String>(), connectionsInicial);
		locations.add(inicial);
		locations.add(sur);
		selectedAdventure.setLocations(locations);
		selectedAdventure.setNpcs(npcs);
		selectedAdventure.setSettings(settings);
		khoeli.setSelectedAdventure(selectedAdventure);

		items.add("espada_vieja");
		Place sueloDeDescampado = new Place("suelo", Genders.MALE, Numbers.SINGULAR, new ArrayList<String>() {
			{
				add("espada_vieja");
			}
		});
		places.add(sueloDeDescampado);
		assertEquals("Juntaste espada_vieja", khoeli.pickUp("espada_vieja", "suelo"));
		assertFalse(sueloDeDescampado.getItems().contains("espada_vieja"));
	}

	@Test
	void pickUpNonExistingItem() {
		Khoeli khoeli = new Khoeli();
		Playable mainCharacter = new Playable("Tigri", Genders.MALE);
		ArrayList<Place> places = new ArrayList<Place>();
		ArrayList<String> items = new ArrayList<String>();
		ArrayList<Connection> connectionsInicial = new ArrayList<Connection>();
		ArrayList<Connection> connectionsSur = new ArrayList<Connection>();
		ArrayList<Endgame> endGames = new ArrayList<Endgame>();
		Adventure selectedAdventure = new Adventure();
		selectedAdventure.setEndgames(endGames);
		ArrayList<Item> adventureItems = new ArrayList<Item>();
		selectedAdventure.setItems(adventureItems);
		ArrayList<Location> locations = new ArrayList<Location>();
		ArrayList<NonPlayable> npcs = new ArrayList<NonPlayable>();
		Settings settings = new Settings("Bienvenido!", mainCharacter);
		connectionsInicial.add(new Connection(Directions.SOUTH, "escuela", null));
		connectionsSur.add(new Connection(Directions.NORTH, "descampado", null));
		Location sur = new Location("escuela", Genders.FEMALE, Numbers.SINGULAR,
				"Estás en una escuela. Al este hay un baño", places, new ArrayList<String>(), connectionsSur);

		Location inicial = new Location("descampado", Genders.MALE, Numbers.SINGULAR,
				"Estás en un descampado, al sur hay una escuela y en el suelo hay una espada vieja", places,
				new ArrayList<String>(), connectionsInicial);
		locations.add(inicial);
		locations.add(sur);
		selectedAdventure.setLocations(locations);
		selectedAdventure.setNpcs(npcs);
		selectedAdventure.setSettings(settings);
		khoeli.setSelectedAdventure(selectedAdventure);

		items.add("espada_vieja");
		Place sueloDeDescampado = new Place("suelo", Genders.MALE, Numbers.SINGULAR, new ArrayList<String>() {
			{
				add("espada_vieja");
			}
		});
		places.add(sueloDeDescampado);
		assertEquals("No hay llaves en suelo", khoeli.pickUp("llaves", "suelo"));
	}

	@Test
	void lookAtExistingItem() {
		Khoeli khoeli = new Khoeli();
		Playable mainCharacter = new Playable("Tigri", Genders.MALE);
		ArrayList<Place> places = new ArrayList<Place>();
		ArrayList<Connection> connectionsInicial = new ArrayList<Connection>();
		ArrayList<Connection> connectionsSur = new ArrayList<Connection>();
		ArrayList<Endgame> endGames = new ArrayList<Endgame>();
		Adventure selectedAdventure = new Adventure();
		selectedAdventure.setEndgames(endGames);
		ArrayList<Item> adventureItems = new ArrayList<Item>();
		selectedAdventure.setItems(adventureItems);
		ArrayList<Location> locations = new ArrayList<Location>();
		ArrayList<NonPlayable> npcs = new ArrayList<NonPlayable>();
		Settings settings = new Settings("Bienvenido!", mainCharacter);
		connectionsInicial.add(new Connection(Directions.SOUTH, "escuela", null));
		connectionsSur.add(new Connection(Directions.NORTH, "descampado", null));
		Location sur = new Location("escuela", Genders.FEMALE, Numbers.SINGULAR,
				"Estás en una escuela. Al este hay un baño", places, new ArrayList<String>(), connectionsSur);

		Location inicial = new Location("descampado", Genders.MALE, Numbers.SINGULAR,
				"Estás en un descampado, al sur hay una escuela y en el suelo hay una espada vieja", places,
				new ArrayList<String>(), connectionsInicial);
		locations.add(inicial);
		locations.add(sur);
		selectedAdventure.setLocations(locations);
		selectedAdventure.setNpcs(npcs);
		selectedAdventure.setSettings(settings);
		khoeli.setSelectedAdventure(selectedAdventure);
		Item espadaVieja = new Item("espada_vieja", "espada vieja", Genders.FEMALE, Numbers.SINGULAR,
				new ArrayList<String>(), "parece que esta a punto de romperse", new ArrayList<Trigger>());
		assertEquals("parece que esta a punto de romperse", khoeli.lookAt(espadaVieja));
	}

	@Test
	void lookAtExistingNpc() {
		Khoeli khoeli = new Khoeli();
		Playable mainCharacter = new Playable("Tigri", Genders.MALE);
		ArrayList<Place> places = new ArrayList<Place>();
		ArrayList<String> items = new ArrayList<String>();
		ArrayList<Connection> connectionsInicial = new ArrayList<Connection>();
		ArrayList<Connection> connectionsSur = new ArrayList<Connection>();
		ArrayList<Endgame> endGames = new ArrayList<Endgame>();
		Adventure selectedAdventure = new Adventure();
		selectedAdventure.setEndgames(endGames);
		ArrayList<Item> adventureItems = new ArrayList<Item>();
		selectedAdventure.setItems(adventureItems);
		ArrayList<Location> locations = new ArrayList<Location>();
		ArrayList<NonPlayable> npcs = new ArrayList<NonPlayable>();
		Settings settings = new Settings("Bienvenido!", mainCharacter);
		connectionsInicial.add(new Connection(Directions.SOUTH, "escuela", null));
		connectionsSur.add(new Connection(Directions.NORTH, "descampado", null));
		Location sur = new Location("escuela", Genders.FEMALE, Numbers.SINGULAR,
				"Estás en una escuela. Al este hay un baño", places, new ArrayList<String>(), connectionsSur);

		Location inicial = new Location("descampado", Genders.MALE, Numbers.SINGULAR,
				"Estás en un descampado, al sur hay una escuela y en el suelo hay una espada vieja", places,
				new ArrayList<String>(), connectionsInicial);
		locations.add(inicial);
		locations.add(sur);
		selectedAdventure.setLocations(locations);
		selectedAdventure.setNpcs(npcs);
		selectedAdventure.setSettings(settings);
		khoeli.setSelectedAdventure(selectedAdventure);
		ArrayList<Trigger> triggersNene = new ArrayList<Trigger>();
		triggersNene
				.add(new Trigger(Types.ITEM, "espada_vieja", "gracias ahora puedo matar a mis enemigos :)", "remove"));

		NonPlayable neneTerrorifico = new NonPlayable("nene_terrorifico", "nene terrorifico",
				"te mira esperando algo y hay un dragon al lado sospechoso(espada) ;)", Genders.MALE, triggersNene,
				"holis");

		assertEquals("te mira esperando algo y hay un dragon al lado sospechoso(espada) ;)",
				khoeli.lookAt(neneTerrorifico));
	}

	@Test
	void lookAtExistingLocation() {
		Khoeli khoeli = new Khoeli();
		Playable mainCharacter = new Playable("Tigri", Genders.MALE);
		ArrayList<Place> places = new ArrayList<Place>();
		ArrayList<String> items = new ArrayList<String>();
		ArrayList<Connection> connectionsInicial = new ArrayList<Connection>();
		ArrayList<Connection> connectionsSur = new ArrayList<Connection>();
		ArrayList<Endgame> endGames = new ArrayList<Endgame>();
		Adventure selectedAdventure = new Adventure();
		selectedAdventure.setEndgames(endGames);
		ArrayList<Item> adventureItems = new ArrayList<Item>();
		selectedAdventure.setItems(adventureItems);
		ArrayList<Location> locations = new ArrayList<Location>();
		ArrayList<NonPlayable> npcs = new ArrayList<NonPlayable>();
		Settings settings = new Settings("Bienvenido!", mainCharacter);
		connectionsInicial.add(new Connection(Directions.SOUTH, "escuela", null));
		connectionsSur.add(new Connection(Directions.NORTH, "descampado", null));
		Location sur = new Location("escuela", Genders.FEMALE, Numbers.SINGULAR,
				"Estás en una escuela. Al este hay un baño", places, new ArrayList<String>(), connectionsSur);

		Location inicial = new Location("descampado", Genders.MALE, Numbers.SINGULAR,
				"Estás en un descampado, al sur hay una escuela y en el suelo hay una espada vieja", places,
				new ArrayList<String>(), connectionsInicial);
		locations.add(inicial);
		locations.add(sur);
		selectedAdventure.setLocations(locations);
		selectedAdventure.setNpcs(npcs);
		selectedAdventure.setSettings(settings);
		khoeli.setSelectedAdventure(selectedAdventure);
		assertEquals("Estás en un descampado, al sur hay una escuela y en el suelo hay una espada vieja",
				khoeli.lookAt(inicial));
	}

	@Test
	void talkToNpc() {
		Khoeli khoeli = new Khoeli();
		Playable mainCharacter = new Playable("Tigri", Genders.MALE);
		ArrayList<Place> places = new ArrayList<Place>();
		ArrayList<String> items = new ArrayList<String>();
		ArrayList<Connection> connectionsInicial = new ArrayList<Connection>();
		ArrayList<Connection> connectionsSur = new ArrayList<Connection>();
		ArrayList<Endgame> endGames = new ArrayList<Endgame>();
		Adventure selectedAdventure = new Adventure();
		selectedAdventure.setEndgames(endGames);
		ArrayList<Item> adventureItems = new ArrayList<Item>();
		selectedAdventure.setItems(adventureItems);
		ArrayList<Location> locations = new ArrayList<Location>();
		ArrayList<NonPlayable> npcs = new ArrayList<NonPlayable>();
		Settings settings = new Settings("Bienvenido!", mainCharacter);
		connectionsInicial.add(new Connection(Directions.SOUTH, "escuela", null));
		connectionsSur.add(new Connection(Directions.NORTH, "descampado", null));
		Location sur = new Location("escuela", Genders.FEMALE, Numbers.SINGULAR,
				"Estás en una escuela. Al este hay un baño", places, new ArrayList<String>(), connectionsSur);

		Location inicial = new Location("descampado", Genders.MALE, Numbers.SINGULAR,
				"Estás en un descampado, al sur hay una escuela y en el suelo hay una espada vieja", places,
				new ArrayList<String>(), connectionsInicial);
		locations.add(inicial);
		locations.add(sur);
		selectedAdventure.setLocations(locations);
		selectedAdventure.setNpcs(npcs);
		selectedAdventure.setSettings(settings);
		khoeli.setSelectedAdventure(selectedAdventure);
		ArrayList<Trigger> triggersNene = new ArrayList<Trigger>();
		triggersNene
				.add(new Trigger(Types.ITEM, "espada_vieja", "gracias ahora puedo matar a mis enemigos :)", "remove"));

		NonPlayable neneTerrorifico = new NonPlayable("nene_terrorifico", "nene terrorifico",
				"te mira esperando algo y hay un dragon al lado sospechoso(espada) ;)", Genders.MALE, triggersNene,
				"holis");

		assertEquals("holis", khoeli.talkTo(neneTerrorifico));
	}

	@Test
	void talkToNpcMudo() {
		Khoeli khoeli = new Khoeli();
		Playable mainCharacter = new Playable("Tigri", Genders.MALE);
		ArrayList<Place> places = new ArrayList<Place>();
		ArrayList<String> items = new ArrayList<String>();
		ArrayList<Connection> connectionsInicial = new ArrayList<Connection>();
		ArrayList<Connection> connectionsSur = new ArrayList<Connection>();
		ArrayList<Endgame> endGames = new ArrayList<Endgame>();
		Adventure selectedAdventure = new Adventure();
		selectedAdventure.setEndgames(endGames);
		ArrayList<Item> adventureItems = new ArrayList<Item>();
		selectedAdventure.setItems(adventureItems);
		ArrayList<Location> locations = new ArrayList<Location>();
		ArrayList<NonPlayable> npcs = new ArrayList<NonPlayable>();
		Settings settings = new Settings("Bienvenido!", mainCharacter);
		connectionsInicial.add(new Connection(Directions.SOUTH, "escuela", null));
		connectionsSur.add(new Connection(Directions.NORTH, "descampado", null));
		Location sur = new Location("escuela", Genders.FEMALE, Numbers.SINGULAR,
				"Estás en una escuela. Al este hay un baño", places, new ArrayList<String>(), connectionsSur);

		Location inicial = new Location("descampado", Genders.MALE, Numbers.SINGULAR,
				"Estás en un descampado, al sur hay una escuela y en el suelo hay una espada vieja", places,
				new ArrayList<String>(), connectionsInicial);
		locations.add(inicial);
		locations.add(sur);
		selectedAdventure.setLocations(locations);
		selectedAdventure.setNpcs(npcs);
		selectedAdventure.setSettings(settings);
		khoeli.setSelectedAdventure(selectedAdventure);
		ArrayList<Trigger> triggersNene = new ArrayList<Trigger>();
		triggersNene
				.add(new Trigger(Types.ITEM, "espada_vieja", "gracias ahora puedo matar a mis enemigos :)", "remove"));

		NonPlayable neneMudo = new NonPlayable("nene_terrorifico", "nene terrorifico",
				"te mira esperando algo y hay un dragon al lado sospechoso(espada) ;)", Genders.MALE, triggersNene, "");

		assertEquals("No tiene nada que decir", khoeli.talkTo(neneMudo));
	}

	@Test
	void customizeCharacter() {
		Khoeli khoeli = new Khoeli();
		Playable mainCharacter = new Playable("Tigri", Genders.MALE);
		ArrayList<Place> places = new ArrayList<Place>();
		ArrayList<String> items = new ArrayList<String>();
		ArrayList<Connection> connectionsInicial = new ArrayList<Connection>();
		ArrayList<Connection> connectionsSur = new ArrayList<Connection>();
		ArrayList<Endgame> endGames = new ArrayList<Endgame>();
		Adventure selectedAdventure = new Adventure();
		selectedAdventure.setEndgames(endGames);
		ArrayList<Item> adventureItems = new ArrayList<Item>();
		selectedAdventure.setItems(adventureItems);
		ArrayList<Location> locations = new ArrayList<Location>();
		ArrayList<NonPlayable> npcs = new ArrayList<NonPlayable>();
		Settings settings = new Settings("Bienvenido!", mainCharacter);
		connectionsInicial.add(new Connection(Directions.SOUTH, "escuela", null));
		connectionsSur.add(new Connection(Directions.NORTH, "descampado", null));
		Location sur = new Location("escuela", Genders.FEMALE, Numbers.SINGULAR,
				"Estás en una escuela. Al este hay un baño", places, new ArrayList<String>(), connectionsSur);

		Location inicial = new Location("descampado", Genders.MALE, Numbers.SINGULAR,
				"Estás en un descampado, al sur hay una escuela y en el suelo hay una espada vieja", places,
				new ArrayList<String>(), connectionsInicial);
		locations.add(inicial);
		locations.add(sur);
		selectedAdventure.setLocations(locations);
		selectedAdventure.setNpcs(npcs);
		selectedAdventure.setSettings(settings);
		khoeli.setSelectedAdventure(selectedAdventure);
		khoeli.getSelectedAdventure().customizeCharacter("Manchita", Genders.FEMALE);
		assertEquals(Genders.FEMALE, khoeli.getSelectedAdventure().getSettings().getCharacter().getGender());
		assertEquals("Manchita", khoeli.getSelectedAdventure().getSettings().getCharacter().getName());
	}

	@Test
	void testEndgame() {
		String endgameDescription = "quedan mas test";
		Khoeli khoeli = new Khoeli();
		Playable mainCharacter = new Playable("Tigri", Genders.MALE);
		ArrayList<Place> places = new ArrayList<Place>();
		ArrayList<Connection> connectionsInicial = new ArrayList<Connection>();
		ArrayList<Endgame> endGames = new ArrayList<Endgame>();
		Adventure selectedAdventure = new Adventure();
		selectedAdventure.setEndgames(endGames);
		ArrayList<Item> adventureItems = new ArrayList<Item>();
		selectedAdventure.setItems(adventureItems);
		ArrayList<Location> locations = new ArrayList<Location>();
		ArrayList<NonPlayable> npcs = new ArrayList<NonPlayable>();
		Settings settings = new Settings("Bienvenido!", mainCharacter);
		ArrayList<Endgame> endgames = new ArrayList<Endgame>();
		endgames.add(new Endgame("location", "move", "sur", endgameDescription));
		Location inicial = new Location("descampado", Genders.MALE, Numbers.SINGULAR, "estas en un descampado", places,
				new ArrayList<String>(), connectionsInicial);
		connectionsInicial.add(new Connection(Directions.SOUTH, "mercia", null));
		ArrayList<Connection> connections = new ArrayList<Connection>();
		Location mercia = new Location("mercia", Genders.MALE, Numbers.SINGULAR, "quedan mas test", places,
				new ArrayList<String>(), connections);
		locations.add(inicial);
		locations.add(mercia);

		selectedAdventure.setLocations(locations);
		selectedAdventure.setNpcs(npcs);
		selectedAdventure.setSettings(settings);
		selectedAdventure.setEndgames(endgames);
		khoeli.setSelectedAdventure(selectedAdventure);

		String locationString = khoeli.move(Directions.SOUTH);
		assertEquals(mercia, khoeli.getCurrentLocation());
		assertEquals(endgameDescription, locationString);
	}

	@Test
	void testUseItemOnNpc() {
		Khoeli khoeli = new Khoeli();
		ArrayList<Place> places = new ArrayList<Place>();
		ArrayList<Trigger> triggersNene = new ArrayList<Trigger>();
		triggersNene
				.add(new Trigger(Types.ITEM, "espada_vieja", "gracias ahora puedo matar a mis enemigos :)", "remove"));
		NonPlayable neneTerrorifico = new NonPlayable("nene_terrorifico", "nene terrorifico",
				"te mira esperando algo y hay un dragon al lado sospechoso(espada) ;)", Genders.MALE, triggersNene,
				"holis");
		Item espadaVieja = new Item("espada_vieja", "espada vieja", Genders.FEMALE, Numbers.SINGULAR,
				new ArrayList<String>(), "parece que esta a punto de romperse", new ArrayList<Trigger>());
		Adventure selectedAdventure = new Adventure();
		ArrayList<Item> adventureItems = new ArrayList<Item>();
		adventureItems.add(espadaVieja);
		selectedAdventure.setItems(adventureItems);
		ArrayList<NonPlayable> npcs = new ArrayList<NonPlayable>();
		npcs.add(neneTerrorifico);
		assertEquals("gracias ahora puedo matar a mis enemigos :)", khoeli.use(espadaVieja, neneTerrorifico));
	}

	@Test
	void testUseItemOnItem() {
		Khoeli khoeli = new Khoeli();
		Playable mainCharacter = new Playable("Tigri", Genders.MALE);
		Settings settings = new Settings("Bienvenido!", mainCharacter);
		ArrayList<Trigger> triggersEspada = new ArrayList<Trigger>();
		ArrayList<Trigger> triggersPiedra = new ArrayList<Trigger>();
		triggersEspada.add(new Trigger(Types.ITEM, "piedra", "La espada ahora está afilada", "delete;piedra"));
		Item piedra = new Item("piedra", "piedra", Genders.FEMALE, Numbers.SINGULAR, new ArrayList<String>(),
				"se puede afilar una espada con esto", triggersPiedra);
		Item espadaVieja = new Item("espada_vieja", "espada vieja", Genders.FEMALE, Numbers.SINGULAR,
				new ArrayList<String>(), "parece que esta a punto de romperse", triggersEspada);
		Adventure selectedAdventure = new Adventure();
		ArrayList<Item> adventureItems = new ArrayList<Item>();
		ArrayList<Location> locations = new ArrayList<Location>();
		Location inicial = new Location("descampado", Genders.MALE, Numbers.SINGULAR, "estas en un descampado",
				new ArrayList<Place>(), new ArrayList<String>(), new ArrayList<Connection>());
		locations.add(inicial);
		adventureItems.add(espadaVieja);
		adventureItems.add(piedra);
		selectedAdventure.setSettings(settings);
		selectedAdventure.setLocations(locations);
		selectedAdventure.setItems(adventureItems);
		khoeli.setSelectedAdventure(selectedAdventure);
		khoeli.getSelectedAdventure().getSettings().getCharacter().getItems().add("piedra");
		assertTrue(khoeli.getSelectedAdventure().getSettings().getCharacter().getItems().contains("piedra"));
		assertEquals("La espada ahora está afilada", khoeli.use(piedra, espadaVieja));
		assertFalse(khoeli.getSelectedAdventure().getSettings().getCharacter().getItems().contains("piedra"));
	}

	@Test
	void testChangeDescription() {
		Khoeli khoeli = new Khoeli();
		Playable mainCharacter = new Playable("Tigri", Genders.MALE);
		Settings settings = new Settings("Bienvenido!", mainCharacter);
		ArrayList<Trigger> triggersCaramelo = new ArrayList<Trigger>();
		triggersCaramelo.add(new Trigger(Types.ITEM, "caramelo", "", ""));
		Item caramelo = new Item("caramelo", "caramelo", Genders.MALE, Numbers.SINGULAR, new ArrayList<String>(),
				"rico caramelo media hora", triggersCaramelo);
		ArrayList<Trigger> triggersNene = new ArrayList<Trigger>();
		triggersNene.add(
				new Trigger(Types.ITEM, "caramelo", "No quiero eso", "changeDescription;Ahora parece más enojado"));
		NonPlayable neneTerrorifico = new NonPlayable("nene_terrorifico", "nene terrorifico",
				"te mira esperando algo y hay un dragon al lado sospechoso(espada) ;)", Genders.MALE, triggersNene,
				"holis");

		assertEquals("No quiero eso", khoeli.use(caramelo, neneTerrorifico));
		assertEquals("Ahora parece más enojado", neneTerrorifico.getDescription());
	}

	// falta probar que agregue un item al inventario.
	@Test
	void testAddItemInventory() {
		Khoeli khoeli = new Khoeli();
		Adventure selectedAdventure = new Adventure();
		Playable mainCharacter = new Playable("Tigri", Genders.MALE);
		Settings settings = new Settings("Bienvenido!", mainCharacter);
		selectedAdventure.setSettings(settings);
		ArrayList<Item> items = new ArrayList<Item>();
		items.add(new Item("celular","celular",Genders.MALE,Numbers.SINGULAR,new ArrayList<String>(),"un celular",new ArrayList<Trigger>()));
		selectedAdventure.setItems(items);
		ArrayList<Trigger> triggersCaramelo = new ArrayList<Trigger>();
		triggersCaramelo.add(new Trigger(Types.ITEM, "caramelo", "", ""));
		Item caramelo = new Item("caramelo", "caramelo", Genders.MALE, Numbers.SINGULAR, new ArrayList<String>(),
				"rico caramelo media hora", triggersCaramelo);
		ArrayList<Trigger> triggersNene = new ArrayList<Trigger>();
		triggersNene.add(
				new Trigger(Types.ITEM, "caramelo", "Ya no necesito este celular (Ahora tenes un celular)", "add;celular"));
		NonPlayable neneTerrorifico = new NonPlayable("nene_terrorifico", "nene terrorifico",
				"te mira esperando algo y hay un dragon al lado sospechoso(espada) ;)", Genders.MALE, triggersNene,
				"holis");

		assertEquals("Ya no necesito este celular (Ahora tenes un celular)", khoeli.use(caramelo, neneTerrorifico));
		assertEquals(, neneTerrorifico.getDescription());
	}

}
