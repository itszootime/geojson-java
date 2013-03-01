package me.itszooti.geojson;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.notNullValue;

import java.util.Map;

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
	
	private void testPosition(GeoPosition position, double expectedX, double expectedY) {
		assertThat(position, notNullValue());
		assertThat(position, equalTo(new GeoPosition(expectedX, expectedY)));
	}
	
	@Test
	public void parsePoint() {
		GeoObject geo = parseFile("point.json");
		assertThat(geo, notNullValue());
		assertThat(geo, instanceOf(GeoPoint.class));
		testPosition(((GeoPoint)geo).getPosition(), 100.0, 0.0);
	}
	
	@Test
	public void parseMultiPoint() {
		GeoObject geo = parseFile("multipoint.json");
		assertThat(geo, notNullValue());
		assertThat(geo, instanceOf(GeoMultiPoint.class));
		GeoMultiPoint multiPoint = (GeoMultiPoint)geo;
		assertThat(multiPoint.getNumPoints(), equalTo(2));
		testPosition(multiPoint.getPoint(0).getPosition(), 100.0, 0.0);
		testPosition(multiPoint.getPoint(1).getPosition(), 101.0, 1.0);
	}
	
	private void testPositions(GeoPosition[] positions, double[][] expected) {
		assertThat(positions.length, equalTo(expected.length));
		for (int i = 0; i < positions.length; i++) {
			assertThat(positions[i], equalTo(new GeoPosition(expected[i][0], expected[i][1])));
		}
	}
	
	@Test
	public void parseLineString() {
		GeoObject geo = parseFile("linestring.json");
		assertThat(geo, notNullValue());
		assertThat(geo, instanceOf(GeoLineString.class));
		GeoLineString lineString = (GeoLineString)geo;
		assertThat(lineString.getNumPositions(), equalTo(2));
		testPositions(lineString.getPositions(), new double[][] { new double[] { 100.0, 0.0 }, new double[] { 101.0, 1.0 } });
	}
	
	@Test
	public void parseMultiLineString() {
		GeoObject geo = parseFile("multilinestring.json");
		assertThat(geo, notNullValue());
		assertThat(geo, instanceOf(GeoMultiLineString.class));
		GeoMultiLineString multiLineString = (GeoMultiLineString)geo;
		assertThat(multiLineString.getNumLineStrings(), equalTo(2));
		testPositions(multiLineString.getLineString(0).getPositions(), new double[][] {
			new double[] { 100.0, 0.0 }, new double[] { 101.0, 1.0 }
		});
		testPositions(multiLineString.getLineString(1).getPositions(), new double[][] {
			new double[] { 102.0, 2.0 }, new double[] { 103.0, 3.0 }
		});
	}
	
	@Test
	public void parsePolygonWithHoles() {
		GeoObject geo = parseFile("polygon-withholes.json");
		assertThat(geo, notNullValue());
		assertThat(geo, instanceOf(GeoPolygon.class));
		GeoPolygon polygon = (GeoPolygon)geo;
	    testPositions(polygon.getExterior(), new double[][] {
	    	new double[] { 100.0, 0.0 },
	    	new double[] { 101.0, 0.0 },
	    	new double[] { 101.0, 1.0 },
	    	new double[] { 100.0, 1.0 },
	    	new double[] { 100.0, 0.0 }
	    });
	    assertThat(polygon.getNumInteriors(), equalTo(1));
	    testPositions(polygon.getInterior(0), new double[][] {
	    	new double[] { 100.2, 0.2 },
	    	new double[] { 100.8, 0.2 },
	    	new double[] { 100.8, 0.8 },
	    	new double[] { 100.2, 0.8 },
	    	new double[] { 100.2, 0.2 }
	    });
	}
	
	@Test
	public void parsePolygonNoHoles() {
		GeoObject geo = parseFile("polygon-noholes.json");
		assertThat(geo, notNullValue());
		assertThat(geo, instanceOf(GeoPolygon.class));
		GeoPolygon polygon = (GeoPolygon)geo;
	    testPositions(polygon.getExterior(), new double[][] {
	    	new double[] { 100.0, 0.0 },
	    	new double[] { 101.0, 0.0 },
	    	new double[] { 101.0, 1.0 },
	    	new double[] { 100.0, 1.0 },
	    	new double[] { 100.0, 0.0 }
	    });
	    assertThat(polygon.getNumInteriors(), equalTo(0));
	}
	
	@Test
	public void parseMultiPolygon() {
		GeoObject geo = parseFile("multipolygon.json");
		assertThat(geo, notNullValue());
		assertThat(geo, instanceOf(GeoMultiPolygon.class));
		GeoMultiPolygon multiPolygon = (GeoMultiPolygon)geo;
		assertThat(multiPolygon.getNumPolygons(), equalTo(2));
		GeoPolygon polygonNoHoles = (GeoPolygon)multiPolygon.getPolygon(0);
	    testPositions(polygonNoHoles.getExterior(), new double[][] {
	    	new double[] { 102.0, 2.0 },
	    	new double[] { 103.0, 2.0 },
	    	new double[] { 103.0, 3.0 },
	    	new double[] { 102.0, 3.0 },
	    	new double[] { 102.0, 2.0 }
	    });
	    assertThat(polygonNoHoles.getNumInteriors(), equalTo(0));
		GeoPolygon polygonWithHoles = (GeoPolygon)multiPolygon.getPolygon(1);
	    testPositions(polygonWithHoles.getExterior(), new double[][] {
	    	new double[] { 100.0, 0.0 },
	    	new double[] { 101.0, 0.0 },
	    	new double[] { 101.0, 1.0 },
	    	new double[] { 100.0, 1.0 },
	    	new double[] { 100.0, 0.0 }
	    });
		assertThat(polygonWithHoles.getNumInteriors(), equalTo(1));
	    testPositions(polygonWithHoles.getInterior(0), new double[][] {
	    	new double[] { 100.2, 0.2 },
	    	new double[] { 100.8, 0.2 },
	    	new double[] { 100.8, 0.8 },
	    	new double[] { 100.2, 0.8 },
	    	new double[] { 100.2, 0.2 }
	    });
	}
	
	@Test
	public void parseGeometryCollection() {
		GeoObject geo = parseFile("geometrycollection.json");
		assertThat(geo, notNullValue());
		assertThat(geo, instanceOf(GeoGeometryCollection.class));
	    GeoGeometryCollection geomCollection = (GeoGeometryCollection)geo;
	    assertThat(geomCollection.getNumGeometries(), equalTo(2));
	    assertThat(geomCollection.getGeometry(0), instanceOf(GeoPoint.class));
	    GeoPoint point = (GeoPoint)geomCollection.getGeometry(0);
	    testPosition(point.getPosition(), 100.0, 0.0);
	    assertThat(geomCollection.getGeometry(1), instanceOf(GeoLineString.class));
	    GeoLineString lineString = (GeoLineString)geomCollection.getGeometry(1);
	    testPositions(lineString.getPositions(), new double[][] { new double[] { 101.0, 0.0 }, new double[] { 102.0, 1.0 } });
	}
	
	@Test
	public void parseFeature() {
		GeoObject geo = parseFile("feature.json");
		assertThat(geo, notNullValue());
		assertThat(geo, instanceOf(GeoFeature.class));
		GeoFeature feature = (GeoFeature)geo;
		assertThat(feature.getId(), equalTo("a_test_feature"));
		GeoGeometry geometry = feature.getGeometry();
		assertThat(geometry, notNullValue());
		assertThat(geometry, instanceOf(GeoPoint.class));
	}
	
	@Test
	public void parseFeatureProperties() {
		GeoFeature feature = (GeoFeature)parseFile("feature-withproperties.json");
		Object number = feature.getProperty("number");
		assertThat(number, instanceOf(Number.class));
		assertThat((Number)number, equalTo((Number)2));
		Object string = feature.getProperty("string"); 
		assertThat(string, instanceOf(String.class));
		assertThat((String)string, equalTo("i am a string"));
		Object array = feature.getProperty("array");
		assertThat(array, instanceOf(Object[].class));
		Object[] arrayCast = (Object[])array;
		assertThat((Number)arrayCast[0], equalTo((Number)1));
		assertThat((Number)arrayCast[1], equalTo((Number)2));
		assertThat((String)arrayCast[2], equalTo("three!"));
		Object object = feature.getProperty("object");
		assertThat(object, instanceOf(Map.class));
		Map<?, ?> objectCast = (Map<?, ?>)object;
		assertThat((String)objectCast.get("inner"), equalTo("objects"));
		assertThat(objectCast.get("may"), instanceOf(Object[].class));
		Object[] innerArrayCast = (Object[])objectCast.get("may");
		assertThat((String)innerArrayCast[0], equalTo("be"));
		assertThat((String)innerArrayCast[1], equalTo("problematic"));
	}
	
	@Test
	public void parseFeatureCollection() {
		GeoObject geo = parseFile("featurecollection.json");
		assertThat(geo, notNullValue());
		assertThat(geo, instanceOf(GeoFeatureCollection.class));
		GeoFeatureCollection featureColl = (GeoFeatureCollection)geo;
	    assertThat(featureColl.getNumFeatures(), equalTo(2));
	    GeoFeature feature1 = featureColl.getFeature(0);
	    assertThat(feature1.getId(), equalTo("a_test_feature"));
	    GeoFeature feature2 = featureColl.getFeature(1);
	    assertThat(feature2.getId(), equalTo("another_test_feature"));
	}
}
