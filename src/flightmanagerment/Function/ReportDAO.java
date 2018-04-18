/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightmanagerment.Function;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Admin
 */
public class ReportDAO {
    
    public static ObservableList<Integer> getMonths() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);
        list.add(10);
        list.add(11);
        list.add(12);
        ObservableList<Integer> listt = FXCollections.observableArrayList(list);
        return listt;
    }
    
    public static ObservableList<Integer> getYears() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(2015);
        list.add(2016);
        list.add(2017);
        list.add(2018);
        ObservableList<Integer> listt = FXCollections.observableArrayList(list);
        return listt;
    }
}
