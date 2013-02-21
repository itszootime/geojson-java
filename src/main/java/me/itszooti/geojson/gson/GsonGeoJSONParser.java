package me.itszooti.geojson.gson;

import java.io.InputStream;
import java.io.InputStreamReader;

import me.itszooti.geojson.GeoGeometry;
import me.itszooti.geojson.GeoGeometryCollection;
import me.itszooti.geojson.GeoJSONParser;
import me.itszooti.geojson.GeoObject;
import me.itszooti.geojson.GeoPosition;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonGeoJSONParser extends GeoJSONParser {
	
	private Gson gson;
	
	public GsonGeoJSONParser() {
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(GeoObject.class, new GeoObjectDeserializer());
		builder.registerTypeAdapter(GeoPosition.class,  new GeoPositionDeserializer());
		builder.registerTypeAdapter(GeoGeometry.class, new GeoGeometryDeserializer());
		builder.registerTypeAdapter(GeoGeometryCollection.class, new GeoGeometryCollectionDeserializer());		
		gson = builder.create();
	}

	@Override
	public GeoObject parse(InputStream in) {
		return gson.fromJson(new InputStreamReader(in), GeoObject.class);
	}

}
