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
import java.util.ArrayList;
import ticketing.interfaces.CheckDAO;
import ticketing.model.Trip;

/**
 *
 * @author Sasitha
 */
public class CheckDaoImpl extends BaseDAO implements CheckDAO{
    
    private static final String ALL_STATIONS = "SELECT name FROM stations";
    private static final String GET_STATION = "SELECT name FROM stations WHERE station_id=?";

    /**
     * 
     * @param id
     * @return 
     */
    @Override
    public String getStation(int id) {
        Connection dbConn = getConnection();
        String station = null;
        ResultSet rs ;
        try {
            
            PreparedStatement ps = dbConn.prepareStatement(GET_STATION);
            ps.setInt(1, id);
            rs = ps.executeQuery();
           
            while (rs.next()) {
                station = rs.getString(1);
            }
                     
        } catch(SQLException e){
            System.out.println(e);
          
        } finally{
            closeConnection(dbConn);			
	}
        
        return station; 
    }

    /**
     * 
     * @param trip
     * @return 
     */
    @Override
    public boolean addTrip(Trip trip) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * 
     * @return 
     */
    @Override
    public ArrayList<String> getAllStations() {
        Connection dbConn = getConnection();
        ResultSet rs ;
        ArrayList<String> list = new ArrayList<>();
        try {
            
            PreparedStatement ps = dbConn.prepareStatement(ALL_STATIONS);
            
            rs = ps.executeQuery();
           
            while (rs.next()) {
                
                list.add(rs.getString(1));
            }
                     
        } catch(SQLException e){
            System.out.println(e);
          
        } finally{
            closeConnection(dbConn);			
	}
        
        return list; 
    }

    /**
     * 
     * @param smartId
     * @return 
     */
    @Override
    public double getPasangerData(int smartId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * 
     * @param cost
     * @param accountId
     * @return 
     */
    @Override
    public boolean updateAmount(double cost, int accountId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
