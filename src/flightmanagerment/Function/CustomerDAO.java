/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightmanagerment.Function;

import flightmanagerment.Config.ConnectDB;
import flightmanagerment.Model.Customer;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
            String sex = rs.getString("sex");
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
