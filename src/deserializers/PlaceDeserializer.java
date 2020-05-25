package deserializers;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import models.Genders;
import models.Numbers;
import models.Place;

public class PlaceDeserializer implements JsonDeserializer<Place>{

	
	@Override
	public Place deserialize(JsonElement json, Type type, JsonDeserializationContext context)
			throws JsonParseException {
		
		 JsonObject jobject = json.getAsJsonObject();
		 	Gson gson = new Gson();
		 	String name = jobject.get("name").getAsString();
            Genders gender = Genders.valueOf(jobject.get("gender").getAsString());
            Numbers number = Numbers.valueOf(jobject.get("number").getAsString());
            List<String> items = gson.fromJson(jobject.get("items").getAsJsonArray(), new TypeToken<List<String>>(){}.getType());
		 	
		    return new Place(name, gender, number, items);
	}
	
}