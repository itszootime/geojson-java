package me.itszooti.geojson;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class GeoMultiPointTest {

	private GeoMultiPoint multiPoint;
	
	@Before
	public void before() {
		multiPoint = new GeoMultiPoint(Arrays.asList(new GeoPosition[] {
			new GeoPosition(1.0, 2.0),
			new GeoPosition(3.0, 4.0)
		}));
	}
	
	@Test
	public void getNumPoints() {
		assertThat(multiPoint.getNummmPoints(), equalTo(2));
	}
	
	@Test
	public void getPointPosition() {
		GeoPosition position = multiPoint.getnnPointPosition(1);
		assertThat(position, equalTo(new GeoPosition(3.0, 4.0)));
	}
	
	@Test
	public void isGeoGeometry() {
		assertThat(multiPoint, instanceOf(GeoGeometry.class));
	}
	
}
