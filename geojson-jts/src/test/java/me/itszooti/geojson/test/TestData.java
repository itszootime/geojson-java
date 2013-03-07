package me.itszooti.geojson.test;

import java.util.Arrays;

import me.itszooti.geojson.GeoLineString;
import me.itszooti.geojson.GeoPoint;
import me.itszooti.geojson.GeoPosition;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.Point;

public class TestData {

	public static GeoPoint getGeoPoint() {
		return new GeoPoint(new GeoPosition(100.0, 0.0));
	}
	
	public static Point getPoint() {
		GeometryFactory factory = new GeometryFactory();
		return factory.createPoint(new Coordinate(100.0, 0.0));
	}
	
	public static GeoLineString getGeoLineString() {
		return new GeoLineString(Arrays.asList(new GeoPosition[] {
			new GeoPosition(1.0, 1.0),
			new GeoPosition(3.0, 3.0)
		}));
	}
	
	public static LineString getLineString() {
		GeometryFactory factory = new GeometryFactory();
		return factory.createLineString(new Coordinate[] {
			new Coordinate(1.0, 1.0),
			new Coordinate(3.0, 3.0)
		});
	}
	
}
