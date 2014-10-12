package org.streetsteam.coffeecart.model;

public class Event {
	
	private String apiEventId;
	private String eventName;
	private String description;
	private String start;
	private String end;
	private String capacity;
	private Venue venue;
	
	public String getApiEventId() {
		return apiEventId;
	}
	public void setApiEventId(String apiEventId) {
		this.apiEventId = apiEventId;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Venue getVenue() {
		return venue;
	}
	public void setVenue(Venue venue) {
		this.venue = venue;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public String getCapacity() {
		return capacity;
	}
	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	//venue string dto field
	private String venueString;

	public String getVenueString() {
		return venueString;
	}
	public void setVenueString(String venueString) {
		this.venueString = venueString;
	}
	@Override
	public String toString() {
		return "Event [apiEventId=" + apiEventId + ", eventName=" + eventName
				+ ", description=" + description + ", start=" + start
				+ ", end=" + end + ", capacity=" + capacity + ", venue="
				+ venue + "]";
	}
}
