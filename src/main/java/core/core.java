package core;

import java.sql.*;

import liquibase.Contexts;
import liquibase.Liquibase;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;

public class core {

	public static void main(String[] args) throws SQLException, LiquibaseException {
		Connection conn = DriverManager.getConnection("jdbc:h2:~/database/hDOS2");
		
		ClassLoaderResourceAccessor resourceOpener = new ClassLoaderResourceAccessor();
		Liquibase liquibase = new Liquibase("DatabaseChangelog.xml", resourceOpener, new JdbcConnection(conn));
		liquibase.validate();
		liquibase.update(new Contexts());
		conn.close();
	}

}
