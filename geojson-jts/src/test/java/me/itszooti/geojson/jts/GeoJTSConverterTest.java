package me.itszooti.geojson.jts;

import org.junit.Test;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class GeoJTSConverterTest {

	@Test
	public void fromJTS() {
		assertThat(true, equalTo(false));
	}
	
	@Test
	public void toJTSPointType() {
		Geometry geom = GeoJTSConverter.toJTS(geoPoint);
		assertThat(geom, instanceOf(Point.class));
	}
	
	@Test
	public void toJTSPointCoords() {
		Point point = (Point)GeoJTSConverter.toJTS(geoPoint);
		Coordinate coord = point.getCoordinate();
		assertThat(coord.x, equalTo(0));
		assertThat(coord.y, equalTo(0));
	}
	
}
