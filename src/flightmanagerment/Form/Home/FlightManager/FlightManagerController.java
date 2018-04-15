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
import flightmanagerment.Function.Seat_TicketDAO;
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
    private TableColumn<?, ?> idFlightCol;
    @FXML
    private TableColumn<?, ?> brandCol;
    @FXML
    private TableColumn<?, ?> flight_numberCol;
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
            lbl_userName.setText(emp.getFirstName());
            loadDB();
            ObservableList<String> list = CityDAO.getAllCity();
            cbb_origin.setItems(list);
            cbb_origin.getSelectionModel().select(1);
            cbb_origin.setPromptText(cbb_origin.getConverter().toString(cbb_origin.getValue()));

            cbb_destination.setItems(list);
            cbb_destination.getSelectionModel().select(1);
            cbb_destination.setPromptText(cbb_destination.getConverter().toString(cbb_destination.getValue()));
        } catch (SQLException ex) {
            Logger.getLogger(FlightManagerController.class.getName()).log(Level.SEVERE, null, ex);
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
        flight_numberCol.setCellValueFactory(new PropertyValueFactory<>("flight_number"));
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
//        table.getSelectionModel().getSelectedItem().getIdFlight();
    }

    @FXML
    private void btn_insert(ActionEvent event) throws IOException {
        Variable_Static.IDFLIGHTNEW = 0;
        Parent root = FXMLLoader.load(getClass().getResource("/flightmanagerment/Form/Home/EditFlightManager/EditFlightManagerUI.fxml"));
        Variable_Static.LinkUI(event, root, "Insert/Edit Flight");
    }

    @FXML
    private void btn_update(ActionEvent event) throws IOException {

        Variable_Static.IDFLIGHTNEW = table.getSelectionModel().getSelectedItem().getIdFlight(); // cho toàn cục

        Parent root = FXMLLoader.load(getClass().getResource("/flightmanagerment/Form/Home/EditFlightManager/EditFlightManagerUI.fxml"));
        Variable_Static.LinkUI(event, root, "Insert/Edit Flight");

    }

    @FXML
    private void btn_delete(ActionEvent event) throws SQLException {
        Seat_TicketDAO.delete(table.getSelectionModel().getSelectedItem().getIdFlight());
        int function = FlightDAO.delete(table.getSelectionModel().getSelectedItem().getIdFlight());
        if (function == 1) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Deleted");
            a.setContentText("Xóa thành công!");
            a.show();
            loadDB();
        } else {
        }
    }

}
