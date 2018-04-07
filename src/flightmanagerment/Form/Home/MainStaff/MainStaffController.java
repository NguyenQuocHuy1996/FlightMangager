/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightmanagerment.Form.Home.MainStaff;

import flightmanagerment.Function.EmployeeDAO;
import flightmanagerment.Model.Employee;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Quoc Huy
 */
public class MainStaffController implements Initializable {

    @FXML
    private Label lbl_userName;

//    @FXML
//    private TextField txtEmail;
//    @FXML
//    private TextField txtPass;
//    
//    @FXML
//    private void MainStaff(ActionEvent event) {
//        if((txtEmail.getText().equals("admin")) && (txtPass.getText().equals("admin123"))){
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Dang nhap thanh cong");
//            alert.show();
//        }
//    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Employee emp = new Employee();
        emp = EmployeeDAO.getEmp(Variable_Static.USERNAME);
        lbl_userName.setText(emp.getFirstName());
    }

    @FXML
    private void btn_Logout(ActionEvent event) throws IOException {
        Variable_Static.USERNAME = "";
        Parent root = FXMLLoader.load(getClass().getResource("/flightmanagerment/Form/Account/Login/LoginUI.fxml"));
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
    private void btn_Report(ActionEvent event) {
    }

    @FXML
    private void btn_historyBooking(ActionEvent event) {
    }

    @FXML
    private void btn_flightManagerment(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(""));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setTitle("Find Flight ");
        stage.show();
    }

    @FXML
    private void btn_flightSearch(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/flightmanagerment/Form/Home/Confirmed_Find_Flight/ConfirmedFindFlightUI.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setTitle("Find Flight ");
        stage.show();
    }

    @FXML
    private void btn_changePassword(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/flightmanagerment/Form/Account/ChangePass/ChangePassUI.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setTitle("Change Password");
        stage.show();
    }

    @FXML
    private void btn_infoAccount(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/flightmanagerment/Form/Home/InfoStaff/InfoStaffUI.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setTitle("Info Staff ");
        stage.show();
    }

    @FXML
    private void btn_registerStaff(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/flightmanagerment/Form/Account/RegisterStaff/RegisterStaffUI.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setTitle("Register Staff ");
        stage.show();
    }

}
