/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightmanagerment.Form.Account.RegisterStaff;

import flightmanagerment.Function.CityDAO;
import flightmanagerment.Function.CustomerDAO;
import flightmanagerment.Function.DistrictDAO;
import flightmanagerment.Function.EmployeeDAO;
import flightmanagerment.Model.Customer;
import flightmanagerment.Model.Employee;
import flightmanagerment.Model.Variable_Static;
import java.io.IOException;
import java.net.URL;
import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 *
 * @author Quoc Huy
 */
public class RegisterStaffController implements Initializable {

    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField confirm_password;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField ic_card;
    @FXML
    private TextField phoneNumber;
    @FXML
    private DatePicker dateOfBirth;
    @FXML
    private RadioButton sex_male;
    @FXML
    private RadioButton sex_female;
    @FXML
    private TextField education_level;
    @FXML
    private ComboBox<String> department;
    @FXML
    private ComboBox<String> homeTown;
    @FXML
    private TextField add_number;
    @FXML
    private TextField add_Street;
    @FXML
    private ComboBox<String> add_city;
    @FXML
    private ComboBox<String> add_district;
    @FXML
    private ImageView image;

//    @FXML
//    private TextField txtEmail;
//    @FXML
//    private TextField txtPass;
//    
//    @FXML
//    private void RegisterStaff(ActionEvent event) {
//        if((txtEmail.getText().equals("admin")) && (txtPass.getText().equals("admin123"))){
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Dang nhap thanh cong");
//            alert.show();
//        
//    
    @FXML
    private void btn_RegisterStaff(ActionEvent event) throws SQLException, IOException {
        Alert a = new Alert(Alert.AlertType.ERROR);
        Boolean check = false;
        List<String> list = EmployeeDAO.getEmail();
        for (String string : list) {
            if (email.getText().equals(string)) {
                check = true;
            }
        }
        Boolean sex;
        if (sex_male.isSelected()) {
            sex = true;
        } else {
            sex = false;
        }
        Date date = Date.valueOf(dateOfBirth.getValue()); // convert từ local date ( datePicker ) wa date sql
        Employee emp = new Employee(email.getText(), password.getText(), firstName.getText(), lastName.getText(), date, ic_card.getText(), education_level.getText(), department.getValue(), homeTown.getValue(), sex, phoneNumber.getText(), add_number.getText(), add_Street.getText(), add_district.getValue(), add_city.getValue());
        if (confirm_password.getText().equals(password.getText())) {
            int function = EmployeeDAO.insert(emp);
            if ((function == -1) || (function == -2) || (function == -3) || (function == -4) || (function == -5) || (function == -6) || (function == -7) || (function == -8) || (function == -9) || (function == -10) || (function == -11) || (function == -12) || (function == -13) || (function == -14) || (function == -15) || (function == -16)) {
                a.setTitle("ERROR");
                a.setContentText("Vui lòng nhập đầy đủ thông tin!");
                a.show();
            } else {
                if (!check) {
                    Alert.AlertType type = Alert.AlertType.INFORMATION;
                    a.setAlertType(type);
                    a.setTitle("Đăng ký thành công!");
                    a.setContentText("Chức mừng bạn đã đăng ký thành công ! - với email : " + emp.getEmail());
                    a.showAndWait();
                    Parent root = FXMLLoader.load(getClass().getResource("/flightmanagerment/Form/Home/MainStaff/MainStaffUI.fxml"));
                    Variable_Static.LinkUI(event, root, "Main Staff");
                } else {
                    Alert.AlertType type = Alert.AlertType.ERROR;
                    a.setAlertType(type);
                    a.setTitle("ERROR");
                    a.setContentText("Email đã tồn tại");
                    a.show();
                }
            }
        } else {
            a.setTitle("ERROR");
            a.setContentText("Xác nhận mật khẩu không trùng khớp!");
            a.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<String> list = CityDAO.getAllCity();
        homeTown.setItems(list);
        homeTown.getSelectionModel().select(1);
        homeTown.setPromptText(homeTown.getConverter().toString(homeTown.getValue()));

        add_city.setItems(list);
        add_city.getSelectionModel().select(1);
        add_city.setPromptText(add_city.getConverter().toString(add_city.getValue()));

        department.setItems(EmployeeDAO.getDepartment());
        department.getSelectionModel().select(1);
        department.setPromptText(department.getConverter().toString(department.getValue()));

        if (sex_male.isSelected()) {
            sex_male.setSelected(true);
            sex_female.setSelected(false);
        } else {
            sex_male.setSelected(false);
            sex_female.setSelected(true);
        }
    }

    @FXML
    private void btn_back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/flightmanagerment/Form/Home/MainStaff/MainStaffUI.fxml"));
        Variable_Static.LinkUI(event, root, "Main Staff");
    }

    @FXML
    private void btn_Choose(ActionEvent event) {
        int maTinh = CityDAO.getMaTinh(add_city.getValue());

        ObservableList<String> list = DistrictDAO.getDistrict(maTinh);
        add_district.setItems(list);
        add_district.getSelectionModel().select(1);
        add_district.setPromptText(add_district.getConverter().toString(add_district.getValue()));
    }

}
