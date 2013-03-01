package me.itszooti.geojson;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
public class GeoJSONEncoderTest {

	private GeoJSONEncoder encoder;
	private JsonParser jsonParser;
	
	@Before
	public void before() {
		encoder = GeoJSONEncoder.create();
		jsonParser = new JsonParser();
	}
	
	@Test
	public void encodePoint() {
		GeoPoint point = new GeoPoint(new GeoPosition(100.0, 0.0));
		String json = encoder.encode(point);
		JsonElement element = jsonParser.parse(json);
		assertThat(element, instanceOf(JsonObject.class));
		JsonObject object = element.getAsJsonObject();
		assertThat(object.has("type"), equalTo(true));
		assertThat(object.getAsJsonPrimitive("type").getAsString(), equalTo("Point"));
		assertThat(object.has("coordinates"), equalTo(true));
		JsonArray coords = object.getAsJsonArray("coordinates");
		assertThat(coords.size(), equalTo(2));
		assertThat(coords.get(0).getAsDouble(), equalTo(100.0));
		assertThat(coords.get(1).getAsDouble(), equalTo(0.0));
	}
	
	@Test
	public void encodeMultiPoint() {
		GeoMultiPoint multiPoint = new GeoMultiPoint(Arrays.asList(new GeoPosition[] {
			new GeoPosition(1.0, 2.0),
			new GeoPosition(3.0, 4.0)
		}));
		String json = encoder.encode(multiPoint);
		JsonElement element = jsonParser.parse(json);
		assertThat(element, instanceOf(JsonObject.class));
		JsonObject object = element.getAsJsonObject();
		assertThat(object.has("type"), equalTo(true));
		assertThat(object.getAsJsonPrimitive("type").getAsString(), equalTo("MultiPoint"));
		assertThat(object.has("coordinates"), equalTo(true));
		JsonArray coords = object.getAsJsonArray("coordinates");
		testPositions(coords, new double[][] {
			new double[] { 1.0, 2.0 },
			new double[] { 3.0, 4.0 }
		});
	}
	
	private void testPositions(JsonArray positions, double[][] expected) {
		assertThat(positions.size(), equalTo(expected.length));
		for (int i = 0; i < positions.size(); i++) {
			JsonArray position = positions.get(i).getAsJsonArray();
			assertThat(position.get(0).getAsDouble(), equalTo(expected[i][0]));
			assertThat(position.get(1).getAsDouble(), equalTo(expected[i][1]));
		}
	}
	
	@Test
	public void encodeLineString() {
		GeoLineString lineString = new GeoLineString(Arrays.asList(new GeoPosition[] {
			new GeoPosition(1.0, 1.0),
			new GeoPosition(3.0, 3.0)
		}));
		String json = encoder.encode(lineString);
		JsonElement element = jsonParser.parse(json);
		assertThat(element, instanceOf(JsonObject.class));
		JsonObject object = element.getAsJsonObject();
		assertThat(object.has("type"), equalTo(true));
		assertThat(object.getAsJsonPrimitive("type").getAsString(), equalTo("LineString"));
		assertThat(object.has("coordinates"), equalTo(true));
		JsonArray coords = object.getAsJsonArray("coordinates");
		testPositions(coords, new double[][] {
			new double[] { 1.0, 1.0 },
			new double[] { 3.0, 3.0 }
		});
	}
	
	@Test
	public void encodeMultiLineString() {
		GeoMultiLineString multiLineString = new GeoMultiLineString(new GeoPosition[][] {
			new GeoPosition[] {
				new GeoPosition(100.0, 0.0),
				new GeoPosition(101.0, 1.0)
			},
			new GeoPosition[] {
				new GeoPosition(102.0, 2.0),
				new GeoPosition(103.0, 3.0)
			}
		});
		String json = encoder.encode(multiLineString);
		JsonElement element = jsonParser.parse(json);
		assertThat(element, instanceOf(JsonObject.class));
		JsonObject object = element.getAsJsonObject();
		assertThat(object.has("type"), equalTo(true));
		assertThat(object.getAsJsonPrimitive("type").getAsString(), equalTo("MultiLineString"));
		assertThat(object.has("coordinates"), equalTo(true));
		JsonArray coords = object.getAsJsonArray("coordinates");
		assertThat(coords.size(), equalTo(2));
		testPositions(coords.get(0).getAsJsonArray(), new double[][] {
			new double[] { 100.0, 0.0 },
			new double[] { 101.0, 1.0 }
		});
		testPositions(coords.get(1).getAsJsonArray(), new double[][] {
			new double[] { 102.0, 2.0 },
			new double[] { 103.0, 3.0 }
		});
	}
	
