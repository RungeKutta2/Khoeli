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
import enums.TriggerType;
import models.Item;
import models.Playable;
import models.Sprite;
import models.Trigger;
import models.aftertrigger.AfterTriggerRequest;

public class ItemDeserializer implements JsonDeserializer<Item>{

	@Override
	public Item deserialize(JsonElement arg0, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
		
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Trigger.class, new TriggerDeserializer());
		gsonBuilder.registerTypeAdapter(Sprite.class, new SpriteDeserializer());
		
		JsonObject jobject = arg0.getAsJsonObject();
		Gson gson = gsonBuilder.create();
		
		String id = jobject.get("id").getAsString();
		String name = jobject.get("name").getAsString();
		Gender gender = Gender.valueOf(jobject.get("gender").getAsString());
		Number number = Number.valueOf(jobject.get("number").getAsString());
		String description = jobject.get("description").getAsString();
		List<Trigger> triggers = gson.fromJson(jobject.get("triggers").getAsJsonArray(), new TypeToken<List<Trigger>>(){}.getType());
		Sprite sprite = gson.fromJson(jobject.get("sprite"), Sprite.class);
		
		return new Item(id, name, gender, number, description, triggers, sprite);
	}
	
}
