/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticketing.interfaces;

import ticketing.model.Payment;

/**
 *
 * @author wasana
 */
public interface PaymentDao {

    /**
     *
     * @return
     */
    public String loadInvoice();

    /**
     *
     * @param smartID
     * @return
     */
    public int checkValidSmartCard(String smartID);

    /**
     *
     * @param pay
     * @return
     */
    public int addPayment(Payment pay);

    /**
     *
     * @param smartID
     * @param amount
     * @return
     */
    public int updateAccount(String smartID, double amount);

    /**
     *
     * @param smartID
     * @return
     */
    public String fillPassenger(String smartID);
}
