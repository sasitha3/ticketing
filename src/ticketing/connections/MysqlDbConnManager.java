/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticketing.connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Sasitha
 */
public enum MysqlDbConnManager {

    MANAGER;

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://188.166.187.121:3306/ticketing";
    static final String USER = "wedding";
    static final String PASS = "Wedding@#0ne";

    public Connection getConnection() {
        Connection dbConn = null;

        if (dbConn == null) {
            try {
                Class.forName(JDBC_DRIVER);
                dbConn = DriverManager.getConnection(DB_URL, USER, PASS);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dbConn;
    }

    public void closeConnection(Connection dbConn) {
        try {
            dbConn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closePreparedStatement(PreparedStatement ps) {
        if (ps != null) {

            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    public void closeResultSet(ResultSet rs) {
        if (rs != null) {

            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }
}
