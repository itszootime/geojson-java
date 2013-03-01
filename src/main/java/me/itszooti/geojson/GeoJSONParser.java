package me.itszooti.geojson;

import java.io.InputStream;

import me.itszooti.geojson.gson.GsonGeoJsonParser;

public abstract class GeoJsonParser {
	
	public static GeoJsonParser create() {
		return new GsonGeoJsonParser();
	}	
	
	public abstract GeoObject parse(InputStream in);

}
