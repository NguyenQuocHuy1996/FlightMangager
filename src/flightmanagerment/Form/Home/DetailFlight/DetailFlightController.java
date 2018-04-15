/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightmanagerment.Form.Home.DetailFlight;

import flightmanagerment.Function.FlightDAO;
import flightmanagerment.Model.Flight;
import flightmanagerment.Model.Variable_Static;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Quoc Huy
 */
public class DetailFlightController implements Initializable {

    @FXML
    private GridPane gridPane;
    private int num_passenger;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            Flight f = FlightDAO.getInfoFlight(Variable_Static.IDFLIGHT);
            num_passenger = f.getPassenger();
        } catch (SQLException ex) {
            Logger.getLogger(DetailFlightController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
