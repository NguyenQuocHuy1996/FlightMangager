/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightmanagerment.Function;

import flightmanagerment.Config.ConnectDB;
import flightmanagerment.Model.Flight;
import flightmanagerment.Model.Seat_Flight;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
