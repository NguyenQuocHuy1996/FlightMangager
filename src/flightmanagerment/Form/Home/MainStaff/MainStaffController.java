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
        try {
            emp = EmployeeDAO.getEmp(Variable_Static.USERNAME);
            lbl_userName.setText(emp.getFirstName());
        } catch (SQLException ex) {
//            Logger.getLogger(MainStaffController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void btn_Logout(ActionEvent event) throws IOException {
        Variable_Static.USERNAME = "";
        Parent root = FXMLLoader.load(getClass().getResource("/flightmanagerment/Form/Account/Login/LoginUI.fxml"));
        Variable_Static.LinkUI(event, root, "Login");
    }

    @FXML
    private void btn_historyBooking(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/flightmanagerment/Form/Home/CustomerHistoryBooking/CustomerHistoryBookingUI.fxml"));
        Variable_Static.LinkUI(event, root, "History Booking");

    }

    @FXML
    private void btn_flightManagerment(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/flightmanagerment/Form/Home/FlightManager/FlightManagerUI.fxml"));
        Variable_Static.LinkUI(event, root, "Flight Manager");
    }

    @FXML
    private void btn_flightSearch(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/flightmanagerment/Form/Home/Confirmed_Find_Flight/ConfirmedFindFlightUI.fxml"));
        Variable_Static.LinkUI(event, root, "Find Flight");
    }

    @FXML
    private void btn_changePassword(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/flightmanagerment/Form/Account/ChangePass/ChangePassUI.fxml"));
        Variable_Static.LinkUI(event, root, "Change Password");
    }

    @FXML
    private void btn_infoAccount(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/flightmanagerment/Form/Home/InfoStaff/InfoStaffUI.fxml"));
        Variable_Static.LinkUI(event, root, "Info Staff");
    }

    @FXML
    private void btn_registerStaff(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/flightmanagerment/Form/Account/RegisterStaff/RegisterStaffUI.fxml"));
        Variable_Static.LinkUI(event, root, "Register Staff");
    }

    @FXML
    private void btn_flightBrand(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/flightmanagerment/Form/Home/FlightBrandManager/FlightBrandManagerUI.fxml"));
        Variable_Static.LinkUI(event, root, "Flight Brand");
    }

}
