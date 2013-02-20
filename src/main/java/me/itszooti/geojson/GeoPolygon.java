package me.itszooti.geojson;

import java.util.ArrayList;
import java.util.List;

public class GeoPolygon extends GeoGeometry {

	private List<GeoPosition> exterior;
	private List<List<GeoPosition>> interiors;
	
	public GeoPolygon(List<GeoPosition> exterior) {
		this.exterior = new ArrayList<GeoPosition>(exterior);
		this.interiors = new ArrayList<List<GeoPosition>>();
	}
	
	public GeoPolygon(List<GeoPosition> exterior, List<List<GeoPosition>> interiors) {
		this(exterior);
		for (List<GeoPosition> interior : interiors) {
			this.interiors.add(new ArrayList<GeoPosition>(interior));
		}
	}
	
	public int getNumInteriors() {
		return interiors.size();
	}
	
	public List<GeoPosition> getInterior(int index) {
		return interiors.get(index);
	}
	
	public List<GeoPosition> getExterior() {
		return exterior;
	}
	
}
