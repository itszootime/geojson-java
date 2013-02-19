package me.itszooti.geojson;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.notNullValue;

import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

public class GeoJSONParserTest {

	private GeoJSONParser parser;
	
	private GeoObject parseFile(String filename) {
		return parser.parse(this.getClass().getClassLoader().getResourceAsStream(filename));
	}
	
	@Before
	public void before() {
		parser = GeoJSONParser.create();
	}
	
	private void testPoint(GeoPoint point, double expectedX, double expectedY) {
		GeoPosition position = point.getPosition();
		assertThat(position, notNullValue());
		assertThat(position.getX(), equalTo(expectedX));
		assertThat(position.getY(), equalTo(expectedY));
	}
	
	@Test
	public void parsePoint() {
		GeoObject geo = parseFile("point.json");
		assertThat(geo, notNullValue());
		assertThat(geo, instanceOf(GeoPoint.class));
		testPoint((GeoPoint)geo, 100.0, 0.0);
	}
	
	@Test
	public void parseMultiPoint() {
		GeoObject geo = parseFile("multipoint.json");
		assertThat(geo, notNullValue());
		assertThat(geo, instanceOf(GeoMultiPoint.class));
		GeoMultiPoint mp = (GeoMultiPoint)geo;
		assertThat(mp.getNumPoints(), equalTo(2));
		testPoint(mp.getPoint(0), 100.0, 0.0);
		testPoint(mp.getPoint(1), 101.0, 1.0);
	}
//	
//	private void testLineString(LineString ls, double[][] expected) {
//		Coordinate[] coords = ls.getCoordinates();
//		assertThat(coords.length, equalTo(expected.length));
//		for (int i = 0; i < coords.length; i++) {
//			assertThat(coords[i].x, equalTo(expected[i][0]));
//			assertThat(coords[i].y, equalTo(expected[i][1]));
//		}
//	}
//	
//	@Test
//	public void parseLineString() {
//		Geometry geom = parseFile("linestring.json");
//		assertThat(geom, notNullValue());
//		assertThat(geom, instanceOf(LineString.class));
//		testLineString((LineString)geom, new double[][] { new double[] { 100.0, 0.0 }, new double[] { 101.0, 1.0 } });
//	}
//	
//	@Test
//	public void parseMultiLineString() {
//		Geometry geom = parseFile("multilinestring.json");
//		assertThat(geom, notNullValue());
//		assertThat(geom, instanceOf(MultiLineString.class));
//		MultiLineString mls = (MultiLineString)geom;
//		assertThat(mls.getNumGeometries(), equalTo(2));
//		testLineString((LineString)mls.getGeometryN(0), new double[][] { new double[] { 100.0, 0.0 }, new double[] { 101.0, 1.0 } });
//		testLineString((LineString)mls.getGeometryN(1), new double[][] { new double[] { 102.0, 2.0 }, new double[] { 103.0, 3.0 } });
//	}
//	
//	@Test
//	public void parsePolygonWithHoles() {
//		Geometry geom = parseFile("polygon-withholes.json");
//		assertThat(geom, notNullValue());
//		assertThat(geom, instanceOf(Polygon.class));
//		Polygon p = (Polygon)geom;
//	    testLineString(p.getExteriorRing(), new double[][] {
//	    	new double[] { 100.0, 0.0 },
//	    	new double[] { 101.0, 0.0 },
//	    	new double[] { 101.0, 1.0 },
//	    	new double[] { 100.0, 1.0 },
//	    	new double[] { 100.0, 0.0 }
//	    });
//	    assertThat(p.getNumInteriorRing(), equalTo(1));
//	    testLineString(p.getInteriorRingN(0), new double[][] {
//	    	new double[] { 100.2, 0.2 },
//	    	new double[] { 100.8, 0.2 },
//	    	new double[] { 100.8, 0.8 },
//	    	new double[] { 100.2, 0.8 },
//	    	new double[] { 100.2, 0.2 }
//	    });
//	}
//	
//	@Test
//	public void parsePolygonNoHoles() {
//		Geometry geom = parseFile("polygon-noholes.json");
//		assertThat(geom, notNullValue());
//		assertThat(geom, instanceOf(Polygon.class));
//		Polygon p = (Polygon)geom;
//	    testLineString(p.getExteriorRing(), new double[][] {
//	    	new double[] { 100.0, 0.0 },
//	    	new double[] { 101.0, 0.0 },
//	    	new double[] { 101.0, 1.0 },
//	    	new double[] { 100.0, 1.0 },
//	    	new double[] { 100.0, 0.0 }
//	    });
//	    assertThat(p.getNumInteriorRing(), equalTo(0));
//	}
//	
//	@Test
//	public void parseMultiPolygon() {
//		Geometry geom = parseFile("multipolygon.json");
//		assertThat(geom, notNullValue());
//		assertThat(geom, instanceOf(MultiPolygon.class));
//		MultiPolygon mp = (MultiPolygon)geom;
//		assertThat(mp.getNumGeometries(), equalTo(2));
//		Polygon p1 = (Polygon)mp.getGeometryN(0);
//	    testLineString(p1.getExteriorRing(), new double[][] {
//	    	new double[] { 102.0, 2.0 },
//	    	new double[] { 103.0, 2.0 },
//	    	new double[] { 103.0, 3.0 },
//	    	new double[] { 102.0, 3.0 },
//	    	new double[] { 102.0, 2.0 }
//	    });
//	    assertThat(p1.getNumInteriorRing(), equalTo(0));
//		Polygon p2 = (Polygon)mp.getGeometryN(1);
//	    testLineString(p2.getExteriorRing(), new double[][] {
//	    	new double[] { 100.0, 0.0 },
//	    	new double[] { 101.0, 0.0 },
//	    	new double[] { 101.0, 1.0 },
//	    	new double[] { 100.0, 1.0 },
//	    	new double[] { 100.0, 0.0 }
//	    });
//		assertThat(p2.getNumInteriorRing(), equalTo(1));
//	    testLineString(p2.getInteriorRingN(0), new double[][] {
//	    	new double[] { 100.2, 0.2 },
//	    	new double[] { 100.8, 0.2 },
//	    	new double[] { 100.8, 0.8 },
//	    	new double[] { 100.2, 0.8 },
//	    	new double[] { 100.2, 0.2 }
//	    });
//	}
//	
//	@Test
//	public void parseGeometryCollection() {
//		Geometry geom = parseFile("geometrycollection.json");
//		assertThat(geom, notNullValue());
//		assertThat(geom, instanceOf(GeometryCollection.class));
//	    GeometryCollection gc = (GeometryCollection)geom;
//	    assertThat(gc.getNumGeometries(), equalTo(2));
//	    assertThat(gc.getGeometryN(0), instanceOf(Point.class));
//	    Point p = (Point)gc.getGeometryN(0);
//	    assertThat(gc.getGeometryN(1), instanceOf(LineString.class));
//	    testPoint(p, 100.0, 0.0);
//	    LineString ls = (LineString)gc.getGeometryN(1);
//	    testLineString(ls, new double[][] { new double[] { 101.0, 0.0 }, new double[] { 102.0, 1.0 } });
//	}
//	
//	@Test
//	public void parseCRS() {
//		assertThat(true, equalTo(false));
//	}
//	
//	@Test
//	public void parseFeature() {
//		assertThat(true, equalTo(false));
//	}
//	
//	@Test
//	public void parseFeatureCollection() {
//		assertThat(true, equalTo(false));
//	}
	
}
