/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightmanagerment.Form.Home.Info;

import flightmanagerment.Function.CityDAO;
import flightmanagerment.Function.CustomerDAO;
import flightmanagerment.Function.DistrictDAO;
import flightmanagerment.Model.City;
import flightmanagerment.Model.Customer;
import flightmanagerment.Model.Variable_Static;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
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
    private RadioButton Male;
    @FXML
    private RadioButton Female;
    @FXML
    private TextField add_Number;
    @FXML
    private TextField add_Street;
    @FXML
    private ComboBox<String> cbb_homeTown;
    @FXML
    private ComboBox<String> cbb_City;
    @FXML
    private ComboBox<String> cbb_District;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private ToggleGroup GroupSex;

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
    private void getInfo() {
        Customer cus = new Customer();
        try {
            cus = CustomerDAO.getCus(Variable_Static.USERNAME);
            email.setText(cus.getEmail());
            password.setText(cus.getPassword());
            firstName.setText(cus.getFirstName());
            lastName.setText(cus.getLastName());
            ic_Card.setText(cus.getIc_Card());

            if (cus.getDateOfBirth() != null) {
//                dateOfBirth.setValue(cus.getDateOfBirth().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy");
                DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
                String date = df.format(cus.getDateOfBirth());
                dateOfBirth.setValue(LocalDate.parse(date,formatter));
                System.out.println("asd");
            }

            if (cus.getSex()) {
                Male.setSelected(true);
                Female.setSelected(false);
            } else {
                Male.setSelected(false);
                Female.setSelected(true);
            }
            phoneNumber.setText(cus.getPhoneNumber());
            add_Number.setText(cus.getAddress_number());
            add_Street.setText(cus.getAddress_street());
            cbb_homeTown.setValue(cus.getHomeTown());
            cbb_City.setValue(cus.getAddress_city());
            cbb_District.setValue(cus.getAddress_district());
            // TODO
        } catch (SQLException ex) {
//            Logger.getLogger(InfoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> list = CityDAO.getAllCity();
        cbb_homeTown.setItems(list);
        cbb_homeTown.getSelectionModel().select(1);
        cbb_homeTown.setPromptText(cbb_homeTown.getConverter().toString(cbb_homeTown.getValue()));

        cbb_City.setItems(list);
        cbb_City.getSelectionModel().select(1);
        cbb_City.setPromptText(cbb_City.getConverter().toString(cbb_City.getValue()));

        getInfo();
    }

    @FXML
    private void btn_Reset(ActionEvent event) {
        getInfo();
    }

    private void btn_Update1(ActionEvent event) {
//        phoneNumber.setEditable(true);
//        lastName.setEditable(false);
//        firstName.setEditable(true);
//        sex_male.setDisable(false);
//        sex_female.setDisable(false);
//        _image.setImage(new Image(""));
    }

    @FXML
    private void btn_Update2(ActionEvent event) {
        firstName.setEditable(true);
        lastName.setEditable(true);
        phoneNumber.setEditable(true);
        ic_Card.setEditable(true);
        dateOfBirth.setDisable(false);
        Male.setDisable(false);
        Female.setDisable(false);
    }

    @FXML
    private void btn_Update3(ActionEvent event) {
        add_Number.setEditable(true);
        add_Street.setEditable(true);
        cbb_homeTown.setDisable(false);
        cbb_City.setDisable(false);
        cbb_District.setDisable(false);
    }

    @FXML
    private void btn_Back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/flightmanagerment/Form/Home/MainUser/MainUserUI.fxml"));
       Variable_Static.LinkUI(event, root, "Main Customer");
    }

    @FXML
    private void btn_ChooseCity(ActionEvent event) {
        int maTinh = CityDAO.getMaTinh(cbb_City.getValue());

        ObservableList<String> list = DistrictDAO.getDistrict(maTinh);
        cbb_District.setItems(list);
        cbb_District.getSelectionModel().select(1);
        cbb_District.setPromptText(cbb_District.getConverter().toString(cbb_District.getValue()));

    }

    @FXML
    private void btn_update(ActionEvent event) throws SQLException {
//        Boolean sex;
//        if (Male.isSelected()) {
//            sex = true;
//        } else {
//            sex = false;
//        }
//
//        Customer cus = new Customer(firstName.getText(), lastName.getText(), dateOfBirth.get, ic_Card.getText(), cbb_homeTown.getValue(), sex, phoneNumber.getText(), add_Number.getText(), add_Street.getText(), cbb_District.getValue(), cbb_City.getValue());
//        cus = CustomerDAO.getCus(Variable_Static.USERNAME);
//        int function = CustomerDAO.update(cus);

    }

}
