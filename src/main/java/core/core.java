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

public class core extends Application {
	private static final String JDBC_STRING = "jdbc:h2:~/database/hDOS2";
	private Database database;
	private Stage window;
	
	public static void main(String[] args) throws SQLException, LiquibaseException {
		Database database = new Database(JDBC_STRING);
		launch(args);
	}
	
	public void start(Stage primaryStage) {
		window = primaryStage;
		
		window.setOnCloseRequest(e -> {
			try {
				Platform.exit();
				stop();
			} catch (SQLException | DatabaseException err) {
				System.out.println("An error occured while closing the database connection");
				err.printStackTrace();
			}
		});
	}
	
	public void stop() throws SQLException, DatabaseException {
		database.exit();
	}
}
