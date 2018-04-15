/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightmanagerment.Function;

import flightmanagerment.Config.ConnectDB;
import flightmanagerment.Model.Brand;
import flightmanagerment.Model.Customer;
import flightmanagerment.Model.Flight;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Admin
 */
public class BrandDAO {

    private static Connection conn = null;
    private static ConnectDB connectDB;
    private static PreparedStatement prest = null;
    private static ResultSet rs;
    private static Statement st;

    public static ObservableList<String> getName() {
        connectDB = new ConnectDB();
        conn = connectDB.getConnect();
        try {
            ArrayList<String> list = new ArrayList<String>();
            String sql = "Select name from brand";
            prest = conn.prepareStatement(sql);
            rs = prest.executeQuery();
            ObservableList<String> data = null;
            while (rs.next()) {
//                int id = rs.getInt("idBrand");
//                String code = rs.getString("code");
                String name = rs.getString("name");
                list.add(name);
            }
            data = FXCollections.observableArrayList(list);
            return data;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public static ObservableList<Brand> getAllBrand() throws SQLException {
        connectDB = new ConnectDB();
        conn = connectDB.getConnect();
        String sql = "select * from brand";
        st = conn.createStatement();
        rs = st.executeQuery(sql);
        ObservableList<Brand> data = FXCollections.observableArrayList();
        while (rs.next()) {
            int idBrand = rs.getInt("idBrand");
            String code = rs.getString("code");
            String name = rs.getString("name");

            Brand brand = new Brand(idBrand, code, name);
            data.add(brand);

        }
        return data;
    }

    public static int delete(int idBrand) throws SQLException {
        try {
            connectDB = new ConnectDB();
            conn = connectDB.getConnect();
            String sql = "delete from customer where idBrand = ?";
            prest = conn.prepareStatement(sql);
            prest.setInt(1, idBrand);
            prest.execute();
            System.out.println("Xóa thành công!");
            return 1;
        } catch (Exception e) {
            System.out.println(e);
            // TODO: handle exception
        } finally {
            if (null != prest) {
                prest.close();
            }
            if (null != conn) {
                conn.close();
            }
        }
        return 0;
    }

    public static int update(Brand brand) throws SQLException {
        try {
            connectDB = new ConnectDB();
            conn = connectDB.getConnect();
            String sql = "update brand set code = ?, name = ? where idBrand = ?";
            prest = conn.prepareStatement(sql);
            prest.setString(1, brand.getCode());
            prest.setString(2, brand.getName());
            prest.setInt(3, brand.getIdBrand());
            prest.execute();
            System.out.println("Update thành công!");
            return 1;
        } catch (Exception e) {
            System.out.println(e);
            // TODO: handle exception
        } finally {
            if (null != prest) {
                prest.close();
            }
            if (null != conn) {
                conn.close();
            }
        }
        return 0;
    }

    public static int insert(Brand brand) throws SQLException {
        if (brand.getCode().equals("") || brand.getName().equals("")) {
            return -1;
        } else {
            try {
                connectDB = new ConnectDB();
                conn = connectDB.getConnect();
                String sql = " insert into brand (code,name)"
                        + " values (?,?)";
                prest = conn.prepareStatement(sql);
                prest.setString(1, brand.getCode());
                prest.setString(2, brand.getName());
                prest.execute();
                if (null != prest) {
                    prest.close();
                }
                if (null != conn) {
                    conn.close();
                }

                System.out.println("Thêm thành công");
                return 1;
            } catch (Exception e) {
                System.out.println(e);
                // TODO: handle exception
            } finally {
                if (null != prest) {
                    prest.close();
                }
                if (null != conn) {
                    conn.close();
                }
                return 0;
            }
        }
    }
}
