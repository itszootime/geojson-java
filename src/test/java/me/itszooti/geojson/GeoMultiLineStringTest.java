package me.itszooti.geojson;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Before;
import org.junit.Test;

public class GeoMultiLineStringTest {

	private GeoMultiLineString multiLineString;
	
	@Before
	public void before() {
		multiLineString = new GeoMultiLineString(new GeoPosition[][] {
			new GeoPosition[] {
				new GeoPosition(100.0, 0.0),
				new GeoPosition(101.0, 1.0)
			},
			new GeoPosition[] {
				new GeoPosition(102.0, 2.0),
				new GeoPosition(103.0, 3.0)
			}
		});
	}
	
	@Test
	public void getNumLineStrings() {
		assertThat(multiLineString.getNumLineStrings(), equalTo(2));
	}
	
	@Test
	public void getLineStringPosition() {
		assertThat(multiLineString.getLineStringPositions(), instanceOf(GeoPosition[][].class));
	}
	
}
