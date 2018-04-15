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
public class Brand {

    private int idBrand;
    private String code;
    private String name;

    public Brand() {
    }

    public int getIdBrand() {
        return idBrand;
    }

    public void setIdBrand(int idBrand) {
        this.idBrand = idBrand;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Brand(int idBrand, String code, String name) {
        this.idBrand = idBrand;
        this.code = code;
        this.name = name;
    }

    public Brand(String code, String name) {
        this.code = code;
        this.name = name;
    }

}
