/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticketing.dao;

import java.sql.SQLException;
import org.json.JSONException;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import ticketing.model.Trip;

/**
 *
 * @author Sasitha
 */
public class negativeTripTesting {
        @Test
    public void testAddPayment() throws SQLException, JSONException {
        System.out.println("addTrip");
        Trip trip = new Trip();

        int tripId = 1;
        String sid = "E10:00:F1:A3";
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
       boolean value= check.addTrip(trip);

       boolean expResult = false;
     
        assertEquals(expResult, value);
       
    
    }
    
    
 
}
