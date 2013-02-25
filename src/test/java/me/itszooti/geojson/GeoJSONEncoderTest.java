package me.itszooti.geojson;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;

import java.util.Arrays;

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
		assertThat(true, equalTo(false));
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
		assertThat(true, equalTo(false));
	}
	
	@Test
	public void encodePolygon() {
		assertThat(true, equalTo(false));
	}
	
	@Test
	public void encodeMultiPolygon() {
		assertThat(true, equalTo(false));
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
