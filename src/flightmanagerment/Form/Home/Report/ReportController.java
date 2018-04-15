/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightmanagerment.Form.Home.Report;

import flightmanagerment.Function.EmployeeDAO;
import flightmanagerment.Function.ReportDAO;
import flightmanagerment.Function.Seat_TicketDAO;
import flightmanagerment.Model.Employee;
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
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

/**
 *
 * @author Quoc Huy
 */
public class ReportController implements Initializable {

    @FXML
    private Label lbl_userName;
    @FXML
    private ComboBox<Integer> cbb_month;
    @FXML
    private ComboBox<Integer> cbb_year;
    @FXML
    private StackPane stackPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Employee emp = null;
        try {
            emp = EmployeeDAO.getEmp(Variable_Static.USERNAME);
        } catch (SQLException ex) {
            Logger.getLogger(ReportController.class.getName()).log(Level.SEVERE, null, ex);
        }
        lbl_userName.setText(emp.getLastName());
        ObservableList<Integer> list = ReportDAO.getMonths();
        cbb_month.setItems(list);
        cbb_month.getSelectionModel().select(1);
        cbb_month.setPromptText(cbb_month.getConverter().toString(cbb_month.getValue()));

        ObservableList<Integer> listt = ReportDAO.getYears();
        cbb_year.setItems(listt);
        cbb_year.getSelectionModel().select(1);
        cbb_year.setPromptText(cbb_year.getConverter().toString(cbb_year.getValue()));
        // TODO
    }

    @FXML
    private void btn_back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/flightmanagerment/Form/Home/MainStaff/MainStaffUI.fxml"));
        Variable_Static.LinkUI(event, root, "Main Staff");
    }

    @FXML
    private void btn_create(ActionEvent event) throws SQLException {
        PieChart chart = new PieChart();
        chart.setTitle("Biểu đồ thể hiện số vé bán được trong tháng: " + cbb_month.getValue() + " năm: " + cbb_year.getValue());
        chart.getData().add(new PieChart.Data("Vé chưa được bán", Seat_TicketDAO.getCountStatusFalse(cbb_month.getValue(), cbb_year.getValue())));
        chart.getData().add(new PieChart.Data("Vé đã được bán", Seat_TicketDAO.getCountStatusTrue(cbb_month.getValue(), cbb_year.getValue())));

        this.stackPane.getChildren().clear();
        this.stackPane.getChildren().add(chart);
    }

}
