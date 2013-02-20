package me.itszooti.geojson.gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.itszooti.geojson.GeoGeometry;
import me.itszooti.geojson.GeoLineString;
import me.itszooti.geojson.GeoPoint;
import me.itszooti.geojson.GeoPolygon;
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
		} else if (type.equals("LineString")) {
			GeoPosition[] positions = (GeoPosition[])context.deserialize(coordinates, GeoPosition[].class);
			geometry = new GeoLineString(positions);
		} else if (type.equals("Polygon")) {
			// get exterior
			JsonArray exteriorCoords = coordinates.get(0).getAsJsonArray();
			GeoPosition[] exteriorArray = (GeoPosition[])context.deserialize(exteriorCoords, GeoPosition[].class);
			List<List<GeoPosition>> interiors = new ArrayList<List<GeoPosition>>();
			for (int i = 1; i < coordinates.size(); i++) {
				JsonArray interiorCoords = coordinates.get(i).getAsJsonArray();
				GeoPosition[] interiorArray = (GeoPosition[])context.deserialize(interiorCoords, GeoPosition[].class);
				interiors.add(Arrays.asList(interiorArray));
			}
			geometry = new GeoPolygon(Arrays.asList(exteriorArray), interiors);
		}
		
		return geometry;
	}
	
}
