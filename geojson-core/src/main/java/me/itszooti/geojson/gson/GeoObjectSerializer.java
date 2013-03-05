package me.itszooti.geojson.gson;

import java.lang.reflect.Type;

import me.itszooti.geojson.GeoFeature;
import me.itszooti.geojson.GeoGeometry;
import me.itszooti.geojson.GeoObject;

import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class GeoObjectSerializer implements JsonSerializer<GeoObject> {

	@Override
	public JsonElement serialize(GeoObject geo, Type type, JsonSerializationContext context) {
		// serialized element
		JsonElement element = null;
		
		// check type
		if (geo instanceof GeoGeometry) {
			element = context.serialize(geo, GeoGeometry.class);
		} else if (geo instanceof GeoFeature) {
			element = context.serialize(geo, GeoFeature.class);
		}
		
		return element;
	}

}
