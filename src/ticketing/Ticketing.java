/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticketing;

import ticketing.connections.SerialConnenction;
import ticketing.ui.login;

/**
 *
 * @author Sasitha
 */
public class Ticketing {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        login log = new login();
        log.setVisible(true);
        
    }
    
}
