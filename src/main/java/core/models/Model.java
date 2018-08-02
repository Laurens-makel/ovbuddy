package core.models;

import java.util.ArrayList;
import java.util.List;

/** Base class model
 * Used as a base class for all models within this application
 * 
 * @author Laurens Mäkel
 * @version 0.0.1
 * @since 02-08-2018
*/

public class Model {
	private static List<Model> models = new ArrayList<Model>();
	private Location location;
	
	public Model() {
		Model.models.add(this);
	}
	
	public void setLocation(Location location) {
		this.location = location;
	}
	
	public Location getLocation() {
		return location;
	}

}
