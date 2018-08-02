package core.models;

import java.util.ArrayList;
import java.util.HashMap;

/** Base class model
 * Used as a base class for all models within this application
 * 
 * @author Laurens Mäkel
 * @version 0.0.1
 * @since 02-08-2018
*/

public class Model {
	private static HashMap<String, Model> models = new HashMap<String, Model>();
	private HashMap<String, Location> locations = new HashMap<String, Location>();
	
	public Model(String controllerName) {
		Model.models.put(controllerName, this);
	}
	
	public void addLocation(String identifier, Location location) {
		locations.put(identifier, location);
	}
	
	public Location getLocation(String identifier) {
		return locations.get(identifier);
	}
	
	public static Model getModel(String controllerName) {
		return Model.models.get(controllerName);
	}

}
