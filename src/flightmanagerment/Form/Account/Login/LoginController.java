/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightmanagerment.Form.Account.Login;

import java.io.IOException;
import java.net.URL;
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
public class LoginController implements Initializable {

    @FXML
    private TextField email;
    @FXML
    private TextField password;

    @FXML
    private void btn_Login(ActionEvent event) throws IOException {
        //Load new windown and hide current windown

        //Load new windown
//        FXMLLoader fxmlLoader = new FXMLLoader();
//        fxmlLoader.setLocation(getClass().getResource("/flightmanagerment/Form/Home/Main/MainUI.fxml"));
//        //Set new Scene
//        Scene scene = new Scene(fxmlLoader.load());
//        
//        //Set new Stage
//        Stage stage = new Stage();        
//        stage.setTitle("New Window");
//        stage.setScene(scene);
//        
//        //Show new windown
//        stage.show();
    }

    @FXML
    private void btn_Register(ActionEvent event) {
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
