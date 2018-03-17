/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightmanagerment.Function;

import flightmanagerment.Config.ConnectDB;
import flightmanagerment.Model.Seat_Ticket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class Seat_TicketDAO {

    private static Connection conn = null;
    private static ConnectDB connectDB;
    private static PreparedStatement prest;
    private static ResultSet rs;

    public static boolean getInfoSeat(int idSeat) throws SQLException {
        try {
            connectDB = new ConnectDB();
            conn = connectDB.getConnect();
            String sql = "select * from seat_ticket where idSeat = ?";
            prest = conn.prepareStatement(sql);
            prest.setInt(1, idSeat);
            rs = prest.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(idSeat);
                String code = rs.getString("code");
                Boolean status = rs.getBoolean("status");
                return status;
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (null != prest) {
                prest.close();
            }
            if (null != conn) {
                conn.close();
            }
            return false;
        }
    }
}
