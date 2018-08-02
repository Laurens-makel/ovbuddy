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
	private Connection conn;
	private JdbcConnection connection;
	private Liquibase liquibase;
	
	/**
		* Database Constructor
		* 
		* Initializes a new database object when jdbc connection string is provided
		* 
		* @param String jdbcString
		* @return Void
		* @exception SQLException On database errors.
		* @exception LiquibaseException On liquibase errors.
	*/

	public Database(String jdbcString) throws LiquibaseException, SQLException {
		conn = DriverManager.getConnection(jdbcString);
		connection = new JdbcConnection(conn);
		initialise();
	}
	
	/**
		* Database Constructor
		* 
		* Initializes a new database object when a Connection is provided
		* 
		* @param Connection c
		* @return Void
		* @exception SQLException On database errors.
		* @exception LiquibaseException On liquibase errors.
	*/
	
	public Database(Connection c) throws LiquibaseException {
		conn = c;
		connection = new JdbcConnection(conn);
		initialise();
	}
	
	/**
		* initialise
		* 
		* Initializes Liquibase
		* 
		* @return Void
		* @exception LiquibaseException On liquibase errors.
	*/
	
	private void initialise() throws LiquibaseException {
		ClassLoaderResourceAccessor resourceOpener = new ClassLoaderResourceAccessor();
		
		liquibase = new Liquibase(CHANGELOG_LOCATION, resourceOpener, connection);
		liquibase.validate();
		liquibase.update(new Contexts());
	}
}
