/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightmanagerment.Form.Home.InfoCustomerHistoryBooking;

import flightmanagerment.Function.EmployeeDAO;
import flightmanagerment.Function.FlightDAO;
import flightmanagerment.Function.Seat_TicketDAO;
import flightmanagerment.Model.Employee;
import flightmanagerment.Model.Flight;
import flightmanagerment.Model.Seat_Ticket;
import flightmanagerment.Model.Variable_Static;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Quoc Huy
 */
public class InfoCustomerHistoryBookingController implements Initializable {

    @FXML
    private Label lbl_userName;
    @FXML
    private Label lbl_idFlight;
    @FXML
    private Label lbl_Brand;
    @FXML
    private Label lbl_price;
    @FXML
    private Label lbl_origin;
    @FXML
    private Label lbl_destination;
    @FXML
    private Label lbl_Depart;
    @FXML
    private Label lbl_flightDepart;
    @FXML
    private Label lbl_arrival;
    @FXML
    private Label lbl_flightArrival;
    @FXML
    private Label lbl_passenger;
    private TableView<Seat_Ticket> table;
    @FXML
    private TableColumn<?, ?> idFlightCol;
    @FXML
    private TableColumn<?, ?> firstNameCol;
    @FXML
    private TableColumn<?, ?> lastNameCol;
    @FXML
    private TableColumn<?, ?> icCardCol;
    @FXML
    private TableColumn<?, ?> codeCol;
    @FXML
    private TableColumn<?, ?> statusCol;

    private ObservableList<Seat_Ticket> list;
    @FXML
    private TableView<Seat_Ticket> tableSold;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            Employee emp = EmployeeDAO.getEmp(Variable_Static.USERNAME);
            lbl_userName.setText(emp.getLastName());
        } catch (SQLException ex) {
            Logger.getLogger(InfoCustomerHistoryBookingController.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            // TODO
            loadDB();
        } catch (SQLException ex) {
            Logger.getLogger(InfoCustomerHistoryBookingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Flight f = FlightDAO.getInfoFlight(Variable_Static.IDFSEAT);
            lbl_Brand.setText(f.getBrand());
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            String depart = df.format(f.getDepart());
            lbl_Depart.setText(depart);
            String arrival = df.format(f.getArrival());
            lbl_arrival.setText(arrival);
            lbl_destination.setText(f.getDestination());
            lbl_origin.setText(f.getOrigin());
            lbl_flightArrival.setText(f.getFlight_arrival());
            lbl_flightDepart.setText(f.getFlight_depart());
            lbl_idFlight.setText(Integer.toString(f.getIdFlight()));
            lbl_passenger.setText(Integer.toString(f.getPassenger()));
            lbl_price.setText(Double.toString(f.getPrice()));
        } catch (SQLException ex) {
            Logger.getLogger(InfoCustomerHistoryBookingController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void loadDB() throws SQLException {
        setCellTable();
        list = Seat_TicketDAO.getAllSeat(Variable_Static.IDFSEAT);
        tableSold.setItems(list);
    }

    private void setCellTable() {
        idFlightCol.setCellValueFactory(new PropertyValueFactory<>("idFlight"));
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        icCardCol.setCellValueFactory(new PropertyValueFactory<>("ic_card"));
        codeCol.setCellValueFactory(new PropertyValueFactory<>("code"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    @FXML
    private void btn_back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/flightmanagerment/Form/Home/CustomerHistoryBooking/CustomerHistoryBookingUI.fxml"));
        Variable_Static.LinkUI(event, root, "History Booking");
    }
}
