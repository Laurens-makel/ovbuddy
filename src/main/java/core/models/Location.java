package core.models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.parser.ParseException;

import core.services.Geo;

public class Location {
	private static HashMap<String, Location> locations = new HashMap<String, Location>();
	private String country;
	private String city;
	private String zip;
	private Double longitude;
	private Double latitude;
	
	public Location(String city, String country, String zip) {
		this.city = city;
		this.country = country;
		this.zip = zip;
		
		System.out.println(toString());
		Location.locations.put(city +", " + zip, this);
		// TODO: Add retrieving location longitude and latitude by provided info
	}
	
	public Location() throws IOException, ParseException {
		HashMap<String, HashMap> geoLocation = Geo.getLocationMap();

		HashMap<String, Double> coordinates = geoLocation.get("coordinates");
		this.latitude = coordinates.get("latitude");
		this.longitude = coordinates.get("longitude");
		
		HashMap<String, String> details = geoLocation.get("details");
		this.country = details.get("country");
		this.city = details.get("city");
		this.zip = details.get("zip");
		
		System.out.println(this.city +", " + this.zip);
		Location.locations.put(this.city +", " + this.zip, this);
	}
	
	public Location(int longitude, int latitude) {
		// TODO: Add retrieving location according to provided longitude & latitude
	}
	
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	
	
	/**
		* getCity
		* 
		* Retrieves city of location
		* 
		* @return String
	*/
	
	public String getCity() {
		return city;
	}
	
	
	/**
		* getCountry
		* 
		* Retrieves country of location
		* 
		* @return String
	*/
	
	public String getCountry() {
		return country;
	}
	
	
	/**
		* getZip
		* 
		* Retrieves zip of location
		* 
		* @return String
	*/
	
	public String getZip() {
		return zip;
	}
	
	
	/**	
	   * Retrieves latlong for current location
	   * 
	   * @return String latLong
	*/
	
	public String getLatlong(){
		return latitude + "," + longitude;
	}
	
	public static Location getLocation(String identifier) {
		return locations.get(identifier);
	}
	
	public String toString() {
		return "This location is ["+city+"] in ["+country+"] with the zip ["+zip+"] with latitude ["+latitude+"] and longitude ["+longitude+"]";
	}
}
