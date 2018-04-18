/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightmanagerment.Form.Home.EditFlightManager;

import flightmanagerment.Function.BrandDAO;
import flightmanagerment.Function.CityDAO;
import flightmanagerment.Function.EmployeeDAO;
import flightmanagerment.Function.FlightDAO;
import flightmanagerment.Model.Brand;
import flightmanagerment.Model.Employee;
import flightmanagerment.Model.Flight;
import flightmanagerment.Model.Variable_Static;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Quoc Huy
 */
public class EditFlightManagerController implements Initializable {

    private Label lbl_userName;
    private ComboBox<String> cbb_brand;
    private ComboBox<String> cbb_origin;
    private ComboBox<String> cbb_destination;
    private TextField txt_flightNumber;
    private TextField txt_idFlight;
    private DatePicker datePicker_Depart;
    private DatePicker datePicker_Arrival;
    private TextField txt_price;
    private TextField txt_passenger;
    private TextField txt_flightDepart;
    private TextField txt_flightArrival;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Employee emp = null;
        try {
            emp = EmployeeDAO.getEmp(Variable_Static.USERNAME);
        } catch (SQLException ex) {
            Logger.getLogger(EditFlightManagerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        lbl_userName.setText(emp.getLastName());

        ObservableList<String> listt = BrandDAO.getName();
        cbb_brand.setItems(listt);
        cbb_brand.getSelectionModel().select(1);
        cbb_brand.setPromptText(cbb_brand.getConverter().toString(cbb_brand.getValue()));
        ObservableList<String> list = CityDAO.getAllCity();
        cbb_origin.setItems(list);
        cbb_origin.getSelectionModel().select(1);
        cbb_origin.setPromptText(cbb_origin.getConverter().toString(cbb_origin.getValue()));
        cbb_destination.setItems(list);
        cbb_destination.getSelectionModel().select(1);
        cbb_destination.setPromptText(cbb_destination.getConverter().toString(cbb_destination.getValue()));
        try {

            if (Variable_Static.IDFLIGHTNEW != 0) {
                getDB();
            }
        } catch (SQLException ex) {
            Logger.getLogger(EditFlightManagerController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void getDB() throws SQLException {

        Flight f = FlightDAO.getInfoFlight(Variable_Static.IDFLIGHTNEW);
        cbb_origin.setValue(f.getOrigin());
        cbb_destination.setValue(f.getDestination());
        cbb_brand.setValue(f.getBrand());
        LocalDate localD = f.getArrival().toLocalDate(); // convert từ date sql wa local date (datePicker)
        datePicker_Arrival.setValue(localD);

        LocalDate localDD = f.getArrival().toLocalDate(); // convert từ date sql wa local date (datePicker)
        datePicker_Depart.setValue(localDD);

        txt_flightArrival.setText(f.getFlight_arrival());
        txt_flightDepart.setText(f.getFlight_depart());
        txt_flightNumber.setText(f.getFlight_number());
        String id = Integer.toString(f.getIdFlight());
        String price = Double.toString(f.getPrice());
        String passenger = Integer.toString(f.getPassenger());
        txt_idFlight.setText(id);
        txt_price.setText(price);
        txt_passenger.setText(passenger);
    }

    private void btn_back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/flightmanagerment/Form/Home/FlightManager/FlightManagerUI.fxml"));
        Variable_Static.LinkUI(event, root, "Flight Manager");
    }

    private void btn_insert(ActionEvent event) throws SQLException, IOException {

        if (txt_idFlight.getText().equals("")) {
            Date depart = Date.valueOf(datePicker_Arrival.getValue());
            Date arrival = Date.valueOf(datePicker_Arrival.getValue());
            double price = Double.parseDouble(txt_price.getText());
            int passenger = Integer.parseInt(txt_passenger.getText());
            Flight f = new Flight(cbb_origin.getValue(), cbb_destination.getValue(), depart, arrival, passenger, cbb_brand.getValue(), txt_flightNumber.getText(), txt_flightArrival.getText(), txt_flightDepart.getText(), price);

            int function = FlightDAO.insert(f);
            if (function == -1 || function == -2 || function == -3 || function == -4 || function == -5 || function == -6 || function == -7 || function == -8 || function == -9 || function == -10) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("ERROR");
                a.setContentText("Vui lòng nhập đủ thông tin");
                a.show();
            } else {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setTitle("Tạo thành công!");
                a.setContentText("Bạn đã tạo thành công chuyến bay");
                a.show();
            }
        } else {
            Date depart = Date.valueOf(datePicker_Arrival.getValue());
            Date arrival = Date.valueOf(datePicker_Arrival.getValue());
            double price = Double.parseDouble(txt_price.getText());
            int passenger = Integer.parseInt(txt_passenger.getText());
            Flight f = new Flight(cbb_origin.getValue(), cbb_destination.getValue(), depart, arrival, passenger, cbb_brand.getValue(), txt_flightNumber.getText(), txt_flightArrival.getText(), txt_flightDepart.getText(), price);
            FlightDAO.Edit(f);
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Cập nhật thành công!");
            a.setContentText("Bạn đã cập nhật thông tin thành công");
            a.showAndWait();
            Parent root = FXMLLoader.load(getClass().getResource("/flightmanagerment/Form/Home/FlightManager/FlightManagerUI.fxml"));
            Variable_Static.LinkUI(event, root, "Flight Manager");
        }

    }

    private void btn_reset(ActionEvent event) {
        txt_flightArrival.setText("");
        txt_flightDepart.setText("");
        txt_flightNumber.setText("");
        txt_passenger.setText("");
        txt_price.setText("");
    }

}
