
package core.services;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import core.models.Location;

public class OV extends Geo {
	private static final String BASE_URL_9292 = "https://api.9292.nl/0.1";
	
	public static HashMap<String, HashMap > getReisplan(Location from, Location to) throws IOException, ParseException {
		HashMap <String, String> queryParams = new HashMap<String, String>();
		
		queryParams.put("from", getLocationid(from));
		queryParams.put("to", getLocationid(to));
		queryParams.put("before", "1");
		queryParams.put("after", "5");
		queryParams.put("sequence", "1");
		queryParams.put("byBus", "true");
		queryParams.put("byFerry", "true");
		queryParams.put("bySubway", "true");
		queryParams.put("byTram", "true");
		queryParams.put("byTrain", "true");
		queryParams.put("interchangeTime", "standard");
		queryParams.put("realtime", "false");
		queryParams.put("dateTime", getFormattedDate());
		
		String url = buildUri("journeys", queryParams);
		JSONArray journeys = (JSONArray) Http.getJson(url).get("journeys");
		JSONObject firstJourney = (JSONObject) journeys.get(0);
		
		HashMap<String, HashMap> reisplan = new HashMap<String, HashMap>();
		
		HashMap<String, String> reisdetails = new HashMap<String, String>();
		reisdetails.put("departureTime", (String) firstJourney.get("departure"));
		reisdetails.put("arrivalTime", (String) firstJourney.get("arrival"));
		reisplan.put("details", reisdetails);

		JSONArray stops = (JSONArray) firstJourney.get("legs");
		HashMap<String, HashMap> reisstops = new HashMap<String, HashMap>();
		
		for (int i=0; i < stops.size(); i++) {
			JSONObject stop = (JSONObject) stops.get(i);
			HashMap<String, HashMap> reisstop = new HashMap<String, HashMap>();
			HashMap<String, String> reisstopDetails = new HashMap<String, String>();
			
			JSONObject mode = (JSONObject) stop.get("mode");
			reisstopDetails.put("type", (String) mode.get("type"));
			reisstopDetails.put("duration", (String) stop.get("duration"));
			reisstop.put("details", reisstopDetails);
			
			HashMap<String, HashMap> stopLocations = new HashMap<String, HashMap>();
			JSONArray sLocations = (JSONArray) stop.get("stops");
			
			for (int z=0; z < sLocations.size(); z++) {
				JSONObject sLocation = (JSONObject) sLocations.get(z);
				HashMap<String, String> stopLocation = new HashMap<String, String>();
				
				JSONObject sL = (JSONObject) sLocation.get("location");
				stopLocation.put("name", (String) sL.get("name"));
				
				stopLocations.put(z == 0 ? "start" : "stop", stopLocation);
			}
			reisstop.put("locations", stopLocations);
			
			reisstops.put(Integer.toString(i), reisstop);
		}
		reisplan.put("stops", reisstops);
		
		System.out.println(reisplan);
		return reisplan;
	}
	
	private static String getLocationid(Location location) throws IOException, ParseException {
		HashMap <String, String> queryParams = new HashMap<String, String>();
		queryParams.put("latlong", location.getLatlong() );
		
		JSONObject result = Http.getJson( buildUri("locations", queryParams) );
		
		JSONArray ovLocations = (JSONArray) result.get("locations");
		JSONObject ovLocation = (JSONObject) ovLocations.get(0);
		
		return (String) ovLocation.get("id");
	}
	
	private static String getFormattedDate() {
		 LocalDateTime date = LocalDateTime.now();
        String dateTime = date.getYear() +"-";
        dateTime += date.getMonthValue() > 9 ? date.getMonthValue() : "0"+date.getMonthValue();
        dateTime += "-";
        dateTime += date.getDayOfMonth() > 9 ? date.getDayOfMonth() : "0"+date.getDayOfMonth();
        dateTime += "T";
        dateTime += date.getHour() > 9 ? date.getHour() : "0"+date.getHour();
        dateTime += date.getMinute() > 9 ? date.getMinute() : "0"+date.getMinute();
        return dateTime;
	}
	
	
	/**	
	   * Builsa up an URL to contact 9292 with provided path and queryParameters
	   * 
	   * @throws IOException
	   * @throws ParseException
	*/
	
	private static String buildUri(String path, HashMap queryParams){
		String url = BASE_URL_9292 + "/" + path +  "?lang=nl-NL";
		
		Iterator it = queryParams.entrySet().iterator();
	    while (it.hasNext()) {
	    	Map.Entry pair = (Map.Entry)it.next();
	    	
	        url += "&" + pair.getKey() + "=" + pair.getValue();
	        it.remove();
	    }
		
		return url;
	}
	
	/**	
	   * Builsa up an URL to contact 9292 with provided path
	   * 
	   * @return String url
	   * @throws IOException
	   * @throws ParseException
	*/
	
	private static String buildUri(String path){
		String url = BASE_URL_9292 + "/" + path +  "?lang=nl-NL";
		return url;
	}
}
