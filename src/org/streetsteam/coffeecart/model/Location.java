package org.streetsteam.coffeecart.model;

import java.io.Serializable;

public class Location implements Serializable {

	private String addressOne;
    private String addressTwo;
    private String city;
    private String region;
    private String postalCode;
    private String country;
    private String latitude;
    private String longitude;
	public String getAddressOne() {
		return addressOne;
	}
	public void setAddressOne(String addressOne) {
		this.addressOne = addressOne;
	}
	public String getAddressTwo() {
		return addressTwo;
	}
	public void setAddressTwo(String addressTwo) {
		this.addressTwo = addressTwo;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	@Override
	public String toString() {
		return  (!addressOne.equals("null") ? addressOne + ", " : "")
				+ (!addressTwo.equals("null") ? (addressTwo + ",\n"):"\n")
				+ city + ", " + region + " " + postalCode
				;
	}
	
	
}
