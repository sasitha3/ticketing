/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticketing.model;

/**
 *
 * @author Sasitha
 */
public class CheckedDet {
     
    private String pName;
    private double amount;

    /**
     * @return the getAmount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * @param amount the availableBalance to set
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * @return the pName
     */
    public String getpName() {
        return pName;
    }

    /**
     * @param pName the pName to set
     */
    public void setpName(String pName) {
        this.pName = pName;
    }

}
