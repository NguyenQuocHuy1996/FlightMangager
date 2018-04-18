/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightmanagerment.Function;

import flightmanagerment.Config.ConnectDB;
import flightmanagerment.Model.Flight;
import flightmanagerment.Model.Variable_Static;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.DatePicker;

/**
 *
 * @author Admin
 */
public class FlightDAO {

    private static Connection conn = null;
    private static ConnectDB connectDB;
    private static PreparedStatement prest = null;
    private static ResultSet rs;
    private static Statement st;

    ///return -1: thiếu điểm đi, return -2: thiếu điểm đến, return -3: thiếu thời gian đi
    ///return -4: thiếu số hành khách, return -5: thiếu thương hiệu máy bay, return -6: thiếu mã số chuyến bay
    ///return -7: thiếu ngày đến, return -8: thiếu giờ đến, return -9: thiếu giờ đi, return -10: thiếu giá
    public static int insert(Flight flight) throws SQLException {
        if ((flight.getOrigin().equals("")) || (flight.getOrigin() == null)) {
            return -1;
        } else if (flight.getDestination().equals("") || flight.getDestination() == null) {
            return -2;
        } else if (flight.getDepart().equals("") || flight.getDepart() == null) {
            return -3;
        } else if (flight.getPassenger() == 0) {
            return -4;
        } else if (flight.getBrand().equals("") || flight.getBrand() == null) {
            return -5;
        } else if (flight.getFlight_number().equals("") || flight.getFlight_number() == null) {
            return -6;
        } else if (flight.getArrival().equals("") || flight.getArrival() == null) {
            return -7;
        } else if (flight.getFlight_arrival().equals("") || flight.getFlight_arrival() == null) {
            return -8;
        } else if (flight.getFlight_depart().equals("") || flight.getFlight_depart() == null) {
            return -9;
        } else if (flight.getPrice().equals("") || flight.getPrice() == null) {
            return -10;
        } else {
            try {
                String[] array = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
                ArrayList<String> arraySeat = new ArrayList<>();
                int p = 1;
                for (int k = 1; k <= 10; k++) {
                    if (p > flight.getPassenger()) {
                        break;
                    }
                    for (int j = 1; j <= 15; j++) {
                        if (p > flight.getPassenger()) {
                            break;
                        }
                        arraySeat.add(array[k - 1] + j);
                        p++;
                    }
                }
                connectDB = new ConnectDB();
                conn = connectDB.getConnect();
                String sql = " insert into flight (origin, destination, depart, arrival, passenger, brand, flight_number, flight_depart, flight_arrival, price)"
                        + " values (?,?,?,?,?,?,?,?,?,?)";
                // st = connection.createStatement();
                String query = "insert into seat_ticket(code, status, firstName, lastName, ic_Card, old, idFlight)"
                        + " values (?,?,?,?,?,?,?)";
                prest = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                prest.setString(1, flight.getOrigin());
                prest.setString(2, flight.getDestination());
                prest.setDate(3, flight.getDepart());
                prest.setDate(4, flight.getArrival());
                prest.setInt(5, flight.getPassenger());
                prest.setString(6, flight.getBrand());
                prest.setString(7, flight.getFlight_number());
                prest.setString(8, flight.getFlight_depart());
                prest.setString(9, flight.getFlight_arrival());
                prest.setDouble(10, flight.getPrice());
                prest.execute();
                rs = prest.getGeneratedKeys();
                int generatedKey = 0;
                while (rs.next()) {
                    generatedKey = rs.getInt(1);
                    System.out.println(generatedKey);
                }

                for (int i = 0; i < flight.getPassenger(); i++) {
                    prest = conn.prepareStatement(query);

                    prest.setString(1, arraySeat.get(i));
                    prest.setBoolean(2, false);
                    prest.setString(3, "");
                    prest.setString(4, "");
                    prest.setString(5, "");
                    prest.setInt(6, 0);
                    prest.setInt(7, generatedKey);
                    prest.execute();
                }
                if (null != prest) {
                    prest.close();
                }
                if (null != conn) {
                    conn.close();
                }

                String contentMail = "- Hãng chuyến bay : " + flight.getBrand() + "\n"
                        + "- Số hiệu : " + flight.getFlight_number() + "\n"
                        + "- Điểm đi : " + flight.getOrigin() + "\n"
                        + "- Điểm đến : " + flight.getDestination() + "\n"
                        + "- Ngày đi : " + flight.getDepart() + "\n"
                        + "- Giờ đi : " + flight.getFlight_depart() + "\n"
                        + "- Ngày đến : " + flight.getArrival() + "\n"
                        + "- Giờ đến : " + flight.getFlight_arrival() + "\n"
                        + "- Tổng số ghế dự kiến : " + flight.getPassenger() + "\n"
                        + "\n"
                        + "Đây là hệ thống mail tự động, vui lòng ko reply. Xin cảm ơn.\n"
                        + "- Hệ thống quản trị chuyến bay.";

                List<String> list = CustomerDAO.getCusWithNotification(Boolean.TRUE);
//                Variable_Static.SendMail("nguyenngocphuongou@gmail.com", contentMail);

                for (String string : list) {
                    Variable_Static.SendMail(string, contentMail);
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

    ///return 1: get thành công
    public static ObservableList<Flight> getAllFlight() throws SQLException {
        connectDB = new ConnectDB();
        conn = connectDB.getConnect();
        String sql = "select * from flight";
        st = conn.createStatement();
        rs = st.executeQuery(sql);
        ObservableList<Flight> data = FXCollections.observableArrayList();
        while (rs.next()) {
            int idFlight = rs.getInt("idFlight");
            String origin = rs.getString("origin");
            String destination = rs.getString("destination");
            Date depart = rs.getDate("depart");
            Date arrival = rs.getDate("arrival");
            int passenger = rs.getInt("passenger");
            String brand = rs.getString("brand");
            String flight_number = rs.getString("flight_number");
            String flight_depart = rs.getString("flight_depart");
            String flight_arrival = rs.getString("flight_arrival");
            double price = rs.getDouble("price");

            Flight f = new Flight(idFlight, origin, destination, depart, arrival, passenger, brand, flight_number, flight_arrival, flight_depart, price);

            data.add(f);

        }
        return data;
    }

    public static int Edit(Flight f) throws SQLException {
        try {

            connectDB = new ConnectDB();
            conn = connectDB.getConnect();
            String sql = "update flight set origin = ?, destination = ?, depart = ?, arrival = ?, passenger = ?, brand = ?, flight_number = ?, flight_depart = ?, flight_arrival = ?, price = ? where idFlight = ?";
            prest = conn.prepareStatement(sql);
            prest.setString(1, f.getOrigin());
            prest.setString(2, f.getDestination());
            prest.setDate(3, f.getDepart());
            prest.setDate(4, f.getArrival());
            prest.setInt(5, f.getPassenger());
            prest.setString(6, f.getBrand());
            prest.setString(7, f.getFlight_number());
            prest.setString(8, f.getFlight_depart());
            prest.setString(9, f.getFlight_arrival());
            prest.setDouble(10, f.getPrice());
            prest.setInt(11, f.getIdFlight());
            prest.execute();
            System.out.println("Update thành công!");
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
        }
        return 0;
    }

//    public static List<Flight> searchFlight(String origin, String destination, Date depart) throws SQLException {
////        if (origin.equals("")) {
////            return null;
////        } else if (destination.equals("")) {
////            return null;
////        } else if (depart == null) {
////            return null;
////        } else {
//        try {
//            ArrayList<Flight> list = new ArrayList<>();
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
//                Date _arrival = rs.getDate("arrival");
//                int _passenger = rs.getInt("passenger");
//                String _brand = rs.getString("brand");
//                String _flight_number = rs.getString("flight_number");
//                String _flight_arrival = rs.getString("flight_arrival");
//                String _flight_depart = rs.getString("flight_depart");
//                Double _price = rs.getDouble("price");
//
//                System.out.println("id: " + id);
//                System.out.println("origin: " + origin);
//                System.out.println("destination: " + destination);
//                System.out.println("depart: " + depart);
//                System.out.println("arrival: " + _arrival);
//                System.out.println("passenger: " + _passenger);
//                System.out.println("brand: " + _brand);
//                System.out.println("flight_number: " + _flight_number);
//                System.out.println("flight_arrival: " + _flight_arrival);
//                System.out.println("flight_depart: " + _flight_depart);
//                System.out.println("price: " + _price);
//                Flight f = new Flight(id, origin, destination, depart, _arrival, _passenger,
//                        _brand, _flight_number, _flight_arrival, _flight_depart, _price);
//                list.add(f);
//            }
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
////        }
//    }
    public static ObservableList<Flight> getInfo(int idFlight, String brand, String flight_number, String origin, String destination) throws SQLException {
        try {
//            System.out.println(idFlight);
//            System.out.println(brand);
//            System.out.println(flight_number);
//            System.out.println(origin);
//            System.out.println(destination);
//            System.out.println(idFlight == 0 && brand.equals("") && flight_number.equals("") && origin.equals(destination));
            ObservableList<Flight> data = FXCollections.observableArrayList();
            connectDB = new ConnectDB();
            conn = connectDB.getConnect();
            String sql = "select *from flight";
            String query = "select * from flight where idFlight is not null";
            int i = 1;
            if (idFlight == 0 && brand.equals("") && flight_number.equals("") && origin.equals(destination)) {

                prest = conn.prepareStatement(sql);
                rs = prest.executeQuery();
                while (rs.next()) {
                    int _idFlight = rs.getInt("idFlight");
                    String _origin = rs.getString("origin");
                    String _destination = rs.getString("destination");
                    Date depart = rs.getDate("depart");
                    Date arrival = rs.getDate("arrival");
                    int passenger = rs.getInt("passenger");
                    String _brand = rs.getString("brand");
                    String _flight_number = rs.getString("flight_number");
                    String flight_depart = rs.getString("flight_depart");
                    String flight_arrival = rs.getString("flight_arrival");
                    double price = rs.getDouble("price");

                    Flight f = new Flight(_idFlight, _origin, _destination, depart, arrival, passenger, _brand, _flight_number, flight_arrival, flight_depart, price);
                    data.add(f);
                }
            } else {
                if (idFlight != 0) {
                    query += " and idFlight = ?";
                }
                if (!brand.equals("")) {
                    query += " and brand = ?";
                }
                if (!flight_number.equals("")) {
                    query += " and flight_number = ?";
                }
                if (!origin.equals(destination)) {
                    query += " and origin = ? and destination = ?";
                }
                prest = conn.prepareStatement(query);

                if (idFlight != 0) {
                    prest.setInt(i, idFlight);
                    i++;
                }
                if (!brand.equals("")) {
                    prest.setString(i, brand);
                    i++;
                }
                if (!flight_number.equals("")) {
                    prest.setString(i, flight_number);
                    i++;
                }
                if (!origin.equals(destination)) {
                    prest.setString(i, origin);
                    i++;
                    prest.setString(i, destination);
                }

                rs = prest.executeQuery();
                while (rs.next()) {
                    int _idFlight = rs.getInt("idFlight");
                    String _origin = rs.getString("origin");
                    String _destination = rs.getString("destination");
                    String _brand = rs.getString("brand");
                    String _flight_number = rs.getString("flight_number");
                    Date depart = rs.getDate("depart");
                    Date arrival = rs.getDate("arrival");
                    int passenger = rs.getInt("passenger");
                    String flight_depart = rs.getString("flight_depart");
                    String flight_arrival = rs.getString("flight_arrival");
                    double price = rs.getDouble("price");

                    Flight f = new Flight(_idFlight, _origin, _destination, depart, arrival, passenger, _brand, _flight_number, flight_arrival, flight_depart, price);
                    data.add(f);
                }
            }

            return data;
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    public static int delete(int idFlight) throws SQLException {
        try {
            connectDB = new ConnectDB();
            conn = connectDB.getConnect();
            String sql = "delete from flight where idFlight = ?";
            prest = conn.prepareStatement(sql);
            prest.setInt(1, idFlight);
            prest.execute();
            System.out.println("Xóa thành công!");
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
        }
        return 0;
    }

    public static Flight getInfoFlight(int idFlight) throws SQLException {
        try {
            connectDB = new ConnectDB();
            conn = connectDB.getConnect();
            String sql = "select * from flight where idFlight = ?";
            prest = conn.prepareStatement(sql);
            prest.setInt(1, idFlight);
            rs = prest.executeQuery();
            while (rs.next()) {
                String origin = rs.getString("origin");
                String destination = rs.getString("destination");
                Date depart = rs.getDate("depart");
                Date arrival = rs.getDate("arrival");
                int passenger = rs.getInt("passenger");
                String brand = rs.getString("brand");
                String flight_number = rs.getString("flight_number");
                String flight_arrival = rs.getString("flight_arrival");
                String flight_depart = rs.getString("flight_depart");
                Double price = rs.getDouble("price");
                Flight f = new Flight(idFlight, origin, destination, depart, arrival, passenger,
                        brand, flight_number, flight_arrival, flight_depart, price);
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
        }
        return null;
    }
}
