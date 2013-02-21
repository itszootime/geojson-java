package me.itszooti.geojson;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.Arrays;

import org.junit.Test;

public class GeoGeometryCollectionTest {

	private GeoGeometryCollection geomCollection;
	
	@Before
	public void before() {
		GeoPoint point = new GeoPoint(new GeoPosition(100.0, 0.0));
		GeoLineString lineString = new GeoLineString(new GeoPosition[] {
			new GeoPosition(101.0, 0.0),
			new GeoPosition(102.0, 1.0)
		});
		geomCollection = new GeoGeometryCollection(Arrays.asList(new GeoGeometry[] {
			point, lineString
		}));
	}
	
	@Test
	public void getNumGeometries() {
		assertThat(geomCollection.getNumGeometries(), equalTo(2));
	}
	
	@Test
	public void getGeometry() {
		// check point
		GeoObject geoFirst = geomCollection.getGeometry(0);
		assertThat(geoFirst, instanceOf(GeoPoint.class));
		GeoPoint point = (GeoPoint)geoPoint;
		assertThat(point.getPosition(), equalTo(new GeoPosition(100.0, 0.0)));
		
		// check second
		GeoObject geoSecond = geomCollection.getGeometry(1);
		assertThat(geoSecond, instanceOf(GeoLineString.class));
		GeoLineString lineString = (GeoLineString)geoSecond;
		assertThat(lineString.getNumPositions(), equalTo(2));
		assertThat(lineString.getPositions()[0], equalTo(new GeoPosition(101.0, 0.0)));
	}
	
}
