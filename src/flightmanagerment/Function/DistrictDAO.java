/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightmanagerment.Function;

import flightmanagerment.Config.ConnectDB;
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
public class DistrictDAO {

    private static Connection conn = null;
    private static ConnectDB connectDB;
    private static PreparedStatement prest = null;
    private static ResultSet rs;

    public static ObservableList<String> getDistrict(int maTinh) {
        connectDB = new ConnectDB();
        conn = connectDB.getConnect();
        try {
            ArrayList<String> list = new ArrayList<String>();
            String sql = "select TenQuanHuyen from quanhuyen where MaTinh = ?";
            prest = conn.prepareStatement(sql);
            prest.setInt(1, maTinh);
            rs = prest.executeQuery();
            ObservableList<String> listt = null;
            while (rs.next()) {
                String tenQuanHuyen = rs.getString("TenQuanHuyen");
                list.add(tenQuanHuyen);
            }
            listt = FXCollections.observableArrayList(list);
            return listt;
        } catch (Exception e) {
            System.out.println(e);
            // TODO: handle exception
        }
        return null;
    }
}
