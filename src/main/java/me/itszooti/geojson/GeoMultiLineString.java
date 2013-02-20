package me.itszooti.geojson;

import java.util.ArrayList;
import java.util.List;

public class GeoMultiLineString extends GeoGeometry {

	private List<List<GeoPosition>> positions;
	
	public GeoMultiLineString(List<List<GeoPosition>> position) {
		this.positions = new ArrayList<List<GeoPosition>>();
	}
	
	public int getNumLineStrings() {
		return positions.size();
	}
	
	public GeoPosition[] getLineStringPositions(int index) {
		return positions.get(index).toArray(new GeoPosition[positions.size()]);
	}
	
}
