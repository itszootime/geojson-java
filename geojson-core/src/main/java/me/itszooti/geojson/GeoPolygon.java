package me.itszooti.geojson;

import java.util.ArrayList;
import java.util.Arrays;
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
	
	public GeoPolygon(GeoPosition[] exterior) {
		this(Arrays.asList(exterior));
	}
	
	public GeoPolygon(GeoPosition[] exterior, GeoPosition[][] interiors) {
		this(Arrays.asList(exterior));
		for (int i = 0; i < interiors.length; i++) {
			this.interiors.add(Arrays.asList(interiors[i]));
		}
	}
	
	public int getNumInteriors() {
		return interiors.size();
	}
	
	public GeoPosition[] getInterior(int index) {
		List<GeoPosition> interior = interiors.get(index);
		return interior.toArray(new GeoPosition[interior.size()]);
	}
	
	public GeoPosition[] getExterior() {
		return exterior.toArray(new GeoPosition[exterior.size()]);
	}
	
}
