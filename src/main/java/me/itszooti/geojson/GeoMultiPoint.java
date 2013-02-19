package me.itszooti.geojson;

import java.util.ArrayList;
import java.util.List;

public class GeoMultiPoint extends GeoGeometry {

	private List<GeoPoint> points;
	
	public GeoMultiPoint(List<GeoPoint> points) {
		this.points = new ArrayList<GeoPoint>(points);
	}
	
	public int getNumPoints() {
		return points.size();
	}
	
	public GeoPoint getPoint(int index) {
		return points.get(index);
	}
	
}
