package helpers;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import enums.AfterTriggerAction;
import enums.AfterTriggerDestination;
import enums.AfterTriggerThing;
import models.aftertrigger.*;

public class AfterTriggerDeserializer implements JsonDeserializer<AfterTriggerRequest> {

	@Override
	public AfterTriggerRequest deserialize(JsonElement json, Type type, JsonDeserializationContext context)
			throws JsonParseException {
		JsonObject jobject = json.getAsJsonObject();

		AfterTriggerAction action = NullSafe.of(jobject.get("action")).call(JsonElement::getAsString).call(AfterTriggerAction::valueOf).get();
		String thing = NullSafe.of(jobject.get("thing")).call(JsonElement::getAsString).get();
		AfterTriggerThing thingType = NullSafe.of(jobject.get("thingType")).call(JsonElement::getAsString).call(AfterTriggerThing::valueOf).get();
		String parentId = NullSafe.of(jobject.get("parentId")).call(JsonElement::getAsString).get();
		String actionDestination =NullSafe.of(jobject.get("actionDestination")).call(JsonElement::getAsString).get();
		AfterTriggerDestination destinationType= NullSafe.of(jobject.get("destinationType")).call(JsonElement::getAsString).call(AfterTriggerDestination::valueOf).get();
		return new AfterTriggerRequest(action, thing, thingType, parentId, actionDestination, destinationType);
	}

}
