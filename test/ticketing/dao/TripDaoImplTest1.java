/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticketing.dao;

import code.tripAddData;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import static org.junit.Assert.*;
import ticketing.model.Trip;

/**
 *
 * @author nadika
 */
public class TripDaoImplTest1 {
    
    public TripDaoImplTest1() {
    }
    
    
   
 
    /**
     * Test of addPayment method, of class PaymentDaoImpl.
     */
    @Test
    public void testAddPayment() throws SQLException, JSONException {
        System.out.println("addTrip");
        Trip trip = new Trip();
        
        int tripId = 1;
        String sid = "A0:00:F1:A3";
        String date = "2017-11-25";
        String time = "00:16:12";
        int current = 1;
        int destination = 4;
        double distance = 3;
        double cost = 30;
        
        trip.setTripID(tripId);
        trip.setsID(sid);
        trip.setDate(date);
        trip.setTime(time);
        trip.setCurrent(current);
        trip.setDestination(destination);
        trip.setDistance(distance);
        trip.setCost(cost);
     
        CheckDaoImpl check = new CheckDaoImpl();
        check.addTrip(trip);
        
        tripAddData td = new tripAddData();
        JSONObject js = td.tripTableData();
        
        
        assertEquals(tripId, js.getString("tripId"));
        assertEquals(sid, js.getString("smart_id"));
        assertEquals(date, js.getString("date"));
        assertEquals(time, js.getString("time"));
        assertEquals(current, Integer.parseInt(js.getString("re_amount")));
        assertEquals(destination, Integer.parseInt(js.getString("destination")));
        assertEquals(distance, Double.parseDouble(js.getString("distance")), 0.0002);
        assertEquals(cost, Double.parseDouble(js.getString("cost")), 0.0002);
    }
   
}
