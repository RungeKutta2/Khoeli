package helpers;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import enums.AfterTriggerAction;
import enums.DestinationTypes;
import models.Adventure;
import models.AfterTrigger;
import models.ThingType;

public class AfterTriggerDeserializer implements JsonDeserializer<AfterTrigger> {

	@Override
	public AfterTrigger deserialize(JsonElement json, Type arg1, JsonDeserializationContext arg2)
			throws JsonParseException {
		JsonObject jobject = json.getAsJsonObject();

		AfterTriggerAction action = NullSafe.of(jobject.get("action")).call(JsonElement::getAsString).call(AfterTriggerAction::valueOf).get();
		String thing = NullSafe.of(jobject.get("thing")).call(JsonElement::getAsString).get();
		ThingType thingType = NullSafe.of(jobject.get("thingType")).call(JsonElement::getAsString).call(ThingType::valueOf).get();
		String parentId = NullSafe.of(jobject.get("parentId")).call(JsonElement::getAsString).get();
		String actionDestination =NullSafe.of(jobject.get("actionDestination")).call(JsonElement::getAsString).get();
		DestinationTypes destinationType= NullSafe.of(jobject.get("destinationType")).call(JsonElement::getAsString).call(DestinationTypes::valueOf).get();
		// TODO Auto-generated method stub
		return new AfterTrigger(action, thing, thingType, parentId, actionDestination, destinationType);
	}

}
