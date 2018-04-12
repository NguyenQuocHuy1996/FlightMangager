/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightmanagerment.Form.Home.HistoryBooking;

import flightmanagerment.Form.Home.FlightManager.FlightManagerController;
import flightmanagerment.Function.CustomerDAO;
import flightmanagerment.Function.EmployeeDAO;
import flightmanagerment.Model.Customer;
import flightmanagerment.Model.Employee;
import flightmanagerment.Model.Flight;
import flightmanagerment.Model.Variable_Static;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Quoc Huy
 */
public class HistoryBookingController implements Initializable {

    @FXML
    private Label lbl_userName;
    @FXML
    private TableColumn<?, ?> STTCol;
    @FXML
    private TableColumn<?, ?> idFlightCol;
    @FXML
    private TableColumn<?, ?> brandCol;
    @FXML
    private TableColumn<?, ?> originCol;
    @FXML
    private TableColumn<?, ?> destinationCol;
    @FXML
    private TableColumn<?, ?> departCol;
    @FXML
    private TableColumn<?, ?> flight_departCol;
    @FXML
    private TableColumn<?, ?> arrivalCol;
    @FXML
    private TableColumn<?, ?> flight_arrivalCol;
    @FXML
    private TableColumn<?, ?> passengerCol;

    private ObservableList<Flight> list;

    private void setCellTable() {
        idFlightCol.setCellValueFactory(new PropertyValueFactory<>("idFlight"));
        brandCol.setCellValueFactory(new PropertyValueFactory<>("brand"));
//        .setCellValueFactory(new PropertyValueFactory<>("flight_number"));
        originCol.setCellValueFactory(new PropertyValueFactory<>("origin"));
        destinationCol.setCellValueFactory(new PropertyValueFactory<>("destination"));
        departCol.setCellValueFactory(new PropertyValueFactory<>("depart"));
        flight_departCol.setCellValueFactory(new PropertyValueFactory<>("flight_depart"));
        arrivalCol.setCellValueFactory(new PropertyValueFactory<>("arrival"));
        flight_arrivalCol.setCellValueFactory(new PropertyValueFactory<>("flight_arrival"));
        passengerCol.setCellValueFactory(new PropertyValueFactory<>("passenger"));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Customer cus = new Customer();
        try {
            cus = CustomerDAO.getCus(Variable_Static.USERNAME);
        } catch (SQLException ex) {
            Logger.getLogger(FlightManagerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        lbl_userName.setText(cus.getFirstName());
        setCellTable();
        list = FXCollections.observableArrayList();

    }

    @FXML
    private void btn_back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/flightmanagerment/Form/Home/MainUser/MainUserUI.fxml"));
        Variable_Static.LinkUI(event, root, "Main Customer");
    }

}
