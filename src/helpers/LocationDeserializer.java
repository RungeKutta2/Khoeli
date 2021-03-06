package helpers;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import enums.Gender;
import enums.Number;
import models.Location;
import models.NonPlayable;
import models.Place;
import models.Sprite;
import models.Trigger;

public class LocationDeserializer implements JsonDeserializer<Location> {

	@Override
	public Location deserialize(JsonElement json, Type type, JsonDeserializationContext context)
			throws JsonParseException {

		GsonBuilder gsonBuilder = new GsonBuilder();

		gsonBuilder.registerTypeAdapter(Place.class, new PlaceDeserializer());
		gsonBuilder.registerTypeAdapter(Trigger.class, new TriggerDeserializer());
		gsonBuilder.registerTypeAdapter(Sprite.class, new SpriteDeserializer());
		gsonBuilder.registerTypeAdapter(NonPlayable.class, new NonPlayableDeserializer());

		JsonObject jobject = json.getAsJsonObject();
		Gson gson = gsonBuilder.create();

		String id = jobject.get("id").getAsString();
		String name = jobject.get("name").getAsString();
		Gender gender = Gender.valueOf(jobject.get("gender").getAsString());
		Number number = Number.valueOf(jobject.get("number").getAsString());
		String description = jobject.get("description").getAsString();
		List<Place> places = gson.fromJson(jobject.get("places").getAsJsonArray(), new TypeToken<List<Place>>() {
		}.getType());
		List<String> npcs = gson.fromJson(jobject.get("npcs").getAsJsonArray(), new TypeToken<List<String>>() {
		}.getType());
		List<Trigger> triggers = gson.fromJson(jobject.get("triggers").getAsJsonArray(),
				new TypeToken<List<Trigger>>() {
				}.getType());
		Sprite sprite = gson.fromJson(jobject.get("sprite"), Sprite.class);

		return new Location(id, name, gender, number, description, places, npcs, triggers, sprite);

	}

}