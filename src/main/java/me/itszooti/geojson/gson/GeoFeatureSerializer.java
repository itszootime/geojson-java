package me.itszooti.geojson.gson;

import java.lang.reflect.Type;

import me.itszooti.geojson.GeoFeature;
import me.itszooti.geojson.GeoObject;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class GeoFeatureSerializer implements JsonSerializer<GeoFeature> {

	@Override
	public JsonElement serialize(GeoFeature feature, Type type, JsonSerializationContext context) {
		// always an object
		JsonObject object = new JsonObject();
		
		// add id
		object.addProperty("id", feature.getId());
		
		// add geometry
		// force to go through GeoObject serializer
		object.add("geometry", context.serialize(feature.getGeometry(), GeoObject.class));
		
		// add properties
		
		
		return object;
	}

	
	
}
