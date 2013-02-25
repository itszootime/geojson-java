package me.itszooti.geojson.gson;

import java.lang.reflect.Type;

import me.itszooti.geojson.GeoPosition;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class GeoPositionSerializer implements JsonSerializer<GeoPosition> {

	@Override
	public JsonElement serialize(GeoPosition pos, Type type, JsonSerializationContext context) {
		JsonArray array = new JsonArray();
		array.add(new JsonPrimitive(pos.getX()));
		array.add(new JsonPrimitive(pos.getY()));
		return array;
	}
	
}
