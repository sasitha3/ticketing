package ticketing.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import ticketing.interfaces.PaymentDao;
import ticketing.model.Payment;

/**
 *
 * @author nadika
 */
public class PaymentDaoImpl extends BaseDAO implements PaymentDao {

    PreparedStatement pst = null;
    ResultSet rs = null;
    Connection dbConn = getConnection();

    @Override
    public String loadInvoice() {
        String next = "";
        int no = 0;

        String sql = "SELECT pid from re_payment ORDER BY pid DESC";
        try {
            pst = (PreparedStatement) dbConn.prepareStatement(sql);
            rs = pst.executeQuery();

            if (!rs.first()) {
                no = 1;

            } else {

                rs.first();
                no = Integer.parseInt(rs.getString(1));
                no++;
            }

            String previous = Integer.toString(no);

            next = "SP-" + previous;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return next;
    }

    @Override
    public int checkValidSmartCard(String smartID) {

        String sql = "SELECT id FROM smartcard";
        try {

            pst = (PreparedStatement) dbConn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                if (smartID.equals(rs.getString(1))) {
                    return 0;
                }//the smart ID 
            }
          //  System.out.println(rs.getString(1));
            return 1;//the smart card id does not provided already exists in the db
 
        } catch (Exception e) {
            System.out.println(e);
            return 2;
        }
    }

    @Override
    public int addPayment(Payment pay) {
        try {

            String pid = pay.getInvoiceNo();
            String sid = pay.getSmartId();
            String date = pay.getDate();
            String time = pay.getTime();
            String type = pay.getType();
            double amount = pay.getAmount();

            if (updateAccount(sid, amount) == 0) {
                String sql = "INSERT INTO re_payment(invoice,smart_id,date,time,type,re_amount)VALUES('" + pid + "','" + sid + "','" + date + "','" + time + "','" + type + "','" + amount + "')";
                pst = (PreparedStatement) dbConn.prepareStatement(sql);
                pst.executeUpdate();//execute the sql query and insert the value to the DB table
                return 0;
            } else {
                return 1;
            }

        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                System.out.println("gotError");
                return 1;
            }//get the error code and check whethere the primary key is duplicate and return 1
            else {
                System.out.println(e);
            }//else print the exception 

        }
        return -1;
    }

    @Override
    public int updateAccount(String smartID, double recharge_amount) {

        int daid;
        double damount = 0, amount = 0;
        double dloan = 0;

        String sql = "SELECT aid,amount,loan FROM smartcard s,account a where s.account_id=a.aid and s.id='" + smartID + "'";
        try {

            pst = (PreparedStatement) dbConn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                daid = rs.getInt(1);
                damount = rs.getDouble(2);
                dloan = rs.getDouble(3);
            }

            if (dloan > 0 && damount == 0) {
                amount = recharge_amount - dloan;
                if (amount >= 0) {
                    dloan = 0;
                } else {
                    dloan = -(amount);
                    amount = 0;

                }

            } else {
                amount = damount + recharge_amount;
            }

            String query = "UPDATE account SET amount='" + amount + "',loan='" + dloan + "' ";

            pst = (PreparedStatement) dbConn.prepareStatement(query);

            pst.executeUpdate();

            return 0;
        } catch (SQLException ex) {
            System.out.println(ex);
            return 1;

        }

    }

    @Override
    public String fillPassenger(String smartID) {

        String sql = "SELECT fname,lname FROM smartcard s,account a,passengers p where s.account_id=a.aid and a.passenger=p.pid    and  s.id='" + smartID + "'";

        String fname = "", lname = "";
        try {

            pst = (PreparedStatement) dbConn.prepareStatement(sql);
            rs = pst.executeQuery();
           
            
            while (rs.next()) {

                fname = rs.getString(1);
                lname = rs.getString(2);

            }
           

        } catch (SQLException ex) {
            System.out.println(ex);
            
            return "fail";
        }

        String fullname = fname + " " + lname;
        return fullname;
    }

}
