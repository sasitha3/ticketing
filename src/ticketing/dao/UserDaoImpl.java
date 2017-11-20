/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticketing.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import ticketing.interfaces.UserDAO;
import ticketing.model.StationUser;


/**
 *
 * @author Sasitha
 */
public class UserDaoImpl extends BaseDAO implements UserDAO{
    
    private static final String USER_DETAILS= "SELECT * FROM station_login WHERE username=? AND password=?";

    
    @Override
    public StationUser login(String username, String password) {
        Connection dbConn = getConnection();
        ResultSet rs ;
        StationUser user = null;
        try {
            
            PreparedStatement ps = dbConn.prepareStatement(USER_DETAILS);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
           
            while (rs.next()) {
                user = new StationUser();
                user.setId(rs.getInt(1));
                user.setName(rs.getString(2));
                user.setUsername(rs.getString(3));
                user.setPassword(rs.getString(4));
                user.setUserRole(rs.getString(5));
                user.setStationId(rs.getInt(6));
            }
                     
        } catch(SQLException e){
            System.out.println(e);
          
        } finally{
            closeConnection(dbConn);			
	}
        
        return user; 
    }

    
}
