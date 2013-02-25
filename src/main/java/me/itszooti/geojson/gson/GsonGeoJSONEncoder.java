package me.itszooti.geojson.gson;

import me.itszooti.geojson.GeoGeometry;
import me.itszooti.geojson.GeoJSONEncoder;
import me.itszooti.geojson.GeoObject;
import me.itszooti.geojson.GeoPosition;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonGeoJSONEncoder extends GeoJSONEncoder {

	private Gson gson;
	
	public GsonGeoJSONEncoder() {
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(GeoObject.class, new GeoObjectSerializer());
		builder.registerTypeAdapter(GeoPosition.class, new GeoPositionSerializer());
		builder.registerTypeAdapter(GeoGeometry.class, new GeoGeometrySerializer());
		gson = builder.create();
	}
	
	@Override
	public String encode(GeoObject geo) {
		return gson.toJson(geo, GeoObject.class);
	}

}
