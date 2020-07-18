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
import models.Item;
import models.Place;
import models.Sprite;

public class PlaceDeserializer implements JsonDeserializer<Place> {

	@Override
	public Place deserialize(JsonElement json, Type type, JsonDeserializationContext context)
			throws JsonParseException {

		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Item.class, new ItemDeserializer());
		gsonBuilder.registerTypeAdapter(Sprite.class, new SpriteDeserializer());

		JsonObject jobject = json.getAsJsonObject();
		Gson gson = gsonBuilder.create();

		String name = jobject.get("name").getAsString();
		Gender gender = Gender.valueOf(jobject.get("gender").getAsString());
		Number number = Number.valueOf(jobject.get("number").getAsString());
		List<String> items = gson.fromJson(jobject.get("items").getAsJsonArray(), new TypeToken<List<String>>() {
		}.getType());
		String description = jobject.get("description").getAsString();
		Sprite sprite = gson.fromJson(jobject.get("sprite"), Sprite.class);

		return new Place(name, gender, number, items, description, sprite);
	}

}