	@Test
	public void encodePolygonNoHoles() {
		List<GeoPosition> exterior = Arrays.asList(new GeoPosition[] {
			new GeoPosition(100.0, 0.0), 
			new GeoPosition(101.0, 0.0),
			new GeoPosition(101.0, 1.0),
			new GeoPosition(100.0, 1.0),
			new GeoPosition(100.0, 0.0)
		});
		GeoPolygon polygonNoHoles = new GeoPolygon(exterior);
		String json = encoder.encode(polygonNoHoles);
		JsonElement element = jsonParser.parse(json);
		assertThat(element, instanceOf(JsonObject.class));
		JsonObject object = element.getAsJsonObject();
		assertThat(object.has("type"), equalTo(true));
		assertThat(object.getAsJsonPrimitive("type").getAsString(), equalTo("Polygon"));
		assertThat(object.has("coordinates"), equalTo(true));
		JsonArray coords = object.getAsJsonArray("coordinates");
		assertThat(coords.size(), equalTo(1));
		JsonArray exteriorCoords = coords.get(0).getAsJsonArray();
		testPositions(exteriorCoords, new double[][] {
			new double[] { 100.0, 0.0 }, 
			new double[] { 101.0, 0.0 },
			new double[] { 101.0, 1.0 },
			new double[] { 100.0, 1.0 },
			new double[] { 100.0, 0.0 }
		});
	}
	
	@Test
	public void encodePolygonWithHoles() {
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
		GeoPolygon polygonWithHoles = new GeoPolygon(exterior, interiors);
		String json = encoder.encode(polygonWithHoles);
		JsonElement element = jsonParser.parse(json);
		assertThat(element, instanceOf(JsonObject.class));
		JsonObject object = element.getAsJsonObject();
		assertThat(object.has("type"), equalTo(true));
		assertThat(object.getAsJsonPrimitive("type").getAsString(), equalTo("Polygon"));
		assertThat(object.has("coordinates"), equalTo(true));
		JsonArray coords = object.getAsJsonArray("coordinates");
		assertThat(coords.size(), equalTo(2));
		JsonArray exteriorCoords = coords.get(0).getAsJsonArray();
		testPositions(exteriorCoords, new double[][] {
			new double[] { 100.0, 0.0 }, 
			new double[] { 101.0, 0.0 },
			new double[] { 101.0, 1.0 },
			new double[] { 100.0, 1.0 },
			new double[] { 100.0, 0.0 }
		});
		JsonArray interiorCoords = coords.get(1).getAsJsonArray();
		testPositions(interiorCoords, new double[][] {
			new double[] { 100.2, 0.2 },
			new double[] { 100.8, 0.2 },
			new double[] { 100.8, 0.8 },
			new double[] { 100.2, 0.8 },
			new double[] { 100.2, 0.2 } 
		});
	}
	
	@Test
	public void encodeMultiPolygon() {
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
		GeoMultiPolygon multiPolygon = new GeoMultiPolygon(exteriors, interiors);
		String json = encoder.encode(multiPolygon);
		JsonElement element = jsonParser.parse(json);
		assertThat(element, instanceOf(JsonObject.class));
		JsonObject object = element.getAsJsonObject();
		assertThat(object.has("type"), equalTo(true));
		assertThat(object.getAsJsonPrimitive("type").getAsString(), equalTo("MultiPolygon"));
		assertThat(object.has("coordinates"), equalTo(true));
		JsonArray coords = object.getAsJsonArray("coordinates");
		assertThat(coords.size(), equalTo(2));
		JsonArray firstCoords = coords.get(0).getAsJsonArray();
		assertThat(firstCoords.size(), equalTo(1)); // no interiors
		testPositions(firstCoords.get(0).getAsJsonArray(), new double[][] {
			new double[] { 100.0, 0.0 }, 
			new double[] { 101.0, 0.0 },
			new double[] { 101.0, 1.0 },
			new double[] { 100.0, 1.0 },
			new double[] { 100.0, 0.0 }
		});
		JsonArray secondCoords = coords.get(1).getAsJsonArray();
		assertThat(secondCoords.size(), equalTo(2)); // has interior
		testPositions(secondCoords.get(0).getAsJsonArray(), new double[][] {
			new double[] { 100.0, 0.0 }, 
			new double[] { 101.0, 0.0 },
			new double[] { 101.0, 1.0 },
			new double[] { 100.0, 1.0 },
			new double[] { 100.0, 0.0 }
		});
		testPositions(secondCoords.get(1).getAsJsonArray(), new double[][] {
			new double[] { 100.2, 0.2 },
			new double[] { 100.8, 0.2 },
			new double[] { 100.8, 0.8 },
			new double[] { 100.2, 0.8 },
			new double[] { 100.2, 0.2 }
		});
	}
	
	@Test
	public void encodeGeometryCollection() {
		assertThat(true, equalTo(false));
	}
	
	@Test
	public void encodeMassiveGeometryCollection() {
		assertThat(true, equalTo(false));
	}
	
	@Test
	public void encodeFeature() {
		assertThat(true, equalTo(false));
	}
	
	@Test
	public void encodeFeatureCollection() {
		assertThat(true, equalTo(false));
	}
	
}
