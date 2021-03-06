package helpers;

import java.lang.reflect.Type;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import models.Sprite;

public class SpriteDeserializer implements JsonDeserializer<Sprite>{

	@Override
	public Sprite deserialize(JsonElement arg0, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
		JsonObject jobject = arg0.getAsJsonObject();

		String path  = jobject.get("path").getAsString();
		int x = jobject.get("x").getAsInt();
		int y = jobject.get("y").getAsInt();
		int width = jobject.get("width").getAsInt();
		int height = jobject.get("height").getAsInt();
		
		return new Sprite(path, x, y, width, height);
	}

}
