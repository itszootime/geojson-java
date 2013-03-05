package me.itszooti.geojson.gson;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import me.itszooti.geojson.GeoFeature;
import me.itszooti.geojson.GeoGeometry;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class GeoFeatureDeserializer implements JsonDeserializer<GeoFeature> {

	@Override
	public GeoFeature deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
		// always an object
		JsonObject object = element.getAsJsonObject();
		
		// get id, can be null
		String id = null;
		if (object.has("id")) {
			id = object.getAsJsonPrimitive("id").getAsString();
		}
		
		// get geometry
		GeoGeometry geometry = (GeoGeometry)context.deserialize(object.get("geometry"), GeoGeometry.class);
		
		// create feature
		GeoFeature feature = new GeoFeature(id, geometry);
		
		// add properties if they exist
		if (object.get("properties").isJsonObject()) {
			for (Entry<String, JsonElement> entry : object.get("properties").getAsJsonObject().entrySet()) {
				feature.setProperty(entry.getKey(), parseProperty(entry.getValue(), context));
			}
		}
		
		// all done
		return feature;
	}
	
	private Object parseProperty(JsonElement element, JsonDeserializationContext context) {
		if (element.isJsonObject()) {
			// map
			Map<String, Object> map = new HashMap<String, Object>();
			JsonObject object = element.getAsJsonObject();
			for (Entry<String, JsonElement> entry : object.entrySet()) {
				map.put(entry.getKey(), parseProperty(entry.getValue(), context));
			}
			return map;
		} else if (element.isJsonArray()) {
			// array
			JsonArray array = element.getAsJsonArray();
			Object[] objArray = new Object[array.size()];
			for (int i = 0; i < array.size(); i++) {
				objArray[i] = parseProperty(array.get(i), context);
			}
			return objArray;
		} else if (element.isJsonPrimitive()) {
			String string = element.toString();
			try {
				return Integer.parseInt(string);
			} catch (NumberFormatException e) {
				try {
					return Double.parseDouble(string);
				} catch (NumberFormatException e2) {
					return element.getAsString();
				}
			}
		} else {
			return null;
		}
	}

}
