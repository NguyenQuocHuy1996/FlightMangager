/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightmanagerment.Form.Home.DetailFlight;

import flightmanagerment.Function.CustomerDAO;
import flightmanagerment.Function.EmployeeDAO;
import flightmanagerment.Function.FlightDAO;
import flightmanagerment.Function.Seat_TicketDAO;
import flightmanagerment.Model.Customer;
import flightmanagerment.Model.Employee;
import flightmanagerment.Model.Flight;
import flightmanagerment.Model.Seat_Ticket;
import flightmanagerment.Model.Variable_Static;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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

    private List<String> list = new ArrayList<>();

    Iterator<String> iter = list.iterator();
    @FXML
    private Label lbl_userName;
    @FXML
    private Label lbl_idFlight;
    @FXML
    private Label lbl_brand;
    @FXML
    private Label lbl_price;
    @FXML
    private Label lbl_origin;
    @FXML
    private Label lbl_destination;
    @FXML
    private Label lbl_depart;
    @FXML
    private Label lbl_flightDepart;
    @FXML
    private Label lbl_arrval;
    @FXML
    private Label lbl_flightArrival;
    @FXML
    private Label lbl_passenger;

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
            try {
                Flight f = FlightDAO.getInfoFlight(Variable_Static.IDFLIGHTSEAT);
                num_passenger = f.getPassenger();
                lbl_idFlight.setText(Integer.toString(f.getIdFlight()));
                lbl_brand.setText(f.getBrand());
                lbl_origin.setText(f.getOrigin());
                lbl_destination.setText(f.getDestination());
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                String depart = df.format(f.getDepart());
                String arrival = df.format(f.getArrival());
                lbl_depart.setText(depart);
                lbl_arrval.setText(arrival);
                lbl_flightDepart.setText(f.getFlight_depart());
                lbl_flightArrival.setText(f.getFlight_arrival());
                lbl_passenger.setText(Integer.toString(f.getPassenger()));
                lbl_price.setText(Double.toString(f.getPrice()));
            } catch (SQLException ex) {
                Logger.getLogger(DetailFlightController.class.getName()).log(Level.SEVERE, null, ex);
            }
            String[] array = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
            int k = 1;
            for (int i = 1; i <= 10; i++) {
                if (k > num_passenger) {
                    break;
                }
                for (int j = 1; j <= 15; j++) {
                    try {
                        if (k > num_passenger) {
                            break;
                        }
                        createButton(array, j, i);
                        k++;
                    } catch (SQLException ex) {
                        Logger.getLogger(DetailFlightController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

            //        Button btn = new Button();
            //        btn.getStyleClass().add("btnSeat");
            //        btn.setText("A1");
            //        gridPane.add(btn, 1, 1);
        } catch (SQLException ex) {
            Logger.getLogger(DetailFlightController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void createButton(String[] array, int i, int j) throws SQLException {

        Button btn = new Button();
        btn.getStyleClass().add("btnSeat");
        btn.setStyle("-fx-font-size:10px");
        btn.setText(array[j - 1] + i);
        btn.setId(array[j - 1] + i);
        gridPane.add(btn, i - 1, j);

        Boolean status = null;

        try {
            status = Seat_TicketDAO.getInfoSeat(Variable_Static.IDFLIGHTSEAT, btn.getText());
        } catch (SQLException ex) {
            Logger.getLogger(DetailFlightController.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (status) {
            btn.setStyle("-fx-background-color:RED;-fx-font-size:10px");
            //btn.setStyle("-fx-font-size:10px");
        }
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Boolean status = false;
                Boolean check = false;
                try {
                    status = Seat_TicketDAO.getInfoSeat(Variable_Static.IDFLIGHTSEAT, btn.getText());
                } catch (SQLException ex) {
                    Logger.getLogger(DetailFlightController.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (status) {
                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setTitle("ERROR");
                    a.setContentText("Ghế đã được mua");
                    a.show();
                } else {
                    if (list.isEmpty()) {
                        btn.setStyle("-fx-background-color:YELLOW;-fx-font-size:10px");
                        list.add(btn.getId());
                    } else {
                        for (String string : list) {
                            if (string.equals(btn.getId())) {
                                Alert a = new Alert(Alert.AlertType.ERROR);
                                a.setTitle("Error");
                                a.setContentText("Bạn hãy bấm nút Hủy để xóa các nút đã chọn");
                                a.show();
                                check = true;
                            }
                        }
                        if (!check && list.size() < 5) {
                            btn.setStyle("-fx-background-color:YELLOW;-fx-font-size:10px");
                            list.add(btn.getId());
                        }
                        if (list.size() >= 5) {
                            Alert a = new Alert(Alert.AlertType.ERROR);
                            a.setTitle("Error");
                            a.setContentText("Bạn đã chọn tối đa 5 ghế trong 1 phiên giao dịch");
                            a.show();
                        }
                    }
                }
            }
        });
    }

    @FXML
    private void btn_buy(ActionEvent event) throws IOException {
        if (list.isEmpty()) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("ERROR");
            a.setContentText("Vui lòng chọn ghế");
            a.show();
        } else {
            Variable_Static.numberseatchoose = list.size(); // 3
            Variable_Static.currentlistseat = list; // list
            Parent root = FXMLLoader.load(getClass().getResource("/flightmanagerment/Form/Home/Ticket/TicketUI.fxml"));
            Variable_Static.LinkUI(event, root, "Ticket");
        }

    }

    @FXML
    private void btn_reset(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/flightmanagerment/Form/Home/DetailFlight/DetailFlightUI.fxml"));
        Variable_Static.LinkUI(event, root, "Detail Flight");
    }

    @FXML
    private void btn_back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/flightmanagerment/Form/Home/MainFilght/MainFilghtUI.fxml"));
        Variable_Static.LinkUI(event, root, "Flight Main");
    }
}
