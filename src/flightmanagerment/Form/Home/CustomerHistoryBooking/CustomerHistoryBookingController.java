/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightmanagerment.Form.Home.CustomerHistoryBooking;

import flightmanagerment.Function.EmployeeDAO;
import flightmanagerment.Function.FlightDAO;
import flightmanagerment.Model.Employee;
import flightmanagerment.Model.Flight;
import flightmanagerment.Model.Variable_Static;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Quoc Huy
 */
public class CustomerHistoryBookingController implements Initializable {

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

    @FXML
    private Label lbl_userName;
    @FXML
    private TableColumn<?, ?> priceCol;
    @FXML
    private TableView<Flight> table;
    @FXML
    private TableColumn<?, ?> flight_numberCol;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Employee emp = EmployeeDAO.getEmp(Variable_Static.USERNAME);
            lbl_userName.setText(emp.getLastName());

        } catch (SQLException ex) {
            Logger.getLogger(CustomerHistoryBookingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            loadDB();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerHistoryBookingController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadDB() throws SQLException {
        setCellTable();
        list = FlightDAO.getAllFlight();
        table.setItems(list);
    }

    private void setCellTable() {
        idFlightCol.setCellValueFactory(new PropertyValueFactory<>("idFlight"));
        brandCol.setCellValueFactory(new PropertyValueFactory<>("brand"));
        originCol.setCellValueFactory(new PropertyValueFactory<>("origin"));
        destinationCol.setCellValueFactory(new PropertyValueFactory<>("destination"));
        departCol.setCellValueFactory(new PropertyValueFactory<>("depart"));
        flight_departCol.setCellValueFactory(new PropertyValueFactory<>("flight_depart"));
        arrivalCol.setCellValueFactory(new PropertyValueFactory<>("arrival"));
        flight_arrivalCol.setCellValueFactory(new PropertyValueFactory<>("flight_arrival"));
        passengerCol.setCellValueFactory(new PropertyValueFactory<>("passenger"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        flight_numberCol.setCellValueFactory(new PropertyValueFactory<>("flight_number"));
    }

    @FXML
    private void btn_back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/flightmanagerment/Form/Home/MainStaff/MainStaffUI.fxml"));
        Variable_Static.LinkUI(event, root, "Main Staff");
    }

    @FXML
    private void getInfoHistoryBooking(MouseEvent event) {
    }

    @FXML
    private void btnDetail(ActionEvent event) throws IOException {
        Variable_Static.IDFSEAT = table.getSelectionModel().getSelectedItem().getIdFlight();
        Parent root = FXMLLoader.load(getClass().getResource("/flightmanagerment/Form/Home/InfoCustomerHistoryBooking/InfoCustomerHistoryBookingUI.fxml"));
        Variable_Static.LinkUI(event, root, "Detail HistoryBooking");
    }

}
