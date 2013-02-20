package me.itszooti.geojson;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.notNullValue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class GeoPolygonTest {

	private GeoPolygon polygonNoHoles;
	private GeoPolygon polygonWithHoles;
	
	@Before
	public void before() {
		List<GeoPosition> exterior = Arrays.asList(new GeoPosition[] {
			new GeoPosition(100.0, 0.0), 
			new GeoPosition(101.0, 0.0),
			new GeoPosition(101.0, 1.0),
			new GeoPosition(100.0, 1.0),
			new GeoPosition(100.0, 0.0)
		});
		List<List<GeoPosition>> interiors = new ArrayList<List<GeoPosition>>();
		interiors.add(Arrays.asList(new GeoPosition[] {
			new GeoPosition(100.2, 0.2),
			new GeoPosition(100.8, 0.2),
			new GeoPosition(100.8, 0.8),
			new GeoPosition(100.2, 0.8),
			new GeoPosition(100.2, 0.2) 
		}));
		polygonNoHoles = new GeoPolygon(exterior);
		polygonWithHoles = new GeoPolygon(exterior, interiors);
	}
	
	@Test
	public void getNumInteriors() {
		assertThat(polygonNoHoles.getNumInteriors(), equalTo(0));
		assertThat(polygonWithHoles.getNumInteriors(), equalTo(1));
	}
	
	@Test
	public void getExterior() {
		List<GeoPosition> exterior = polygonNoHoles.getExterior();
		assertThat(exterior, notNullValue());
		assertThat(exterior.size(), equalTo(5));
		assertThat(exterior.get(2), equalTo(new GeoPosition(101.0, 1.0)));
	}
	
	@Test
	public void getInterior() {
		List<GeoPosition> interior = polygonWithHoles.getInterior(0);
		assertThat(interior, notNullValue());
		assertThat(interior.size(), equalTo(5));
		assertThat(interior.get(0), equalTo(new GeoPosition(100.2, 0.2)));
	}
	
	@Test
	public void isGeoGeometry() {
		assertThat(polygonNoHoles, instanceOf(GeoGeometry.class));
	}
	
}
