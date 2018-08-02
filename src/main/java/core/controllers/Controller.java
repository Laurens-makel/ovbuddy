package core.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import core.models.Model;
import javafx.stage.Stage;
import javafx.scene.Scene;

/** Base class controller
 * Used as a base class for all controllers within this application
 * 
 * @author Laurens Mäkel
 * @version 0.0.1
 * @since 02-08-2018
*/

public abstract class Controller {
	private static HashMap<String, Controller> controllers = new HashMap<String, Controller>();
	protected static int HEIGHT = 300;
	protected static int WIDTH = 600;
	protected Model model;
	protected Stage window;
	protected Scene scene;
	protected String title;
	
	public Controller(String controllerName) {
		controllers.put(controllerName, this);
		setModel( new Model(controllerName) );
	}
	
	public void setModel(Model model) {
		this.model = model;
	}
	
	public Model getModel() {
		return model;
	}

	public void setWindow(Stage window) {
		this.window = window;
	}
	
	public void setScene(Scene scene) {
		this.scene = scene;
		window.setScene(this.scene);
	}
	
	public void setTitle(String title) {
		this.title = title;
		window.setTitle(title);
	}
	
	public void show() {
		window.show();
	}
	
	public static Controller getController(String controllerName) {
		return Controller.controllers.get(controllerName);
	}
}
