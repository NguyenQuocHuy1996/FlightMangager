/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightmanagerment.Form.Home.Ticket;

import flightmanagerment.Function.CustomerDAO;
import flightmanagerment.Function.EmployeeDAO;
import flightmanagerment.Function.Seat_TicketDAO;
import flightmanagerment.Model.Customer;
import flightmanagerment.Model.Employee;
import flightmanagerment.Model.Seat_Ticket;
import flightmanagerment.Model.Variable_Static;
import java.io.IOException;
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
    @FXML
    private Label lbl_userName;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Customer cus = new Customer();
        Employee emp = new Employee();
        try {
            cus = CustomerDAO.getCus(Variable_Static.USERNAME);
            emp = EmployeeDAO.getEmp(Variable_Static.USERNAME);
            if (cus != null) {
                lbl_userName.setText(cus.getLastName());
            } else if (emp != null) {
                lbl_userName.setText(emp.getLastName());
            } else {
                lbl_userName.setText("Khách hàng");
            }
            currentseat = Variable_Static.currentlistseat.get(Variable_Static.currentnumberseat);

        } catch (SQLException ex) {
            Logger.getLogger(TicketController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btn_submit(ActionEvent event) throws SQLException, IOException {
        Customer cus = new Customer();
        Employee emp = new Employee();

        cus = CustomerDAO.getCus(Variable_Static.USERNAME);
        emp = EmployeeDAO.getEmp(Variable_Static.USERNAME);

        if (txt_firstname.getText().equals("") || txt_lastname.getText().equals("") || txt_iccard.getText().equals("")) {
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
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setTitle("ERROR");
                a.setContentText("Thanh toán thành công");
                a.showAndWait();
                if (cus != null) {
                    Parent root = FXMLLoader.load(getClass().getResource("/flightmanagerment/Form/Home/MainUser/MainUserUI.fxml"));
                    Variable_Static.LinkUI(event, root, "Main Customer");
                } else if (emp != null) {
                    Parent root = FXMLLoader.load(getClass().getResource("/flightmanagerment/Form/Home/MainStaff/MainStaffUI.fxml"));
                    Variable_Static.LinkUI(event, root, "Main Staff");
                } else {
                    Parent root = FXMLLoader.load(getClass().getResource("/flightmanagerment/Form/Home/Main/MainUI.fxml"));
                    Variable_Static.LinkUI(event, root, "Main");
                }
            } else {
                Variable_Static.currentnumberseat++;
                Parent root = FXMLLoader.load(getClass().getResource("/flightmanagerment/Form/Home/Ticket/TicketUI.fxml"));
                Variable_Static.LinkUI(event, root, "Ticket");
            }
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("ERROR");
            a.setContentText("Vui lòng nhập đủ thông tin");
            a.show();
        }

    }

    @FXML
    private void btn_back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/flightmanagerment/Form/Home/DetailFlight/DetailFlightUI.fxml"));
        Variable_Static.LinkUI(event, root, "Detail Flight");
    }

}
