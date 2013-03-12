package me.itszooti.geojson.jts;

import me.itszooti.geojson.GeoGeometry;
import me.itszooti.geojson.GeoLineString;
import me.itszooti.geojson.GeoPoint;
import me.itszooti.geojson.GeoPolygon;
import me.itszooti.geojson.GeoPosition;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.LinearRing;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;

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
			throw new IllegalArgumentException("Unsupported JTS Geometry type: " + geom.getClass().getSimpleName());
		}
		
		return geoGeom;
	}
	
	private GeoPoint fromJtsPoint(Point point) {
		GeoPosition pos = new GeoPosition(point.getX(), point.getY());
		return new GeoPoint(pos);
	}
	
	private GeoLineString fromJtsLineString(LineString lineString) {
		GeoPosition[] positions = toPositionsArray(lineString.getCoordinates());
		return new GeoLineString(positions);
	}
	
	private GeoPolygon fromJtsPolygon(Polygon polygon) {
		GeoPosition[] exterior = toPositionsArray(polygon.getExteriorRing().getCoordinates());
	}
	
	private GeoPosition[] toPositionsArray(Coordinate[] coordinateArray) {
		GeoPosition[] positions = new GeoPosition[coordinateArray.length];
		for (int i = 0; i < coordinateArray.length; i++) {
			positions[i] = new GeoPosition(coordinateArray[i].x, coordinateArray[i].y);
		}
		return positions;
	}
	
	public Geometry toJts(GeoGeometry geoGeom) {
		Geometry geom = null;
		
		// check type and convert
		if (geoGeom instanceof GeoPoint) {
			geom = toJtsPoint((GeoPoint)geoGeom);
		} else if (geoGeom instanceof GeoLineString) {
			geom = toJtsLineString((GeoLineString)geoGeom);
		} else if (geoGeom instanceof GeoPolygon) {
			geom = toJtsPolygon((GeoPolygon)geoGeom);
		} else {
			throw new IllegalArgumentException("Unsupported GeoGeometry type: " + geoGeom.getClass().getSimpleName());
		}
		
		return geom;
	}
	
	private Point toJtsPoint(GeoPoint geoPoint) {
		GeoPosition pos = geoPoint.getPosition();
		return geomFactory.createPoint(new Coordinate(pos.getX(), pos.getY()));
	}
	
	private LineString toJtsLineString(GeoLineString geoLineString) {
		Coordinate[] coords = toCoordinateArray(geoLineString.getPositions());
		return geomFactory.createLineString(coords);
	}
	
	private Polygon toJtsPolygon(GeoPolygon geoPolygon) {
		Coordinate[] exteriorCoords = toCoordinateArray(geoPolygon.getExterior());
		LinearRing shell = geomFactory.createLinearRing(exteriorCoords);
		LinearRing[] holes = new LinearRing[geoPolygon.getNumInteriors()];
		for (int i = 0; i < holes.length; i++) {
			Coordinate[] interiorCoords = toCoordinateArray(geoPolygon.getInterior(i));
			holes[i] = geomFactory.createLinearRing(interiorCoords);
		}		
		return geomFactory.createPolygon(shell, holes);
	}
	
	private Coordinate[] toCoordinateArray(GeoPosition[] positionsArray) {
		GeoPosition[] positions = positionsArray;
		Coordinate[] coords = new Coordinate[positions.length];
		for (int i = 0; i < positions.length; i++) {
			coords[i] = new Coordinate(positions[i].getX(), positions[i].getY());
		}
		return coords;
	}
	
}
