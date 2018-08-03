package core.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class Http {
	
	public static JSONObject getJson(URL providedUrl) throws IOException, ParseException {
	    HttpURLConnection conn = (HttpURLConnection) providedUrl.openConnection();
	    conn.setRequestMethod("GET");
	    
	    String result = "";
	    BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	    String line;
	    while ((line = rd.readLine()) != null) {
	    	result += line;
	    }
	    rd.close();
      	
	    JSONParser jsonParser = new JSONParser();
	    JSONObject jsonResult = (JSONObject) jsonParser.parse(result);
	    
		return jsonResult;
	}

	public static JSONObject getJson(String providedUrl) throws IOException, ParseException{
		URL url = new URL(providedUrl); // Turn string into URL
		return getJson(url);
	}
}
