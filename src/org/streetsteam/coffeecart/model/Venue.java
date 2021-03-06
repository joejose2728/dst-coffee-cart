package org.streetsteam.coffeecart.model;

import java.io.Serializable;

public class Venue implements Serializable{

	private Location location;
	private String name;
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return name + "\n" + location;
	}
	
}
