package news.caughtup.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResourceDBAdapter extends DBAdapter {
	public static Long createResource() throws SQLException{
		PreparedStatement statement = driver.getPreparedStatement("createResource");
		statement.executeUpdate();
		ResultSet result = statement.getGeneratedKeys();
		if(result.next())
		{
			return result.getLong(baseIndex);
		} else {
			return errorId;
		}
	}
}
