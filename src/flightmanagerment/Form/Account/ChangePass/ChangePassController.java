/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightmanagerment.Form.Account.ChangePass;

import flightmanagerment.Function.AccountDAO;
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
public class ChangePassController implements Initializable {

    @FXML
    private PasswordField old_password;
    @FXML
    private PasswordField new_password;
    @FXML
    private PasswordField confirmed_password;

//    @FXML
//    private TextField txtEmail;
//    @FXML
//    private TextField txtPass;
//    
//    @FXML
//    private void ChangePass(ActionEvent event) {
//        if((txtEmail.getText().equals("admin")) && (txtPass.getText().equals("admin123"))){
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Dang nhap thanh cong");
//            alert.show();
//        }
//    }
    @FXML
    private void btn_changedPassword(ActionEvent event) throws SQLException, IOException {
        Alert a = new Alert(Alert.AlertType.ERROR);
        if (old_password.getText().equals("") || (new_password.getText().equals("")) || (confirmed_password.getText().equals(""))) {
            a.setTitle("ERROR");
            a.setContentText("Vui lòng nhập đủ thông tin!");
            a.show();
        } else {
            if (new_password.getText().equals(confirmed_password.getText())) {
                int function = AccountDAO.checkRole(Variable_Static.USERNAME);
                if (function == 1) {
                    if (AccountDAO.changePasswordCus(old_password.getText(), new_password.getText(), Variable_Static.USERNAME) == 1) {
                        Alert.AlertType type = Alert.AlertType.INFORMATION;
                        a.setAlertType(type);
                        a.setTitle("Đổi mật khẩu thành công!");
                        a.setContentText("Bạn đã đổi mật khẩu thành công!");
                        a.show();
                        
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
                    }

                } else {
                    if (AccountDAO.changePasswordEmp(old_password.getText(), new_password.getText(), Variable_Static.USERNAME) == 1) {
                        Alert.AlertType type = Alert.AlertType.INFORMATION;
                        a.setAlertType(type);
                        a.setTitle("Đổi mật khẩu thành công!");
                        a.setContentText("Bạn đã đổi mật khẩu thành công!");
                        a.show();
                        
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
                    }

                }
            } else {
                Alert.AlertType type = Alert.AlertType.ERROR;
                a.setAlertType(type);
                a.setTitle("ERROR");
                a.setContentText("Vui lòng xác nhận lại mật khẩu!");
                a.show();
            }

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
