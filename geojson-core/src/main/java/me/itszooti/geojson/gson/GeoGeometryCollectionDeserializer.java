package me.itszooti.geojson.gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import me.itszooti.geojson.GeoGeometry;
import me.itszooti.geojson.GeoGeometryCollection;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class GeoGeometryCollectionDeserializer implements JsonDeserializer<GeoGeometryCollection> {

	@Override
	public GeoGeometryCollection deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
		JsonArray geometriesArray = element.getAsJsonObject().getAsJsonArray("geometries");
		List<GeoGeometry> geometries = new ArrayList<GeoGeometry>();
		for (JsonElement geometry : geometriesArray) {
			geometries.add((GeoGeometry)context.deserialize(geometry, GeoGeometry.class));
		}
		GeoGeometryCollection geomCollection = new GeoGeometryCollection(geometries);
		return geomCollection;
	}

}
