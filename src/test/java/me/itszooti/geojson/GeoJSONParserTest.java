package me.itszooti.geojson;

import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class GeoJSONParserTest {

	private GeoJSONParser parser;
	
	@Before
	public void before() {
		parser = new GeoJSONParser();
	}
	
	@Test
	public void parsePoint() {
		assertThat(true, equalTo(false));
	}
	
	@Test
	public void parseMultiPoint() {
		assertThat(true, equalTo(false));
	}
	
	@Test
	public void parseLineString() {
		assertThat(true, equalTo(false));
	}
	
	@Test
	public void parseMultiLineString() {
		assertThat(true, equalTo(false));
	}
	
	@Test
	public void parsePolygon() {
		assertThat(true, equalTo(false));
	}
	
	@Test
	public void parseMultiPolygon() {
		assertThat(true, equalTo(false));
	}
	
	@Test
	public void parseGeometryCollection() {
		assertThat(true, equalTo(false));
	}
	
	@Test
	public void parseMassiveGeometryCollection() {
		assertThat(true, equalTo(false));
	}
	
	@Test
	public void parseFeature() {
		assertThat(true, equalTo(false));
	}
	
	@Test
	public void parseFeatureCollection() {
		assertThat(true, equalTo(false));
	}
	
}
