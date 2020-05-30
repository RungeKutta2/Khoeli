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

import enums.Genders;
import enums.Numbers;
import models.AfterTrigger;
import models.Connection;
import models.Location;
import models.Place;
import models.Trigger;

public class LocationDeserializer implements JsonDeserializer<Location> {

	@Override
	public Location deserialize(JsonElement json, Type type, JsonDeserializationContext context)
			throws JsonParseException {

		GsonBuilder gsonBuilder = new GsonBuilder();

		gsonBuilder.registerTypeAdapter(Place.class, new PlaceDeserializer());
		gsonBuilder.registerTypeAdapter(Trigger.class, new TriggerDeserializer());

		JsonObject jobject = json.getAsJsonObject();
		Gson gson = gsonBuilder.create();
		
		String name = jobject.get("name").getAsString();
		Genders gender = Genders.valueOf(jobject.get("gender").getAsString());
		Numbers number = Numbers.valueOf(jobject.get("number").getAsString());
		String description = jobject.get("description").getAsString();
		List<Place> places = gson.fromJson(jobject.get("places").getAsJsonArray(), new TypeToken<List<Place>>() {
		}.getType());
		List<String> npcs = gson.fromJson(jobject.get("npcs").getAsJsonArray(), new TypeToken<List<String>>() {
		}.getType());
        List<Trigger> triggers = gson.fromJson(jobject.get("triggers").getAsJsonArray(), new TypeToken<List<Trigger>>(){}.getType());

		Location location = new Location(name, gender, number, description, places, npcs,triggers);

		return location;
	}

}