/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticketing.dao;

import java.sql.SQLException;
import org.json.JSONException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import ticketing.model.Payment;

/**
 *
 * @author nadika
 */
public class negativeTesting {
    
    public negativeTesting() {
    }
/*try to update account by giving wrong smart card ID */
@Test
    public void testUpdateAccount() throws SQLException, JSONException {
        System.out.println("updateAccount negative testing");
        
        
        String smartID = "A10:00:F1:A3";
        double recharge_amount = 20.00;
        
        PaymentDaoImpl instance = new PaymentDaoImpl();
        
        
       int expResult = 1;
        int result = instance.updateAccount(smartID, recharge_amount);
        assertEquals(expResult, result);
        
         
        
    }
    /*ceck smart card Id is not vlidated is performed by giving wrong smart card ID */
   @Test
    public void testCheckValidSmartCard() {
        System.out.println("checkValidSmartCard negative testing");
        String smartID = "A10:00:F1:A3";
        PaymentDaoImpl instance = new PaymentDaoImpl();
        int expResult = 1;
        int result = instance.checkValidSmartCard(smartID);
        assertEquals(expResult, result);
       
    }
    
    @Test
    public void testAddPayment() {
        System.out.println("addPayment negative testing");
        String InvoiceNo = "SP-111";
        String smartID = "50:00:F1:A3";
        String type = "Cash";
        String date = "2017-05-25";
        String time = "08:43:54 AM";
        double amount = 10.00;
        
         Payment pay = new Payment();
        
        pay.setInvoiceNo(InvoiceNo);
        pay.setSmartId(smartID);
        pay.setType(type);
        pay.setDate(date);
        pay.setTime(time);
        pay.setAmount(amount);
        
        
        
        PaymentDaoImpl instance = new PaymentDaoImpl();
         
        int expResult = 1;
        int result = instance.addPayment(pay);
        assertEquals(expResult, result);
       
    }  

}
