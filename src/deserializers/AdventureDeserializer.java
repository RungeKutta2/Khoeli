package deserializers;

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

import models.Adventure;
import models.Connection;
import models.Endgame;
import models.Item;
import models.Location;
import models.NonPlayable;
import models.Place;
import models.Playable;

public class AdventureDeserializer implements JsonDeserializer<Adventure>{
	@Override
	public Adventure deserialize(JsonElement json, Type type, JsonDeserializationContext context)
			throws JsonParseException {
		
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder .registerTypeAdapter(Location.class, new LocationDeserializer());
			gsonBuilder.registerTypeAdapter(Playable.class, new PlayableDeserializer());
		
		 	JsonObject jobject = json.getAsJsonObject();
		 	Gson gson = gsonBuilder.create();
		 	
		 	List<NonPlayable> npcs = gson.fromJson(jobject.get("npcs").getAsJsonArray(), new TypeToken<List<NonPlayable>>(){}.getType());	    
		 	List<Location> locations = gson.fromJson(jobject.get("locations").getAsJsonArray(), new TypeToken<List<Location>>(){}.getType());
			List<Item> items = gson.fromJson(jobject.get("items").getAsJsonArray(), new TypeToken<List<Item>>(){}.getType());
			List<Endgame> endGames = gson.fromJson(jobject.get("endgames").getAsJsonArray(), new TypeToken<List<Endgame>>(){}.getType());
			Playable selectedPlayer = gson.fromJson(jobject.get("npcs"), Playable.class);
			Adventure adventure = Adventure.init(locations, npcs, items, endGames, selectedPlayer);
		    
		    return adventure;
	}
}
