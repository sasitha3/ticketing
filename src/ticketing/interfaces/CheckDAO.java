/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticketing.interfaces;

import java.util.ArrayList;
import ticketing.model.Trip;

/**
 *
 * @author Sasitha
 */
public interface CheckDAO {
    
    /**
     * Station name get from the station id
     * @param id
     * @return 
     */
    public String getStation(int id);
     
    /**
     * 
     * @return 
     */
    public ArrayList<String> getAllStations();
    
    /**
     * 
     * @param smartId
     * @return 
     */
    public double getPasangerData(int smartId);
    
    /**
     * 
     * @param trip
     * @return 
     */
    public boolean addTrip(Trip trip);
    
    /**
     * 
     * @param cost
     * @param accountId
     * @return 
     */
    public boolean updateAmount(double cost, int accountId);
    
    
}
