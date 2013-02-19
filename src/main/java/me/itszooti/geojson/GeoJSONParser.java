package me.itszooti.geojson;

import java.io.InputStream;

import me.itszooti.geojson.gson.GsonGeoJSONParser;

public abstract class GeoJSONParser {
	
	public static GeoJSONParser create() {
		return new GsonGeoJSONParser();
	}	
	
	public abstract GeoObject parse(InputStream in);

}
