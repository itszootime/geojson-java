package me.itszooti.geojson;

import me.itszooti.geojson.gson.GsonGeoJsonEncoder;

public abstract class GeoJsonEncoder {

	public static GeoJsonEncoder create() {
		return new GsonGeoJsonEncoder();
	}
	
	public abstract String encode(GeoObject geo);
	
}
