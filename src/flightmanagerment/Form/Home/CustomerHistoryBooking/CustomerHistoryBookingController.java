/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightmanagerment.Form.Home.CustomerHistoryBooking;

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
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Quoc Huy
 */
public class CustomerHistoryBookingController implements Initializable {

    @FXML
    private Label lbl_userName;
    @FXML
    private TextField ic_Card;
    @FXML
    private TextField cusName;
    @FXML
    private TableView<Seat_Ticket> table;

//    @FXML
//    private TextField txtEmail;
//    @FXML
//    private TextField txtPass;
//    
//    @FXML
//    private void CustomerHistoryBooking(ActionEvent event) {
//        if((txtEmail.getText().equals("admin")) && (txtPass.getText().equals("admin123"))){
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Dang nhap thanh cong");
//            alert.show();
//        }
//    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void btn_back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/flightmanagerment/Form/Home/MainStaff/MainStaffUI.fxml"));
        Variable_Static.LinkUI(event, root, "Main Staff");
    }

    @FXML
    private void getID(MouseEvent event) throws SQLException {
        if (event.getClickCount() == 2) {
//            table.getSelectionModel().getSelectedItem().getIdFlight();
            int count = Seat_TicketDAO.historyBookingofEmp(table.getSelectionModel().getSelectedItem().getIdFlight(), Boolean.TRUE);
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Title");
            a.setContentText("Có tất cả " + count + "chuyến đã được đặt");
        }
    }

}
