/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightmanagerment.Form.Home.Info;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author Quoc Huy
 */
public class InfoController implements Initializable {

    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField phoneNumber;
    @FXML
    private TextField ic_Card;
    @FXML
    private DatePicker dateOfBirth;
    @FXML
    private RadioButton sex_male;
    @FXML
    private RadioButton sex_female;
    @FXML
    private TextField add_Number;
    @FXML
    private TextField add_Street;
    @FXML
    private ComboBox<?> homeTown;
    @FXML
    private ComboBox<?> add_City;
    @FXML
    private ComboBox<?> add_District;
    @FXML
    private ImageView _image;
    @FXML
    private ToggleGroup GroupRadioButton;

//    @FXML
//    private TextField txtEmail;
//    @FXML
//    private TextField txtPass;
//    
//    @FXML
//    private void Info(ActionEvent event) {
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
    private void btn_Update(ActionEvent event) {
    }

    @FXML
    private void btn_Reset(ActionEvent event) {
    }

    @FXML
    private void btn_Update1(ActionEvent event) {
        phoneNumber.setEditable(true);
        lastName.setEditable(false);
        firstName.setEditable(true);
        sex_male.setDisable(false);
        sex_female.setDisable(false);
        _image.setImage(new Image(""));
    }

    @FXML
    private void btn_Update2(ActionEvent event) {
        try {
            Stage stage = new Stage();
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            File file = fileChooser.showOpenDialog(stage);
            if (file != null) {
                System.out.println(file.getPath());
                _image.setImage(new Image("..\\..\\..\\Asset\\img\\icon\\user.png"));
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @FXML
    private void btn_Update3(ActionEvent event) {
    }

}
