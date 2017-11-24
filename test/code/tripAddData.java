/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;
import ticketing.dao.BaseDAO;


public class tripAddData extends BaseDAO {
    PreparedStatement pst = null;
    ResultSet rs = null;
    Connection dbConn = getConnection();

    JSONObject jsonObject = new JSONObject();

    public JSONObject tripTableData() throws SQLException, JSONException {

        int tripId = 1;
        String sid = "";
        String date = "";
        String time = "";
        int current = 0;
        int destination = 0;
        double distance = 0;
        double cost = 0;

        String payment = "SELECT * FROM trip ORDER BY tripid DESC LIMIT 1";
        pst = dbConn.prepareStatement(payment);
        rs = pst.executeQuery();

        while (rs.next()) {
            tripId = rs.getInt(1);
            sid = rs.getString(2);
            date = rs.getString(3);
            time = rs.getString(4);
            current = rs.getInt(5);
            destination = rs.getInt(6);
            distance = rs.getDouble(7);
            cost = rs.getDouble(8);

        }
        jsonObject.put("tripId", tripId);
        jsonObject.put("smart_id", sid);
        jsonObject.put("date", date);
        jsonObject.put("time", time);
        jsonObject.put("current", current);
        jsonObject.put("destination", destination);
        jsonObject.put("distance", distance);
        jsonObject.put("cost", cost);

        System.out.println(jsonObject);
        return jsonObject;

    }

}
