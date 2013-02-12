package me.itszooti.geojson.gson;

import java.io.InputStream;
import java.io.InputStreamReader;

import me.itszooti.geojson.GeoJSONParser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vividsolutions.jts.geom.Geometry;

public class GsonGeoJSONParser extends GeoJSONParser {
	
	private Gson gson;
	
	public GsonGeoJSONParser() {
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(Geometry.class, new GeometryDeserializer());
		gson = builder.create();
	}

	@Override
	public Geometry parseGeometry(InputStream in) {
		return gson.fromJson(new InputStreamReader(in), Geometry.class);
	}

}
