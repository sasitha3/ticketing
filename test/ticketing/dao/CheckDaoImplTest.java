/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticketing.dao;

import java.sql.Date;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import ticketing.model.CheckedDet;
import ticketing.model.Trip;

/**
 *
 * @author Sasitha
 */
public class CheckDaoImplTest {
    
    public CheckDaoImplTest() {
    }
    
    

    
    /**
     * Test of addTrip method, of class CheckDaoImpl.
     */
    @Test
    public void testAddTrip() {
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
       boolean value= check.addTrip(trip);

       boolean expResult = true;
     
        assertEquals(expResult, value);
    }


    /**
     * Test of updateAmount method, of class CheckDaoImpl.
     */
    @Test
    public void testUpdateAmount() {
        System.out.println("updateAmount");
        double cost = 20.0;
        String sId = "A0:00:F1:A3";
        CheckDaoImpl instance = new CheckDaoImpl();
        boolean expResult = true;
        boolean result = instance.updateAmount(cost, sId);
        assertEquals(expResult, result);
       
    }



  
    
}
