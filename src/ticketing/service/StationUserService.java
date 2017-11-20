/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticketing.service;

import ticketing.dao.UserDaoImpl;
import ticketing.interfaces.UserDAO;
import ticketing.model.StationUser;

/**
 *
 * @author Sasitha
 */
public class StationUserService {
    
    /**
     * 
     * @param username
     * @param password
     * @return 
     */
    public boolean userLogin(String username, String password){
        boolean result = false;
        UserDAO user = new UserDaoImpl();
        StationUser userStation = null;
        userStation = user.login(username, password);
        if( userStation != null ){
            CheckService.userDetails = userStation;
            result = true;
        }
        
        return result;
    }
}
