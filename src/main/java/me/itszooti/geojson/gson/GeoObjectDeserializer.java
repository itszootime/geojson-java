package me.itszooti.geojson.gson;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

import me.itszooti.geojson.GeoGeometry;
import me.itszooti.geojson.GeoObject;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class GeoObjectDeserializer implements JsonDeserializer<GeoObject> {

	private static final List<String> GEOMETRY_TYPES = Arrays.asList(new String[] {
		"Point", "LineString", "Polygon", "MultiPoint", "MultiLineString"
	});
	
	@Override
	public GeoObject deserialize(JsonElement arg0, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
		// geojson is an object
		JsonObject object = arg0.getAsJsonObject();
		
		// get type
		String type = object.getAsJsonPrimitive("type").getAsString();
		
		// parse
		GeoObject geo = null;
		if (GEOMETRY_TYPES.contains(type)) {
			geo = (GeoGeometry)arg2.deserialize(object, GeoGeometry.class);
		}
		
		return geo;
	}

}
