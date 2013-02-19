package me.itszooti.geojson;

public class GeoPoint extends GeoGeometry {

	private GeoPosition position;
	
	public GeoPoint(GeoPosition position) {
		this.position = position;
	}
	
	public GeoPosition getPosition() {
		return position;
	}
	
}
