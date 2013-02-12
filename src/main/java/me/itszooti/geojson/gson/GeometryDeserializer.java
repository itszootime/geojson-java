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

class GeometryDeserializer implements JsonDeserializer<Geometry> {

	@Override
	public Geometry deserialize(JsonElement json, Type t, JsonDeserializationContext context) throws JsonParseException {
		// geojson is always an object
		JsonObject object = json.getAsJsonObject();
		
		// check type
		String type = object.getAsJsonPrimitive("type").getAsString();
		
		// factory for geometry
		GeometryFactory factory = new GeometryFactory();
		
		// check type and deserialize
		Geometry geometry = null;		
		if (type.equals("Point")) {
			geometry = factory.createPoint(deserializeCoordinate(object.getAsJsonArray("coordinates"), context));
		}
		
		return geometry;
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
