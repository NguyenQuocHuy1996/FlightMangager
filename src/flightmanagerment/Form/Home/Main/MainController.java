/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightmanagerment.Form.Home.Main;

import java.io.IOException;
import java.net.URL;
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
public class MainController implements Initializable {

//    @FXML
//    private TextField txtEmail;
//    @FXML
//    private TextField txtPass;
//    
//    @FXML
//    private void Main(ActionEvent event) {
//        if((txtEmail.getText().equals("admin")) && (txtPass.getText().equals("admin123"))){
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Dang nhap thanh cong");
//            alert.show();
//        }
//    }
    @FXML
    private void btn_login(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/flightmanagerment/Form/Account/Login/LoginUI.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
        stage.setScene(scene);
        stage.setTitle("Login ");
        stage.show();

    }

    @FXML
    private void btn_flightSearch(ActionEvent event) {

    }

    @FXML
    private void btn_flightManagement(ActionEvent event) {

    }

    @FXML
    private void btn_historyBooking(ActionEvent event) {

    }

    @FXML
    private void btn_historyBookingOfEmployee(ActionEvent event) {

    }

    @FXML
    private void btn_report(ActionEvent event) {

    }

    @FXML
    private void btn_infomation(ActionEvent event) {

    }

    @FXML
    private void btn_logout(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
