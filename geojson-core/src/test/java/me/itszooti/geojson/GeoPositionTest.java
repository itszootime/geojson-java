package me.itszooti.geojson;

import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class GeoPositionTest {

	private GeoPosition position;
	
	@Before
	public void before() {
		position = new GeoPosition(100.0, 50.0);
	}
	
	@Test
	public void getX() {
		assertThat(position.getX(), equalTo(100.0));
	}
	
	@Test
	public void getY() {
		assertThat(position.getY(), equalTo(50.0));
	}
	
	@Test
	public void equals() {
		assertThat(position, equalTo(new GeoPosition(100.0, 50.0)));
	}
	
}
