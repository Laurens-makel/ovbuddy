package core.controllers;

import java.util.ArrayList;

import java.util.List;

import core.models.Model;

/** Base class controller
 * Used as a base class for all controllers within this application
 * 
 * @author Laurens Mäkel
 * @version 0.0.1
 * @since 02-08-2018
*/

public abstract class Controller {
	private static List<Controller> controllers = new ArrayList<Controller>();
	protected Model dataModel;
	
	public Controller() {
		controllers.add(this);
	}
	
	public void setModel(Model model) {
		dataModel = model;
	}
	
	public Model getModel() {
		return dataModel;
	}
}
