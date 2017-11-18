package ticketing.dao;

import java.sql.Connection;
import java.sql.SQLException;

import ticketing.connections.MysqlDbConnManager;

public class BaseDAO {
	
	private Connection connection = null;

	public BaseDAO() {
		this.connection = MysqlDbConnManager.MANAGER.getConnection();
	}

	public Connection getConnection() {
		return this.connection;
	}
	
	public void closeConnection(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}