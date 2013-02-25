package me.itszooti.geojson.gson;

import java.lang.reflect.Type;

import me.itszooti.geojson.GeoGeometry;
import me.itszooti.geojson.GeoPoint;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class GeoGeometrySerializer implements JsonSerializer<GeoGeometry> {

	@Override
	public JsonElement serialize(GeoGeometry geom, Type type, JsonSerializationContext context) {
		// always an object with type
		JsonObject obj = new JsonObject();
		obj.addProperty("type", geom.getClass().getSimpleName().substring(3));
		
		// check type
		if (geom instanceof GeoPoint) {
			GeoPoint point = (GeoPoint)geom;
			obj.add("coordinates", context.serialize(point.getPosition()));
		}
		
		return obj;
	}

}
