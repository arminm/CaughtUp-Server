package news.caughtup.database;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

public class MySQLDriver {
	private Connection 			connection;
	private static Properties 	sqlProps;

	public MySQLDriver() {
		if (connection == null) {
		    String dbPropsPath = System.getProperty( "catalina.base" ) + "/webapps/db.properties";
			try {
				/* Initialize Connection with database */
				Properties props = new Properties();
				FileInputStream in = new FileInputStream(dbPropsPath);
				
				props.load(in);
				String driverName = props.getProperty("driverName");
				String url = props.getProperty("url");
				String user = props.getProperty("username");
				String passwd = props.getProperty("password");
				Class.forName(driverName).newInstance();
				System.out.println("Opening db connection");
				connection = DriverManager.getConnection(url, user, passwd);
				System.out.println("Connection opened");
			} catch (IOException e) {
				System.out.printf("Cannot open properties file '%s' to read DB options\n", dbPropsPath);
				System.out.println("Catalina Base:"+ System.getProperty( "catalina.base" ));
			} catch (InstantiationException ex) {
				System.err.println("Cannot instantiate a database driver instance.");
				System.err.println(ex);
			} catch (ClassNotFoundException ex) {
				System.err.println("Cannot find the database driver classes.");
				System.err.println(ex);
			} catch (SQLException ex) {
				System.err.println("Cannot connect to this database.");
				System.err.println(ex);
			} catch (IllegalAccessException ex) {
				System.err.println("Illegal argument used.");
				System.err.println(ex);
			}
		}

		if (sqlProps == null) {
			sqlProps = new Properties();
			FileInputStream in;
			String sqlPropsPath = System.getProperty( "catalina.base" ) + "/webapps/caughtup/WEB-INF/classes/SQLQueries.properties";
			try {
				in = new FileInputStream(sqlPropsPath);
				sqlProps.load(in);
			} catch (FileNotFoundException e) {
				System.err.printf("Cannot find properties file '%s' to read SQL Queries.\n", sqlPropsPath);
				System.err.println(e);
			} catch (IOException e) {
				System.err.printf("Cannot open properties file '%s' to read SQL Queries.\n", sqlPropsPath);
				System.err.println(e);
			}
		}
	}

	public PreparedStatement getPreparedStatement(String sqlKey) throws SQLException {
		String query = sqlProps.getProperty(sqlKey);
		return connection.prepareStatement(query);
	}

	public ArrayList<HashMap<String, Object>> executeStatement(PreparedStatement statement) throws SQLException {
		if (connection == null || statement == null) {
			System.err.println("There is no database to execute the query.");
			return null;
		}
		ResultSet resultSet = statement.executeQuery();
		return parseResultSet(resultSet);
	}

	public static ArrayList<HashMap<String,Object>> parseResultSet(ResultSet resultSet) throws SQLException {
		ArrayList<HashMap<String, Object>> rows = new ArrayList<HashMap<String, Object>>();
		ResultSetMetaData metaData = resultSet.getMetaData();
		int numberOfColumns =  metaData.getColumnCount();

		// Get all rows
		while (resultSet.next()) {
			HashMap<String, Object> newRow = new  HashMap<String, Object>();
			// Get all columns
			for (int i = 1; i <= numberOfColumns; i++) {
				String columnName = metaData.getColumnLabel(i);
				newRow.put(columnName, resultSet.getObject(i));
			}
			rows.add(newRow);
		}
		return rows;
	}
	
	public void close() throws SQLException {
		System.out.println("Closing db connection");
		connection.close();
	}

	protected void finalize() throws Throwable {
		close();
		super.finalize();
	}
}
