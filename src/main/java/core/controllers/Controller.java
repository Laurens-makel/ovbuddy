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
 * @author Laurens M�kel
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
	
	/**
		* Controller
		* 
		* Registers new controller with provided controllerName
		* Instantiates a new model and attaches it to the instance of Controller
		* 
		* @param Model model
		* @return Void
	*/
	
	public Controller(String controllerName) {
		controllers.put(controllerName, this);
		setModel( new Model(controllerName) );
	}
	
	/**
		* setModel
		* 
		* Sets the model for instance of Controller
		* 
		* @param Model model
		* @return Void
	*/
	
	public void setModel(Model model) {
		this.model = model;
	}
	
	/**
		* setModel
		* 
		* Retrieves the model for instance of Controller
		* 
		* @param Model model
		* @return Model
	*/
	
	public Model getModel() {
		return model;
	}
	
	/**
		* setWindow
		* 
		* Sets the window for instance of Controller
		* 
		* @param Stage window
		* @return Void
	*/

	public void setWindow(Stage window) {
		this.window = window;
	}
	
	/**
		* setWindow
		* 
		* Sets the scene for instance of Controller
		* 
		* @param Scene scene
		* @return Void
	*/
	
	public void setScene(Scene scene) {
		this.scene = scene;
		window.setScene(this.scene);
	}
	
	/**
		* setTitle
		* 
		* Sets the title for instance of Controller
		* Attaches title to window attached to instance of Controller
		* 
		* @param String title
		* @return Void
	*/
	
	public void setTitle(String title) {
		this.title = title;
		window.setTitle(title);
	}
	
	/**
		* show
		* 
		* Shows the scene attached to the window attached to instance of Controller
		* 
		* @return Void
	*/
	
	public void show() {
		window.show();
	}
	
	/**
		* getController
		* 
		* Retrieves an instance of Controller according to provided controllerName
		* 
		* @param String controllerName
		* @return Controller
	*/
	
	public static Controller getController(String controllerName) {
		return Controller.controllers.get(controllerName);
	}
	
	/**
		* createScene
		* 
		* Each instance of Controller should implement a method which is responsible for creating a view
		* 
		* @return Void
	*/
	
//	public abstract void createScene();
}
