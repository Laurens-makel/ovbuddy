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
	
	public Location(int longitude, int latitude) {
		// TODO: Add retrieving location according to provided longitude & latitude
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
		* getAddress
		* 
		* Retrieves address of location
		* 
		* @return String
	*/
	
	public String getAddress() {
		return address;
	}
	
	public String toString() {
		return "This location is ["+city+"] in ["+country+"] with the address ["+address+"] and zip ["+zip+"]";
	}
}
