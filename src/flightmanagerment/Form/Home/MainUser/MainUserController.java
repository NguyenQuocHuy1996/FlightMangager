/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightmanagerment.Form.Home.MainUser;

import flightmanagerment.Function.CustomerDAO;
import flightmanagerment.Model.Customer;
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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Quoc Huy
 */
public class MainUserController implements Initializable {

    @FXML
    private Label lbl_userName;

//    @FXML
//    private TextField txtEmail;
//    @FXML
//    private TextField txtPass;
//    
//    @FXML
//    private void MainUser(ActionEvent event) {
//        if((txtEmail.getText().equals("admin")) && (txtPass.getText().equals("admin123"))){
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Dang nhap thanh cong");
//            alert.show();
//        }
//    }
    @FXML
    private void btn_flightSearch(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/flightmanagerment/Form/Home/Confirmed_Find_Flight/ConfirmedFindFlightUI.fxml"));
        Variable_Static.LinkUI(event, root, "Main");
    }

    @FXML
    private void btn_logout(ActionEvent event) throws IOException {
        Variable_Static.USERNAME = "";
        Parent root = FXMLLoader.load(getClass().getResource("/flightmanagerment/Form/Account/Login/LoginUI.fxml"));
       Variable_Static.LinkUI(event, root, "Login");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Customer cus = new Customer();
        try {
            cus = CustomerDAO.getCus(Variable_Static.USERNAME);
            lbl_userName.setText(cus.getFirstName());
        } catch (SQLException ex) {
//            Logger.getLogger(MainUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btn_historyBooking(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/flightmanagerment/Form/Home/CustomerHistoryBooking/CustomerHistoryBookingUI.fxml"));
        Variable_Static.LinkUI(event, root, "History Booking");
    }

    @FXML
    private void btn_changePassword(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/flightmanagerment/Form/Account/ChangePass/ChangePassUI.fxml"));
     Variable_Static.LinkUI(event, root, "Change Password");
    }

    @FXML
    private void btn_infoAccount(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/flightmanagerment/Form/Home/Info/InfoUI.fxml"));
     Variable_Static.LinkUI(event, root, "Info Customer");
    }

    @FXML
    private void btn_notification(ActionEvent event) {
    }

}
