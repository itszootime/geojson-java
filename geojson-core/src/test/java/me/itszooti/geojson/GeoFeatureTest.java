package me.itszooti.geojson;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.notNullValue;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class GeoFeatureTest {

	private GeoFeature feature;
	
	@Before
	public void before() {
		feature = new GeoFeature("a_test_feature", new GeoPoint(new GeoPosition(100.0, 0.0)));
		feature.setProperty("number", 2);
		feature.setProperty("letter", "B");
	}
	
	@Test
	public void getId() {
		assertThat(feature.getId(), equalTo("a_test_feature"));
	}
	
	@Test
	public void getGeometry() {
		GeoGeometry geometry = feature.getGeometry();
		assertThat(geometry, notNullValue());
		assertThat(geometry, instanceOf(GeoPoint.class));
	}
	
	@Test
	public void getProperty() {
		assertThat(feature.getProperty("number"), equalTo((Object)2));
		assertThat(feature.getProperty("letter"), equalTo((Object)"B"));
	}
	
	@Test
	public void getPropertyNames() {
		Set<String> names = feature.getPropertyNames();
		assertThat(names.size(), equalTo(2));
		assertThat(names.contains("number"), equalTo(true));
		assertThat(names.contains("letter"), equalTo(true));
	}
	
	@Test
	public void isGeoObject() {
		assertThat(feature, instanceOf(GeoObject.class));
	}
	
}
