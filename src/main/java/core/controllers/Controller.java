package core.controllers;

import java.util.ArrayList;
import java.util.List;

import core.models.Model;

public abstract class Controller {
	private static List<Controller> controllers = new ArrayList<Controller>();
	protected Model dataModel;
	
	public Controller() {
		controllers.add(this);
	}
	
	public void setModel(Model model) {
		dataModel = model;
		
		initModel(); // Automatically init 
	}
	
	public void setModel(Model model, Boolean cancelInit) {
		dataModel = model;
		
		if(!cancelInit)
			initModel(); // Automatically init
	}
	
	public Model getModel() {
		return dataModel;
	}
	
	protected abstract void initModel();

}
