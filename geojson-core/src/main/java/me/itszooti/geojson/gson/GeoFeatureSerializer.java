package me.itszooti.geojson.gson;

import java.lang.reflect.Type;
import java.util.Set;

import me.itszooti.geojson.GeoFeature;
import me.itszooti.geojson.GeoObject;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
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
		if (feature.getId() != null) {
			object.addProperty("id", feature.getId());
		}

		// add geometry
		// force to go through GeoObject serializer
		object.add("geometry", context.serialize(feature.getGeometry(), GeoObject.class));

		// add properties
		JsonElement properties = JsonNull.INSTANCE;
		Set<String> propNames = feature.getPropertyNames();
		if (propNames.size() > 0) {
			JsonObject propsObj = new JsonObject();
			for (String name : propNames) {
				propsObj.add(name, context.serialize(feature.getProperty(name)));
			}
			properties = propsObj;			
		}
		object.add("properties", properties);
		
		return object;
	}
	
}