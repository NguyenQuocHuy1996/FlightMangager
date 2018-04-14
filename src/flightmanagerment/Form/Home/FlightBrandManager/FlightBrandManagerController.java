/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightmanagerment.Form.Home.FlightBrandManager;

import flightmanagerment.Function.BrandDAO;
import flightmanagerment.Function.EmployeeDAO;
import flightmanagerment.Model.Brand;
import flightmanagerment.Model.Employee;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Quoc Huy
 */
public class FlightBrandManagerController implements Initializable {

    @FXML
    private Label lbl_userName;
    @FXML
    private TableView<Brand> table;
    @FXML
    private TableColumn<?, ?> codeCol;
    @FXML
    private TableColumn<?, ?> nameCol;
    @FXML
    private TextField txt_code;
    @FXML
    private TextField txt_name;

    private ObservableList<Brand> list;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            loadDB();
            Employee emp = new Employee();
            emp = EmployeeDAO.getEmp(Variable_Static.USERNAME);
            lbl_userName.setText(emp.getFirstName());
        } catch (SQLException ex) {
            Logger.getLogger(FlightBrandManagerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btn_back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/flightmanagerment/Form/Home/MainStaff/MainStaffUI.fxml"));
        Variable_Static.LinkUI(event, root, "Main Staff");
    }

    @FXML
    private void btn_insert(ActionEvent event) throws SQLException {
        Brand brand = new Brand(txt_code.getText(), txt_name.getText());
        int function = BrandDAO.insert(brand);
        if (function == -1) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("ERROR");
            a.setContentText("Vui lòng nhập đày đủ thông tin");
            a.show();
        } else {
            loadDB();
        }

    }

    @FXML
    private void btn_reset(ActionEvent event) {
        txt_code.setText("");
        txt_name.setText("");
    }

    private void setCellTable() {
        codeCol.setCellValueFactory(new PropertyValueFactory<>("code"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

    }

    private void loadDB() throws SQLException {
        setCellTable();
        list = BrandDAO.getAllBrand();
        table.setItems(list);
    }
}
