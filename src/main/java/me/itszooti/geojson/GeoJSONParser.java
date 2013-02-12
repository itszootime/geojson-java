package me.itszooti.geojson;

import java.io.InputStream;

import me.itszooti.geojson.gson.GsonGeoJSONParser;


import com.vividsolutions.jts.geom.Geometry;

public abstract class GeoJSONParser {
	
	public static GeoJSONParser create() {
		return new GsonGeoJSONParser();
	}	
	
	public abstract Geometry parseGeometry(InputStream in);

}
