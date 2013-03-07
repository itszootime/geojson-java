package me.itszooti.geojson.jts;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import me.itszooti.geojson.GeoGeometry;
import me.itszooti.geojson.GeoLineString;
import me.itszooti.geojson.GeoPoint;
import me.itszooti.geojson.GeoPosition;
import me.itszooti.geojson.test.TestData;

import org.junit.Before;
import org.junit.Test;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.Point;

public class GeoJTSConverterTest {

	private GeoJTSConverter conv;
	
	@Before
	public void before() {
		conv = new GeoJTSConverter();
	}
	
	@Test
	public void fromJTSPointType() {
		Point point = TestData.getPoint();
		GeoGeometry geoGeom = conv.fromJTS(point);
		assertThat(geoGeom, instanceOf(GeoPoint.class));
	}
	
	@Test
	public void fromJTSPointCoords() {
		Point point = TestData.getPoint();
		GeoPoint geoPoint = (GeoPoint)conv.fromJTS(point);
		GeoPosition pos = geoPoint.getPosition();
		assertThat(pos.getX(), equalTo(100.0));
		assertThat(pos.getY(), equalTo(0.0));
	}
	
	@Test
	public void fromJTSLineStringType() {
		LineString lineString = TestData.getLineString();
		GeoGeometry geoGeom = conv.fromJTS(lineString);
		assertThat(geoGeom, instanceOf(GeoLineString.class));
	}
	
	@Test
	public void fromJTSLineStringCoords() {
		LineString lineString = TestData.getLineString();
		GeoLineString geoLineString = (GeoLineString)conv.fromJTS(lineString);
		GeoPosition[] positions = geoLineString.getPositions();
		assertThat(positions[0].getX(), equalTo(1.0));
		assertThat(positions[0].getY(), equalTo(1.0));
		assertThat(positions[1].getX(), equalTo(3.0));
		assertThat(positions[1].getY(), equalTo(3.0));
	}
	
	@Test
	public void toJTSPointType() {
		GeoPoint geoPoint = TestData.getGeoPoint();
		Geometry geom = conv.toJTS(geoPoint);
		assertThat(geom, instanceOf(Point.class));
	}
	
	@Test
	public void toJTSPointCoords() {
		GeoPoint geoPoint = TestData.getGeoPoint();
		Point point = (Point)conv.toJTS(geoPoint);
		Coordinate coord = point.getCoordinate();
		assertThat(coord.x, equalTo(100.0));
		assertThat(coord.y, equalTo(0.0));
	}
	
	@Test
	public void toJTSLineStringType() {
		GeoLineString geoLineString = TestData.getGeoLineString();
		Geometry geom = conv.toJTS(geoLineString);
		assertThat(geom, instanceOf(LineString.class));
	}
	
	@Test
	public void toJTSLineStringCoords() {
		GeoLineString geoLineString = TestData.getGeoLineString();
		LineString lineString = (LineString)conv.toJTS(geoLineString);
		Coordinate[] coords = lineString.getCoordinates();
		assertThat(coords.length, equalTo(2));
		assertThat(coords[0].x, equalTo(1.0));
		assertThat(coords[0].y, equalTo(1.0));
		assertThat(coords[1].x, equalTo(3.0));
		assertThat(coords[1].y, equalTo(3.0));
	}
	
}
