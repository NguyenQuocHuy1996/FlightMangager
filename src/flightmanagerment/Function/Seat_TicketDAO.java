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

    ///return 1: trả về tất cả dữ liệu của ghế theo id
    public int getAllSeat(int idFlight) throws SQLException {
        connectDB = new ConnectDB();
        conn = connectDB.getConnect();
        String sql = "select * from seat_ticket where idFlight = ?";
        prest = conn.prepareStatement(sql);
        prest.setInt(1, idFlight);
        rs = prest.executeQuery();
        while (rs.next()) {

            int idSeat = rs.getInt("idSeat");
            String code = rs.getString("code");
            Boolean status = rs.getBoolean("status");
            String firstName = rs.getString("firstName");
            String lastName = rs.getString("lastName");
            String ic_Card = rs.getString("ic_Card");
            int old = rs.getInt("old");

            System.out.println("--------------------");
            System.out.println("idSeat: " + idSeat);
            System.out.println("code: " + code);
            System.out.println("status: " + status);
            System.out.println("firstName: " + firstName);
            System.out.println("lastName: " + lastName);
            System.out.println("ic_Card: " + ic_Card);
            System.out.println("old: " + old);
            System.out.println("idFlight: " + idFlight);
        }
        return 1;
    }

    ///return s: trả về info của ghế theo id
    public Seat_Ticket getSeat(int idSeat) throws SQLException {
        try {
            connectDB = new ConnectDB();
            conn = connectDB.getConnect();
            String sql = "select * from seat_ticket where idSeat = ?";
            prest = conn.prepareStatement(sql);
            prest.setInt(1, idSeat);
            rs = prest.executeQuery();
            while (rs.next()) {
                String code = rs.getString("code");
                Boolean status = rs.getBoolean("status");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String ic_Card = rs.getString("ic_Card");
                int old = rs.getInt("old");
                int idFlight = rs.getInt("idFlight");

                System.out.println("code: " + code);
                System.out.println("status: " + status);
                System.out.println("firstName: " + firstName);
                System.out.println("lastName: " + lastName);
                System.out.println("ic_Card: " + ic_Card);
                System.out.println("old: " + old);
                System.out.println("idFlight: " + idFlight);

                Seat_Ticket s = new Seat_Ticket(idSeat, code, status, firstName, lastName, ic_Card, old, idFlight);
                return s;
            }
        } catch (Exception ex) {
            System.out.println(ex);
            // TODO: handle exception
        } finally {
            if (null != prest) {
                prest.close();
            }
            if (null != conn) {
                conn.close();
            }
        }
        return null;

    }

    ///return status: trả về trạng thái của ghế
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
