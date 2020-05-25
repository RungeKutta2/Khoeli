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

import models.Connection;
import models.Directions;

public class ConnectionDeserializer implements JsonDeserializer<Connection>{
	@Override
	public Connection deserialize(JsonElement json, Type type, JsonDeserializationContext context)
			throws JsonParseException {
		
		 	JsonObject jobject = json.getAsJsonObject();
	
		    Directions direction = Directions.valueOf(jobject.get("direction").getAsString());
			String location = jobject.get("location").getAsString();
			String obstacle = jobject.get("obstacle").getAsString();
			Connection connection = new Connection(direction,location,obstacle);
		    
		    return connection;
	}
}
