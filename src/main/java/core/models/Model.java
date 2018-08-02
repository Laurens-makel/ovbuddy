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
	
	/**
		* Model
		* 
		* Registers new model with provided controllerName
		* 
		* @return Void
	*/
	
	public Model(String controllerName) {
		Model.models.put(controllerName, this);
	}
	
	
	/**
		* addLocation
		* 
		* Registers a new location within the model with the provided identifier
		* 
		* @param String identifier
		* @param Location location
		* @return Void
	*/
	
	public void addLocation(String identifier, Location location) {
		locations.put(identifier, location);
	}
	
	
	/**
		* getLocation
		* 
		* Retrieves a location within the model with the provided identifier
		* 
		* @param String identifier
		* @return Location
	*/
	public Location getLocation(String identifier) {
		return locations.get(identifier);
	}
	
	
	/**
		* getController
		* 
		* Retrieves an instance of Model according to provided controllerName
		* 
		* @param String controllerName
		* @return Model
	*/
	
	public static Model getModel(String controllerName) {
		return Model.models.get(controllerName);
	}

}
