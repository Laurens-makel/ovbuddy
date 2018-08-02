package core;

import java.sql.*;

import core.services.Database;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
	}
}
