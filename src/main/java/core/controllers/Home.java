package core.controllers;

import core.models.Location;
import core.models.Model;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;

/** Home controller
 * Used as controller for the Home screen
 * 
 * @author Laurens Mäkel
 * @version 0.0.1
 * @since 02-08-2018
*/
public class Home extends Controller {

	public Home() {
		super("Home");
		model.addLocation( "Current", new Location() );
	}

}
