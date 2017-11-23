/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticketing.calc;

/**
 *
 * @author Sasitha
 */
public class DistanceCalc {

    private static final double PRICE = 10.00;

    /**
     * @param current
     * @param dest
     * @return 
     */
    public static int getDistance(int current, int dest) {
        int distance = dest - current;
        if (distance < 0) {
            distance = distance * -1;
        }
        return distance;
    }

    /**
     * @param current
     * @param dest
     * @return
     */
    public static double getCost(int current, int dest) {
        int distance = dest - current;
        if (distance < 0) {
            distance = distance * -1;
        }
        return PRICE * distance;
    }

}
