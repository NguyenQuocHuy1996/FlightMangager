/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightmanagerment.Function;

import flightmanagerment.Config.ConnectDB;
import flightmanagerment.Model.Customer;
import flightmanagerment.Model.Flight;
import flightmanagerment.Model.Seat_Flight;
import flightmanagerment.Model.Seat_Ticket;
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

/**
 *
 * @author Admin
 */
public class Seat_TicketDAO {

    private static Connection conn = null;
    private static ConnectDB connectDB;
    private static PreparedStatement prest;
    private static ResultSet rs;
    private static Statement st;

    private static String contentMail;

    public static double getCountStatusTrue(int depart, int year) throws SQLException {
        connectDB = new ConnectDB();
        conn = connectDB.getConnect();
        String sql = "select Count(*) as total\n"
                + "from seat_ticket S JOIN flight F ON S.idFlight = F.idFlight\n"
                + "where status = true AND MONTH(F.depart) = ? AND YEAR(F.depart) = ?";
        prest = conn.prepareStatement(sql);
        prest.setInt(1, depart);
        prest.setInt(2, year);
        rs = prest.executeQuery();
        while (rs.next()) {
            Double count = rs.getDouble("total");
            return count;
        }
        return 0;
    }

    public static ObservableList<Seat_Ticket> getAllSeat(int idFlight) throws SQLException {
        connectDB = new ConnectDB();
        conn = connectDB.getConnect();
        String sql = "select * from seat_ticket where idFlight = ? and status = 1";
        prest = conn.prepareStatement(sql);
        prest.setInt(1, idFlight);

        rs = prest.executeQuery();

        ObservableList<Seat_Ticket> data = FXCollections.observableArrayList();
        while (rs.next()) {
            String firstName = rs.getString("firstName");
            String lastName = rs.getString("lastName");
            String ic_card = rs.getString("ic_card");
            String code = rs.getString("code");
            Boolean status = true;

            Seat_Ticket s = new Seat_Ticket(code, status, firstName, lastName, ic_card, idFlight);
            data.add(s);
        }
        return data;
    }

    public static double getCountStatusFalse(int depart, int year) throws SQLException {
        connectDB = new ConnectDB();
        conn = connectDB.getConnect();
        String sql = "select Count(*) as total\n"
                + "from seat_ticket S JOIN flight F ON S.idFlight = F.idFlight\n"
                + "where status = false AND MONTH(F.depart) = ? AND YEAR(F.depart) = ?";
        prest = conn.prepareStatement(sql);
        prest.setInt(1, depart);
        prest.setInt(2, year);
        rs = prest.executeQuery();
        while (rs.next()) {
            Double count = rs.getDouble("total");
            return count;
        }
        return 0;
    }

