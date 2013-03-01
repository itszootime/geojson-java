package me.itszooti.geojson;

import java.util.HashMap;
import java.util.Map;

public class GeoFeature extends GeoObject {

	private String id;
	private GeoGeometry geometry;
	private Map<String, Object> properties;
	
	public GeoFeature(String id, GeoGeometry geometry) {
		this.id = id;
		this.geometry = geometry;
		properties = new HashMap<String, Object>();
	}
	
	public String getId() {
		return id;
	}
	
	public GeoGeometry getGeometry() {
		return geometry;
	}
	
	public Object getProperty(String name) {
		return properties.get(name);
	}
	
	public void setProperty(String name, Object value) {
		properties.put(name, value);
	}
	
}
