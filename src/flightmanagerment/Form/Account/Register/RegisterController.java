/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightmanagerment.Form.Account.Register;

import flightmanagerment.Function.CustomerDAO;
import flightmanagerment.Model.Customer;
import flightmanagerment.Model.Variable_Static;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Quoc Huy
 */
public class RegisterController implements Initializable {

    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField confirm_password;

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
//        
//    
    @FXML
    private void btn_Login(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/flightmanagerment/Form/Account/Login/LoginUI.fxml"));
        Variable_Static.LinkUI(event, root, "Login");
    }

    @FXML
    private void btn_Register(ActionEvent event) throws SQLException, IOException {
        Alert a = new Alert(Alert.AlertType.ERROR);
        Customer cus = new Customer(email.getText(), password.getText(), firstName.getText(), lastName.getText());
        if (confirm_password.getText().equals(password.getText())) {
            int function = CustomerDAO.insert(cus);
            if ((function == -1) || (function == -2) || (function == -3) || (function == -4)) {
                a.setTitle("ERROR");
                a.setContentText("Vui lòng nhập đầy đủ thông tin!");
                a.show();
            } else {
                Alert.AlertType type = Alert.AlertType.INFORMATION;
                a.setAlertType(type);
                a.setTitle("Đăng ký thành công!");
                a.setContentText("Chức mừng bạn đã đăng ký thành công ! - với email : " + cus.getEmail());
                a.showAndWait();
                Parent root = FXMLLoader.load(getClass().getResource("/flightmanagerment/Form/Account/Login/LoginUI.fxml"));
                Variable_Static.LinkUI(event, root, "Login");
            }
        } else {
            a.setTitle("ERROR");
            a.setContentText("Xác nhận mật khẩu không trùng khớp!");
            a.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void btn_back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/flightmanagerment/Form/Home/Main/MainUI.fxml"));
        Variable_Static.LinkUI(event, root, "Main");
    }

}
