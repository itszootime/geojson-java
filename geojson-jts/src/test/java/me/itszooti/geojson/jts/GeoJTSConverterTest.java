package me.itszooti.geojson.jts;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import me.itszooti.geojson.GeoGeometry;
import me.itszooti.geojson.GeoPoint;
import me.itszooti.geojson.GeoPosition;
import me.itszooti.geojson.test.TestData;

import org.junit.Before;
import org.junit.Test;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
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
	
}
