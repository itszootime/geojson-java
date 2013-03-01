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
		object.addProperty("type", "Feature");
		
		// add id
		object.addProperty("id", feature.getId());
		
		// add geometry
		// force to go through GeoObject serializer
		object.add("geometry", context.serialize(feature.getGeometry(), GeoObject.class));
		
		// add properties
		JsonObject properties = new JsonObject();
		object.add("properties", properties);
		for (String name : feature.getPropertyNames()) {
			properties.add(name, context.serialize(feature.getProperty(name)));
		}
		
		return object;
	}

	
	
}
