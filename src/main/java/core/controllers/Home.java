package core.controllers;

import java.io.IOException;

import org.json.simple.parser.ParseException;

import core.models.Location;
import core.models.Model;
import core.services.Geo;
import core.services.OV;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/** Home controller
 * Used as controller for the Home screen
 * 
 * @author Laurens Mäkel
 * @version 0.0.1
 * @since 02-08-2018
*/
public class Home extends Controller {
	private Location current;
	
    @FXML
    private TextField from;

    @FXML
    private TextField to;
    
    @FXML
    private Button search;

    @FXML
    private Button saved;

    @FXML
    private VBox sideBox;

    @FXML
    private Button plan;

    @FXML
    void handleSearch(ActionEvent event) throws IOException, ParseException {
    	String department = from.getText();
    	String destination = to.getText();
    	
    	System.out.println("From ["+ department +"]");
    	System.out.println("To ["+ destination +"]");
    	
    	System.out.println(OV.getReisplan(Location.getLocation(department), Geo.getLocation(destination) ));
    } 

	public Home() throws IOException, ParseException {
		super("Home");
		
		Controller.getModel().addLocation( "current", new Location() );
		current = Controller.getModel().getLocation("current");
	}

	@FXML
	public void initialize() {
	    from.setText(current.getCity() +  ", " + current.getZip());
	}

}
	