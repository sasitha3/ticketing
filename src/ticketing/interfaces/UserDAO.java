/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticketing.interfaces;

import ticketing.model.StationUser;

/**
 *
 * @author Sasitha
 */
public interface UserDAO {
   public StationUser login(String username, String password);
}
