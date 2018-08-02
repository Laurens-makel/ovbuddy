package core.models;

import java.util.ArrayList;
import java.util.List;

/** Base class model
 * Used as a base class for all models within this application
 * 
 * @author Laurens M�kel
 * @version 0.0.1
 * @since 02-08-2018
*/

public class Model {
	private static List<Model> models = new ArrayList<Model>();
	public Model() {
		models.add(this);
	}

}
