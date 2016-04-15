package news.caughtup.database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class MySQLDriver {
	private Connection connection;
	private PreparedStatement statement;
	
	public MySQLDriver() {
		if (connection == null) {
			try {
				/* Initialize Connection with database */
				Properties props = new Properties();
				FileInputStream in = new FileInputStream(this.getClass()
						.getResource("db.properties").getFile());
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
				System.out.println("Cannot open properties file to read DB options");
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
	}
	
	public void close() throws SQLException {
        System.out.println("Closing db connection");
        statement.close();
        connection.close();
    }

    protected void finalize() throws Throwable {
        close();
        super.finalize();
    }
}
