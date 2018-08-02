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

public class Database {
	private static final String CHANGELOG_LOCATION = "DatabaseChangelog.xml";
	private Connection conn;
	private JdbcConnection connection;
	private Liquibase liquibase;

	public Database(String JdbcString) throws LiquibaseException, SQLException {
		Connection conn = DriverManager.getConnection(JdbcString);
		connection = new JdbcConnection(conn);
		initialise();
	}
	
	public Database(Connection c) throws LiquibaseException {
		conn = c;
		connection = new JdbcConnection(conn);
		initialise();
	}
	
	public Database(JdbcConnection c) throws LiquibaseException {
		connection = c;
		initialise();
	}
	
	private void initialise() throws LiquibaseException {
		ClassLoaderResourceAccessor resourceOpener = new ClassLoaderResourceAccessor();
		
		Liquibase liquibase = new Liquibase(CHANGELOG_LOCATION, resourceOpener, connection);
		liquibase.validate();
		liquibase.update(new Contexts());
	}
	
	public void exit() throws SQLException, DatabaseException {
		conn.close();
		connection.close();
	}

}
