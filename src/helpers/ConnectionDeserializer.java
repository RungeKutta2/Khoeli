package helpers;

import java.lang.reflect.Type;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import enums.Directions;
import models.Connection;

public class ConnectionDeserializer implements JsonDeserializer<Connection>{
	@Override
	public Connection deserialize(JsonElement json, Type type, JsonDeserializationContext context)
			throws JsonParseException {
		
		 	JsonObject jobject = json.getAsJsonObject();
	
		    Directions direction = Directions.valueOf(jobject.get("direction").getAsString());
			String location = jobject.get("location").getAsString();
			String obstacle = NullSafe.of((jobject.get("obstacle"))).call(JsonElement::getAsString).get();
			Connection connection = new Connection(direction,location,obstacle);
		    
		    return connection;
	}
}
