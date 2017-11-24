/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticketing.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import ticketing.calc.DistanceCalc;
import ticketing.dao.CheckDaoImpl;
import ticketing.interfaces.CheckDAO;
import ticketing.model.CheckedDet;
import ticketing.model.StationUser;
import ticketing.model.Trip;

/**
 *
 * @author Sasitha
 */
public class CheckService {

    public static StationUser userDetails;

    /**
     *
     * @return
     */
    public String getCurrentStation() {
        CheckDAO check = new CheckDaoImpl();
        return check.getStation(userDetails.getStationId());
    }

    /**
     *
     * @param sId
     * @return
     */
    public CheckedDet validateCard(String sId) {

        CheckDAO getPas = new CheckDaoImpl();
        CheckedDet check = getPas.getPasangerData(sId);
        return check;
    }

    /**
     *
     * @param trip
     * @param balance
     * @return
     */
    public boolean verifyTrip(Trip trip, double balance) {

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        String dateNtime = dateFormat.format(date);
        String dateTime[];
        dateTime = dateNtime.split(" ");
        trip.setDate(dateTime[0]);
        trip.setTime(dateTime[1]);
        CheckDAO verify = new CheckDaoImpl();
        trip.setCurrent(userDetails.getStationId());
        verify.addTrip(trip);
        verify.updateAmount(balance, trip.getsID());
        return true;
    }

    /**
     *
     * @param station
     * @return
     */
    public int getStationId(String station) {
        CheckDAO check = new CheckDaoImpl();
        return check.getStationID(station);
    }

    /**
     *
     * @param destination
     * @return
     */
    public int getDistance(String destination) {
        return DistanceCalc.getDistance(userDetails.getStationId(), getStationId(destination));
    }

    /**
     *
     * @param destination
     * @return
     */
    public double getCost(String destination) {
        return DistanceCalc.getCost(userDetails.getStationId(), getStationId(destination));
    }

    /**
     *
     * @param current
     * @return
     */
    public int getCount(int current) {

        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        CheckDAO check = new CheckDaoImpl();
        
        return check.getCount(current, sqlDate);
    }

}
