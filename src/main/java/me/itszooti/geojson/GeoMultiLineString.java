package me.itszooti.geojson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GeoMultiLineString extends GeoGeometry {

	private List<List<GeoPosition>> lineStringPositions;
	
	private GeoMultiLineString() {
		this.lineStringPositions = new ArrayList<List<GeoPosition>>();
	}
	
	public GeoMultiLineString(List<List<GeoPosition>> lineStringPositions) {
		this();
		for (List<GeoPosition> lsp : lineStringPositions) {
			this.lineStringPositions.add(new ArrayList<GeoPosition>(lsp));
		}
	}
	
	public GeoMultiLineString(GeoPosition[][] lineStringPositions) {
		this();
		for (GeoPosition[] lsp : lineStringPositions) {
			this.lineStringPositions.add(Arrays.asList(lsp));	
		}
	}
	
	public int getNumLineStrings() {
		return lineStringPositions.size();
	}
	
	public GeoLineString getLineString(int index) {
		GeoLineString lineString = new GeoLineString(lineStringPositions.get(index));
		return lineString;
	}
	
}
