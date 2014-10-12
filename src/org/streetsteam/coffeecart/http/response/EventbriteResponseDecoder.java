package org.streetsteam.coffeecart.http.response;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.streetsteam.coffeecart.model.Event;
import org.streetsteam.coffeecart.model.Location;
import org.streetsteam.coffeecart.model.Venue;

public class EventbriteResponseDecoder {

	public List<Event> decode(JSONObject json) throws JSONException{
		JSONArray events = json.getJSONArray("events");
		int length = events.length();
		List<Event> eventList = new ArrayList<Event>();
		for (int i=0; i<length; i++){
			Event newEvent = new Event();
			JSONObject event = events.getJSONObject(i);
			newEvent.setApiEventId(event.getString("id"));
			newEvent.setEventName(event.getJSONObject("name").getString("text"));
			newEvent.setCapacity(event.getString("capacity"));
			newEvent.setStart(event.getJSONObject("start").getString("local"));
			newEvent.setEnd(event.getJSONObject("end").getString("local"));
			newEvent.setDescription(event.getJSONObject("description").getString("text"));
			
			Venue venue = new Venue();
			JSONObject venueJson = event.getJSONObject("venue"); 
			venue.setName(venueJson.getString("name"));
			
			JSONObject locationJson = venueJson.getJSONObject("location");
			Location location = new Location();
			location.setAddressOne(locationJson.getString("address_1"));
			location.setAddressTwo(locationJson.getString("address_2"));
			location.setLatitude(locationJson.getString("latitude"));
			location.setLongitude(locationJson.getString("longitude"));
			location.setCity(locationJson.getString("city"));
			location.setCountry(locationJson.getString("country"));
			location.setRegion(locationJson.getString("region"));
			location.setPostalCode(locationJson.getString("postal_code"));
			venue.setLocation(location);
			newEvent.setVenue(venue);
			eventList.add(newEvent);
		}
		return eventList;
	}
}
