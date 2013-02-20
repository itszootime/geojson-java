package me.itszooti.geojson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GeoMultiPoint extends GeoGeometry {

	private List<GeoPosition> positions;
	
	public GeoMultiPoint(List<GeoPosition> positions) {
		this.positions = new ArrayList<GeoPosition>(positions);
	}
	
	public GeoMultiPoint(GeoPosition[] positions) {
		this(Arrays.asList(positions));
	}
	
	public int getNumPoints() {
		return positions.size();
	}
	public GeoPosition getPointPosition(int index) {
		return positions.get(index);
	}
	
}
