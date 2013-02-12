package me.itszooti.geojson;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.notNullValue;

import java.io.InputStream;

import org.junit.Before;
import org.junit.Test;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;

public class GeoJSONParserTest {

	private GeoJSONParser parser;
	
	private InputStream getTestFileStream(String filename) {
		return this.getClass().getClassLoader().getResourceAsStream(filename);
	}
	
	@Before
	public void before() {
		parser = GeoJSONParser.create();
	}
	
	@Test
	public void parsePoint() {
		Geometry geom = parser.parseGeometry(getTestFileStream("point.json"));
		assertThat(geom, notNullValue());
		assertThat(geom, instanceOf(Point.class));
		Coordinate coord = geom.getCoordinate();
		assertThat(coord.x, equalTo(100.0));
		assertThat(coord.y, equalTo(0.0));
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
