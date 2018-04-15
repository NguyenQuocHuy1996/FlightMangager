/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightmanagerment.Form.Home.EditFlightManager;

import flightmanagerment.Function.BrandDAO;
import flightmanagerment.Function.CityDAO;
import flightmanagerment.Function.FlightDAO;
import flightmanagerment.Model.Brand;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
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

    @FXML
    private Label lbl_userName;
    @FXML
    private ComboBox<String> cbb_brand;
    @FXML
    private ComboBox<String> cbb_origin;
    @FXML
    private ComboBox<String> cbb_destination;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

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
        // TODO
    }

    @FXML
    private void btn_back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/flightmanagerment/Form/Home/FlightManager/FlightManagerUI.fxml"));
        Variable_Static.LinkUI(event, root, "Flight Manager");
    }

}
