package me.itszooti.geojson;

public class GeoPosition {
	
	private double x;
	private double y;
	
	public GeoPosition(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof GeoPosition) {
			GeoPosition position = (GeoPosition)obj;
			return getX() == position.getX() && getY() == position.getY();
		}
		return false;
	}

}
