/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightmanagerment.Function;

import flightmanagerment.Config.ConnectDB;
import flightmanagerment.Model.Flight;
import flightmanagerment.Model.Seat_Flight;
import flightmanagerment.Model.Seat_Ticket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Admin
 */
public class Seat_FlightDAO {

    private static Connection conn = null;
    private static ConnectDB connectDB;
    private static PreparedStatement prest = null;
    private static ResultSet rs;
    private static Statement st;

    public static List<Seat_Flight> getSeatID(int idAccout) throws SQLException {
        try {
            List<Seat_Flight> list = new ArrayList<Seat_Flight>();
            connectDB = new ConnectDB();
            conn = connectDB.getConnect();
            String sql = "select S.idFlight, code, firstName, lastName, ic_card, price from seat_ticket S JOIN flight F ON S.idFlight = F.idFlight\n"
                    + "Where idAccount = ?;";
            prest = conn.prepareStatement(sql);
            prest.setInt(1, idAccout);
            rs = prest.executeQuery();
            while (rs.next()) {
                int idFlight = rs.getInt("idFlight");
                String code = rs.getString("code");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String ic_card = rs.getString("ic_card");
                Double price = rs.getDouble("price");
                Seat_Flight sf = new Seat_Flight(idFlight, ic_card, code, firstName, lastName, price);
                list.add(sf);
            }
            return list;
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

    public static double getPrice(int depart, int year) throws SQLException {
        connectDB = new ConnectDB();
        conn = connectDB.getConnect();
        String sql = "SELECT  Sum(price) as Total\n"
                + "					 FROM seat_ticket S JOIN flight F ON S.idFlight = F.idFlight\n"
                + "					 WHERE status = 1 and month(F.depart) = ?AND YEAR(F.depart) = ?";

        prest = conn.prepareStatement(sql);
        prest.setInt(1, depart);
        prest.setInt(2, year);
        rs = prest.executeQuery();
        while (rs.next()) {
            Double price = rs.getDouble("Total");
            return price;
        }
        return 0;
    }

    public static ObservableList<Seat_Flight> getAllSeat_Flight(int idAccount) throws SQLException {
        connectDB = new ConnectDB();
        conn = connectDB.getConnect();
        ObservableList<Seat_Flight> data = FXCollections.observableArrayList();
        String sql = "SELECT * \n"
                + "FROM seat_ticket S JOIN flight F ON S.idFlight = F.idFlight\n"
                + "WHERE S.idAccount = ?";
        prest = conn.prepareStatement(sql);
        prest.setInt(1, idAccount);
        rs = prest.executeQuery();
        while (rs.next()) {
            int idFlight = rs.getInt("idFlight");
            int idSeat = rs.getInt("idSeat");
            String origin = rs.getString("origin");
            String destination = rs.getString("destination");
            String ic_card = rs.getString("ic_card");
            String firstName = rs.getString("firstName");
            String lastName = rs.getString("lastName");
            String code = rs.getString("code");

            Seat_Flight sf = new Seat_Flight(idFlight, idSeat, origin, destination, ic_card, code, firstName, lastName);
            data.add(sf);
        }
        return data;
    }
}
