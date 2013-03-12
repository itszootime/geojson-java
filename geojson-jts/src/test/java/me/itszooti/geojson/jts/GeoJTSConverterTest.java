package me.itszooti.geojson.jts;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import me.itszooti.geojson.GeoGeometry;
import me.itszooti.geojson.GeoLineString;
import me.itszooti.geojson.GeoPoint;
import me.itszooti.geojson.GeoPolygon;
import me.itszooti.geojson.GeoPosition;
import me.itszooti.geojson.test.TestData;

import org.junit.Before;
import org.junit.Test;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;

public class GeoJtsConverterTest {

	private GeoJtsConverter conv;
	
	@Before
	public void before() {
		conv = new GeoJtsConverter();
	}
	
	@Test
	public void fromJTSPointType() {
		Point point = TestData.getPoint();
		GeoGeometry geoGeom = conv.fromJts(point);
		assertThat(geoGeom, instanceOf(GeoPoint.class));
	}
	
	@Test
	public void fromJTSPointCoords() {
		Point point = TestData.getPoint();
		GeoPoint geoPoint = (GeoPoint)conv.fromJts(point);
		GeoPosition pos = geoPoint.getPosition();
		assertThat(pos.getX(), equalTo(100.0));
		assertThat(pos.getY(), equalTo(0.0));
	}
	
	@Test
	public void fromJTSLineStringType() {
		LineString lineString = TestData.getLineString();
		GeoGeometry geoGeom = conv.fromJts(lineString);
		assertThat(geoGeom, instanceOf(GeoLineString.class));
	}
	
	@Test
	public void fromJTSLineStringCoords() {
		LineString lineString = TestData.getLineString();
		GeoLineString geoLineString = (GeoLineString)conv.fromJts(lineString);
		GeoPosition[] positions = geoLineString.getPositions();
		assertThat(positions[0].getX(), equalTo(1.0));
		assertThat(positions[0].getY(), equalTo(1.0));
		assertThat(positions[1].getX(), equalTo(3.0));
		assertThat(positions[1].getY(), equalTo(3.0));
	}
	
	@Test
	public void toJTSPointType() {
		GeoPoint geoPoint = TestData.getGeoPoint();
		Geometry geom = conv.toJts(geoPoint);
		assertThat(geom, instanceOf(Point.class));
	}
	
	@Test
	public void toJTSPointCoords() {
		GeoPoint geoPoint = TestData.getGeoPoint();
		Point point = (Point)conv.toJts(geoPoint);
		Coordinate coord = point.getCoordinate();
		assertThat(coord.x, equalTo(100.0));
		assertThat(coord.y, equalTo(0.0));
	}
	
	@Test
	public void toJTSLineStringType() {
		GeoLineString geoLineString = TestData.getGeoLineString();
		Geometry geom = conv.toJts(geoLineString);
		assertThat(geom, instanceOf(LineString.class));
	}
	
	@Test
	public void toJTSLineStringCoords() {
		GeoLineString geoLineString = TestData.getGeoLineString();
		LineString lineString = (LineString)conv.toJts(geoLineString);
		Coordinate[] coords = lineString.getCoordinates();
		assertThat(coords.length, equalTo(2));
		assertThat(coords[0].x, equalTo(1.0));
		assertThat(coords[0].y, equalTo(1.0));
		assertThat(coords[1].x, equalTo(3.0));
		assertThat(coords[1].y, equalTo(3.0));
	}
	
	@Test
	public void toJTSPolygonType() {
		GeoPolygon geoPolygon = TestData.getGeoPolygon();
		Geometry geom = conv.toJts(geoPolygon);
		assertThat(geom, instanceOf(Polygon.class));
	}
	
	@Test
	public void toJTSPolygonExteriorCoords() {
		GeoPolygon geoPolygon = TestData.getGeoPolygon();
		Polygon polygon = (Polygon)conv.toJts(geoPolygon);
		Coordinate[] exteriorCoords = polygon.getExteriorRing().getCoordinates();
		assertThat(exteriorCoords.length, equalTo(5));
		assertThat(exteriorCoords[0].x, equalTo(100.0));
		assertThat(exteriorCoords[0].y, equalTo(0.0));
		assertThat(exteriorCoords[1].x, equalTo(101.0));
		assertThat(exteriorCoords[1].y, equalTo(0.0));
		assertThat(exteriorCoords[2].x, equalTo(101.0));
		assertThat(exteriorCoords[2].y, equalTo(1.0));
		assertThat(exteriorCoords[3].x, equalTo(100.0));
		assertThat(exteriorCoords[3].y, equalTo(1.0));
		assertThat(exteriorCoords[4].x, equalTo(100.0));
		assertThat(exteriorCoords[4].y, equalTo(0.0));
	}
	
	@Test
	public void toJTSPolygonInteriorCoords() {
		GeoPolygon geoPolygon = TestData.getGeoPolygon();
		Polygon polygon = (Polygon)conv.toJts(geoPolygon);
		assertThat(polygon.getNumInteriorRing(), equalTo(1));
		Coordinate[] interiorCoords = polygon.getInteriorRingN(0).getCoordinates();
		assertThat(interiorCoords[0].x, equalTo(100.2));
		assertThat(interiorCoords[0].y, equalTo(0.2));
		assertThat(interiorCoords[1].x, equalTo(100.8));
		assertThat(interiorCoords[1].y, equalTo(0.2));
		assertThat(interiorCoords[2].x, equalTo(100.8));
		assertThat(interiorCoords[2].y, equalTo(0.8));
		assertThat(interiorCoords[3].x, equalTo(100.2));
		assertThat(interiorCoords[3].y, equalTo(0.8));
		assertThat(interiorCoords[4].x, equalTo(100.2));
		assertThat(interiorCoords[4].y, equalTo(0.2));
	}
	
}
