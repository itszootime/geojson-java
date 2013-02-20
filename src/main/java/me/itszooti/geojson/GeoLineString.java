package me.itszooti.geojson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GeoLineString extends GeoGeometry {

	private List<GeoPosition> positions;
	
	public GeoLineString(List<GeoPosition> positions) {
		this.positions = new ArrayList<GeoPosition>(positions);
	}
	
	public GeoLineString(GeoPosition[] positions) {
		this(Arrays.asList(positions));
	}
	
	public int getNumPositions() {
		return positions.size();
	}
	
	public GeoPosition getPosition(int index) {
		return positions.get(index);
	}
	
}
