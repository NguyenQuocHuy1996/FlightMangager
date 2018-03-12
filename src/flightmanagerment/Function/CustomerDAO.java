/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightmanagerment.Function;

import flightmanagerment.Config.ConnectDB;
import flightmanagerment.Model.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.control.Alert;

/**
 *
 * @author Admin
 */
public class CustomerDAO {

    private Connection conn = null;
    private ConnectDB connectDB;
    private ResultSet rs;
    private Statement st;
    private PreparedStatement prest = null;
    private String confirm_password = null;

    public void insert(Customer cus) throws SQLException {
        try {
            connectDB = new ConnectDB();
            conn = connectDB.getConnect();
            String sql = " insert into customer (email,password,firstName,lastName)"
                    + " values (?,?,?,?)";
            // st = connection.createStatement();
            if (this.confirm_password.equals(cus.getPassword())) {
                prest = conn.prepareStatement(sql);
                prest.setString(1, cus.getEmail());
                prest.setString(2, cus.getPassword());
                prest.setString(3, cus.getFirstName());
                prest.setString(4, cus.getLastName());
                prest.execute();

                System.out.println("Thêm thành công");
            } else {
                System.out.println("Thêm thất bại");
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

    }
}
