/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightmanagerment.Function;

import flightmanagerment.Config.ConnectDB;
import flightmanagerment.Model.City;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Admin
 */
public class CityDAO {

    private static Connection conn = null;
    private static ConnectDB connectDB;
    private static PreparedStatement prest = null;
    private static ResultSet rs;

    public static int getMaTinh(String TenTinh) {
        connectDB = new ConnectDB();
        conn = connectDB.getConnect();
        try {
            String sql = "select MaTinh from tinhthanh where TenTinh = ?";
            prest = conn.prepareStatement(sql);
            prest.setString(1, TenTinh);
            rs = prest.executeQuery();
            while (rs.next()) {                
                int maTinh = rs.getInt("MaTinh");
                return maTinh;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    public static ObservableList<String> getAllCity() {
        connectDB = new ConnectDB();
        conn = connectDB.getConnect();
        try {
            ArrayList<String> list = new ArrayList<String>();
            String sql = "Select TenTinh from tinhthanh";
            prest = conn.prepareStatement(sql);
            rs = prest.executeQuery();
            ObservableList<String> listt = null;
            while (rs.next()) {
//                int id = rs.getInt("id");
//                int maTinh = rs.getInt("MaTinh");
                String tenTinhthanh = rs.getString("TenTinh");
                list.add(tenTinhthanh);
            }
            listt = FXCollections.observableArrayList(list);
            return listt;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
