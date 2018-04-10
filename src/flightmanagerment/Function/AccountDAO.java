/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightmanagerment.Function;

import flightmanagerment.Config.ConnectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class AccountDAO {

    private static Connection conn = null;
    private static ConnectDB connectDB;
    private static PreparedStatement prest = null;
    private static ResultSet rs;

    /// return 1: trả về email của customer, return 2: trả về email của
    /// employee, return 0: cus và emp đều k có email đó
    public static int checkRole(String userName) {
        connectDB = new ConnectDB();
        conn = connectDB.getConnect();
        try {
            if (getUserCus(userName) == 1) {
                System.out.println("1");
                return 1;
            } else if (getUserCus(userName) == 0) {
                System.out.println("2");
                return 2;
            }
            else {
                return 0;
            }
        } catch (SQLException e) {
            System.out.println(e);
            // TODO: handle exception
        }
        return 0;
    }

    /// get userName table customer
    public static int getUserCus(String userName) throws SQLException {
        connectDB = new ConnectDB();
        conn = connectDB.getConnect();
        try {
            String sql = "select email from customer where email = ?";
            prest = conn.prepareStatement(sql);
            prest.setString(1, userName);
            rs = prest.executeQuery();
            while (rs.next()) {
                String email = rs.getString("email");
                if (userName.equals(email)) {
                    return 1;
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    /// get userName table employee
    public static int getUserEmp(String userName) throws SQLException {
        connectDB = new ConnectDB();
        conn = connectDB.getConnect();
        String sql = "select email from employee where email = ?";
        prest = conn.prepareStatement(sql);
        prest.setString(1, userName);
        rs = prest.executeQuery();
        while (rs.next()) {
            String email = rs.getString("email");
            if (userName.equals(email)) {
                return 1;
            }
        }
        return 0;
    }

    ///return 1: login thành công, return 0: login thất bại
    public static int loginCus(String userName, String password) throws SQLException {
        try {
            connectDB = new ConnectDB();
            conn = connectDB.getConnect();

            String sql = "select * from customer where email = ? and password = ?";
            prest = conn.prepareStatement(sql);
            prest.setString(1, userName);
            prest.setString(2, password);
            ResultSet rs = prest.executeQuery();

            if (rs.next()) {
                System.out.println("Đăng nhập thành công với role cus!");
                return 1;
            } else {
                System.out.println("Đăng nhập thất bại!");
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
        return 0;
    }

    ///return 1: login thành công, return 0: login thất bại
    public static int loginEmp(String userName, String password) throws SQLException {
        try {
            connectDB = new ConnectDB();
            conn = connectDB.getConnect();

            String sql = "select * from employee where email = ? and password = ?";
            prest = conn.prepareStatement(sql);
            prest.setString(1, userName);
            prest.setString(2, password);
            ResultSet rs = prest.executeQuery();

            if (rs.next()) {
                System.out.println("Đăng nhập thành công với role emp!");
                return 1;
            } else {
                System.out.println("Đăng nhập thất bại!");
            }
        } catch (SQLException e) {
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

    ///return 1: đổi thành công, return 0: đổi thất bại
    public static int changePasswordCus(String old_password, String new_password, String email) throws SQLException {
        try {
            connectDB = new ConnectDB();
            conn = connectDB.getConnect();

            String query = "select password from customer where email = ?";
            prest = conn.prepareStatement(query);
            prest.setString(1, email);
            rs = prest.executeQuery();
            while (rs.next()) {
                String password = rs.getString("password");
                if (old_password.equals(password)) {
                    String sql = "update customer set password = ? where email = ?";
                    prest = conn.prepareStatement(sql);
                    prest.setString(1, new_password);
                    prest.setString(2, email);
                    prest.execute();
                    System.out.println("Đổi mật khẩu thành công!");
                    return 1;
                }
            }

        } catch (SQLException e) {
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

    ///return 1: đổi thành công, return 0: đổi thất bại
    public static int changePasswordEmp(String old_password, String new_password, String email) throws SQLException {
        try {
            connectDB = new ConnectDB();
            conn = connectDB.getConnect();

            String query = "select password from employee where email = ?";
            prest = conn.prepareStatement(query);
            prest.setString(1, email);
            rs = prest.executeQuery();
            while (rs.next()) {
                String password = rs.getString("password");
                if (old_password.equals(password)) {
                    String sql = "update employee set password = ? where email = ?";
                    prest = conn.prepareStatement(sql);
                    prest.setString(1, new_password);
                    prest.setString(2, email);
                    prest.execute();
                    System.out.println("Đổi mật khẩu thành công!");
                    return 1;
                }
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
        return 0;
    }

}
