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

public class GeoMultiPolygonTest {

	private GeoMultiPolygon multiPolygon;
	
	@Before
	public void before() {
		List<List<GeoPosition>> exteriors = new ArrayList<List<GeoPosition>>();
		exteriors.add(Arrays.asList(new GeoPosition[] {
			new GeoPosition(100.0, 0.0), 
			new GeoPosition(101.0, 0.0),
			new GeoPosition(101.0, 1.0),
			new GeoPosition(100.0, 1.0),
			new GeoPosition(100.0, 0.0)
		}));
		exteriors.add(Arrays.asList(new GeoPosition[] {
			new GeoPosition(100.0, 0.0), 
			new GeoPosition(101.0, 0.0),
			new GeoPosition(101.0, 1.0),
			new GeoPosition(100.0, 1.0),
			new GeoPosition(100.0, 0.0)
		}));
		
		List<List<List<GeoPosition>>> interiors = new ArrayList<List<List<GeoPosition>>>();
		interiors.add(new ArrayList<List<GeoPosition>>());
		List<List<GeoPosition>> interior = new ArrayList<List<GeoPosition>>();
		interior.add(Arrays.asList(new GeoPosition[] {
			new GeoPosition(100.2, 0.2),
			new GeoPosition(100.8, 0.2),
			new GeoPosition(100.8, 0.8),
			new GeoPosition(100.2, 0.8),
			new GeoPosition(100.2, 0.2) 
		}));
		interiors.add(interior);
		multiPolygon = new GeoMultiPolygon(exteriors, interiors);
	}
	
	@Test
	public void getNumPolygons() {
		assertThat(multiPolygon.getNumPolygons(), equalTo(2));
	}
	
	@Test
	public void getPolygon() {
		// check no holes polygon
		GeoPolygon polygonNoHoles = multiPolygon.getPolygon(0);
		assertThat(polygonNoHoles.getNumInteriors(), equalTo(0));
		GeoPosition[] noHolesexterior = polygonNoHoles.getExterior();
		assertThat(noHolesexterior, notNullValue());
		assertThat(noHolesexterior.length, equalTo(5));
		assertThat(noHolesexterior[2], equalTo(new GeoPosition(101.0, 1.0)));
		
		// check with holes polygon
		GeoPolygon polygonWithHoles = multiPolygon.getPolygon(1);
		assertThat(polygonWithHoles.getNumInteriors(), equalTo(1));
		GeoPosition[] withHolesInterior = polygonWithHoles.getInterior(0);
		assertThat(withHolesInterior, notNullValue());
		assertThat(withHolesInterior.length, equalTo(5));
		assertThat(withHolesInterior[0], equalTo(new GeoPosition(100.2, 0.2)));
	}
	
	@Test
	public void isGeoGeometry() {
		assertThat(multiPolygon, instanceOf(GeoGeometry.class));
	}
	
}
