package me.itszooti.geojson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GeoMultiPoint extends GeoGeometry {

	private List<GeoPosition> pointPositions;
	
	public GeoMultiPoint(List<GeoPosition> pointPositions) {
		this.pointPositions = new ArrayList<GeoPosition>(pointPositions);
	}
	
	public GeoMultiPoint(GeoPosition[] pointPositions) {
		this(Arrays.asList(pointPositions));
	}
	
	public int getNumPoints() {
		return pointPositions.size();
	}
	public GeoPoint getPoint(int index) {
		return new GeoPoint(pointPositions.get(index));
	}
	
}
