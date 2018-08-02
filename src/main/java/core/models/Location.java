package core.models;

public class Location {
	private String country;
	private String city;
	private String address;
	private String zip;
	
	public Location(String city, String country, String address, String zip) {
		this.city = city;
		this.country = country;
		this.address = address;
		this.zip = zip;
	}
	
	public Location() {
		// TODO: Add retrieving current location
	}
	
	public String toString() {
		return "This location is ["+city+"] in ["+country+"] with the address ["+address+"] and zip ["+zip+"]";
	}
}
