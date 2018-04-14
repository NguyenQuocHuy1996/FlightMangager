/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightmanagerment.Form.Home.MainFilght;

import flightmanagerment.Function.CustomerDAO;
import flightmanagerment.Function.EmployeeDAO;
import flightmanagerment.Model.Customer;
import flightmanagerment.Model.Employee;
import flightmanagerment.Model.Flight;
import flightmanagerment.Model.Variable_Static;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 *
 * @author Quoc Huy
 */
public class MainFilghtController implements Initializable {

    @FXML
    private Label lbl_userName;
    @FXML
    private Label origin1;
    @FXML
    private Label destination1;
    @FXML
    private Label depart1;
    @FXML
    private Label flight_depart1;
    @FXML
    private Label flight_number1;
    @FXML
    private Label origin2;
    @FXML
    private Label destination2;
    @FXML
    private Label depart2;
    @FXML
    private Label flight_depart2;
    @FXML
    private Label flight_number2;
    @FXML
    private Label origin3;
    @FXML
    private Label destination3;
    @FXML
    private Label depart3;
    @FXML
    private Label flight_depart3;
    @FXML
    private Label flight_number3;
    @FXML
    private Label origin4;
    @FXML
    private Label destination4;
    @FXML
    private Label depart4;
    @FXML
    private Label flight_depart4;
    @FXML
    private Label flight_number4;
    @FXML
    private Label origin5;
    @FXML
    private Label destination5;
    @FXML
    private Label depart5;
    @FXML
    private Label flight_depart5;
    @FXML
    private Label flight_number5;
    @FXML
    private Label origin6;
    @FXML
    private Label destination6;
    @FXML
    private Label depart6;
    @FXML
    private Label flight_depart6;

    @FXML
    private Label destination7;
    @FXML
    private Label origin8;
    @FXML
    private Label destination8;
    @FXML
    private Label origin9;
    @FXML
    private Label destination9;
    @FXML
    private Label origin10;
    @FXML
    private Label destination10;
    @FXML
    private Label flight_number6;
    @FXML
    private Label depart7;
    @FXML
    private Label flight_depart7;
    @FXML
    private Label flight_number7;
    @FXML
    private Label depart8;
    @FXML
    private Label flight_depart8;
    @FXML
    private Label flight_number8;
    @FXML
    private Label depart9;
    @FXML
    private Label flight_depart9;
    @FXML
    private Label flight_number9;
    @FXML
    private Label depart10;
    @FXML
    private Label flight_depart10;
    @FXML
    private Label flight_number10;
    @FXML
    private Label origin7;

//    @FXML
//    private TextField txtEmail;
//    @FXML
//    private TextField txtPass;
//    
//    @FXML
//    private void MainFilght(ActionEvent event) {
//        if((txtEmail.getText().equals("admin")) && (txtPass.getText().equals("admin123"))){
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Dang nhap thanh cong");
//            alert.show();
//        }
//    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Customer cus = new Customer();
        Employee emp = new Employee();
        try {
            cus = CustomerDAO.getCus(Variable_Static.USERNAME);
            emp = EmployeeDAO.getEmp(Variable_Static.USERNAME);
            if (cus != null) {
                lbl_userName.setText(cus.getFirstName());
            } else if (emp != null) {
                lbl_userName.setText(emp.getFirstName());
            } else {
                lbl_userName.setText("Khách hàng");
            }

        } catch (SQLException ex) {
//            Logger.getLogger(MainUserController.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            ArrayList<Flight> list = Variable_Static.searchFlight(Variable_Static.ORIGIN, Variable_Static.DESTINATION, Variable_Static.DEPART);
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            String date = df.format(Variable_Static.DEPART);

            origin1.setText(Variable_Static.ORIGIN);
            destination1.setText(Variable_Static.DESTINATION);
            depart1.setText(date);
            flight_depart1.setText(list.get(0).getFlight_depart());
            flight_number1.setText(list.get(0).getFlight_number());

            origin2.setText(Variable_Static.ORIGIN);
            destination2.setText(Variable_Static.DESTINATION);
            depart2.setText(date);
            flight_depart2.setText(list.get(1).getFlight_depart());
            flight_number2.setText(list.get(1).getFlight_number());

            origin3.setText(Variable_Static.ORIGIN);
            destination3.setText(Variable_Static.DESTINATION);
            depart3.setText(date);
            flight_depart3.setText(list.get(2).getFlight_depart());
            flight_number3.setText(list.get(2).getFlight_number());

            origin4.setText(Variable_Static.ORIGIN);
            destination4.setText(Variable_Static.DESTINATION);
            depart4.setText(date);
            flight_depart4.setText(list.get(3).getFlight_depart());
            flight_number4.setText(list.get(3).getFlight_number());

            origin5.setText(Variable_Static.ORIGIN);
            destination5.setText(Variable_Static.DESTINATION);
            depart5.setText(date);
            flight_depart5.setText(list.get(4).getFlight_depart());
            flight_number5.setText(list.get(4).getFlight_number());

            origin6.setText(Variable_Static.ORIGIN);
            destination6.setText(Variable_Static.DESTINATION);
            depart6.setText(date);
            flight_depart6.setText(list.get(5).getFlight_depart());
            flight_number6.setText(list.get(5).getFlight_number());

            origin7.setText(Variable_Static.ORIGIN);
            destination7.setText(Variable_Static.DESTINATION);
            depart7.setText(date);
            flight_depart7.setText(list.get(6).getFlight_depart());
            flight_number7.setText(list.get(6).getFlight_number());

            origin8.setText(Variable_Static.ORIGIN);
            destination8.setText(Variable_Static.DESTINATION);
            depart8.setText(date);
            flight_depart8.setText(list.get(7).getFlight_depart());
            flight_number8.setText(list.get(7).getFlight_number());

            origin9.setText(Variable_Static.ORIGIN);
            destination9.setText(Variable_Static.DESTINATION);
            depart9.setText(date);
            flight_depart9.setText(list.get(8).getFlight_depart());
            flight_number9.setText(list.get(8).getFlight_number());

            origin10.setText(Variable_Static.ORIGIN);
            destination10.setText(Variable_Static.DESTINATION);
            depart10.setText(date);
            flight_depart10.setText(list.get(9).getFlight_depart());
            flight_number10.setText(list.get(9).getFlight_number());
        } catch (SQLException ex) {
            Logger.getLogger(MainFilghtController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void btn_back(ActionEvent event) throws IOException, SQLException {
        Customer cus = CustomerDAO.getCus(Variable_Static.USERNAME);
        Employee emp = EmployeeDAO.getEmp(Variable_Static.USERNAME);
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

    }

}
