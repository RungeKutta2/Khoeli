package tests;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import models.*;

class KhoeliTest {

	@Test
	void testMoveToExistingLocation() {
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
		places.add(new Place("suelo descampado", Genders.MALE, Numbers.SINGULAR, items));
		items.add("espada_vieja");
		String expected = "Estás en una escuela. Al este hay un baño";
		String result = khoeli.move(Directions.SOUTH);
		assertEquals(expected, result);
		assertEquals(sur, khoeli.getCurrentLocation());
	}

	@Test
	void testMoveToNonExistingLocation() {
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
	void testPickUpExistingItem() {
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
		List<String> itemsSueloDeDescampado = new ArrayList<String>();
		itemsSueloDeDescampado.add("espada_vieja");
		Place sueloDeDescampado = new Place("suelo", Genders.MALE, Numbers.SINGULAR, itemsSueloDeDescampado);
		places.add(sueloDeDescampado);
		assertEquals("Juntaste espada_vieja", khoeli.pickUp("espada_vieja", "suelo"));
		assertFalse(sueloDeDescampado.getItems().contains("espada_vieja"));
	}

	@Test
	void testPickUpNonExistingItem() {
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
		List<String> itemsSueloDeDescampado = new ArrayList<String>();
		itemsSueloDeDescampado.add("espada_vieja");
		Place sueloDeDescampado = new Place("suelo", Genders.MALE, Numbers.SINGULAR, itemsSueloDeDescampado);
		places.add(sueloDeDescampado);
		assertEquals("No hay llaves en suelo", khoeli.pickUp("llaves", "suelo"));
	}

	@Test
	void testLookAtExistingItem() {
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
	void testLookAtExistingNpc() {
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
		ArrayList<Trigger> triggersNene = new ArrayList<Trigger>();
		NonPlayable neneTerrorifico = new NonPlayable("nene_terrorifico", "nene terrorifico",
				"te mira esperando algo y hay un dragon al lado sospechoso(espada) ;)", Genders.MALE, triggersNene,
				"holis");
		assertEquals("te mira esperando algo y hay un dragon al lado sospechoso(espada) ;)",
				khoeli.lookAt(neneTerrorifico));
	}

	@Test
	void testLookAtExistingLocation() {
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
		assertEquals("Estás en un descampado, al sur hay una escuela y en el suelo hay una espada vieja",
				khoeli.lookAt(inicial));
	}

	@Test
	void testTalkToNpc() {
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
		ArrayList<Trigger> triggersNene = new ArrayList<Trigger>();
		NonPlayable neneTerrorifico = new NonPlayable("nene_terrorifico", "nene terrorifico",
				"te mira esperando algo y hay un dragon al lado sospechoso(espada) ;)", Genders.MALE, triggersNene,
				"holis");
		assertEquals("holis", khoeli.talkTo(neneTerrorifico));
	}

	@Test
	void testTalkToNpcMudo() {
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
		ArrayList<Trigger> triggersNene = new ArrayList<Trigger>();
		NonPlayable neneMudo = new NonPlayable("nene_terrorifico", "nene terrorifico",
				"te mira esperando algo y hay un dragon al lado sospechoso(espada) ;)", Genders.MALE, triggersNene, "");
		assertEquals("No tiene nada que decir", khoeli.talkTo(neneMudo));
	}

	@Test
	void testCustomizeCharacter() {
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
		khoeli.getSelectedAdventure().customizeCharacter("Manchita", Genders.FEMALE);
		assertEquals(Genders.FEMALE, khoeli.getSelectedAdventure().getSettings().getCharacter().getGender());
		assertEquals("Manchita", khoeli.getSelectedAdventure().getSettings().getCharacter().getName());
	}

	@Test
	void testEndgame() {
		String endgameDescription = "Lo lograste, ahora disfuta con tus amigos";
		Khoeli khoeli = new Khoeli();
		Playable mainCharacter = new Playable("Tigri", Genders.MALE);
		ArrayList<Place> places = new ArrayList<Place>();
		ArrayList<Connection> connectionsInicial = new ArrayList<Connection>();
		Adventure selectedAdventure = new Adventure();
		ArrayList<Item> adventureItems = new ArrayList<Item>();
		selectedAdventure.setItems(adventureItems);
		ArrayList<Location> locations = new ArrayList<Location>();
		ArrayList<NonPlayable> npcs = new ArrayList<NonPlayable>();
		Settings settings = new Settings("Bienvenido!", mainCharacter);
		ArrayList<Endgame> endgames = new ArrayList<Endgame>();
		endgames.add(new Endgame("location", "move", "mercia", endgameDescription));
		Location inicial = new Location("descampado", Genders.MALE, Numbers.SINGULAR, "estas en un descampado", places,
				new ArrayList<String>(), connectionsInicial);
		connectionsInicial.add(new Connection(Directions.SOUTH, "mercia", null));
		ArrayList<Connection> connections = new ArrayList<Connection>();
		Location mercia = new Location("mercia", Genders.MALE, Numbers.SINGULAR,
				"Estas en mercia, un bar muy lindo para estar con amigos, al norte hay un descampado", places,
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
		Playable mainCharacter = new Playable("Tigri", Genders.MALE);
		Settings settings = new Settings("Bienvenido!", mainCharacter);
		ArrayList<Trigger> triggersNene = new ArrayList<Trigger>();
		triggersNene.add(new Trigger(Types.ITEM, "espada_vieja", "gracias ahora puedo matar a mis enemigos :)",
				new AfterTrigger(TriggerActions.REMOVE, "espada_vieja", null, DestinationTypes.INVENTORY)));
		NonPlayable neneTerrorifico = new NonPlayable("nene_terrorifico", "nene terrorifico",
				"te mira esperando algo y hay un dragon al lado sospechoso(espada) ;)", Genders.MALE, triggersNene,
				"holis");
		Item espadaVieja = new Item("espada_vieja", "espada vieja", Genders.FEMALE, Numbers.SINGULAR,
				new ArrayList<String>(), "parece que esta a punto de romperse", new ArrayList<Trigger>());
		Adventure selectedAdventure = new Adventure();
		ArrayList<Item> adventureItems = new ArrayList<Item>();
		adventureItems.add(espadaVieja);
		ArrayList<Location> locations = new ArrayList<Location>();
		Location inicial = new Location("descampado", Genders.MALE, Numbers.SINGULAR, "estas en un descampado",
				new ArrayList<Place>(), new ArrayList<String>(), new ArrayList<Connection>());
		selectedAdventure.setItems(adventureItems);
		locations.add(inicial);
		selectedAdventure.setSettings(settings);
		selectedAdventure.setLocations(locations);
		khoeli.setSelectedAdventure(selectedAdventure);
		ArrayList<NonPlayable> npcs = new ArrayList<NonPlayable>();
		npcs.add(neneTerrorifico);
		assertEquals("gracias ahora puedo matar a mis enemigos :)", khoeli.use(espadaVieja, neneTerrorifico));
		assertFalse(selectedAdventure.getSettings().getCharacter().getItems().contains("espada_vieja"));
	}

	@Test
	void testUseItemOnItem() {
		Khoeli khoeli = new Khoeli();
		Playable mainCharacter = new Playable("Tigri", Genders.MALE);
		Settings settings = new Settings("Bienvenido!", mainCharacter);
		ArrayList<Trigger> triggersEspada = new ArrayList<Trigger>();
		ArrayList<Trigger> triggersPiedra = new ArrayList<Trigger>();
		triggersEspada.add(new Trigger(Types.ITEM, "piedra", "La espada ahora está afilada",
				new AfterTrigger(TriggerActions.REMOVE, "piedra", null, DestinationTypes.INVENTORY)));
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
		ArrayList<Trigger> triggersCaramelo = new ArrayList<Trigger>();
		Item caramelo = new Item("caramelo", "caramelo", Genders.MALE, Numbers.SINGULAR, new ArrayList<String>(),
				"rico caramelo media hora", triggersCaramelo);
		ArrayList<Trigger> triggersNene = new ArrayList<Trigger>();
		triggersNene.add(new Trigger(Types.ITEM, "caramelo", "No quiero eso",
				new AfterTrigger(TriggerActions.CHANGE_DESCRIPTION, "Ahora parece más enojado", null, null)));
		NonPlayable neneTerrorifico = new NonPlayable("nene_terrorifico", "nene terrorifico",
				"te mira esperando algo y hay un dragon al lado sospechoso(espada) ;)", Genders.MALE, triggersNene,
				"holis");
		assertEquals("No quiero eso", khoeli.use(caramelo, neneTerrorifico));
		assertEquals("Ahora parece más enojado", neneTerrorifico.getDescription());
	}

	@Test
	void testAddItemInventory() {
		Khoeli khoeli = new Khoeli();
		Adventure selectedAdventure = new Adventure();
		Playable mainCharacter = new Playable("Tigri", Genders.MALE);
		Settings settings = new Settings("Bienvenido!", mainCharacter);

		ArrayList<Item> items = new ArrayList<Item>();
		items.add(new Item("celular", "celular", Genders.MALE, Numbers.SINGULAR, new ArrayList<String>(), "un celular",
				new ArrayList<Trigger>()));
		ArrayList<Trigger> triggersCaramelo = new ArrayList<Trigger>();
		Item caramelo = new Item("caramelo", "caramelo", Genders.MALE, Numbers.SINGULAR, new ArrayList<String>(),
				"rico caramelo media hora", triggersCaramelo);
		ArrayList<Trigger> triggersNene = new ArrayList<Trigger>();
		triggersNene.add(new Trigger(Types.ITEM, "caramelo", "Ya no necesito este celular (Ahora tenes un celular)",
				new AfterTrigger(TriggerActions.ADD, "celular", null, DestinationTypes.INVENTORY)));
		NonPlayable neneTerrorifico = new NonPlayable("nene_terrorifico", "nene terrorifico",
				"te mira esperando algo y hay un dragon al lado sospechoso(espada) ;)", Genders.MALE, triggersNene,
				"holis");
		ArrayList<Location> locations = new ArrayList<Location>();
		Location inicial = new Location("descampado", Genders.MALE, Numbers.SINGULAR, "estas en un descampado",
				new ArrayList<Place>(), new ArrayList<String>(), new ArrayList<Connection>());
		locations.add(inicial);
		selectedAdventure.setSettings(settings);
		selectedAdventure.setLocations(locations);
		selectedAdventure.setItems(items);
		khoeli.setSelectedAdventure(selectedAdventure);
		assertEquals("Ya no necesito este celular (Ahora tenes un celular)", khoeli.use(caramelo, neneTerrorifico));
		assertTrue(khoeli.getSelectedAdventure().getSettings().getCharacter().getItems().contains("celular"));
	}

	@Test
	void testNpcBlocksPath() {
		List<Trigger> triggers = new ArrayList<Trigger>();
		triggers.add(new Trigger(Types.ACTION, TriggerActions.TALK.toString(),
				"AL fin alguien se dio cuenta que estoy aca, ahora podes pasar", new AfterTrigger(TriggerActions.REMOVE,
						"nene_terrorifico", Directions.SOUTH.toString(), DestinationTypes.CONNECTION)));
		NonPlayable npc = new NonPlayable("nene_terrorifico", "nene terrorifico",
				"Hay un nene con la mirada perdida que parece muy extraño", Genders.MALE, triggers,
				"No vas a poder pasar mientras yo esté acá");
		List<Place> places = new ArrayList<Place>();
		List<String> npcs = new ArrayList<String>();
		npcs.add("nene_terrorifico");
		List<Connection> connections = new ArrayList<Connection>();
		connections.add(new Connection(Directions.SOUTH, "mercia", "nene_terrorifico"));
		Location location = new Location("descampado", Genders.MALE, Numbers.SINGULAR,
				"Estas en un descampado, al sur esta mercia, no hay nada mas", places, npcs, connections);
		Adventure selectedAdventure = new Adventure();
		ArrayList<Location> locations = new ArrayList<Location>();
		locations.add(location);
		ArrayList<NonPlayable> adventureNpc = new ArrayList<NonPlayable>();
		adventureNpc.add(npc);
		selectedAdventure.setLocations(locations);
		selectedAdventure.setNpcs(adventureNpc);
		Khoeli khoeli = new Khoeli();
		khoeli.setSelectedAdventure(selectedAdventure);
		assertEquals("No vas a poder pasar mientras yo esté acá", khoeli.move(Directions.SOUTH));
		assertEquals(location, khoeli.getCurrentLocation());
	}

	@Test
	void testItemBlocksPath() {
		List<Trigger> triggers = new ArrayList<Trigger>();
		triggers.add(new Trigger(Types.ITEM, "pico", "Hiciste trizas la piedra", new AfterTrigger(TriggerActions.REMOVE,
				"piedra", Directions.SOUTH.toString(), DestinationTypes.CONNECTION)));
		Item pico = new Item("pico", "pico", Genders.MALE, Numbers.SINGULAR, new ArrayList<String>(),
				"Es un pico que puede romper MUCHAS cosas", null);
		Item piedra = new Item("piedra_en_el_camino", "piedra", Genders.FEMALE, Numbers.SINGULAR,
				new ArrayList<String>(), "Hay una piedra en el camino y no podes pasar", triggers);
		List<Place> places = new ArrayList<Place>();
		List<String> npcs = new ArrayList<String>();
		List<Connection> connections = new ArrayList<Connection>();
		connections.add(new Connection(Directions.SOUTH, "mercia", "piedra_en_el_camino"));
		Location location = new Location("descampado", Genders.MALE, Numbers.SINGULAR,
				"Estas en un descampado, al sur esta mercia, no hay nada mas", places, npcs, connections);
		Adventure selectedAdventure = new Adventure();
		ArrayList<Location> locations = new ArrayList<Location>();
		locations.add(location);
		ArrayList<Item> adventureItem = new ArrayList<Item>();
		adventureItem.add(pico);
		adventureItem.add(piedra);
		selectedAdventure.setLocations(locations);
		selectedAdventure.setItems(adventureItem);
		Khoeli khoeli = new Khoeli();
		khoeli.setSelectedAdventure(selectedAdventure);
		assertEquals("Hay una piedra en el camino y no podes pasar", khoeli.move(Directions.SOUTH));
		assertEquals(location, khoeli.getCurrentLocation());
	}

	@Test
	void testAfterTriggerRemovesNpcFromConnection() {
		Item daga = new Item("daga", "daga", Genders.FEMALE, Numbers.SINGULAR, null, "Filosa como la hoja", null);
		List<Trigger> triggers = new ArrayList<Trigger>();
		triggers.add(new Trigger(Types.ITEM, "daga", "Corta muy bien", new AfterTrigger(TriggerActions.REMOVE,
				"nene_terrorifico", Directions.SOUTH.toString(), DestinationTypes.CONNECTION)));
		List<Item> items = new ArrayList<Item>();
		items.add(daga);
		NonPlayable npc = new NonPlayable("nene_terrorifico", "nene terrorifico",
				"Hay un nene con la mirada perdida que parece muy extraño", Genders.MALE, triggers,
				"No vas a poder pasar mientras yo esté acá");
		List<Place> places = new ArrayList<Place>();
		List<String> npcs = new ArrayList<String>();
		npcs.add("nene_terrorifico");
		List<Connection> connections = new ArrayList<Connection>();
		connections.add(new Connection(Directions.SOUTH, "mercia", "nene_terrorifico"));
		Location location = new Location("descampado", Genders.MALE, Numbers.SINGULAR,
				"Estas en un descampado, al sur esta mercia, no hay nada mas", places, npcs, connections);
		Location locationFinal = new Location("mercia", Genders.FEMALE, Numbers.SINGULAR,
				"Estas en mercia, al norte esta el descampado, no hay nada mas", null, null, null);
		Adventure selectedAdventure = new Adventure();
		ArrayList<Location> locations = new ArrayList<Location>();
		locations.add(location);
		locations.add(locationFinal);
		ArrayList<NonPlayable> adventureNpc = new ArrayList<NonPlayable>();
		adventureNpc.add(npc);
		selectedAdventure.setItems(items);
		selectedAdventure.setLocations(locations);
		selectedAdventure.setNpcs(adventureNpc);
		Khoeli khoeli = new Khoeli();
		khoeli.setSelectedAdventure(selectedAdventure);
		assertEquals("Corta muy bien", khoeli.use(daga, npc));
		assertEquals(null, khoeli.getCurrentLocation().getConnection(Directions.SOUTH).getObstacle());
		assertEquals("Estas en mercia, al norte esta el descampado, no hay nada mas", khoeli.move(Directions.SOUTH));
		assertEquals(locationFinal, khoeli.getCurrentLocation());
	}

	@Test
	void testAfterTriggerRemovesItemFromConnection() {
		List<Trigger> triggers = new ArrayList<Trigger>();
		triggers.add(new Trigger(Types.ITEM, "pico", "Hiciste trizas la piedra", new AfterTrigger(TriggerActions.REMOVE,
				"piedra", Directions.SOUTH.toString(), DestinationTypes.CONNECTION)));
		Item pico = new Item("pico", "pico", Genders.MALE, Numbers.SINGULAR, new ArrayList<String>(),
				"Es un pico que puede romper MUCHAS cosas", null);
		Item piedra = new Item("piedra_en_el_camino", "piedra", Genders.FEMALE, Numbers.SINGULAR,
				new ArrayList<String>(), "Hay una piedra en el camino y no podes pasar", triggers);
		List<Place> places = new ArrayList<Place>();
		List<String> npcs = new ArrayList<String>();
		List<Connection> connections = new ArrayList<Connection>();
		connections.add(new Connection(Directions.SOUTH, "mercia", "piedra_en_el_camino"));
		Location location = new Location("descampado", Genders.MALE, Numbers.SINGULAR,
				"Estas en un descampado, al sur esta mercia, no hay nada mas", places, npcs, connections);
		Location locationFinal = new Location("mercia", Genders.FEMALE, Numbers.SINGULAR,
				"Estas en mercia, al norte esta el descampado, no hay nada mas", null, null, null);
		Adventure selectedAdventure = new Adventure();
		ArrayList<Location> locations = new ArrayList<Location>();
		locations.add(location);
		locations.add(locationFinal);
		ArrayList<Item> adventureItem = new ArrayList<Item>();
		adventureItem.add(pico);
		adventureItem.add(piedra);
		selectedAdventure.setLocations(locations);
		selectedAdventure.setItems(adventureItem);
		Khoeli khoeli = new Khoeli();
		khoeli.setSelectedAdventure(selectedAdventure);
		assertEquals("Hiciste trizas la piedra", khoeli.use(pico, piedra));
		assertEquals(null, khoeli.getCurrentLocation().getConnection(Directions.SOUTH).getObstacle());
		assertEquals("Estas en mercia, al norte esta el descampado, no hay nada mas", khoeli.move(Directions.SOUTH));
		assertEquals(locationFinal, khoeli.getCurrentLocation());
	}

	@Test
	void testAfterTriggerRemovesItemFromInventory() {
		Playable mainCharacter = new Playable("Tigri", Genders.MALE);
		Settings settings = new Settings("Bienvenido!", mainCharacter);
		List<Trigger> triggers = new ArrayList<Trigger>();
		triggers.add(new Trigger(Types.ITEM, "pico", "El pico era muy fragil se rompio!",
				new AfterTrigger(TriggerActions.REMOVE, "pico", null, DestinationTypes.INVENTORY)));
		Item pico = new Item("pico", "pico", Genders.MALE, Numbers.SINGULAR, new ArrayList<String>(),
				"Es un pico que puede romper MUCHAS cosas", null);
		Item piedra = new Item("piedra_en_el_camino", "piedra", Genders.FEMALE, Numbers.SINGULAR,
				new ArrayList<String>(), "Hay una piedra en el camino y no podes pasar", triggers);
		List<Place> places = new ArrayList<Place>();
		List<String> npcs = new ArrayList<String>();
		List<Connection> connections = new ArrayList<Connection>();
		connections.add(new Connection(Directions.SOUTH, "mercia", "piedra_en_el_camino"));
		Location location = new Location("descampado", Genders.MALE, Numbers.SINGULAR,
				"Estas en un descampado, al sur esta mercia, no hay nada mas", places, npcs, connections);
		Adventure selectedAdventure = new Adventure();
		ArrayList<Location> locations = new ArrayList<Location>();
		locations.add(location);
		ArrayList<Item> adventureItem = new ArrayList<Item>();
		adventureItem.add(pico);
		adventureItem.add(piedra);
		selectedAdventure.setLocations(locations);
		selectedAdventure.setItems(adventureItem);
		selectedAdventure.setSettings(settings);
		Khoeli khoeli = new Khoeli();
		khoeli.setSelectedAdventure(selectedAdventure);
		khoeli.getSelectedAdventure().getSettings().getCharacter().addItem("pico");
		assertEquals("El pico era muy fragil se rompio!", khoeli.use(pico, piedra));
		assertFalse(khoeli.getSelectedAdventure().getSettings().getCharacter().getItems().contains("pico"));
	}

	@Test
	void testAfterTriggerRemovesItemFromPlace() {
		List<Trigger> triggers = new ArrayList<Trigger>();
		triggers.add(new Trigger(Types.ITEM, "pico", "La piedra se rompio, tienes mucha fuerza!",
				new AfterTrigger(TriggerActions.REMOVE, "piedra", "piso", DestinationTypes.PLACE)));
		Item pico = new Item("pico", "pico", Genders.MALE, Numbers.SINGULAR, new ArrayList<String>(),
				"Es un pico que puede romper MUCHAS cosas", null);
		Item piedra = new Item("piedra_en_el_camino", "piedra", Genders.FEMALE, Numbers.SINGULAR,
				new ArrayList<String>(), "Hay una piedra en el camino y no podes pasar", triggers);
		List<String> items = new ArrayList<String>();
		items.add("piedra");
		List<Place> places = new ArrayList<Place>();
		places.add(new Place("piso", Genders.MALE, Numbers.SINGULAR, items));
		List<String> npcs = new ArrayList<String>();
		List<Connection> connections = new ArrayList<Connection>();
		connections.add(new Connection(Directions.SOUTH, "mercia", "piedra_en_el_camino"));
		Location location = new Location("descampado", Genders.MALE, Numbers.SINGULAR,
				"Estas en un descampado, al sur esta mercia, no hay nada mas", places, npcs, connections);
		Adventure selectedAdventure = new Adventure();
		ArrayList<Location> locations = new ArrayList<Location>();
		locations.add(location);
		ArrayList<Item> adventureItem = new ArrayList<Item>();
		adventureItem.add(pico);
		adventureItem.add(piedra);
		selectedAdventure.setLocations(locations);
		selectedAdventure.setItems(adventureItem);
		Khoeli khoeli = new Khoeli();
		khoeli.setSelectedAdventure(selectedAdventure);
		assertEquals("La piedra se rompio, tienes mucha fuerza!", khoeli.use(pico, piedra));
		assertFalse(khoeli.getCurrentLocation().getPlace("piso").getItems().contains("piedra"));
	}

	@Test
	void testAfterTriggerAddsItemToInventory() {
		Playable mainCharacter = new Playable("SuperTigri", Genders.MALE);
		Settings settings = new Settings("Bienvenido!", mainCharacter);
		List<Trigger> triggers = new ArrayList<Trigger>();
		triggers.add(new Trigger(Types.ITEM, "pico", "Recolectaste una piedra!",
				new AfterTrigger(TriggerActions.ADD, "piedra", null, DestinationTypes.INVENTORY)));
		Item pico = new Item("pico", "pico", Genders.MALE, Numbers.SINGULAR, new ArrayList<String>(),
				"Es un pico que puede romper MUCHAS cosas", null);
		Item piedra = new Item("piedra_en_el_camino", "piedra", Genders.FEMALE, Numbers.SINGULAR,
				new ArrayList<String>(), "Hay una piedra en el camino y no podes pasar", triggers);
		List<Place> places = new ArrayList<Place>();
		List<String> npcs = new ArrayList<String>();
		List<Connection> connections = new ArrayList<Connection>();
		connections.add(new Connection(Directions.SOUTH, "mercia", "piedra_en_el_camino"));
		Location location = new Location("descampado", Genders.MALE, Numbers.SINGULAR,
				"Estas en un descampado, al sur esta mercia, no hay nada mas", places, npcs, connections);
		Adventure selectedAdventure = new Adventure();
		ArrayList<Location> locations = new ArrayList<Location>();
		locations.add(location);
		ArrayList<Item> adventureItem = new ArrayList<Item>();
		adventureItem.add(pico);
		adventureItem.add(piedra);
		selectedAdventure.setLocations(locations);
		selectedAdventure.setItems(adventureItem);
		selectedAdventure.setSettings(settings);
		Khoeli khoeli = new Khoeli();
		khoeli.setSelectedAdventure(selectedAdventure);
		assertEquals("Recolectaste una piedra!", khoeli.use(pico, piedra));
		assertTrue(khoeli.getSelectedAdventure().getSettings().getCharacter().getItems().contains("piedra"));
	}

	@Test
	void testAfterTriggerAddsItemToPlace() {
		List<Trigger> triggers = new ArrayList<Trigger>();
		triggers.add(new Trigger(Types.ITEM, "pico", "Dejaste el pico en el piso intentando romper la piedra",
				new AfterTrigger(TriggerActions.ADD, "pico", "piso", DestinationTypes.PLACE)));
		Item pico = new Item("pico", "pico", Genders.MALE, Numbers.SINGULAR, new ArrayList<String>(),
				"Es un pico que puede romper MUCHAS cosas", null);
		Item piedra = new Item("piedra_en_el_camino", "piedra", Genders.FEMALE, Numbers.SINGULAR,
				new ArrayList<String>(), "Hay una piedra en el camino y no podes pasar", triggers);
		List<String> items = new ArrayList<String>();
		items.add("piedra");
		List<Place> places = new ArrayList<Place>();
		places.add(new Place("piso", Genders.MALE, Numbers.SINGULAR, items));
		List<String> npcs = new ArrayList<String>();
		List<Connection> connections = new ArrayList<Connection>();
		connections.add(new Connection(Directions.SOUTH, "mercia", "piedra_en_el_camino"));
		Location location = new Location("descampado", Genders.MALE, Numbers.SINGULAR,
				"Estas en un descampado, al sur esta mercia, no hay nada mas", places, npcs, connections);
		Adventure selectedAdventure = new Adventure();
		ArrayList<Location> locations = new ArrayList<Location>();
		locations.add(location);
		ArrayList<Item> adventureItem = new ArrayList<Item>();
		adventureItem.add(pico);
		adventureItem.add(piedra);
		selectedAdventure.setLocations(locations);
		selectedAdventure.setItems(adventureItem);
		Khoeli khoeli = new Khoeli();
		khoeli.setSelectedAdventure(selectedAdventure);
		assertEquals("Dejaste el pico en el piso intentando romper la piedra", khoeli.use(pico, piedra));
		assertTrue(khoeli.getCurrentLocation().getPlace("piso").getItems().contains("pico"));
	}

	@Test
	void testUseItemOnInvalidItem() {
		Khoeli khoeli = new Khoeli();
		Playable mainCharacter = new Playable("Tigri", Genders.MALE);
		Settings settings = new Settings("Bienvenido!", mainCharacter);
		ArrayList<Trigger> triggersPiedra = new ArrayList<Trigger>();
		Item piedra = new Item("piedra", "piedra", Genders.FEMALE, Numbers.SINGULAR, new ArrayList<String>(),
				"se puede afilar una espada con esto", triggersPiedra);
		Item espadaVieja = new Item("espada_vieja", "espada vieja", Genders.FEMALE, Numbers.SINGULAR,
				new ArrayList<String>(), "parece que esta a punto de romperse", null);
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
		assertEquals("No se puede usar piedra con espada vieja", khoeli.use(piedra, espadaVieja));
	}

	@Test
	void testSingleItemAfterTriggerRemovesItemFromConnection() {
		List<Trigger> triggers = new ArrayList<Trigger>();
		triggers.add(new Trigger(Types.ACTION, TriggerActions.USE.toString(), "Auch, tu sangre removio el obstaculo",
				new AfterTrigger(TriggerActions.REMOVE, "obstaculo", Directions.SOUTH.toString(),
						DestinationTypes.CONNECTION)));
		Item daga = new Item("daga", "daga", Genders.FEMALE, Numbers.SINGULAR, null, "Filosa como la hoja", triggers);
		Item obstaculo = new Item("obstaculo", "obstaculo", Genders.MALE, Numbers.SINGULAR, null, "No te deja pasar",
				null);
		List<Item> items = new ArrayList<Item>();
		items.add(daga);
		items.add(obstaculo);
		List<Connection> connections = new ArrayList<Connection>();
		connections.add(new Connection(Directions.SOUTH, "mercia", "obstaculo"));
		Location location = new Location("descampado", Genders.MALE, Numbers.SINGULAR,
				"Estas en un descampado, al sur esta mercia, no hay nada mas", null, null, connections);
		Location locationFinal = new Location("mercia", Genders.FEMALE, Numbers.SINGULAR,
				"Estas en mercia, al norte esta el descampado, no hay nada mas", null, null, null);
		Adventure selectedAdventure = new Adventure();
		ArrayList<Location> locations = new ArrayList<Location>();
		locations.add(location);
		locations.add(locationFinal);
		selectedAdventure.setItems(items);
		selectedAdventure.setLocations(locations);
		Khoeli khoeli = new Khoeli();
		khoeli.setSelectedAdventure(selectedAdventure);
		assertEquals("Auch, tu sangre removio el obstaculo", khoeli.use(daga));
		assertEquals(null, khoeli.getCurrentLocation().getConnection(Directions.SOUTH).getObstacle());
		assertEquals("Estas en mercia, al norte esta el descampado, no hay nada mas", khoeli.move(Directions.SOUTH));
		assertEquals(locationFinal, khoeli.getCurrentLocation());
	}

	@Test
	void testSingleItemAfterTriggerRemovesNpcFromConnection() {
		List<Trigger> triggers = new ArrayList<Trigger>();
		triggers.add(new Trigger(Types.ACTION, TriggerActions.USE.toString(), "Auch, tu sangre expulsa al tiburon",
				new AfterTrigger(TriggerActions.REMOVE, "tiburon", Directions.SOUTH.toString(),
						DestinationTypes.CONNECTION)));
		Item daga = new Item("daga", "daga", Genders.FEMALE, Numbers.SINGULAR, null, "Filosa como la hoja", triggers);
		List<Item> items = new ArrayList<Item>();
		items.add(daga);
		List<Connection> connections = new ArrayList<Connection>();
		NonPlayable npc = new NonPlayable("tiburon", "tiburon", "tiene dientes grandes", Genders.MALE, null, "rawrrr");
		List<NonPlayable> npcs = new ArrayList<NonPlayable>();
		npcs.add(npc);
		connections.add(new Connection(Directions.SOUTH, "mercia", "tiburon"));
		Location location = new Location("descampado", Genders.MALE, Numbers.SINGULAR,
				"Estas en un descampado, al sur esta mercia, no hay nada mas", null, null, connections);
		Location locationFinal = new Location("mercia", Genders.FEMALE, Numbers.SINGULAR,
				"Estas en mercia, al norte esta el descampado, no hay nada mas", null, null, null);
		Adventure selectedAdventure = new Adventure();
		ArrayList<Location> locations = new ArrayList<Location>();
		locations.add(location);
		locations.add(locationFinal);
		selectedAdventure.setItems(items);
		selectedAdventure.setLocations(locations);
		selectedAdventure.setNpcs(npcs);
		Khoeli khoeli = new Khoeli();
		khoeli.setSelectedAdventure(selectedAdventure);
		assertEquals("Auch, tu sangre expulsa al tiburon", khoeli.use(daga));
		assertEquals(null, khoeli.getCurrentLocation().getConnection(Directions.SOUTH).getObstacle());
		assertEquals("Estas en mercia, al norte esta el descampado, no hay nada mas", khoeli.move(Directions.SOUTH));
		assertEquals(locationFinal, khoeli.getCurrentLocation());
	}

	@Test
	void testSingleItemAfterTriggerRemovesItemFromInventory() {
		Khoeli khoeli = new Khoeli();
		Playable mainCharacter = new Playable("Tigri", Genders.MALE);
		Settings settings = new Settings("Bienvenido!", mainCharacter);
		ArrayList<Trigger> triggersPocion = new ArrayList<Trigger>();
		triggersPocion.add(new Trigger(Types.ACTION, TriggerActions.USE.toString(), "Has restaurado 50 puntos de vida!",
				new AfterTrigger(TriggerActions.REMOVE, "pocion", null, DestinationTypes.INVENTORY)));
		Item pocion = new Item("pocion", "pocion", Genders.FEMALE, Numbers.SINGULAR, new ArrayList<String>(),
				"Restaura vida!", triggersPocion);
		Adventure selectedAdventure = new Adventure();
		ArrayList<Item> adventureItems = new ArrayList<Item>();
		adventureItems.add(pocion);
		ArrayList<Location> locations = new ArrayList<Location>();
		Location inicial = new Location("descampado", Genders.MALE, Numbers.SINGULAR, "estas en un descampado",
				new ArrayList<Place>(), new ArrayList<String>(), new ArrayList<Connection>());
		selectedAdventure.setItems(adventureItems);
		locations.add(inicial);
		selectedAdventure.setSettings(settings);
		selectedAdventure.setLocations(locations);
		khoeli.setSelectedAdventure(selectedAdventure);
		assertEquals("Has restaurado 50 puntos de vida!", khoeli.use(pocion));
		assertFalse(selectedAdventure.getSettings().getCharacter().getItems().contains("pocion"));
	}

	@Test
	void testSingleItemAfterTriggerRemovesItemFromPlace() {
		List<Trigger> triggers = new ArrayList<Trigger>();
		triggers.add(new Trigger(Types.ACTION, TriggerActions.USE.toString(), "La piedra se rompio, tienes mucha fuerza!",
				new AfterTrigger(TriggerActions.REMOVE, "piedra", "piso", DestinationTypes.PLACE)));
		Item pico = new Item("pico", "pico", Genders.MALE, Numbers.SINGULAR, new ArrayList<String>(),
				"Es un pico que puede romper MUCHAS cosas", triggers);
		Item piedra = new Item("piedra_en_el_camino", "piedra", Genders.FEMALE, Numbers.SINGULAR,
				new ArrayList<String>(), "Hay una piedra en el camino y no podes pasar", null);
		List<String> items = new ArrayList<String>();
		items.add("piedra");
		List<Place> places = new ArrayList<Place>();
		places.add(new Place("piso", Genders.MALE, Numbers.SINGULAR, items));
		List<String> npcs = new ArrayList<String>();
		List<Connection> connections = new ArrayList<Connection>();
		connections.add(new Connection(Directions.SOUTH, "mercia", "piedra_en_el_camino"));
		Location location = new Location("descampado", Genders.MALE, Numbers.SINGULAR,
				"Estas en un descampado, al sur esta mercia, no hay nada mas", places, npcs, connections);
		Adventure selectedAdventure = new Adventure();
		ArrayList<Location> locations = new ArrayList<Location>();
		locations.add(location);
		ArrayList<Item> adventureItem = new ArrayList<Item>();
		adventureItem.add(pico);
		adventureItem.add(piedra);
		selectedAdventure.setLocations(locations);
		selectedAdventure.setItems(adventureItem);
		Khoeli khoeli = new Khoeli();
		khoeli.setSelectedAdventure(selectedAdventure);
		assertEquals("La piedra se rompio, tienes mucha fuerza!", khoeli.use(pico));
		assertFalse(khoeli.getCurrentLocation().getPlace("piso").getItems().contains("piedra"));
	}

	@Test
	void testSingleItemAfterTriggerAddsItemToInventory() {
		Playable mainCharacter = new Playable("SuperTigri", Genders.MALE);
		Settings settings = new Settings("Bienvenido!", mainCharacter);
		List<Trigger> triggers = new ArrayList<Trigger>();
		triggers.add(new Trigger(Types.ACTION, TriggerActions.USE.toString(), "Recolectaste una piedra!",
				new AfterTrigger(TriggerActions.ADD, "piedra", null, DestinationTypes.INVENTORY)));
		Item pico = new Item("pico", "pico", Genders.MALE, Numbers.SINGULAR, new ArrayList<String>(),
				"Es un pico que puede romper MUCHAS cosas", triggers);
		Item piedra = new Item("piedra_en_el_camino", "piedra", Genders.FEMALE, Numbers.SINGULAR,
				new ArrayList<String>(), "Hay una piedra en el camino y no podes pasar", null);
		List<Place> places = new ArrayList<Place>();
		List<String> npcs = new ArrayList<String>();
		List<Connection> connections = new ArrayList<Connection>();
		connections.add(new Connection(Directions.SOUTH, "mercia", "piedra_en_el_camino"));
		Location location = new Location("descampado", Genders.MALE, Numbers.SINGULAR,
				"Estas en un descampado, al sur esta mercia, no hay nada mas", places, npcs, connections);
		Adventure selectedAdventure = new Adventure();
		ArrayList<Location> locations = new ArrayList<Location>();
		locations.add(location);
		ArrayList<Item> adventureItem = new ArrayList<Item>();
		adventureItem.add(pico);
		adventureItem.add(piedra);
		selectedAdventure.setLocations(locations);
		selectedAdventure.setItems(adventureItem);
		selectedAdventure.setSettings(settings);
		Khoeli khoeli = new Khoeli();
		khoeli.setSelectedAdventure(selectedAdventure);
		assertEquals("Recolectaste una piedra!", khoeli.use(pico));
		assertTrue(khoeli.getSelectedAdventure().getSettings().getCharacter().getItems().contains("piedra"));
	}

	@Test
	void testSingleItemAfterTriggerAddsItemToPlace() {
		List<Trigger> triggers = new ArrayList<Trigger>();
		triggers.add(new Trigger(Types.ACTION, TriggerActions.USE.toString(), "Dejaste el pico en el piso intentando romper la piedra",
				new AfterTrigger(TriggerActions.ADD, "pico", "piso", DestinationTypes.PLACE)));
		Item pico = new Item("pico", "pico", Genders.MALE, Numbers.SINGULAR, new ArrayList<String>(),
				"Es un pico que puede romper MUCHAS cosas", triggers);
		Item piedra = new Item("piedra_en_el_camino", "piedra", Genders.FEMALE, Numbers.SINGULAR,
				new ArrayList<String>(), "Hay una piedra en el camino y no podes pasar", null);
		List<String> items = new ArrayList<String>();
		items.add("piedra");
		List<Place> places = new ArrayList<Place>();
		places.add(new Place("piso", Genders.MALE, Numbers.SINGULAR, items));
		List<String> npcs = new ArrayList<String>();
		List<Connection> connections = new ArrayList<Connection>();
		connections.add(new Connection(Directions.SOUTH, "mercia", "piedra_en_el_camino"));
		Location location = new Location("descampado", Genders.MALE, Numbers.SINGULAR,
				"Estas en un descampado, al sur esta mercia, no hay nada mas", places, npcs, connections);
		Adventure selectedAdventure = new Adventure();
		ArrayList<Location> locations = new ArrayList<Location>();
		locations.add(location);
		ArrayList<Item> adventureItem = new ArrayList<Item>();
		adventureItem.add(pico);
		adventureItem.add(piedra);
		selectedAdventure.setLocations(locations);
		selectedAdventure.setItems(adventureItem);
		Khoeli khoeli = new Khoeli();
		khoeli.setSelectedAdventure(selectedAdventure);
		assertEquals("Dejaste el pico en el piso intentando romper la piedra", khoeli.use(pico));
		assertTrue(khoeli.getCurrentLocation().getPlace("piso").getItems().contains("pico"));
	}
	
	@Test
	void testSingleItemAfterTriggerChangeDescription() {
		List<Trigger> triggers = new ArrayList<Trigger>();
		triggers.add(new Trigger(Types.ACTION, TriggerActions.USE.toString(), "El pico era muy fragil y se rompio",
				new AfterTrigger(TriggerActions.CHANGE_DESCRIPTION, "pico roto", "pico", null)));
		Item pico = new Item("pico", "pico", Genders.MALE, Numbers.SINGULAR, new ArrayList<String>(),
				"Es un pico que puede romper MUCHAS cosas", triggers);
		List<Connection> connections = new ArrayList<Connection>();
		connections.add(new Connection(Directions.SOUTH, "mercia", "piedra_en_el_camino"));
		Location location = new Location("descampado", Genders.MALE, Numbers.SINGULAR,
				"Estas en un descampado, al sur esta mercia, no hay nada mas", null, null, connections);
		Adventure selectedAdventure = new Adventure();
		ArrayList<Location> locations = new ArrayList<Location>();
		locations.add(location);
		ArrayList<Item> adventureItem = new ArrayList<Item>();
		adventureItem.add(pico);
		selectedAdventure.setLocations(locations);
		selectedAdventure.setItems(adventureItem);
		Khoeli khoeli = new Khoeli();
		khoeli.setSelectedAdventure(selectedAdventure);
		assertEquals("El pico era muy fragil y se rompio", khoeli.use(pico));
		assertEquals("pico roto",pico.getDescription());
	}
	
	@Test
	void testSingleItemInvalidUse() {
		Item pico = new Item("pico", "pico", Genders.MALE, Numbers.SINGULAR, new ArrayList<String>(),
				"Es un pico que puede romper MUCHAS cosas", null);
		List<Connection> connections = new ArrayList<Connection>();
		connections.add(new Connection(Directions.SOUTH, "mercia", "piedra_en_el_camino"));
		Location location = new Location("descampado", Genders.MALE, Numbers.SINGULAR,
				"Estas en un descampado, al sur esta mercia, no hay nada mas", null, null, connections);
		Adventure selectedAdventure = new Adventure();
		ArrayList<Location> locations = new ArrayList<Location>();
		locations.add(location);
		ArrayList<Item> adventureItem = new ArrayList<Item>();
		adventureItem.add(pico);
		selectedAdventure.setLocations(locations);
		selectedAdventure.setItems(adventureItem);
		Khoeli khoeli = new Khoeli();
		khoeli.setSelectedAdventure(selectedAdventure);
		assertEquals("No se puede usar pico", khoeli.use(pico));
	}
}
