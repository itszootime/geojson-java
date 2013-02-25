package me.itszooti.geojson;

import me.itszooti.geojson.gson.GsonGeoJSONEncoder;

public abstract class GeoJSONEncoder {

	public static GeoJSONEncoder create() {
		return new GsonGeoJSONEncoder();
	}
	
	public abstract String encode(GeoObject geo);
	
}
