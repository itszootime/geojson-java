package me.itszooti.geojson.gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import me.itszooti.geojson.GeoFeature;
import me.itszooti.geojson.GeoFeatureCollection;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class GeoFeatureCollectionDeserializer implements JsonDeserializer<GeoFeatureCollection> {

	@Override
	public GeoFeatureCollection deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
		JsonArray featuresArray = element.getAsJsonObject().getAsJsonArray("features");
		List<GeoFeature> features = new ArrayList<GeoFeature>();
		for (JsonElement feature : featuresArray) {
			features.add((GeoFeature)context.deserialize(feature, GeoFeature.class));
		}
		GeoFeatureCollection featureColl = new GeoFeatureCollection(features);
		return featureColl;
	}

}
