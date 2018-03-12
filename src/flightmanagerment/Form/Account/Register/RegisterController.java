/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightmanagerment.Form.Account.Register;

import flightmanagerment.Config.ConnectDB;
import flightmanagerment.Function.CustomerDAO;
import flightmanagerment.Model.Customer;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 *
 * @author Quoc Huy
 */
public class RegisterController implements Initializable {

//    @FXML
//    private TextField txtEmail;
//    @FXML
//    private TextField txtPass;
//    
//    @FXML
//    private void Register(ActionEvent event) {
//        if((txtEmail.getText().equals("admin")) && (txtPass.getText().equals("admin123"))){
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Dang nhap thanh cong");
//            alert.show();
//        }
//    }
    private Connection conn = null;
    private ConnectDB connectDB;
    private ResultSet rs;
    private Statement st;
    private PreparedStatement prest = null;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField email;
    @FXML
    private TextField password;
    @FXML
    private TextField confirm_password;

    @FXML
    private void Register(ActionEvent event) throws SQLException {
//        if ((firstName.getText().equals("")) || (lastName.getText().equals("")) || (email.getText().equals("")) || (password.getText().equals("")) || (confirm_password.getText().equals(""))) {
//            Alert a = new Alert(Alert.AlertType.ERROR);
//            a.setTitle("Nhập thiếu thông tin");
//            a.setContentText("Vui lòng nhập đầy đủ thông tin!");
//        }

        try {
            connectDB = new ConnectDB();
            conn = connectDB.getConnect();
            String sql = " insert into customer (email,password,firstName,lastName)"
                    + " values (?,?,?,?)";
            // st = connection.createStatement();
            Customer cus = new Customer();
            cus.setEmail(email.getText());
            cus.setPassword(password.getText());
            cus.setFirstName(firstName.getText());
            cus.setLastName(lastName.getText());
            if (confirm_password.getText().equals(cus.getPassword())) {
                prest = conn.prepareStatement(sql);
                prest.setString(1, cus.getEmail());
                prest.setString(2, cus.getPassword());
                prest.setString(3, cus.getFirstName());
                prest.setString(4, cus.getLastName());
                prest.execute();
                System.out.println("Đăng ký thành công!");
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setTitle("Đăng ký thành công!");
                a.setContentText("Chúc mừng bạn đã đăng ký thành công!");
                a.show();
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
//    private void insert(Customer cus) throws SQLException {
//        try {
//            connectDB = new ConnectDB();
//            conn = connectDB.getConnect();
//            String sql = " insert into customer (email,password,firstName,lastName)"
//                    + " values (?,?,?,?)";
//            // st = connection.createStatement();
//            if (this.confirm_password.equals(cus.getPassword())) {
//                prest = conn.prepareStatement(sql);
//                prest.setString(1, cus.getEmail());
//                prest.setString(2, cus.getPassword());
//                prest.setString(3, cus.getFirstName());
//                prest.setString(4, cus.getLastName());
//                rs = prest.executeQuery();
//
//                System.out.println("Thêm thành công");
//            } else {
//                System.out.println("Thêm thất bại");
//            }
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
//
//    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
