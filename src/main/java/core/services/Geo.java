package core.services;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import core.models.Location;

public class Geo {
	private static final String BASE_URL_GEO = "http://api.ipstack.com";
	private static final String API_KEY = "6594d28cefb621df70ac0b641de904c1";
	private static final String GEOCODING_API_KEY = "AIzaSyB-H4nZ4I9FwDTIxXROZXMRgktNmhRK3Q4";
	private static final String GEOCODING_BASE_URL = "https://maps.googleapis.com/maps/api/geocode/json";
	private static final String OPEN_GEO_BASE_URL = " https://nominatim.openstreetmap.org/search";
	
	public static Location getLocation() throws IOException, ParseException{
		JSONObject result = Http.getJson( buildUri("check") );
		
		String country = (String) result.get("country_name");
		String city = (String) result.get("city");
		String zip = (String) result.get("zip");
		Double latitude = (Double) result.get("latitude");
		Double longitude = (Double) result.get("longitude");
		
		Location location = new Location(city, country, zip);
		location.setLatitude(latitude);
		location.setLongitude(longitude);

		return location;
	}
	
	public static HashMap<String, HashMap> getLocationMap() throws IOException, ParseException{
		JSONObject result = Http.getJson( buildUri("check") );
		
		String country = (String) result.get("country_name");
		String city = (String) result.get("city");
		String zip = (String) result.get("zip");
		Double latitude = (Double) result.get("latitude");
		Double longitude = (Double) result.get("longitude");
		
		HashMap<String, HashMap> response = new HashMap<String, HashMap>();
		HashMap<String, Double> coordinates = new HashMap<String, Double>();
		HashMap<String, String> details = new HashMap<String, String>();
		
		coordinates.put("latitude", latitude);
		coordinates.put("longitude", longitude); 
		
		details.put("country", country);
		details.put("city", city);
		details.put("zip", zip);
		
		response.put("coordinates", coordinates);
		response.put("details", details);
		
		return response;
	}
	
	public static Location getLocation(String name) throws IOException, ParseException{
//		HashMap<String, String> locationDetails = new HashMap<String, String>();
//		HashMap<String, Double> coordinates = new HashMap<String, Double>();
//		
//		HashMap<String,String> queryParams = new HashMap<String,String>();
//		queryParams.put("address", name);
//		
//		JSONObject result = Http.getJson( buildGeocodingUri(queryParams) );
//		JSONArray results =  (JSONArray) result.get("results");
//		JSONObject r = (JSONObject) results.get(0);
//		
//		JSONArray addressComponents = (JSONArray) r.get("address_components");
//		for(int i = 0; i < addressComponents.size(); i++){
//			JSONObject addressComponent = (JSONObject) addressComponents.get(i);
//			JSONArray types = (JSONArray) addressComponent.get("types");
//			
//			if(types.contains("country")){
//				locationDetails.put("country_name", (String) addressComponent.get("long_name"));
//			}
//			if(types.contains("locality")){
//				locationDetails.put("city", (String) addressComponent.get("long_name"));
//			}
//			if(types.contains("postal_code")){
//				locationDetails.put("zip", (String) addressComponent.get("long_name"));
//			}
//		}
//		
//		JSONObject geometry = (JSONObject) r.get("geometry"); 
//		JSONObject location = (JSONObject) geometry.get("location"); 
//		coordinates.put( "latitude", (Double) location.get("lat") );
//		coordinates.put( "longitude", (Double) location.get("lng") );
//		
//		Location response = new Location(locationDetails.get("city"),locationDetails.get("country"), locationDetails.get("zip"));
//		response.setLatitude(coordinates.get("latitude"));
//		response.setLongitude(coordinates.get("longitude"));
//		
		HashMap<String,String> queryParams = new HashMap<String, String>();
		queryParams.put("q", name);
		queryParams.put("addressdetails", "1");
		
		JSONArray results =  (JSONArray) Http.getJsonArray(buildOpenUri(queryParams));
		JSONObject r = (JSONObject) results.get(0);
		JSONObject address = (JSONObject) r.get("address");
		
		Location location = new Location((String) address.get("city"), (String) address.get("country"), (String) address.get("zip"));
		location.setLatitude(Double.parseDouble((String) r.get("lat")));
		location.setLongitude(Double.parseDouble((String) r.get("lon")));
		
		return location;
	}
	
	private static String buildOpenUri(HashMap<String,String> queryParams) throws UnsupportedEncodingException {
		String url = OPEN_GEO_BASE_URL + "?format=json";
		Iterator it = queryParams.entrySet().iterator();
	    while (it.hasNext()) {
	    	Map.Entry pair = (Map.Entry)it.next();
	    	
	        url += "&" + URLEncoder.encode((String) pair.getKey(), "UTF-8") + "=" + URLEncoder.encode((String) pair.getValue(), "UTF-8");
	        it.remove();
	    }
		
		return url;
	}
	
	private static String buildUri(String path){
		return BASE_URL_GEO + "/" + path + "?access_key=" + API_KEY;
	}
	
	private static String buildGeocodingUri(HashMap<String, String> queryParams) throws UnsupportedEncodingException{
		String url = GEOCODING_BASE_URL + "?key=" + GEOCODING_API_KEY;
		
		Iterator it = queryParams.entrySet().iterator();
	    while (it.hasNext()) {
	    	Map.Entry pair = (Map.Entry)it.next();
	    	
	        url += "&" + URLEncoder.encode((String) pair.getKey(), "UTF-8") + "=" + URLEncoder.encode((String) pair.getValue(), "UTF-8");
	        it.remove();
	    }
		
		return url;
	}
	
}
