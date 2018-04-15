/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightmanagerment.Function;

import flightmanagerment.Config.ConnectDB;
import flightmanagerment.Model.Customer;
import java.sql.Blob;
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
public class CustomerDAO {

    private static Connection conn = null;
    private static ConnectDB connectDB;
    private static PreparedStatement prest = null;
    private static String confirm_password = null;
    private static ResultSet rs;
    private static Statement st;

    public static int delete(int idAccount) throws SQLException {
        try {
            connectDB = new ConnectDB();
            conn = connectDB.getConnect();
            String sql = "delete from customer where idAccount = ?";
            prest = conn.prepareStatement(sql);
            prest.setInt(1, idAccount);
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

    /// return 1: xuất thành công
    public static int get_info() throws SQLException {
        connectDB = new ConnectDB();
        conn = connectDB.getConnect();
        String sql = "select * from customer";
        st = conn.createStatement();
        rs = st.executeQuery(sql);
        while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
            int idAccount = rs.getInt(1);
            // String password = rs.getString("Emp_Name");
            String email = rs.getString("email");
            String password = rs.getString("password");
            String firstName = rs.getString("firstName");
            String lastName = rs.getString("lastName");
            Date dateOfBirth = rs.getDate("dateOfBirth");
            String ic_Card = rs.getString("ic_Card");
            String homeTown = rs.getString("homeTown");
            Boolean sex = rs.getBoolean("sex");
            String phoneNumber = rs.getString("phoneNumber");

            ///in ra consoles
            System.out.println("--------------------");
            System.out.println("idAccount: " + idAccount);
            System.out.println("email: " + email);
            System.out.println("password: " + password);
            System.out.println("firstName: " + firstName);
            System.out.println("lastName: " + lastName);
            System.out.println("dateOfBirth: " + dateOfBirth);
            System.out.println("ic_Card: " + ic_Card);
            System.out.println("homeTown: " + homeTown);
            System.out.println("sex: " + sex);
            System.out.println("phoneNumber: " + phoneNumber);
        }
        return 1;
    }

    public static Customer getCus(String email) throws SQLException {
        try {
            connectDB = new ConnectDB();
            conn = connectDB.getConnect();
            String sql = "select * from customer where email = ?";
            prest = conn.prepareStatement(sql);
            prest.setString(1, email);
            rs = prest.executeQuery();
            while (rs.next()) {
                int idAccount = rs.getInt("idAccount");

                String password = rs.getString("password");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                Date dateOfBirth = rs.getDate("dateOfBirth");
                String ic_Card = rs.getString("ic_Card");
                String homeTown = rs.getString("homeTown");
                Boolean sex = rs.getBoolean("sex");
                String phoneNumber = rs.getString("phoneNumber");
                Blob image = rs.getBlob("image");
                String address_number = rs.getString("address_number");
                String address_street = rs.getString("address_street");
                String address_district = rs.getString("address_district");
                String address_city = rs.getString("address_city");

                Customer cus = new Customer(idAccount, email, password, firstName, lastName, dateOfBirth, ic_Card, homeTown, sex, phoneNumber, image, address_number, address_street, address_district, address_city);
                return cus;
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

    public static Customer getCus(int idAccount) throws SQLException {
        try {
            connectDB = new ConnectDB();
            conn = connectDB.getConnect();
            String sql = "select * from customer where idAccount = ?";
            prest = conn.prepareStatement(sql);
            prest.setInt(1, idAccount);
            rs = prest.executeQuery();
            while (rs.next()) {
                String email = rs.getString("email");
                String password = rs.getString("password");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                Date dateOfBirth = rs.getDate("dateOfBirth");
                String ic_Card = rs.getString("ic_Card");
                String homeTown = rs.getString("homeTown");
                Boolean sex = rs.getBoolean("sex");
                String phoneNumber = rs.getString("phoneNumber");
                Blob image = rs.getBlob("image");
                String address_number = rs.getString("address_number");
                String address_street = rs.getString("address_street");
                String address_district = rs.getString("address_district");
                String address_city = rs.getString("address_city");
                // System.out.println("idAccount: " + idAccount);
                // Customer cus = new
                // Customer(id,email,password,firstName,lastName,dateOfBirth,ic_Card,homeTown,sex,phoneNumber);
                // return cus;
                System.out.println("email: " + email);
                System.out.println("password: " + password);
                System.out.println("firstName: " + firstName);
                System.out.println("lastName: " + lastName);
                System.out.println("dateOfBirth: " + dateOfBirth);
                System.out.println("ic_Card: " + ic_Card);
                System.out.println("homeTown: " + homeTown);
                System.out.println("sex: " + sex);
                System.out.println("phoneNumber: " + phoneNumber);
                System.out.println("image: " + image);
                System.out.println("address_number: " + address_number);
                System.out.println("address_street: " + address_street);
                System.out.println("address_district: " + address_district);
                System.out.println("address_city: " + address_city);
                Customer cus = new Customer(idAccount, email, password, firstName, lastName, dateOfBirth, ic_Card, homeTown, sex, phoneNumber, image, address_number, address_street, address_district, address_city);
                return cus;
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

    public static Boolean getNotification(String email) throws SQLException {

        connectDB = new ConnectDB();
        conn = connectDB.getConnect();
        String sql = "select notification from customer where email = ?";
        prest = conn.prepareStatement(sql);
        prest.setString(1, email);
        rs = prest.executeQuery();
        while (rs.next()) {
            Boolean status = rs.getBoolean("notification");
            return status;
        }
        return false;
    }

    public static List<String> getCusWithNotification(Boolean notification) throws SQLException {
        try {
            List<String> list = new ArrayList<String>();
            connectDB = new ConnectDB();
            conn = connectDB.getConnect();
            String sql = "select email from customer where notification = ?";
            prest = conn.prepareStatement(sql);
            prest.setBoolean(1, notification);
            rs = prest.executeQuery();
            while (rs.next()) {
                String email = rs.getString("email");
                list.add(email);
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

    public static Boolean updateNotification(Boolean status, String email) throws SQLException {
        try {
            connectDB = new ConnectDB();
            conn = connectDB.getConnect();
            String sql = "update customer set notification = ? where email = ?";
            prest = conn.prepareStatement(sql);
            prest.setBoolean(1, status);
            prest.setString(2, email);
            prest.execute();
            return status;
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
        return false;
    }

    public static int update(Customer cus) throws SQLException {
        try {
            connectDB = new ConnectDB();
            conn = connectDB.getConnect();
            String sql = "update customer set firstName = ?, lastName = ?, dateOfBirth =?, ic_Card = ?, homeTown = ?, sex =?, phoneNumber = ?, image = ?, address_number = ?, address_street = ?, address_district = ?, address_city = ? where email = ?";
            prest = conn.prepareStatement(sql);
            prest.setString(1, cus.getFirstName());
            prest.setString(2, cus.getLastName());
            prest.setDate(3, cus.getDateOfBirth());
            prest.setString(4, cus.getIc_Card());
            prest.setString(5, cus.getHomeTown());
            prest.setBoolean(6, cus.getSex());
            prest.setString(7, cus.getPhoneNumber());
            prest.setBlob(8, cus.getImage());
            prest.setString(9, cus.getAddress_number());
            prest.setString(10, cus.getAddress_street());
            prest.setString(11, cus.getAddress_district());
            prest.setString(12, cus.getAddress_city());
            prest.setString(13, cus.getEmail());
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

    ///return -1: thiếu email, return -2: thiếu pass, return -3: thiếu firstName, return -4: thiếu lastName, return 1: thêm thành công, return 0: thêm thất bại
    public static int insert(Customer cus) throws SQLException {
        if ((cus.getEmail().equals("")) || (cus.getEmail() == null)) {
            return -1;
        } else if (cus.getPassword().equals("") || cus.getPassword() == null) {
            return -2;
        } else if (cus.getFirstName().equals("") || cus.getFirstName() == null) {
            return -3;
        } else if (cus.getLastName().equals("") || cus.getLastName() == null) {
            return -4;
        } else {
            try {
                connectDB = new ConnectDB();
                conn = connectDB.getConnect();
                String sql = " insert into customer (email,password,firstName,lastName)"
                        + " values (?,?,?,?)";
                // st = connection.createStatement();

                prest = conn.prepareStatement(sql);
                prest.setString(1, cus.getEmail());
                prest.setString(2, cus.getPassword());
                prest.setString(3, cus.getFirstName());
                prest.setString(4, cus.getLastName());
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
}
