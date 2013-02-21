package me.itszooti.geojson;

import java.util.ArrayList;
import java.util.List;

public class GeoMultiPolygon extends GeoGeometry {

	private List<List<GeoPosition>> exteriors;
	private List<List<List<GeoPosition>>> interiors;
	
	public GeoMultiPolygon(List<List<GeoPosition>> exteriors, List<List<List<GeoPosition>>> interiors) {
		this.exteriors = new ArrayList<List<GeoPosition>>();
		for (List<GeoPosition> exterior : exteriors) {
			this.exteriors.add(new ArrayList<GeoPosition>(exterior));
		}
		this.interiors = new ArrayList<List<List<GeoPosition>>>();
		if (interiors != null) {
			for (List<List<GeoPosition>> interior : interiors) {
				List<List<GeoPosition>> newInterior = new ArrayList<List<GeoPosition>>();
				for (List<GeoPosition> thisInterior : interior) {
					newInterior.add(new ArrayList<GeoPosition>(thisInterior));
				}
				this.interiors.add(newInterior);
			}
		}
	}
	
	public int getNumPolygons() {
		return exteriors.size();
	}
	
	public GeoPolygon getPolygon(int index) {
		GeoPolygon polygon = new GeoPolygon(exteriors.get(index), interiors.get(index));
		return polygon;
	}
	
}
