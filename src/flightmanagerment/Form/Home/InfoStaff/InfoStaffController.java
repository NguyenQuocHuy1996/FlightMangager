/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightmanagerment.Form.Home.InfoStaff;

import flightmanagerment.Function.CustomerDAO;
import flightmanagerment.Function.EmployeeDAO;
import flightmanagerment.Model.Customer;
import flightmanagerment.Model.Employee;
import flightmanagerment.Model.Variable_Static;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javax.print.attribute.standard.DateTimeAtCompleted;

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
    private Label lastName;
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
    private Label education_level;
    @FXML
    private Label department;
    @FXML
    private Label add_Num;
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

        Employee emp = new Employee();
        try {
            emp = EmployeeDAO.getEmp(Variable_Static.USERNAME);
            email.setText(emp.getEmail());
            password.setText(emp.getPassword());
            firstName.setText(emp.getFirstName());
            lastName.setText(emp.getLastName());
            ic_Card.setText(emp.getIc_Card());
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            String date = df.format(emp.getDateOfBirth());
            dateOfBirth.setText(date);
            if (emp.getSex()) {
                sex_Male.setSelected(true);
                sex_Female.setSelected(false);
            } else {
                sex_Male.setSelected(false);
                sex_Female.setSelected(true);
            }
            phoneNumber.setText(emp.getPhoneNumber());
            add_Num.setText(emp.getAddress_number());
            add_Street.setText(emp.getAddress_street());
            homeTown.setText(emp.getHomeTown());
            department.setText(emp.getDepartment());
            add_City.setText(emp.getAddress_city());
            add_District.setText(emp.getAddress_district());
            education_level.setText(emp.getEducation_level());

            // TODO
        } catch (SQLException ex) {
//            Logger.getLogger(InfoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btn_Back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/flightmanagerment/Form/Home/MainStaff/MainStaffUI.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setTitle("Main Staff ");
        stage.show();
    }

}
