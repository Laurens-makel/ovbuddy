package core.models;

import java.util.ArrayList;
import java.util.List;

public abstract class Model {
	private static List<Model> models = new ArrayList<Model>();

	public Model() {
		models.add(this);
	}

}
