package me.itszooti.geojson;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class GeoLineStringTest {
	
	private GeoLineString lineString;

	@Before
	public void before() {
		lineString = new GeoLineString(Arrays.asList(new GeoPosition[] {
			new GeoPosition(1.0, 1.0),
			new GeoPosition(3.0, 3.0)
		}));
	}
	
	@Test
	public void getNumPositions() {
		assertThat(lineString.getNumPositions(), equalTo(2));
	}
	
	@Test
	public void getPositions() {
		assertThat(lineString.getPositions(), instanceOf(GeoPosition[].class));
	}
	
	@Test
	public void getPosition() {
		GeoPosition position = lineString.getPosition(0);
		assertThat(position, equalTo(new GeoPosition(1.0, 1.0)));
	}
	
	@Test
	public void isGeoGeometry() {
		assertThat(lineString, instanceOf(GeoGeometry.class));
	}
	
}
