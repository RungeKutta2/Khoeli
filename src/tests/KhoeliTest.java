package tests;

import static org.junit.Assert.*;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.google.gson.*;
import helpers.*;
import enums.Direction;
import enums.Gender;
import models.*;

class KhoeliTest {
	Adventure selectedAdventure;

	@BeforeEach
	void setearTodo() {
		try {
			Gson gson = new Gson();
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.registerTypeAdapter(Adventure.class, new AdventureDeserializer());
			gson = gsonBuilder.create();
			Reader reader = Files.newBufferedReader(Paths.get("./aventuras/AventuraTest.json"));
			selectedAdventure = gson.fromJson(reader, Adventure.class);
			reader.close();

		} catch (Exception ex) {
			ex.printStackTrace();
			System.err.println(ex.getMessage());
		}
	}

	@Test
	void testMoveToExistingLocation() {
		Playable selectedPlayer = selectedAdventure.getSelectedPlayer();
		Location initialLocation = selectedPlayer.getCurrentLocation();
		String result = selectedPlayer.move(Direction.SOUTH);
		selectedAdventure.getSelectedPlayer().getInventory().add(selectedAdventure.findItem("pico"));
		assertTrue(selectedPlayer.getInventory().contains(selectedAdventure.findItem("pico")));
		assertEquals("Es una piedra enorme que te impide pasar mientras este ahí", result);
		assertEquals(initialLocation, selectedPlayer.getCurrentLocation());
		assertEquals("la piedra se rompió pero el pico tambien porque era muy debil",
				selectedPlayer.use(selectedAdventure.findItem("pico"), selectedAdventure.findItem("piedra")));
		assertNull(initialLocation.findObstacle(Direction.SOUTH));
		assertFalse(selectedPlayer.getInventory().contains(selectedAdventure.findItem("pico")));
		assertEquals("Estas en una cueva, al norte esta el descampado", selectedPlayer.move(Direction.SOUTH));
	}

	@Test
	void testMoveToNonExistingLocation() {
		Playable selectedPlayer = selectedAdventure.getSelectedPlayer();
		Location initialLocation = selectedPlayer.getCurrentLocation();
		String result = selectedPlayer.move(Direction.EAST);
		assertEquals("No hay nada al este", result);
		assertEquals(initialLocation, selectedPlayer.getCurrentLocation());
	}


	@Test
	void testPickUpExistingItem() {
		Playable selectedPlayer = selectedAdventure.getSelectedPlayer();
		Place place = selectedPlayer.getCurrentLocation().getPlace("piso");
		Item item = selectedAdventure.findItem("pico");
		assertFalse(selectedPlayer.getInventory().contains(item));
		String result = selectedPlayer.pickUp(item, place);
		assertEquals("No puedo juntar eso.", result);
		assertFalse(selectedPlayer.getInventory().contains(item)); // ? cambio valor
	}

	@Test
	void testPickUpNonExistingItem() {
		Playable selectedPlayer = selectedAdventure.getSelectedPlayer();
		Place place = selectedPlayer.getCurrentLocation().getPlace("piso");
		Item item = selectedAdventure.findItem("espada_vieja");
		String result = selectedPlayer.pickUp(item, place);
		assertEquals("No puedo juntar eso.", result);
		assertFalse(selectedPlayer.getInventory().contains(item));

	}

	@Test
	void testLookAtExistingItem() {
		Playable selectedPlayer = selectedAdventure.getSelectedPlayer();
		Item item = selectedAdventure.findItem("espada_vieja");
		assertEquals("Es una espada un poco oxidada", selectedPlayer.lookAt(item));
	}

	@Test
	void testLookAtExistingNpc() {
		Playable selectedPlayer = selectedAdventure.getSelectedPlayer();
		NonPlayable npc = selectedAdventure.findNpc("nene_terrorifico");
		assertEquals("Es un nene de aproximadamente diez años, tiene una mirada perdida y parece medio extraño.",
				selectedPlayer.lookAt(npc));
	}

	@Test
	void testLookAtExistingLocation() {
		Playable selectedPlayer = selectedAdventure.getSelectedPlayer();
		Location location = selectedAdventure.findLocation("descampado");

		assertEquals("Estas en un descampado, al norte hay un bar y al oeste hay una escuela. Despues no hay mas nada",
				selectedPlayer.lookAt(location));
	}

	@Test
	void testTalkToNpc() {
		Playable selectedPlayer = selectedAdventure.getSelectedPlayer();
		NonPlayable npc = selectedAdventure.findNpc("nene_terrorifico");
		assertEquals("Al fin alguien se dio cuenta que estoy acá, ahora puedo irme a mi casa tranquilo",
				selectedPlayer.talkTo(npc));
	}

//	@Test
//	void testTalkToNpcMudo() {
//		Playable selectedPlayer = selectedAdventure.getSelectedPlayer();
//		NonPlayable npc = selectedAdventure.findNpc("npc_mudo");
//		assertEquals("No tiene nada que decir", selectedPlayer.talkTo(npc));
//	}

	@Test
	void testCustomizeCharacter() {
		Playable selectedPlayer = selectedAdventure.getSelectedPlayer();
		selectedPlayer.customize("Julia", Gender.FEMALE);
		assertEquals(Gender.FEMALE, selectedPlayer.getGender());
		assertEquals("Julia", selectedPlayer.getName());
	}

