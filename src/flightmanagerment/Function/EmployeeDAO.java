/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightmanagerment.Function;

import flightmanagerment.Config.ConnectDB;
import flightmanagerment.Model.Employee;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Admin
 */
public class EmployeeDAO {

    private static Connection conn = null;
    private static ConnectDB connectDB;
    private static PreparedStatement prest = null;
    private static String confirm_password = null;
    private static ResultSet rs;
    private static Statement st;

    public static ObservableList<String> getDepartment() {
        ArrayList<String> list = new ArrayList<>();
        list.add("Trưởng phòng");
        list.add("Nhân viên giám sát");
        list.add("Nhân viên quản lý");
        list.add("Nhân viên soát vé");
        list.add("Nhân viên chăm sóc khách hàng");
        ObservableList<String> listt = FXCollections.observableArrayList(list);
        return listt;
    }

    public static int insert(Employee emp) throws SQLException {
        if ((emp.getEmail().equals("")) || (emp.getEmail() == null)) {
            return -1;
        } else if (emp.getPassword().equals("") || emp.getPassword() == null) {
            return -2;
        } else if (emp.getFirstName().equals("") || emp.getFirstName() == null) {
            return -3;
        } else if (emp.getLastName().equals("") || emp.getLastName() == null) {
            return -4;
        } else if (emp.getDateOfBirth().equals("") || emp.getDateOfBirth() == null) {
            return -5;
        } else if (emp.getIc_Card().equals("") || emp.getIc_Card() == null) {
            return -6;
        } else if (emp.getEducation_level().equals("") || emp.getEducation_level() == null) {
            return -7;
        } else if (emp.getDepartment().equals("") || emp.getDepartment() == null) {
            return -8;
        } else if (emp.getHomeTown().equals("") || emp.getHomeTown() == null) {
            return -9;
        } else if (emp.getSex().equals("") || emp.getSex() == null) {
            return -10;
        } else if (emp.getPhoneNumber().equals("") || emp.getPhoneNumber() == null) {
            return -11;
//        } else if (emp.getSex().equals("") || emp.getSex() == null) {
//            return -12;
        } else if (emp.getAddress_number().equals("") || emp.getAddress_number() == null) {
            return -13;
        } else if (emp.getAddress_street().equals("") || emp.getAddress_street() == null) {
            return -14;
        } else if (emp.getAddress_district().equals("") || emp.getAddress_district() == null) {
            return -15;
        } else if (emp.getAddress_city().equals("") || emp.getAddress_city() == null) {
            return -16;
        } else {
            try {
                connectDB = new ConnectDB();
                conn = connectDB.getConnect();
                String sql = " insert into employee (email, password, firstName, lastName, dateOfBirth, ic_Card, education_level, department, homeTown, sex, phoneNumber, address_number, address_street, address_district, address_city)"
                        + " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                // st = connection.createStatement();

                prest = conn.prepareStatement(sql);
                prest.setString(1, emp.getEmail());
                prest.setString(2, emp.getPassword());
                prest.setString(3, emp.getFirstName());
                prest.setString(4, emp.getLastName());
                prest.setDate(5, emp.getDateOfBirth());
                prest.setString(6, emp.getIc_Card());
                prest.setString(7, emp.getEducation_level());
                prest.setString(8, emp.getDepartment());
                prest.setString(9, emp.getHomeTown());
                prest.setBoolean(10, emp.getSex());
                prest.setString(11, emp.getPhoneNumber());
//                prest.setBlob(12, emp.getImage());
                prest.setString(12, emp.getAddress_number());
                prest.setString(13, emp.getAddress_street());
                prest.setString(14, emp.getAddress_district());
                prest.setString(15, emp.getAddress_city());
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

    public static Employee getEmp(int idAccount) throws SQLException {
        try {
            connectDB = new ConnectDB();
            conn = connectDB.getConnect();
            String sql = "select * from employee where idAccount = ?";
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
                String education_level = rs.getString("education_level");
                String department = rs.getString("department");
                String homeTown = rs.getString("homeTown");
                Boolean sex = rs.getBoolean("sex");
                String phoneNumber = rs.getString("phoneNumber");
                Blob image = rs.getBlob("image");
                String address_number = rs.getString("address_number");
                String address_street = rs.getString("address_street");
                String address_district = rs.getString("address_district");
                String address_city = rs.getString("address_city");

                Employee emp = new Employee(idAccount, email, password, firstName, lastName, dateOfBirth, ic_Card, education_level, department, homeTown, sex, phoneNumber, image, address_number, address_street, address_district, address_city);
                return emp;
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

    public static Employee getEmp(String email) throws SQLException {
        try {
            connectDB = new ConnectDB();
            conn = connectDB.getConnect();
            String sql = "select * from employee where email = ?";
            prest = conn.prepareStatement(sql);
            prest.setString(1, email);
            rs = prest.executeQuery();
            while (rs.next()) {
                int idAccount = rs.getInt("idAccount");
                //email
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
                String department = rs.getString("department");
                String education_level = rs.getString("education_level");
                Employee emp = new Employee(idAccount, email, password, firstName, lastName, dateOfBirth, ic_Card, education_level, department, homeTown, sex, phoneNumber, image, address_number, address_street, address_district, address_city);
                return emp;
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
}
