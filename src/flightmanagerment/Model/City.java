/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightmanagerment.Model;

/**
 *
 * @author Admin
 */
public class City {

    private int id;
    private int matinh;
    private String tentinh;

    public City() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMatinh() {
        return matinh;
    }

    public void setMatinh(int matinh) {
        this.matinh = matinh;
    }

    public String getTentinh() {
        return tentinh;
    }

    public void setTentinh(String tentinh) {
        this.tentinh = tentinh;
    }

    public City(int id, int matinh, String tentinh) {
        this.id = id;
        this.matinh = matinh;
        this.tentinh = tentinh;
    }
}
