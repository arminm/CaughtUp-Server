package news.caughtup.database;

import java.sql.SQLException;

public abstract class DBAdapter {
	protected static final int baseIndex = 1;
    protected static MySQLDriver driver = new MySQLDriver();

    protected static void close() throws SQLException {
        driver.close();
    }
}