    ///return 1: trả về tất cả dữ liệu của ghế theo id
    public int getSeatt(int idFlight) throws SQLException {
        connectDB = new ConnectDB();
        conn = connectDB.getConnect();
        String sql = "select * from seat_ticket where idFlight = ?";
        prest = conn.prepareStatement(sql);
        prest.setInt(1, idFlight);
        rs = prest.executeQuery();
        while (rs.next()) {

            String code = rs.getString("code");
            Boolean status = rs.getBoolean("status");
            String firstName = rs.getString("firstName");
            String lastName = rs.getString("lastName");
            String ic_Card = rs.getString("ic_Card");

        }
        return 1;
    }

//    public static int getHistoryBooking(int idFlight) {
//        try {
//            connectDB = new ConnectDB();
//            conn = connectDB.getConnect();
//            String sql = "SELECT F.*,COUNT(*) AS GHEDUOCMUA\n"
//                    + "FROM seat_ticket S JOIN flight F ON S.idFlight = F.idFlight\n"
//                    + "WHERE S.status = TRUE\n"
//                    + "GROUP BY S.idFlight ";
//            prest = conn.prepareStatement(sql);
//            prest.setInt(1, idFlight);
//            rs = prest.executeQuery();
//            while (rs.next()) {                
//                String origin = rs.getString("origin");
//                String destination = rs.getString("destination");
//                int passenger = rs.getInt("passenger");
//                
//            }
//        } catch (Exception e) {
//        }
//        return 0;
//    }
    ///return s: trả về info của ghế theo id
    public static Seat_Ticket getSeat(int idSeat) throws SQLException {
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
                int idAccount = rs.getInt("idAccount");

                System.out.println("code: " + code);
                System.out.println("status: " + status);
                System.out.println("firstName: " + firstName);
                System.out.println("lastName: " + lastName);
                System.out.println("ic_Card: " + ic_Card);
                System.out.println("old: " + old);
                System.out.println("idFlight: " + idFlight);

                Seat_Ticket s = new Seat_Ticket(idSeat, code, status, firstName, lastName, ic_Card, old, idFlight, idAccount);
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

    public static int historyBookingofEmp(int idFlight, Boolean status) throws SQLException {
//        ObservableList<Seat_Ticket> data = FXCollections.observableArrayList();
        int count = 0;
        connectDB = new ConnectDB();
        conn = connectDB.getConnect();
        String sql = "select * from seat_ticket where idFlight = ? and status = ?";
        prest = conn.prepareStatement(sql);
        prest.setInt(1, idFlight);
        prest.setBoolean(2, true);
        rs = prest.executeQuery();
        while (rs.next()) {
            int idSeat = rs.getInt("idSeat");
            String code = rs.getString("code");
            // String brand = rs.getString("brand");
            // String origin = rs.getString("origin");
            // String destination = rs.getString("destination");
            // Date depart = rs.getDate("depart");
            // Date arrival = rs.getDate("arrival");
            // String flight_depart = rs.getString("flight_depart");
            // String flight_arrival = rs.getString("flight_arrival");

            String ic_Card = rs.getString("ic_card");
            int old = rs.getInt("old");
            String firstName = rs.getString("firstName");
            String lastName = rs.getString("lastName");
            int idAccount = rs.getInt("idAccount");
            count++;
//            Seat_Ticket seat = new Seat_Ticket(idSeat, code, status, firstName, lastName, ic_Card, old, idFlight, idAccount);
//            data.add(seat);

        }
        return count;
    }

    public static ObservableList<Seat_Ticket> historyBookingofCus(int idAccount) throws SQLException {
        ObservableList<Seat_Ticket> data = FXCollections.observableArrayList();
        connectDB = new ConnectDB();
        conn = connectDB.getConnect();
        String sql = "select * from seat_ticket where idAccount = ?";
        prest = conn.prepareStatement(sql);
        prest.setInt(1, idAccount);
        rs = prest.executeQuery();
        while (rs.next()) {

            int idSeat = rs.getInt("idSeat");
            String code = rs.getString("code");
            Boolean status = rs.getBoolean("status");
            String firstName = rs.getString("firstName");
            String lastName = rs.getString("lastName");
            String ic_Card = rs.getString("ic_Card");
            int old = rs.getInt("old");
            int idFlight = rs.getInt("idFlight");
            Seat_Ticket seat = new Seat_Ticket(idSeat, code, status, firstName, lastName, ic_Card, old, idFlight, idAccount);
            data.add(seat);

            // System.out.println("--------------------");
            // System.out.println("idSeat: " + idSeat);
            // System.out.println("code: " + code);
            // System.out.println("status: " + status);
            // System.out.println("firstName: " + firstName);
            // System.out.println("lastName: " + lastName);
            // System.out.println("ic_Card: " + ic_Card);
            // System.out.println("old: " + old);
            // System.out.println("idAccount: " + idAccount);
            // System.out.println("idFlight: " + idFlight);
        }
        return data;
    }

    public static int delete(int idFlight) throws SQLException {
        try {
            connectDB = new ConnectDB();
            conn = connectDB.getConnect();
            String sql = "delete from seat_ticket where idFlight = ?";
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

    public static int booking(Seat_Ticket seat, int idAccount) throws SQLException {
//        Scanner sc = new Scanner(System.in);
//        System.out.println("Nhập id chuyến bay cần update: ");
//        seat.setIdFlight(Integer.parseInt(sc.nextLine()));
//        System.out.println("Nhập idAccount: ");
//        seat.setIdAccount(Integer.parseInt(sc.nextLine()));
//        System.out.println("Nhập họ: ");
//        seat.setFirstName(sc.nextLine());
//        System.out.println("Nhập tên: ");
//        seat.setLastName(sc.nextLine());
//        System.out.println("Nhập cmnd: ");
//        seat.setIc_Card(sc.nextLine());
//        System.out.println("Nhập tuổi: ");
//        seat.setOld(Integer.parseInt(sc.nextLine()));
//        System.out.println("Nhập số ghế: ");
//        seat.setCode(sc.nextLine());
//        System.out.println("status: ");
//        seat.setStatus(Boolean.parseBoolean(sc.nextLine()));
        try {
            connectDB = new ConnectDB();
            conn = connectDB.getConnect();
            String sql = "update seat_ticket set idAccount = ?, firstName = ?, lastName = ?, ic_card = ?, status = ? where idFlight = ? and code = ?";
            prest = conn.prepareStatement(sql);
            prest.setInt(1, seat.getIdAccount());
            prest.setString(2, seat.getFirstName());
            prest.setString(3, seat.getLastName());
            prest.setString(4, seat.getIc_Card());

            prest.setBoolean(5, seat.getStatus());
            prest.setInt(6, seat.getIdFlight());
            prest.setString(7, seat.getCode());
            prest.execute();
            System.out.println("update thành công");

            List<Seat_Flight> list = Seat_FlightDAO.getSeatID(idAccount);
            for (Seat_Flight sf : list) {
                contentMail = "Ticket:\n"
                        + "Mã chuyến bay: " + sf.getIdFlight() + "\n"
                        + "Số ghế: " + sf.getCode() + "\n"
                        + "Họ: " + sf.getFirstName() + "\n"
                        + "Tên: " + sf.getLastName() + "\n"
                        + "CMND: " + sf.getIc_card() + "\n"
                        + "Giá: " + sf.getPrice();
            }

            Customer cus = CustomerDAO.getCus(idAccount);
            Variable_Static.SendMail(cus.getEmail(), contentMail);
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

//    public static int booking(Seat_Ticket seat) throws SQLException {
//        try {
//            connectDB = new ConnectDB();
//            conn = connectDB.getConnect();
//            String sql = "update seat_ticket set idAccount = ?, firstName = ?, lastName = ?, ic_card = ?, old = ?,code = ? where idFlight = ?";
//            prest = conn.prepareStatement(sql);
//            prest.setInt(1, seat.getIdAccount());
//            prest.setString(2, seat.getFirstName());
//            prest.setString(3, seat.getLastName());
//            prest.setString(4, seat.getIc_Card());
//            prest.setInt(5, seat.getOld());
//            prest.setString(6, seat.getCode());
//            prest.setInt(7, seat.getIdFlight());
//            prest.execute();
//            System.out.println("update thành công");
//            return 1;
//
//        } catch (Exception e) {
//            System.out.println(e);
//            // TODO: handle exception
//        } finally {
//            if (null != prest) {
//                prest.close();
//            }
//            if (null != conn) {
//                conn.close();
//            }
//        }
//        return 0;
//    }
    ///return status: trả về trạng thái của ghế
    public static Boolean getInfoSeat(int idFlight, String code) throws SQLException {
        try {
            List<Boolean> list = new ArrayList<>();
            connectDB = new ConnectDB();
            conn = connectDB.getConnect();
            String sql = "select * from seat_ticket where idFlight = ? and code = ?";
            prest = conn.prepareStatement(sql);
            prest.setInt(1, idFlight);
            prest.setString(2, code);
            rs = prest.executeQuery();
            while (rs.next()) {
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
        }
        return false;
    }
}
