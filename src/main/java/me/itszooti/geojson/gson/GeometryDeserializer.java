package me.itszooti.geojson.gson;

import java.lang.reflect.Type;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.LinearRing;
import com.vividsolutions.jts.geom.Polygon;

public class GeometryDeserializer implements JsonDeserializer<Geometry> {
	
	private GeometryFactory factory;
	
	public GeometryDeserializer() {
		factory = new GeometryFactory();
	}

	@Override
	public Geometry deserialize(JsonElement json, Type t, JsonDeserializationContext context) throws JsonParseException {
		// geojson is always an object
		JsonObject object = json.getAsJsonObject();
		
		// check type
		String type = object.getAsJsonPrimitive("type").getAsString();
		
		// check type and deserialize
		Geometry geometry = null;
		JsonArray coords = object.getAsJsonArray("coordinates");
		if (type.equals("Point")) {
			geometry = factory.createPoint(deserializeCoordinate(coords, context));
		} else if (type.equals("MultiPoint")) {
			geometry = factory.createMultiPoint(deserializeCoordinateArray(coords, context));
		} else if (type.equals("LineString")) {
			geometry = factory.createLineString(deserializeCoordinateArray(coords, context));
		} else if (type.equals("MultiLineString")) {
			LineString[] lineStrings = new LineString[coords.size()];
			for (int i = 0; i < lineStrings.length; i++) {
				Coordinate[] innerCoords = deserializeCoordinateArray(coords.get(i).getAsJsonArray(), context);
				lineStrings[i] = factory.createLineString(innerCoords);
			}
			geometry = factory.createMultiLineString(lineStrings);
		} else if (type.equals("Polygon")) {			
			geometry = deserializePolygon(coords, context);
		} else if (type.equals("MultiPolygon")) {
			Polygon[] polygons = new Polygon[coords.size()];
			for (int i = 0; i < polygons.length; i++) {
				polygons[i] = deserializePolygon(coords.get(i).getAsJsonArray(), context);
			}
			geometry = factory.createMultiPolygon(polygons);
		} else if (type.equals("GeometryCollection")) {
			JsonArray geometries = object.getAsJsonArray("geometries");
			Geometry[] gs = new Geometry[geometries.size()];
			for (int i = 0; i < gs.length; i++) {
				gs[i] = context.deserialize(geometries.get(i), Geometry.class);
			}
			geometry = factory.createGeometryCollection(gs);
		}
		
		return geometry;
	}
	
	private Polygon deserializePolygon(JsonArray coords, JsonDeserializationContext context) throws JsonParseException {
		LinearRing shell = factory.createLinearRing(deserializeCoordinateArray(coords.get(0).getAsJsonArray(), context));
		LinearRing[] holes = new LinearRing[coords.size() - 1];
		for (int i = 0; i < holes.length; i++) {
			holes[i] = factory.createLinearRing(deserializeCoordinateArray(coords.get(i + 1).getAsJsonArray(), context));
		}
		return factory.createPolygon(shell, holes);
	}
	
	private Coordinate[] deserializeCoordinateArray(JsonArray array, JsonDeserializationContext context) throws JsonParseException {
		Coordinate[] coords = new Coordinate[array.size()];
		for (int i = 0; i < coords.length; i++) {
			coords[i] = deserializeCoordinate(array.get(i).getAsJsonArray(), context);
		}
		return coords;
	}
	
	private Coordinate deserializeCoordinate(JsonArray array, JsonDeserializationContext context) throws JsonParseException {
		Double[] values = context.deserialize(array, Double[].class);
		if (values.length == 3) {
			return new Coordinate(values[0], values[1], values[2]);
		} else if (values.length == 2) {
			return new Coordinate(values[0], values[1]);
		} else {
			throw new JsonParseException("Invalid number of values in coordinate array");
		}
	}
	
}
