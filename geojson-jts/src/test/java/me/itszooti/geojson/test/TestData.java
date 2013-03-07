package me.itszooti.geojson.test;

import me.itszooti.geojson.GeoPoint;
import me.itszooti.geojson.GeoPosition;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;

public class TestData {

	public static GeoPoint getGeoPoint() {
		return new GeoPoint(new GeoPosition(100.0, 0.0));
	}
	
	public static Point getPoint() {
		GeometryFactory factory = new GeometryFactory();
		return factory.createPoint(new Coordinate(100.0, 0.0));
	}
	
}