	@Test
	void testEndgame() {
		Playable selectedPlayer = selectedAdventure.getSelectedPlayer();
		Location mercia = selectedAdventure.findLocation("bar");
		String result = selectedPlayer.move(Direction.NORTH);
		assertEquals(mercia, selectedPlayer.getCurrentLocation());
		assertEquals(
				"Estas en un bar llamado Mercia, al sur esta el descampadoPasaste una noche muy divertida con tus amigos. THE END",
				result);
	}
//
//	@Test
//	void testUseItemOnNpc() {
//		Khoeli khoeli = new Khoeli();
//		Playable mainCharacter = new Playable("Tigri", Genders.MALE);
//		Settings settings = new Settings("Bienvenido!", mainCharacter);
//		ArrayList<Trigger> triggersNene = new ArrayList<Trigger>();
//		triggersNene.add(new Trigger(Types.ITEM, "espada_vieja", "gracias ahora puedo matar a mis enemigos :)",
//				new AfterTrigger(TriggerActions.REMOVE, "espada_vieja", null, DestinationTypes.INVENTORY)));
//		NonPlayable neneTerrorifico = new NonPlayable("nene_terrorifico", "nene terrorifico",
//				"te mira esperando algo y hay un dragon al lado sospechoso(espada) ;)", Genders.MALE, triggersNene,
//				"holis");
//		Item espadaVieja = new Item("espada_vieja", "espada vieja", Genders.FEMALE, Numbers.SINGULAR,
//				new ArrayList<String>(), "parece que esta a punto de romperse", new ArrayList<Trigger>());
//		Adventure selectedAdventure = new Adventure();
//		ArrayList<Item> adventureItems = new ArrayList<Item>();
//		adventureItems.add(espadaVieja);
//		ArrayList<Location> locations = new ArrayList<Location>();
//		Location inicial = new Location("descampado", Genders.MALE, Numbers.SINGULAR, "estas en un descampado",
//				new ArrayList<Place>(), new ArrayList<String>(), new ArrayList<Connection>());
//		selectedAdventure.setItems(adventureItems);
//		locations.add(inicial);
//		selectedAdventure.setSettings(settings);
//		selectedAdventure.setLocations(locations);
//		khoeli.setSelectedAdventure(selectedAdventure);
//		ArrayList<NonPlayable> npcs = new ArrayList<NonPlayable>();
//		npcs.add(neneTerrorifico);
//		assertEquals("gracias ahora puedo matar a mis enemigos :)", khoeli.use(espadaVieja, neneTerrorifico));
//		assertFalse(selectedAdventure.getSettings().getCharacter().getItems().contains("espada_vieja"));
//	}

	@Test
	void testChangeDescription() {
		Playable selectedPlayer = selectedAdventure.getSelectedPlayer();
		Item item = selectedAdventure.findItem("espada_vieja");
		NonPlayable npc = selectedAdventure.findNpc("nene_terrorifico");
		assertEquals("Atacaste al nene terrorifico con la espada. Se lo merecia", selectedPlayer.use(item, npc));
		assertEquals("Ahora está muy enojado", npc.getDescription());
	}

//	@Test
//	void testNpcBlocksPath() {
//		List<Trigger> triggers = new ArrayList<Trigger>();
//		triggers.add(new Trigger(Types.ACTION, TriggerActions.TALK.toString(),
//				"AL fin alguien se dio cuenta que estoy aca, ahora podes pasar", new AfterTrigger(TriggerActions.REMOVE,
//						"nene_terrorifico", Directions.SOUTH.toString(), DestinationTypes.CONNECTION)));
//		NonPlayable npc = new NonPlayable("nene_terrorifico", "nene terrorifico",
//				"Hay un nene con la mirada perdida que parece muy extraño", Genders.MALE, triggers,
//				"No vas a poder pasar mientras yo esté acá");
//		List<Place> places = new ArrayList<Place>();
//		List<String> npcs = new ArrayList<String>();
//		npcs.add("nene_terrorifico");
//		List<Connection> connections = new ArrayList<Connection>();
//		connections.add(new Connection(Directions.SOUTH, "mercia", "nene_terrorifico"));
//		Location location = new Location("descampado", Genders.MALE, Numbers.SINGULAR,
//				"Estas en un descampado, al sur esta mercia, no hay nada mas", places, npcs, connections);
//		Adventure selectedAdventure = new Adventure();
//		ArrayList<Location> locations = new ArrayList<Location>();
//		locations.add(location);
//		ArrayList<NonPlayable> adventureNpc = new ArrayList<NonPlayable>();
//		adventureNpc.add(npc);
//		selectedAdventure.setLocations(locations);
//		selectedAdventure.setNpcs(adventureNpc);
//		Khoeli khoeli = new Khoeli();
//		khoeli.setSelectedAdventure(selectedAdventure);
//		assertEquals("No vas a poder pasar mientras yo esté acá", khoeli.move(Directions.SOUTH));
//		assertEquals(location, khoeli.getCurrentLocation());
//	}

	@Test
	void testUseItemOnInvalidItem() {
		Playable selectedPlayer = selectedAdventure.getSelectedPlayer();
		Item item1 = selectedAdventure.findItem("espada_vieja");
		Item item2 = selectedAdventure.findItem("piedra");

		assertEquals("no se puede usar espada vieja con piedra", selectedPlayer.use(item1, item2));
	}
}
