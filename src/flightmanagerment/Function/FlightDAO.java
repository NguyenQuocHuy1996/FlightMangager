/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightmanagerment.Function;

import flightmanagerment.Config.ConnectDB;
import flightmanagerment.Model.Flight;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class FlightDAO {

    private static Connection conn = null;
    private static ConnectDB connectDB;
    private static PreparedStatement prest = null;
    private static String confirm_password = null;
    private static ResultSet rs;

    ///return -1: thiếu điểm đi, return -2: thiếu điểm đến, return -3: thiếu thời gian đi, return -4: thiếu thời gian về, return -5: thiếu số hành khách, return -6: thiếu thương hiệu máy bay, return -7: thiếu mã số chuyến bay
    public static int insert(Flight flight) throws SQLException {
        if ((flight.getOrigin().equals("")) || (flight.getOrigin() == null)) {
            return -1;
        } else if (flight.getDestination().equals("") || flight.getDestination() == null) {
            return -2;
        } else if (flight.getDepart().equals("") || flight.getDepart() == null) {
            return -3;
        } else if (flight.getReturn().equals("") || flight.getReturn() == null) {
            return -4;
        } else if (flight.getPassenger() == 0) {
            return -5;
        } else if (flight.getBrand().equals("") || flight.getBrand() == null) {
            return -6;
        } else if (flight.getFlight_number().equals("") || flight.getFlight_number() == null) {
            return -7;
        } else {
            try {
                connectDB = new ConnectDB();
                conn = connectDB.getConnect();
                String sql = " insert into customer (origin, destination, depart, _return, passenger, brand, flight_number)"
                        + " values (?,?,?,?,?,?,?)";
                // st = connection.createStatement();

                prest = conn.prepareStatement(sql);
                prest.setString(1, flight.getOrigin());
                prest.setString(2, flight.getDestination());
                prest.setDate(3, flight.getDepart());
                prest.setDate(4, flight.getReturn());
                prest.setInt(5, flight.getPassenger());
                prest.setString(6, flight.getBrand());
                prest.setString(7, flight.getFlight_number());
                prest.execute();
                if (null != prest) {
                    prest.close();
                }
                if (null != conn) {
                    conn.close();
                }

                System.out.println("Thêm thành công");
                return 1;

            } catch (Exception e) {
                System.out.println(e);
                // TODO: handle exception
            } finally {
                if (null != prest) {
                    prest.close();
                }
                if (null != conn) {
                    conn.close();
                }
                return 0;
            }
        }
    }

//    public static List<Flight> searchFlight(String origin, String destination, Date depart) throws SQLException {
//        try {
//
//            connectDB = new ConnectDB();
//            conn = connectDB.getConnect();
//            String sql = "select  * from flight where origin = ? and destination = ? and depart = ? limit 10";
//            prest = conn.prepareStatement(sql);
//            prest.setString(1, origin);
//            prest.setString(2, destination);
//            prest.setDate(3, depart);
//            rs = prest.executeQuery();
//            while (rs.next()) {
//                int id = rs.getInt("idFlight");
//                String _origin = rs.getString(origin);
//                String _destination = rs.getString(destination);
//                //Date _depart = rs.getDate(depart);// getDate trả về kiểu int or String
//                Date _return = rs.getDate("_return");
//                int _passenger = rs.getInt("passenger");
//                String _brand = rs.getString("brand");
//                String _flight_number = rs.getString("flight_number");
//                System.out.println("id: " + id);
//                System.out.println("origin: " + _origin);
//                System.out.println("destination: " + _destination);
//                //System.out.println("depart: " + _depart);
//                System.out.println("return: " + _return);
//                System.out.println("passenger: " + _passenger);
//                System.out.println("brand: " + _brand);
//                System.out.println("flight_number: " + _flight_number);
//            }
//            ArrayList<Flight> list = new ArrayList<>();
//
//            if (null != prest) {
//                prest.close();
//            }
//            if (null != conn) {
//                conn.close();
//            }
//            return list;
//        } catch (Exception e) {
//            System.out.println(e);
//        } finally {
//            if (null != prest) {
//                prest.close();
//            }
//            if (null != conn) {
//                conn.close();
//            }
//            return null;
//        }
//    }

    public static Flight getInfoFlight(int idFlight) throws SQLException {
        try {
            connectDB = new ConnectDB();
            conn = connectDB.getConnect();
            String sql = "select * from flight where idAccount = ?";
            prest = conn.prepareStatement(sql);
            prest.setInt(1, idFlight);
            rs = prest.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(idFlight);
                String origin = rs.getString("origin");
                String destination = rs.getString("destination");
                Date depart = rs.getDate("depart");
                Date _return = rs.getDate("_return");
                int passenger = rs.getInt("passenger");
                String brand = rs.getString("brand");
                String flight_number = rs.getString("flight_number");
                Flight f = new Flight(id, origin, destination, depart, _return, passenger, brand, flight_number);
                return f;
            }
        } catch (Exception e) {
            System.out.println(e);
            // TODO: handle exception
        } finally {
            if (null != prest) {
                prest.close();
            }
            if (null != conn) {
                conn.close();
            }
            return null;
        }
    }
}
