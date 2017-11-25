/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticketing.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import ticketing.interfaces.CheckDAO;
import ticketing.model.CheckedDet;
import ticketing.model.Trip;

/**
 *
 * @author Sasitha
 */
public class CheckDaoImpl extends BaseDAO implements CheckDAO {

    private static final String ALL_STATIONS = "SELECT name FROM stations";
    private static final String GET_STATION = "SELECT name FROM stations WHERE station_id=?";
    private static final String STATION_ID = "SELECT station_id FROM stations WHERE name=?";
    private static final String UPDATE_AMOUNT = "UPDATE account SET amount=? WHERE aid=(SELECT account_id FROM smartcard WHERE id=?)";
    private static final String CREATE_TRIP = "INSERT INTO trip VALUES(?,?,?,?,?,?,?,?)";
    private static final String GET_PASANGER = "SELECT p.fname,p.lname,a.amount,a.loan from passengers p,account a, smartcard s "
            + "WHERE s.id=? AND a.aid=s.account_id AND a.passenger=p.pid";
    private static final String UPDATE_LOAN = "UPDATE account SET loan=?,amount=0 WHERE aid=(SELECT account_id FROM smartcard WHERE id=?)";
    private static final String GET_COUNT = "SELECT COUNT(tripid) FROM trip WHERE current=? AND date=?";
    
    /**
     *
     * @param id
     * @return
     */
    @Override
    public String getStation(int id) {
        Connection dbConn = getConnection();
        String station = null;
        ResultSet rs;
        try {

            PreparedStatement ps = dbConn.prepareStatement(GET_STATION);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                station = rs.getString(1);
            }

        } catch (SQLException e) {
            System.out.println(e);

        } finally {
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
        Connection dbConn = getConnection();
        
        boolean result = false;
        try {

            PreparedStatement ps = dbConn.prepareStatement(CREATE_TRIP);
            
            ps.setInt(1, 0);
            ps.setString(2, trip.getsID());
            ps.setString(3, trip.getDate());
            ps.setString(4, trip.getTime());
            ps.setInt(5, trip.getCurrent());
            ps.setInt(6, trip.getDestination());
            ps.setDouble(7, trip.getDistance());
            ps.setDouble(8, trip.getCost());
            ps.executeUpdate();
            result = true;

        } catch (SQLException e) {
            System.out.println(e);

        } finally {
            closeConnection(dbConn);
        }
        return result;
    }

    /**
     *
     * @return
     */
    @Override
    public ArrayList<String> getAllStations() {
        Connection dbConn = getConnection();
        ResultSet rs;
        ArrayList<String> list = new ArrayList<>();
        try {

            PreparedStatement ps = dbConn.prepareStatement(ALL_STATIONS);

            rs = ps.executeQuery();

            while (rs.next()) {

                list.add(rs.getString(1));
            }

        } catch (SQLException e) {
            System.out.println(e);

        } finally {
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
    public CheckedDet getPasangerData(String smartId) {    
        Connection dbConn = getConnection();
        CheckedDet data = null;
        ResultSet rs;
        try {

            PreparedStatement ps = dbConn.prepareStatement(GET_PASANGER);
            ps.setString(1, smartId);
            rs = ps.executeQuery();

            while (rs.next()) {
                data = new CheckedDet();
                data.setsId(smartId);
                data.setpName(rs.getString(1) + " " + rs.getString(2));
                if(rs.getDouble(4) == 0){
                    data.setAmount(rs.getDouble(3));
                } else {
                    data.setAmount(rs.getDouble(3) - rs.getDouble(4));
                }            
                
            }

        } catch (SQLException e) {
            System.out.println(e);

        } finally {
            closeConnection(dbConn);
        }
        return data;
    }

    /**
     * @param cost
     * @param sId
     * @return
     */
    @Override
    public boolean updateAmount(double cost, String sId) {
        Connection dbConn = getConnection();
        boolean result = false;
        try {

            PreparedStatement ps = null;
            if(cost < 0){
            ps = dbConn.prepareStatement(UPDATE_LOAN);
            cost = cost * -1;
            } else if(cost >= 0){
            ps = dbConn.prepareStatement(UPDATE_AMOUNT);
            }
            ps.setDouble(1, cost);
            ps.setString(2, sId);
            ps.executeUpdate();
            result = true;

        } catch (SQLException e) {
            System.out.println(e);

        } finally {
            closeConnection(dbConn);
        }
        return result;
    }

    /**
     *
     * @param station
     * @return
     */
    @Override
    public int getStationID(String station) {
        Connection dbConn = getConnection();
        ResultSet rs;
        try {

            PreparedStatement ps = dbConn.prepareStatement(STATION_ID);
            ps.setString(1, station);
            rs = ps.executeQuery();

            while (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println(e);

        } finally {
            closeConnection(dbConn);
        }
        return -1;
    }
    
    /**
     *
     * @param current
     * @return
     */
    @Override
    public int getCount(int current, Date date) {
        Connection dbConn = getConnection();
        int count = 0;
        ResultSet rs;
        try {

            PreparedStatement ps = dbConn.prepareStatement(GET_COUNT);
            ps.setInt(1, current);
            ps.setDate(2, date);
            rs = ps.executeQuery();

            while (rs.next()) {
                count = rs.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println(e);

        } finally {
            closeConnection(dbConn);
        }

        return count;
    }

}
