package me.itszooti.geojson;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class GeoFeatureCollectionTest {

	private GeoFeatureCollection featureColl;
	
	@Before
	public void before() {
		GeoFeature[] features = new GeoFeature[] {
			new GeoFeature("a_test_feature", new GeoPoint(new GeoPosition(100.0, 0.0))),
			new GeoFeature("another_test_feature", new GeoPoint(new GeoPosition(0.0, 100.0)))
		};
		featureColl = new GeoFeatureCollection(Arrays.asList(features));
	}
	
	@Test
	public void getNumFeatures() {
		assertThat(featureColl.getNumFeatures(), equalTo(2));
	}
	
	@Test
	public void getFeature() {
		// get first
		GeoFeature feature1 = featureColl.getFeature(0);
		assertThat(feature1.getID(), equalTo("a_test_feature"));
		
		// get second
		GeoFeature feature2 = featureColl.getFeature(1);
		assertThat(feature2.getID(), equalTo("another_test_feature"));
	}
	
	@Test
	public void isGeoObject() {
		assertThat(featureColl, instanceOf(GeoObject.class));
	}
	
}
