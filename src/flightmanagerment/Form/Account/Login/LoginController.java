/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightmanagerment.Form.Account.Login;

import flightmanagerment.Function.AccountDAO;
import flightmanagerment.Model.Variable_Static;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Quoc Huy
 */
public class LoginController implements Initializable {

    @FXML
    private TextField email;
    @FXML
    private PasswordField password;

    @FXML
    private void btn_Register(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/flightmanagerment/Form/Account/Register/RegisterUI.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setTitle("Login ");
        stage.show();
    }

    @FXML
    private void btn_Login(ActionEvent event) throws IOException, SQLException {
        Alert a = new Alert(Alert.AlertType.ERROR);
        if (email.getText().equals("") || (password.getText().equals(""))) {
            a.setTitle("ERROR");
            a.setContentText("Vui lòng nhập đủ thông tin!");
            a.show();
        } else {
            int function = AccountDAO.checkRole(email.getText());
            if (function == 1) {
                Variable_Static.USERNAME = email.getText();
                if (AccountDAO.loginCus(email.getText(), password.getText()) == 1) {
                    Parent root = FXMLLoader.load(getClass().getResource("/flightmanagerment/Form/Home/MainUser/MainUserUI.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.hide();
                    stage.setScene(scene);
                    stage.setMaximized(true);
                    stage.resizableProperty().setValue(Boolean.FALSE);
                    stage.setTitle("Main Customer ");
                    stage.show();
                } else {
                    a.setContentText("Mật khẩu không chính xác");
                    a.show();
                }

            } else if (function == 2) {
                Variable_Static.USERNAME = email.getText();
                if (AccountDAO.loginEmp(email.getText(), password.getText()) == 1) {
                    Parent root = FXMLLoader.load(getClass().getResource("/flightmanagerment/Form/Home/MainStaff/MainStaffUI.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.hide();
                    stage.setScene(scene);
                    stage.setMaximized(true);
                    stage.resizableProperty().setValue(Boolean.FALSE);
                    stage.setTitle("Main Staff ");
                    stage.show();
                } else {
                    a.setContentText("Mật khẩu không chính xác");
                    a.show();
                }

            } else {
                a.setTitle("Lỗi trong quá trình đăng nhập");
                a.setContentText("Sai tên đăng nhập hoặc mật khẩu");
                a.show();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
