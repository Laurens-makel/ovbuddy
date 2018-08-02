package core.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import liquibase.Contexts;
import liquibase.Liquibase;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.DatabaseException;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;

/** Database service
 * Represents a database and provides methods to interact with the dataset
 * 
 * @author Laurens Mäkel
 * @version 0.0.1
 * @since 02-08-2018
*/

public class Database {
	private static final String CHANGELOG_LOCATION = "DatabaseChangelog.xml";
	private static Connection conn;
	private static JdbcConnection connection;
	private static Liquibase liquibase;
	
	/**
		* setConnection
		* 
		* Initializes a database when jdbc connection string is provided
		* 
		* @param String jdbcString
		* @return Void
		* @exception SQLException On database errors.
		* @exception LiquibaseException On liquibase errors.
	*/

	public static void setConnection(String jdbcString) throws LiquibaseException, SQLException {
		conn = DriverManager.getConnection(jdbcString);
		connection = new JdbcConnection(conn);
		initialise();
	}
	
	public static synchronized Connection getInstance(){
		return conn;
	}
	
	
	/**
		* initialise
		* 
		* Initializes Liquibase
		* 
		* @return Void
		* @exception LiquibaseException On liquibase errors.
	*/
	
	private static void initialise() throws LiquibaseException {
		ClassLoaderResourceAccessor resourceOpener = new ClassLoaderResourceAccessor();
		
		liquibase = new Liquibase(CHANGELOG_LOCATION, resourceOpener, connection);
		liquibase.validate();
		liquibase.update(new Contexts());
	}
}
