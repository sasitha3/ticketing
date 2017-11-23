package ticketing.dao;

import java.sql.Connection;
import java.sql.SQLException;

import ticketing.connections.MysqlDbConnManager;

public class BaseDAO {
	
	public Connection getConnection() {
		return MysqlDbConnManager.MANAGER.getConnection();
	}
	
	public void closeConnection(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}