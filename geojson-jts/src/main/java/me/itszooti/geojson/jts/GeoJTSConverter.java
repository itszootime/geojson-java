package me.itszooti.geojson.jts;

import me.itszooti.geojson.GeoGeometry;
import me.itszooti.geojson.GeoLineString;
import me.itszooti.geojson.GeoPoint;
import me.itszooti.geojson.GeoPosition;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.Point;

public class GeoJtsConverter {

	private GeometryFactory geomFactory;
	
	public GeoJtsConverter() {
		geomFactory = new GeometryFactory();
	}
	
	public GeoGeometry fromJts(Geometry geom) {
		GeoGeometry geoGeom = null;
		
		// check type and convert
		if (geom instanceof Point) {
			geoGeom = fromJtsPoint((Point)geom);
		} else if (geom instanceof LineString) {
			geoGeom = fromJtsLineString((LineString)geom);
		} else {
			// need exception?
		}
		
		return geoGeom;
	}
	
	private GeoPoint fromJtsPoint(Point point) {
		GeoPosition pos = new GeoPosition(point.getX(), point.getY());
		return new GeoPoint(pos);
	}
	
	private GeoLineString fromJtsLineString(LineString lineString) {
		Coordinate[] coords = lineString.getCoordinates();
		GeoPosition[] positions = new GeoPosition[coords.length];
		for (int i = 0; i < coords.length; i++) {
			positions[i] = new GeoPosition(coords[i].x, coords[i].y);
		}
		return new GeoLineString(positions);
	}
	
	public Geometry toJts(GeoGeometry geoGeom) {
		Geometry geom = null;
		
		// check type and convert
		if (geoGeom instanceof GeoPoint) {
			geom = toJtsPoint((GeoPoint)geoGeom);
		} else if (geoGeom instanceof GeoLineString) {
			geom = toJtsLineString((GeoLineString)geoGeom);
		} else {
			// need exception?
		}
		
		return geom;
	}
	
	private Point toJtsPoint(GeoPoint geoPoint) {
		GeoPosition pos = geoPoint.getPosition();
		return geomFactory.createPoint(new Coordinate(pos.getX(), pos.getY()));
	}
	
	private LineString toJtsLineString(GeoLineString geoLineString) {
		GeoPosition[] positions = geoLineString.getPositions();
		Coordinate[] coords = new Coordinate[positions.length];
		for (int i = 0; i < positions.length; i++) {
			coords[i] = new Coordinate(positions[i].getX(), positions[i].getY());
		}
		return geomFactory.createLineString(coords);
	}
	
}
