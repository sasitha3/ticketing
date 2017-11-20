/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticketing.service;

import ticketing.dao.CheckDaoImpl;
import ticketing.interfaces.CheckDAO;
import ticketing.model.StationUser;

/**
 *
 * @author Sasitha
 */
public class CheckService {
    
    public static StationUser userDetails;
    
    public String getCurrentStation(){
        CheckDAO check = new CheckDaoImpl();
        return check.getStation(userDetails.getStationId());
    }
}
