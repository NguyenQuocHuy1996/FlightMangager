/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightmanagerment.Form.Home.FlightManager;

import flightmanagerment.Config.ConnectDB;
import flightmanagerment.Function.CityDAO;
import flightmanagerment.Function.EmployeeDAO;
import flightmanagerment.Function.FlightDAO;
import flightmanagerment.Model.Employee;
import flightmanagerment.Model.Flight;
import flightmanagerment.Model.Variable_Static;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author Quoc Huy
 */
public class FlightManagerController implements Initializable {

    @FXML
    private TextField txt_idFlight;
    @FXML
    private TextField txt_brand;
    @FXML
    private TextField txt_flightNumber;
    @FXML
    private TableColumn<Flight, Integer> idFlightCol;
    @FXML
    private TableColumn<Flight, String> brandCol;
    @FXML
    private TableColumn<Flight, String> flight_numberCol;
    @FXML
    private TableColumn<Flight, String> originCol;
    @FXML
    private TableColumn<Flight, String> destinationCol;
    @FXML
    private TableColumn<Flight, Date> departCol;
    @FXML
    private TableColumn<Flight, String> flight_departCol;
    @FXML
    private TableColumn<Flight, Date> arrivalCol;
    @FXML
    private TableColumn<Flight, String> flight_arrivalCol;
    @FXML
    private TableColumn<Flight, Integer> passengerCol;
    @FXML
    private TableView<Flight> table;

    private ObservableList<Flight> list;

    @FXML
    private Label lbl_userName;
    @FXML
    private ComboBox<String> cbb_origin;
    @FXML
    private ComboBox<String> cbb_destination;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Employee emp = new Employee();
        try {
            emp = EmployeeDAO.getEmp(Variable_Static.USERNAME);
        } catch (SQLException ex) {
            Logger.getLogger(FlightManagerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        lbl_userName.setText(emp.getFirstName());
        list = FXCollections.observableArrayList();
        setCellTable();
        try {
            loadDB();
        } catch (SQLException ex) {
            Logger.getLogger(FlightManagerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<String> list = CityDAO.getAllCity();
        cbb_origin.setItems(list);
        cbb_origin.getSelectionModel().select(1);
        cbb_origin.setPromptText(cbb_origin.getConverter().toString(cbb_origin.getValue()));

        cbb_destination.setItems(list);
        cbb_destination.getSelectionModel().select(1);
        cbb_destination.setPromptText(cbb_destination.getConverter().toString(cbb_destination.getValue()));
    }

    private void loadDB() throws SQLException {
        list = FlightDAO.getAllFlight();
        table.setItems(list);

//        try {
//            connectDB = new ConnectDB();
//            conn = connectDB.getConnect();
//            String sql = "select * from flight";
//            prest = conn.prepareStatement(sql);
//            rs = prest.executeQuery();
//            while (rs.next()) {
//                int idFlight = rs.getInt("idFlight");
//                String origin = rs.getString("origin");
//                String destination = rs.getString("destination");
//                Date depart = rs.getDate("depart");
//                Date arrival = rs.getDate("arrival");
//                int passenger = rs.getInt("passenger");
//                String brand = rs.getString("brand");
//                String flight_number = rs.getString("flight_number");
//                String flight_depart = rs.getString("flight_depart");
//                String flight_arrival = rs.getString("flight_arrival");
//                double price = rs.getDouble("price");
//
//                Flight f = new Flight(idFlight, origin, destination, depart, arrival, passenger, brand, flight_number, flight_arrival, flight_depart, price);
//                list.add(f);
//            }
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        table.setItems(list);
    }

    private void setCellTable() {
        idFlightCol.setCellValueFactory(new PropertyValueFactory<Flight, Integer>("idFlight"));
        brandCol.setCellValueFactory(new PropertyValueFactory<Flight, String>("brand"));
        flight_numberCol.setCellValueFactory(new PropertyValueFactory<Flight, String>("flight_number"));
        originCol.setCellValueFactory(new PropertyValueFactory<Flight, String>("origin"));
        destinationCol.setCellValueFactory(new PropertyValueFactory<Flight, String>("destination"));
        departCol.setCellValueFactory(new PropertyValueFactory<Flight, Date>("depart"));
        flight_departCol.setCellValueFactory(new PropertyValueFactory<Flight, String>("flight_depart"));
        arrivalCol.setCellValueFactory(new PropertyValueFactory<Flight, Date>("arrival"));
        flight_arrivalCol.setCellValueFactory(new PropertyValueFactory<Flight, String>("flight_arrival"));
        passengerCol.setCellValueFactory(new PropertyValueFactory<Flight, Integer>("passenger"));
    }

    @FXML
    private void btn_back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/flightmanagerment/Form/Home/MainStaff/MainStaffUI.fxml"));
        Variable_Static.LinkUI(event, root, "Main Staff");
//        Scene scene = new Scene(root);
//        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        stage.hide();
//        stage.setScene(scene);
//        stage.setMaximized(true);
//        stage.resizableProperty().setValue(Boolean.FALSE);
//        stage.setTitle("Main Staff ");
//        stage.show();
    }

    @FXML
    private void btn_search(ActionEvent event) throws SQLException {
        if (txt_idFlight.getText().equals("")) {
            list = FXCollections.observableArrayList();
            setCellTable();
            list = FlightDAO.getInfo(0, txt_brand.getText(), txt_flightNumber.getText(), cbb_origin.getValue(), cbb_destination.getValue());
            table.setItems(list);
        } else {
            try {
                int id = Integer.parseInt(txt_idFlight.getText());
                list = FXCollections.observableArrayList();
                setCellTable();
                list = FlightDAO.getInfo(id, txt_brand.getText(), txt_flightNumber.getText(), cbb_origin.getValue(), cbb_destination.getValue());
                table.setItems(list);
            } catch (Exception e) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("ERROR");
                a.setContentText("Vui lòng nhập đúng định dạng!");
                a.show();
            }
        }

    }

    @FXML
    private void getID(MouseEvent event) {
        table.getSelectionModel().getSelectedItem().getIdFlight();
    }

    @FXML
    private void btn_insert(ActionEvent event) {
        
    }

    @FXML
    private void btn_update(ActionEvent event) {
    }

    @FXML
    private void btn_delete(ActionEvent event) {
    }

}
