/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticketing.interfaces;

import ticketing.model.Payment;

/**
 *
 * @author nadika
 */
public interface PaymentDao {

    public String loadInvoice();

    public int checkValidSmartCard(String smartID);

    public int addPayment(Payment pay);

    public int updateAccount(String smartID, double amount);

    public String fillPassenger(String smartID);
}
