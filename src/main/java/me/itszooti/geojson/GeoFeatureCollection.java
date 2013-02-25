package me.itszooti.geojson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GeoFeatureCollection extends GeoObject {

	private List<GeoFeature> features;
	
	public GeoFeatureCollection(List<GeoFeature> features) {
		this.features = new ArrayList<GeoFeature>(features);
	}
	
	public GeoFeatureCollection(GeoFeature[] features) {
		this(Arrays.asList(features));
	}
	
	public int getNumFeatures() {
		return features.size();
	}
	
	public GeoFeature getFeature(int index) {
		return features.get(index);
	}
	
}
