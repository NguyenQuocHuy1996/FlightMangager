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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
    @FXML
    private VBox s;

    private ArrayList<Flight> list;
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
    @FXML
    private Button btn1;
    @FXML
    private Button btn2;
    @FXML
    private Button btn3;
    @FXML
    private Button btn4;
    @FXML
    private Button btn5;
    @FXML
    private Button btn6;
    @FXML
    private Button btn7;
    @FXML
    private Button btn8;
    @FXML
    private Button btn9;
    @FXML
    private Button btn10;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        Customer cus = null;
        try {
            cus = CustomerDAO.getCus(Variable_Static.USERNAME);
        } catch (SQLException ex) {
            Logger.getLogger(MainFilghtController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Employee emp = null;
        try {
            emp = EmployeeDAO.getEmp(Variable_Static.USERNAME);
        } catch (SQLException ex) {
            Logger.getLogger(MainFilghtController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (cus != null) {
            lbl_userName.setText(cus.getLastName());
        } else if (emp != null) {
            lbl_userName.setText(emp.getLastName());
        } else {
            lbl_userName.setText("Khách hàng");
        }

        try {
            list = Variable_Static.searchFlight(Variable_Static.ORIGIN, Variable_Static.DESTINATION, Variable_Static.DEPART);

        } catch (SQLException ex) {
            Logger.getLogger(MainFilghtController.class.getName()).log(Level.SEVERE, null, ex);
        }
//            DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
//            String date = df.format(Variable_Static.DEPART);
        int countList = list.size();
        int i = 1;
        if (i <= countList) {
            origin1.setText(Variable_Static.ORIGIN);
            destination1.setText(Variable_Static.DESTINATION);
            depart1.setText(Variable_Static.DEPART.toString());
            flight_depart1.setText(list.get(0).getFlight_depart());
            flight_number1.setText(list.get(0).getFlight_number());
            btn1.setVisible(true);
            i++;
        } else {
            btn1.setVisible(false);
        }

        if (i <= countList) {
            origin2.setText(Variable_Static.ORIGIN);
            destination2.setText(Variable_Static.DESTINATION);
            depart2.setText(Variable_Static.DEPART.toString());
            flight_depart2.setText(list.get(1).getFlight_depart());
            flight_number2.setText(list.get(1).getFlight_number());
            btn2.setVisible(true);
            i++;
        } else {
            btn2.setVisible(false);
        }

        if (i <= countList) {
            origin3.setText(Variable_Static.ORIGIN);
            destination3.setText(Variable_Static.DESTINATION);
            depart3.setText(Variable_Static.DEPART.toString());
            flight_depart3.setText(list.get(2).getFlight_depart());
            flight_number3.setText(list.get(2).getFlight_number());
            btn3.setVisible(true);
            i++;
        } else {
            btn3.setVisible(false);
        }

        if (i <= countList) {
            origin4.setText(Variable_Static.ORIGIN);
            destination4.setText(Variable_Static.DESTINATION);
            depart4.setText(Variable_Static.DEPART.toString());
            flight_depart4.setText(list.get(3).getFlight_depart());
            flight_number4.setText(list.get(3).getFlight_number());
            btn4.setVisible(true);
            i++;
        } else {
            btn4.setVisible(false);
        }

        if (i <= countList) {
            origin5.setText(Variable_Static.ORIGIN);
            destination5.setText(Variable_Static.DESTINATION);
            depart5.setText(Variable_Static.DEPART.toString());
            flight_depart5.setText(list.get(4).getFlight_depart());
            flight_number5.setText(list.get(4).getFlight_number());
            btn5.setVisible(true);
            i++;
        } else {
            btn5.setVisible(false);
        }

        if (i <= countList) {
            origin6.setText(Variable_Static.ORIGIN);
            destination6.setText(Variable_Static.DESTINATION);
            depart6.setText(Variable_Static.DEPART.toString());
            flight_depart6.setText(list.get(5).getFlight_depart());
            flight_number6.setText(list.get(5).getFlight_number());
            btn6.setVisible(true);
            i++;
        } else {
            btn6.setVisible(false);
        }

        if (i <= countList) {
            origin7.setText(Variable_Static.ORIGIN);
            destination7.setText(Variable_Static.DESTINATION);
            depart7.setText(Variable_Static.DEPART.toString());
            flight_depart7.setText(list.get(6).getFlight_depart());
            flight_number7.setText(list.get(6).getFlight_number());
            btn7.setVisible(true);
            i++;
        } else {
            btn7.setVisible(false);
        }

        if (i <= countList) {
            origin8.setText(Variable_Static.ORIGIN);
            destination8.setText(Variable_Static.DESTINATION);
            depart8.setText(Variable_Static.DEPART.toString());
            flight_depart8.setText(list.get(7).getFlight_depart());
            flight_number8.setText(list.get(7).getFlight_number());
            btn8.setVisible(true);
            i++;
        } else {
            btn8.setVisible(false);
        }

        if (i <= countList) {
            origin9.setText(Variable_Static.ORIGIN);
            destination9.setText(Variable_Static.DESTINATION);
            depart9.setText(Variable_Static.DEPART.toString());
            flight_depart9.setText(list.get(8).getFlight_depart());
            flight_number9.setText(list.get(8).getFlight_number());
            btn9.setVisible(true);
            i++;
        } else {
            btn9.setVisible(false);
        }

        if (i <= countList) {
            origin10.setText(Variable_Static.ORIGIN);
            destination10.setText(Variable_Static.DESTINATION);
            depart10.setText(Variable_Static.DEPART.toString());
            flight_depart10.setText(list.get(9).getFlight_depart());
            flight_number10.setText(list.get(9).getFlight_number());
            btn10.setVisible(true);
            i++;
        } else {
            btn10.setVisible(false);
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

    @FXML
    private void btn_Choose1(ActionEvent event) throws SQLException, IOException {
        list = Variable_Static.searchFlight(Variable_Static.ORIGIN, Variable_Static.DESTINATION, Variable_Static.DEPART);
        Variable_Static.IDFLIGHTSEAT = list.get(0).getIdFlight();
        Parent root = FXMLLoader.load(getClass().getResource("/flightmanagerment/Form/Home/DetailFlight/DetailFlightUI.fxml"));
        Variable_Static.LinkUI(event, root, "Detail Flight");
    }

    @FXML
    private void btn_Choose2(ActionEvent event) throws SQLException, IOException {
        list = Variable_Static.searchFlight(Variable_Static.ORIGIN, Variable_Static.DESTINATION, Variable_Static.DEPART);
        Variable_Static.IDFLIGHTSEAT = list.get(1).getIdFlight();
        Parent root = FXMLLoader.load(getClass().getResource("/flightmanagerment/Form/Home/DetailFlight/DetailFlightUI.fxml"));
        Variable_Static.LinkUI(event, root, "Detail Flight");
    }

    @FXML
    private void btn_Choose3(ActionEvent event) throws SQLException, IOException {
        list = Variable_Static.searchFlight(Variable_Static.ORIGIN, Variable_Static.DESTINATION, Variable_Static.DEPART);
        Variable_Static.IDFLIGHTSEAT = list.get(2).getIdFlight();
        Parent root = FXMLLoader.load(getClass().getResource("/flightmanagerment/Form/Home/DetailFlight/DetailFlightUI.fxml"));
        Variable_Static.LinkUI(event, root, "Detail Flight");
    }

    @FXML
    private void btn_Choose4(ActionEvent event) throws SQLException, IOException {
        list = Variable_Static.searchFlight(Variable_Static.ORIGIN, Variable_Static.DESTINATION, Variable_Static.DEPART);
        Variable_Static.IDFLIGHTSEAT = list.get(3).getIdFlight();
        Parent root = FXMLLoader.load(getClass().getResource("/flightmanagerment/Form/Home/DetailFlight/DetailFlightUI.fxml"));
        Variable_Static.LinkUI(event, root, "Detail Flight");
    }

    @FXML
    private void btn_Choose5(ActionEvent event) throws SQLException, IOException {
        list = Variable_Static.searchFlight(Variable_Static.ORIGIN, Variable_Static.DESTINATION, Variable_Static.DEPART);
        Variable_Static.IDFLIGHTSEAT = list.get(4).getIdFlight();
        Parent root = FXMLLoader.load(getClass().getResource("/flightmanagerment/Form/Home/DetailFlight/DetailFlightUI.fxml"));
        Variable_Static.LinkUI(event, root, "Detail Flight");
    }

    @FXML
    private void btn_Choose6(ActionEvent event) throws SQLException, IOException {
        list = Variable_Static.searchFlight(Variable_Static.ORIGIN, Variable_Static.DESTINATION, Variable_Static.DEPART);
        Variable_Static.IDFLIGHTSEAT = list.get(5).getIdFlight();
        Parent root = FXMLLoader.load(getClass().getResource("/flightmanagerment/Form/Home/DetailFlight/DetailFlightUI.fxml"));
        Variable_Static.LinkUI(event, root, "Detail Flight");
    }

    @FXML
    private void btn_Choose7(ActionEvent event) throws SQLException, IOException {
        list = Variable_Static.searchFlight(Variable_Static.ORIGIN, Variable_Static.DESTINATION, Variable_Static.DEPART);
        Variable_Static.IDFLIGHTSEAT = list.get(6).getIdFlight();
        Parent root = FXMLLoader.load(getClass().getResource("/flightmanagerment/Form/Home/DetailFlight/DetailFlightUI.fxml"));
        Variable_Static.LinkUI(event, root, "Detail Flight");
    }

    @FXML
    private void btn_Choose8(ActionEvent event) throws SQLException, IOException {
        list = Variable_Static.searchFlight(Variable_Static.ORIGIN, Variable_Static.DESTINATION, Variable_Static.DEPART);
        Variable_Static.IDFLIGHTSEAT = list.get(7).getIdFlight();
        Parent root = FXMLLoader.load(getClass().getResource("/flightmanagerment/Form/Home/DetailFlight/DetailFlightUI.fxml"));
        Variable_Static.LinkUI(event, root, "Detail Flight");
    }

    @FXML
    private void btn_Choose9(ActionEvent event) throws SQLException, IOException {
        list = Variable_Static.searchFlight(Variable_Static.ORIGIN, Variable_Static.DESTINATION, Variable_Static.DEPART);
        Variable_Static.IDFLIGHTSEAT = list.get(8).getIdFlight();
        Parent root = FXMLLoader.load(getClass().getResource("/flightmanagerment/Form/Home/DetailFlight/DetailFlightUI.fxml"));
        Variable_Static.LinkUI(event, root, "Detail Flight");
    }

    @FXML
    private void btn_Choose10(ActionEvent event) throws SQLException, IOException {
        list = Variable_Static.searchFlight(Variable_Static.ORIGIN, Variable_Static.DESTINATION, Variable_Static.DEPART);
        Variable_Static.IDFLIGHTSEAT = list.get(9).getIdFlight();
        Parent root = FXMLLoader.load(getClass().getResource("/flightmanagerment/Form/Home/DetailFlight/DetailFlightUI.fxml"));
        Variable_Static.LinkUI(event, root, "Detail Flight");
    }

}
