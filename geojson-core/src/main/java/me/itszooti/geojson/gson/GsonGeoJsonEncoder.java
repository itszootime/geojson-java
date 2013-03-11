package me.itszooti.geojson.gson;

import me.itszooti.geojson.GeoFeature;
import me.itszooti.geojson.GeoGeometry;
import me.itszooti.geojson.GeoJsonEncoder;
import me.itszooti.geojson.GeoObject;
import me.itszooti.geojson.GeoPosition;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonGeoJsonEncoder extends GeoJsonEncoder {

	private Gson gson;
	
	public GsonGeoJsonEncoder() {
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(GeoObject.class, new GeoObjectSerializer());
		builder.registerTypeAdapter(GeoPosition.class, new GeoPositionSerializer());
		builder.registerTypeAdapter(GeoGeometry.class, new GeoGeometrySerializer());
		builder.registerTypeAdapter(GeoFeature.class, new GeoFeatureSerializer());
		builder.serializeNulls();
		gson = builder.create();
	}
	
	@Override
	public String encode(GeoObject geo) {
		return gson.toJson(geo, GeoObject.class);
	}

}
