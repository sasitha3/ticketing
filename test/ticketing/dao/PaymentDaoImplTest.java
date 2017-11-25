/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticketing.dao;

import code.testAddData;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import static org.junit.Assert.*;
import ticketing.model.Payment;

/**
 *
 * @author nadika
 */
public class PaymentDaoImplTest {

    public PaymentDaoImplTest() {
    }

    /**
     * Test of CheckValidSmartCard method, of class PaymentDaoImpl.
     */
    @Test
    public void testCheckValidSmartCard() {
        System.out.println("checkValidSmartCard");
        String smartID = "A0:00:F1:A3";
        PaymentDaoImpl instance = new PaymentDaoImpl();
        int expResult = 0;
        int result = instance.checkValidSmartCard(smartID);
        assertEquals(expResult, result);

    }

    /**
     * Test of addPayment method, of class PaymentDaoImpl.
     */
    @Test
    public void testAddPayment() throws SQLException, JSONException {
        System.out.println("addPayment");
        Payment pay = new Payment();

        String InvoiceNo = "SP-111";
        String smartID = "A0:00:F1:A3";
        String type = "Cash";
        String date = "2017-05-25";
        String time = "08:43:54 AM";
        double amount = 10.00;

        pay.setInvoiceNo(InvoiceNo);
        pay.setSmartId(smartID);
        pay.setType(type);
        pay.setDate(date);
        pay.setTime(time);
        pay.setAmount(amount);

        PaymentDaoImpl instance = new PaymentDaoImpl();
        instance.addPayment(pay);

        testAddData td = new testAddData();
        JSONObject js = td.re_paymentTableData();

        assertEquals(InvoiceNo, js.getString("invoice"));
        assertEquals(smartID, js.getString("smart_id"));
        assertEquals(type, js.getString("type"));
        assertEquals(date, js.getString("date"));
        assertEquals(time, js.getString("time"));
        assertEquals(amount, Double.parseDouble(js.getString("re_amount")), 0.0002);
    }

    /**
     * Test of updateAccount method, of class PaymentDaoImpl.
     */
    @Test
    public void testUpdateAccount() throws SQLException, JSONException {
        System.out.println("updateAccount");

        String smartID = "A0:00:F1:A3";
        double recharge_amount = 20.00;

        PaymentDaoImpl instance = new PaymentDaoImpl();

        int expResult = 0;
        int result = instance.updateAccount(smartID, recharge_amount);
        assertEquals(expResult, result);

    }

}
