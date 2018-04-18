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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Quoc Huy
 */
public class MainUserController implements Initializable {

    @FXML
    private Label lbl_userName;
    @FXML
    private Button notification;

    private Boolean status;
    @FXML
    private Label lbl_Time;

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
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), e -> {
            Date dateCreated = new Date();

            SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String time = date.format(dateCreated);
            lbl_Time.setText(time);
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        Customer cus = new Customer();

        try {
            cus = CustomerDAO.getCus(Variable_Static.USERNAME);
            Variable_Static.IDACCOUNT = cus.getIdAccount();
            lbl_userName.setText(cus.getLastName());
            status = CustomerDAO.getNotification(Variable_Static.USERNAME);

            if (status) {
                notification.setText("Tắt thông báo!");
            } else {
                notification.setText("Bật thông báo!");
            }
        } catch (SQLException ex) {
//            Logger.getLogger(MainUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btn_historyBooking(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/flightmanagerment/Form/Home/HistoryBooking/HistoryBookingUI.fxml"));
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
    private void btn_notification(ActionEvent event) throws SQLException {
        if (status) {
            CustomerDAO.updateNotification(Boolean.FALSE, Variable_Static.USERNAME);
            status = CustomerDAO.getNotification(Variable_Static.USERNAME);
            notification.setText("Bật thông báo!");
        } else {
            CustomerDAO.updateNotification(Boolean.TRUE, Variable_Static.USERNAME);
            status = CustomerDAO.getNotification(Variable_Static.USERNAME);
            notification.setText("Tắt thông báo!");
        }

    }

}
