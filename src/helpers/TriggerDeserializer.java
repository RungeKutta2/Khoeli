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

import enums.TriggerType;
import models.Trigger;
import models.aftertrigger.AfterTriggerRequest;

public class TriggerDeserializer implements JsonDeserializer<Trigger>{

	@Override
	public Trigger deserialize(JsonElement json, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {

		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(AfterTriggerRequest.class, new AfterTriggerDeserializer());

		JsonObject jobject = json.getAsJsonObject();
		Gson gson = gsonBuilder.create();
		TriggerType type= TriggerType.valueOf(jobject.get("type").getAsString());
		String thing= jobject.get("thing").getAsString();
		String onTrigger= jobject.get("onTrigger").getAsString();
		List<AfterTriggerRequest> afterTriggers = gson.fromJson(jobject.get("afterTrigger"), new TypeToken<List<AfterTriggerRequest>>() {}.getType());

		return new Trigger(type, thing, onTrigger, afterTriggers);
	}



}
