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


public class testAddData extends BaseDAO {
    PreparedStatement pst = null;
    ResultSet rs = null;
    Connection dbConn = getConnection();

    JSONObject jsonObject = new JSONObject();

    public JSONObject re_paymentTableData() throws SQLException, JSONException {

        String InvoiceNo = "";
        String smartID = "";
        String type = "";
        String date = "";
        String time = "";
        double re_amount = 0;

        String payment = "SELECT invoice,smart_id,type,date,time,re_amount "
                + "FROM re_payment ORDER BY pid DESC LIMIT 1";
        pst = dbConn.prepareStatement(payment);
        rs = pst.executeQuery();

        while (rs.next()) {
            InvoiceNo = rs.getString("invoice");
            smartID = rs.getString("smart_id");
            type = rs.getString("type");
            date = rs.getString("date");
            time = rs.getString("time");
            re_amount = Double.parseDouble(rs.getString("re_amount"));

        }
        jsonObject.put("invoice", InvoiceNo);
        jsonObject.put("smart_id", smartID);
        jsonObject.put("type", type);
        jsonObject.put("date", date);
        jsonObject.put("time", time);
        jsonObject.put("re_amount", re_amount);

        System.out.println(jsonObject);
        return jsonObject;

    }

}
