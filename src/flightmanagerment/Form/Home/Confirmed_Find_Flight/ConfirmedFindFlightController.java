/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightmanagerment.Form.Home.Confirmed_Find_Flight;

import flightmanagerment.Function.CityDAO;
import flightmanagerment.Function.CustomerDAO;
import flightmanagerment.Function.EmployeeDAO;
import flightmanagerment.Function.FlightDAO;
import flightmanagerment.Model.City;
import flightmanagerment.Model.Customer;
import flightmanagerment.Model.Employee;
import flightmanagerment.Model.Flight;
import flightmanagerment.Model.Variable_Static;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Quoc Huy
 */
public class ConfirmedFindFlightController implements Initializable {

    @FXML
    private Label lbl_userName;
    @FXML
    private ComboBox<String> cbb_origin;
    @FXML
    private ComboBox<String> cbb_destination;
    @FXML
    private DatePicker depart;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> list = CityDAO.getAllCity();
        cbb_origin.setItems(list);
        cbb_origin.getSelectionModel().select(1);
        cbb_origin.setPromptText(cbb_origin.getConverter().toString(cbb_origin.getValue()));

        cbb_destination.setItems(list);
        cbb_destination.getSelectionModel().select(1);
        cbb_destination.setPromptText(cbb_origin.getConverter().toString(cbb_origin.getValue()));
        Customer cus = new Customer();
        Employee emp = new Employee();
        try {
            cus = CustomerDAO.getCus(Variable_Static.USERNAME);
            emp = EmployeeDAO.getEmp(Variable_Static.USERNAME);
            if (cus != null) {
                lbl_userName.setText(cus.getFirstName());
            } else if (emp != null) {
                lbl_userName.setText(emp.getFirstName());
            } else {
                lbl_userName.setText("Khách hàng");
            }

        } catch (SQLException ex) {
//            Logger.getLogger(MainUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO
    }

    @FXML
    private void btn_back(ActionEvent event) throws IOException, SQLException {
        Customer cus = CustomerDAO.getCus(Variable_Static.USERNAME);
        Employee emp = EmployeeDAO.getEmp(Variable_Static.USERNAME);
        if (cus != null) {
            Parent root = FXMLLoader.load(getClass().getResource("/flightmanagerment/Form/Home/MainUser/MainUserUI.fxml"));
            Variable_Static.LinkUI(event, root, "Main Customer");

        } else if (emp != null) {
            Parent root = FXMLLoader.load(getClass().getResource("/flightmanagerment/Form/Home/MainStaff/MainStaffUI.fxml"));
            Variable_Static.LinkUI(event, root, "Main Staff");
        } else {
            Parent root = FXMLLoader.load(getClass().getResource("/flightmanagerment/Form/Home/Main/MainUI.fxml"));
            Variable_Static.LinkUI(event, root, "Main");
        }

    }

    @FXML
    private void btn_findFlight(ActionEvent event) throws IOException, SQLException {

        Alert a = new Alert(Alert.AlertType.ERROR);
        if (depart.getValue() == null) {
            a.setTitle("ERROR");
            a.setContentText("Vui lòng nhập đủ thông tin");
            a.show();
        } else {
            Date date = Date.valueOf(depart.getValue()); // convert từ local date ( datePicker ) wa date sql
            System.out.println(cbb_origin.getValue());
            System.out.println(cbb_destination.getValue());
            System.out.println(date);
            List<Flight> list = Variable_Static.searchFlight(cbb_origin.getValue(), cbb_destination.getValue(), date);
            Variable_Static.ORIGIN = cbb_origin.getValue();
            Variable_Static.DESTINATION = cbb_destination.getValue();
            Variable_Static.DEPART = date;
            if (list == null) {
                a.setTitle("Không có chuyến bay phù hợp");
                a.setContentText("Hiện tại không có chuyến bay phù hợp với yêu cầu!");
                a.show();
            } else {
                Parent root = FXMLLoader.load(getClass().getResource("/flightmanagerment/Form/Home/MainFilght/MainFilghtUI.fxml"));
                Variable_Static.LinkUI(event, root, "Main Flight");
            }
        }

    }

    @FXML
    private void btn_choosefrom(ActionEvent event) {
    }

}
