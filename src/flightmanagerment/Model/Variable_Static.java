/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightmanagerment.Model;

import flightmanagerment.Config.ConnectDB;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Admin
 */
public final class Variable_Static {

    public static String USERNAME = "";
    public static int IDFLIGHT = 0;
    public static int IDFLIGHTNEW = 0;

    public static String ORIGIN = "";
    public static String DESTINATION = "";
    public static Date DEPART = null;
    private static Connection conn = null;
    private static ConnectDB connectDB;
    private static PreparedStatement prest = null;
    private static ResultSet rs;
    private static Statement st;

    public static boolean SendMail(String To, String Text) {
        try {
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class",
                    "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");

            Session session = Session.getDefaultInstance(props,
                    new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("yacnadota001@gmail.com", "12343214a");
                }
            });
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("yacnadota001@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(To));
            message.setSubject("THÔNG BÁO CHUYẾN BAY MỚI");
            message.setText(Text);

            Transport.send(message);
            return true;

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public static void LinkUI(ActionEvent event, Parent root, String title) throws IOException {
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setTitle(title);
        stage.show();

    }

    public static ArrayList<Flight> searchFlight(String origin, String destination, Date depart) throws SQLException {
        try {
            ArrayList<Flight> list = new ArrayList<>();
            connectDB = new ConnectDB();
            conn = connectDB.getConnect();
            String sql = "select  * from flight where origin = ? and destination = ? and depart = ? limit 10";
            prest = conn.prepareStatement(sql);
            prest.setString(1, origin);
            prest.setString(2, destination);
            prest.setDate(3, depart);
            rs = prest.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("idFlight");
                Date _arrival = rs.getDate("arrival");
                int _passenger = rs.getInt("passenger");
                String _brand = rs.getString("brand");
                String _flight_number = rs.getString("flight_number");
                String _flight_arrival = rs.getString("flight_arrival");
                String _flight_depart = rs.getString("flight_depart");
                Double _price = rs.getDouble("price");

                System.out.println("id: " + id);
                System.out.println("origin: " + origin);
                System.out.println("destination: " + destination);
                System.out.println("depart: " + depart);
                System.out.println("arrival: " + _arrival);
                System.out.println("passenger: " + _passenger);
                System.out.println("brand: " + _brand);
                System.out.println("flight_number: " + _flight_number);
                System.out.println("flight_arrival: " + _flight_arrival);
                System.out.println("flight_depart: " + _flight_depart);
                System.out.println("price: " + _price);
                Flight f = new Flight(id, origin, destination, depart, _arrival, _passenger,
                        _brand, _flight_number, _flight_arrival, _flight_depart, _price);
                list.add(f);
            }

            if (null != prest) {
                prest.close();
            }
            if (null != conn) {
                conn.close();
            }
            return list;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (null != prest) {
                prest.close();
            }
            if (null != conn) {
                conn.close();
            }
        }
        return null;
    }
}
