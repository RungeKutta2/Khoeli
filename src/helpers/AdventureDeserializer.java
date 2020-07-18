package helpers;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import models.Adventure;
import models.Connection;
import models.Item;
import models.Location;
import models.NonPlayable;
import models.Playable;
import models.Trigger;

public class AdventureDeserializer implements JsonDeserializer<Adventure> {
	@Override
	public Adventure deserialize(JsonElement json, Type type, JsonDeserializationContext context)
			throws JsonParseException {

		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Location.class, new LocationDeserializer());
		gsonBuilder.registerTypeAdapter(Playable.class, new PlayableDeserializer());
		gsonBuilder.registerTypeAdapter(Connection.class, new ConnectionDeserializer());
		gsonBuilder.registerTypeAdapter(Trigger.class, new TriggerDeserializer());
		gsonBuilder.registerTypeAdapter(Item.class, new ItemDeserializer());

		JsonObject jobject = json.getAsJsonObject();
		Gson gson = gsonBuilder.create();

		Adventure adventure = Adventure.getSelectedAdventure();
		List<NonPlayable> npcs = gson.fromJson(jobject.get("npcs").getAsJsonArray(),
				new TypeToken<List<NonPlayable>>() {
				}.getType());
		adventure.setNpcs(npcs);
		List<Item> items = gson.fromJson(jobject.get("items").getAsJsonArray(), new TypeToken<List<Item>>() {
		}.getType());
		adventure.setItems(items);
		JsonArray locationsArray = jobject.get("locations").getAsJsonArray();

		List<Location> locations = gson.fromJson(locationsArray, new TypeToken<List<Location>>() {
		}.getType());

		adventure.setLocations(locations);

		for (int i = 0; i < locationsArray.size(); i++) {
			List<Connection> connections = gson.fromJson(
					locationsArray.get(i).getAsJsonObject().get("connections").getAsJsonArray(),
					new TypeToken<List<Connection>>() {
					}.getType());
			locations.get(i).setConnections(connections);
		}

		Playable selectedPlayer = gson.fromJson(jobject.get("selectedPlayer"), Playable.class);
		adventure.setSelectedPlayer(selectedPlayer);
		
		String welcomeMessage = jobject.get("welcomeMessage").getAsString();
		adventure.setWelcomeMessage(welcomeMessage);

		return adventure;
	}
}
