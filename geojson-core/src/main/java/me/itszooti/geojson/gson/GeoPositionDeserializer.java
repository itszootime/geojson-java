package me.itszooti.geojson.gson;

import java.lang.reflect.Type;

import me.itszooti.geojson.GeoPosition;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;


public class GeoPositionDeserializer implements JsonDeserializer<GeoPosition> {

	@Override
	public GeoPosition deserialize(JsonElement arg0, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
		// a position is an array
		JsonArray array = arg0.getAsJsonArray();
		
		// get x and y
		double x = array.get(0).getAsDouble();
		double y = array.get(1).getAsDouble();
		
		// all done
		return new GeoPosition(x, y);
	}

}
