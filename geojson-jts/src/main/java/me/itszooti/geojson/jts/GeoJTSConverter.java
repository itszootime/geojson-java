package me.itszooti.geojson.jts;

import me.itszooti.geojson.GeoGeometry;
import me.itszooti.geojson.GeoPoint;
import me.itszooti.geojson.GeoPosition;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;

public class GeoJTSConverter {

	private GeometryFactory geomFactory;
	
	public GeoJTSConverter() {
		geomFactory = new GeometryFactory();
	}
	
	public GeoGeometry fromJTS(Geometry geom) {
		GeoGeometry geoGeom = null;
		
		// check type and convert
		if (geom instanceof Point) {
			geoGeom = fromJTS((Point)geom);
		}
		
		return geoGeom;
	}
	
	public GeoPoint fromJTS(Point point) {
		GeoPosition pos = new GeoPosition(point.getX(), point.getY());
		return new GeoPoint(pos);
	}
	
	public Geometry toJTS(GeoGeometry geoGeom) {
		Geometry geom = null;
		
		// check type and convert
		if (geoGeom instanceof GeoPoint) {
			geom = toJTS((GeoPoint)geoGeom);
		} else {
			// need exception?
		}
		
		return geom;
	}
	
	private Point toJTS(GeoPoint geoPoint) {
		GeoPosition pos = geoPoint.getPosition();
		return geomFactory.createPoint(new Coordinate(pos.getX(), pos.getY()));
	}
	
}
