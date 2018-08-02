package core;

import java.sql.*;

import core.services.Database;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import liquibase.exception.DatabaseException;
import liquibase.exception.LiquibaseException;

/**
* <h1>ovBuddy</h1>
* This is a piece of software which retrieves your current location and nearby public transportation stops.
* 
* <i>ovBuddy helps you plan your travels</i>
* 
* @author  Laurens Mäkel
* @version 0.0.1
* @since   01-08-2018
*/

public class core extends Application {
	private static final String JDBC_STRING = "jdbc:h2:~/database/hDOS2";
	private static Database database;
	private Stage window;
	
	/**
		* This is the main method which starts all services and launches GUI
		* 
		* @param args Unused
		* @return Void
		* @exception SQLException On database errors.
		* @exception LiquibaseException On liquibase errors.
	*/
	
	public static void main(String[] args) throws SQLException, LiquibaseException {
		database = new Database(JDBC_STRING);
		launch(args);
	}
	
	public void start(Stage primaryStage) {
		window = primaryStage;
		
	}
	
	/**
		* stop
		* 
		* Stops the application
		* 
		* @return Void
		* @exception LiquibaseException On liquibase errors.
	*/
	
	public void stop() throws SQLException, DatabaseException {
		System.out.println("Shutting down..");
		Platform.exit();
	}
}
