package me.itszooti.geojson.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.itszooti.geojson.GeoLineString;
import me.itszooti.geojson.GeoPoint;
import me.itszooti.geojson.GeoPolygon;
import me.itszooti.geojson.GeoPosition;

import org.junit.Ignore;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.LinearRing;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;

@Ignore
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
	
	public static GeoPolygon getGeoPolygon() {
		List<GeoPosition> exterior = Arrays.asList(new GeoPosition[] {
			new GeoPosition(100.0, 0.0), 
			new GeoPosition(101.0, 0.0),
			new GeoPosition(101.0, 1.0),
			new GeoPosition(100.0, 1.0),
			new GeoPosition(100.0, 0.0)
		});
		List<List<GeoPosition>> interiors = new ArrayList<List<GeoPosition>>();
		interiors.add(Arrays.asList(new GeoPosition[] {
			new GeoPosition(100.2, 0.2),
			new GeoPosition(100.8, 0.2),
			new GeoPosition(100.8, 0.8),
			new GeoPosition(100.2, 0.8),
			new GeoPosition(100.2, 0.2) 
		}));
		return new GeoPolygon(exterior, interiors);
	}
	
	public static Polygon getPolygon() {
		GeometryFactory factory = new GeometryFactory();
		LinearRing exterior = factory.createLinearRing(new Coordinate[] {
			new Coordinate(100.0, 0.0), 
			new Coordinate(101.0, 0.0),
			new Coordinate(101.0, 1.0),
			new Coordinate(100.0, 1.0),
			new Coordinate(100.0, 0.0)
		});
		LinearRing[] interiors = new LinearRing[] {
			factory.createLinearRing(new Coordinate[] {
				new Coordinate(100.2, 0.2),
				new Coordinate(100.8, 0.2),
				new Coordinate(100.8, 0.8),
				new Coordinate(100.2, 0.8),
				new Coordinate(100.2, 0.2) 
			})
		};		
		return factory.createPolygon(exterior, interiors);
	}
	
}
