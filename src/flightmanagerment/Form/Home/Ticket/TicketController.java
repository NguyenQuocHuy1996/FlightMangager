/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightmanagerment.Form.Home.Ticket;

import flightmanagerment.Function.Seat_TicketDAO;
import flightmanagerment.Model.Seat_Ticket;
import flightmanagerment.Model.Variable_Static;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author Quoc Huy
 */
public class TicketController implements Initializable {

    @FXML
    private TextField txt_firstname;
    @FXML
    private TextField txt_lastname;
    @FXML
    private TextField txt_iccard;

    String currentseat = "";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        currentseat = Variable_Static.currentlistseat.get(Variable_Static.currentnumberseat);
    }

    @FXML
    private void btn_submit(ActionEvent event) throws SQLException, IOException {
        Seat_Ticket t = new Seat_Ticket();
        t.setIdAccount(Variable_Static.IDACCOUNT);
        t.setFirstName(txt_firstname.getText());
        t.setLastName(txt_lastname.getText());
        t.setIc_Card(txt_iccard.getText());
        t.setStatus(Boolean.TRUE);
        t.setIdFlight(Variable_Static.IDFLIGHTSEAT);
        t.setCode(currentseat);
        Seat_TicketDAO.booking(t);
        if (Variable_Static.currentnumberseat >= Variable_Static.numberseatchoose - 1) {
//            Parent root = FXMLLoader.load(getClass().getResource("/flightmanagerment/Form/Home/Ticket/TicketUI.fxml"));
//            Variable_Static.LinkUI(event, root, "Ticket");
            System.out.println("Qua form thanh toán");
        } else {
            Variable_Static.currentnumberseat++;
            Parent root = FXMLLoader.load(getClass().getResource("/flightmanagerment/Form/Home/Ticket/TicketUI.fxml"));
            Variable_Static.LinkUI(event, root, "Ticket");
        }
    }

}
