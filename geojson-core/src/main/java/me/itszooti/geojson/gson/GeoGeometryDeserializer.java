package me.itszooti.geojson.gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.itszooti.geojson.GeoGeometry;
import me.itszooti.geojson.GeoLineString;
import me.itszooti.geojson.GeoMultiLineString;
import me.itszooti.geojson.GeoMultiPoint;
import me.itszooti.geojson.GeoMultiPolygon;
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
			List<GeoPosition> exterior = parsePolygonExterior(coordinates, context);
			List<List<GeoPosition>> interiors = parsePolygonInteriors(coordinates, context);
			geometry = new GeoPolygon(exterior, interiors);
		} else if (type.equals("MultiPoint")) {
			GeoPosition[] positions = (GeoPosition[])context.deserialize(coordinates, GeoPosition[].class);
			geometry = new GeoMultiPoint(positions);
		} else if (type.equals("MultiLineString")) {
			List<List<GeoPosition>> lineStringPositions = new ArrayList<List<GeoPosition>>();
			for (int i = 0; i < coordinates.size(); i++) {
				JsonArray lineStringCoords = coordinates.get(i).getAsJsonArray();
				GeoPosition[] lineStringArray = (GeoPosition[])context.deserialize(lineStringCoords, GeoPosition[].class);
				lineStringPositions.add(Arrays.asList(lineStringArray));
			}
			geometry = new GeoMultiLineString(lineStringPositions);
		} else if (type.equals("MultiPolygon")) {
			List<List<GeoPosition>> exteriors = new ArrayList<List<GeoPosition>>();
			List<List<List<GeoPosition>>> interiors = new ArrayList<List<List<GeoPosition>>>();
			for (int i = 0; i < coordinates.size(); i++) {
				JsonArray polygonCoordinates = coordinates.get(i).getAsJsonArray();
				List<GeoPosition> polygonExterior = parsePolygonExterior(polygonCoordinates, context);
				List<List<GeoPosition>> polygonInteriors = parsePolygonInteriors(polygonCoordinates, context);
				exteriors.add(polygonExterior);
				interiors.add(polygonInteriors);
			}
			geometry = new GeoMultiPolygon(exteriors, interiors);
		}
		
		return geometry;
	}
	
	private List<List<GeoPosition>> parsePolygonInteriors(JsonArray coordinates, JsonDeserializationContext context) {
		List<List<GeoPosition>> interiors = new ArrayList<List<GeoPosition>>();
		for (int i = 1; i < coordinates.size(); i++) {
			JsonArray interiorCoords = coordinates.get(i).getAsJsonArray();
			GeoPosition[] interiorArray = (GeoPosition[])context.deserialize(interiorCoords, GeoPosition[].class);
			interiors.add(Arrays.asList(interiorArray));
		}
		return interiors;
	}

	private List<GeoPosition> parsePolygonExterior(JsonArray coordinates, JsonDeserializationContext context) {
		JsonArray exteriorCoords = coordinates.get(0).getAsJsonArray();
		GeoPosition[] exteriorArray = (GeoPosition[])context.deserialize(exteriorCoords, GeoPosition[].class);
		return Arrays.asList(exteriorArray);
	}
	
}
