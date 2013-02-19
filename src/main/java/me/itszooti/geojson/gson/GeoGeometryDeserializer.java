package me.itszooti.geojson.gson;

import java.lang.reflect.Type;

import me.itszooti.geojson.GeoGeometry;
import me.itszooti.geojson.GeoMultiPoint;
import me.itszooti.geojson.GeoPoint;
import me.itszooti.geojson.GeoPosition;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class GeoGeometryDeserializer implements JsonDeserializer<GeoGeometry> {

	@Override
	public GeoGeometry deserialize(JsonElement json, Type t, JsonDeserializationContext context) throws JsonParseException {
		// geojson is always an object
		JsonObject object = json.getAsJsonObject();
		
		// get type
		String type = object.getAsJsonPrimitive("type").getAsString();
		
		// check type and deserialize
		GeoGeometry geometry = null;
		JsonArray coordinates = object.getAsJsonArray("coordinates");
		if (type.equals("Point")) {
			GeoPosition position = (GeoPosition)context.deserialize(coordinates, GeoPosition.class);
			geometry = new GeoPoint(position);
		} else if (type.equals("MultiPoint")) {
			GeoPosition[] positions = (GeoPosition[])context.deserialize(coordinates, GeoPosition[].class);
			geometry = new GeoMultiPoint(positions);
		}
		
		return geometry;
	}
	
}
