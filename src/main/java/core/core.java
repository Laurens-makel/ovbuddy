package core;

import java.sql.*;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import liquibase.exception.LiquibaseException;

public class core extends Application {
	private Stage window;
	
	public static void main(String[] args) throws SQLException, LiquibaseException {
		launch(args);
	}
	
	public void start(Stage primaryStage) {
		window = primaryStage;
	}
}
