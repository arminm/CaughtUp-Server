package news.caughtup.database;

import java.sql.SQLException;

/**
 * @author CaughtUp
 * 
 */
public abstract class DBAdapter {
	protected static final int baseIndex = 1;
	protected static final Long errorId = new Long(-1);
	protected static MySQLDriver driver = new MySQLDriver();

	protected static void close() throws SQLException {
		driver.close();
	}
}
