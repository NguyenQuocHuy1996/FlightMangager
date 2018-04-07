/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightmanagerment.Form.Home.MainUser;

import flightmanagerment.Function.CustomerDAO;
import flightmanagerment.Model.Customer;
import flightmanagerment.Model.USER;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Quoc Huy
 */
public class MainUserController implements Initializable {

    @FXML
    private Label lbl_userName;

  

//    @FXML
//    private TextField txtEmail;
//    @FXML
//    private TextField txtPass;
//    
//    @FXML
//    private void MainUser(ActionEvent event) {
//        if((txtEmail.getText().equals("admin")) && (txtPass.getText().equals("admin123"))){
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Dang nhap thanh cong");
//            alert.show();
//        }
//    }
 
    
    @FXML
    private void btn_flightSearch(ActionEvent event) {
         
        
    }


    @FXML
    private void btn_logout(ActionEvent event) throws IOException {
        USER.USERNAME = "";
        Parent root = FXMLLoader.load(getClass().getResource("/flightmanagerment/Form/Account/Login/LoginUI.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.hide();
            stage.setScene(scene);
            stage.setMaximized(true);
            stage.resizableProperty().setValue(Boolean.FALSE);
            stage.setTitle("Login ");
            stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Customer cus = new Customer();
        try {
            cus = CustomerDAO.getCus(USER.USERNAME);
            lbl_userName.setText(cus.getLastName());
        } catch (SQLException ex) {
//            Logger.getLogger(MainUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
