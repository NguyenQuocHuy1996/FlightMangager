/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightmanagerment.Form.Home.Main;

import flightmanagerment.Form.Account.Login.LoginController;
import flightmanagerment.Model.Variable_Static;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Quoc Huy
 */
public class MainController implements Initializable {

    @FXML
    private Label lbl_time;

    @FXML
    private void btn_login(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/flightmanagerment/Form/Account/Login/LoginUI.fxml"));
        Variable_Static.LinkUI(event, root, "Login");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), e -> {
            Date dateCreated = new Date();

            SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String time = date.format(dateCreated);
            lbl_time.setText(time);
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        // TODO
    }

    @FXML
    private void btn_flightSearch(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/flightmanagerment/Form/Home/Confirmed_Find_Flight/ConfirmedFindFlightUI.fxml"));
        Variable_Static.LinkUI(event, root, "FInd Flight");
    }

    @FXML
    private void btn_register(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/flightmanagerment/Form/Account/Register/RegisterUI.fxml"));
        Variable_Static.LinkUI(event, root, "Register");
    }

}
