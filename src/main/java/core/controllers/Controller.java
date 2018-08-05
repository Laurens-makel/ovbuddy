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
	protected static Model model;
	protected static Stage window;
	protected static Scene scene;
	protected static String title;
	
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
	
	public static void setModel(Model model) {
		Controller.model = model;
	}
	
	/**
		* setModel
		* 
		* Retrieves the model for instance of Controller
		* 
		* @param Model model
		* @return Model
	*/
	
	public static Model getModel() {
		return Controller.model;
	}
	
	/**
		* setWindow
		* 
		* Sets the window for instance of Controller
		* 
		* @param Stage window
		* @return Void
	*/

	public static void setWindow(Stage window) {
		Controller.window = window;
	}
	
	/**
		* setWindow
		* 
		* Sets the scene for instance of Controller
		* 
		* @param Scene scene
		* @return Void
	*/
	
	public static void setScene(Scene scene) {
		Controller.scene = scene;
		Controller.getWindow().setScene(scene);
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
	
	public static void setTitle(String title) {
		Controller.title = title;
		Controller.getWindow().setTitle(title);
	}
	
	/**
		* show
		* 
		* Shows the scene attached to the window attached to instance of Controller
		* 
		* @return Void
	*/
	
	public static void show() {
		Controller.getWindow().show();
	}
	
	public static Stage getWindow() {
		return Controller.window;
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
