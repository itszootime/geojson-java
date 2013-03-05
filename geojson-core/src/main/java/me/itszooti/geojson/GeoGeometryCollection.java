package me.itszooti.geojson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GeoGeometryCollection extends GeoObject {

	private List<GeoGeometry> geometries;
	
	public GeoGeometryCollection(List<GeoGeometry> geometries) {
		this.geometries = new ArrayList<GeoGeometry>(geometries);
	}
	
	public GeoGeometryCollection(GeoGeometry[] geometries) {
		this(Arrays.asList(geometries));
	}
	
	public int getNumGeometries() {
		return geometries.size();
	}
	
	public GeoGeometry getGeometry(int index) {
		return geometries.get(index);
	}
	
}
