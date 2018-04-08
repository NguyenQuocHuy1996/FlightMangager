/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightmanagerment.Form.Home.InfoStaff;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 *
 * @author Quoc Huy
 */
public class InfoStaffController implements Initializable {

    @FXML
    private ImageView image;
    @FXML
    private Label email;
    @FXML
    private Label password;
    @FXML
    private Label firstName;
    @FXML
    private HBox lastName;
    @FXML
    private Label ic_Card;
    @FXML
    private Label phoneNumber;
    @FXML
    private Label dateOfBirth;
    @FXML
    private RadioButton sex_Male;
    @FXML
    private RadioButton sex_Female;
    @FXML
    private Label education_Level;
    @FXML
    private Label department;
    @FXML
    private Label add_Number;
    @FXML
    private Label add_Street;
    @FXML
    private Label homeTown;
    @FXML
    private Label add_City;
    @FXML
    private Label add_District;

//    @FXML
//    private TextField txtEmail;
//    @FXML
//    private TextField txtPass;
//    
//    @FXML
//    private void InfoStaff(ActionEvent event) {
//        if((txtEmail.getText().equals("admin")) && (txtPass.getText().equals("admin123"))){
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Dang nhap thanh cong");
//            alert.show();
//        }
//    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void btn_Back(ActionEvent event) {
    }

}
