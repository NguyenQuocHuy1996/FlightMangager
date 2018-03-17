/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightmanagerment.Model;

import java.sql.Date;

/**
 *
 * @author Admin
 */
public class Flight {

    private int idFlight;
    private String origin;
    private String destination;
    private Date depart;
    private Date _return;
    private int passenger;
    private String brand;
    private String flight_number;

    public Flight() {
    }

    public int getIdFlight() {
        return idFlight;
    }

    public void setIdFlight(int idFlight) {
        this.idFlight = idFlight;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getDepart() {
        return depart;
    }

    public void setDepart(Date depart) {
        this.depart = depart;
    }

    public Date getReturn() {
        return _return;
    }

    public void setReturn(Date _return) {
        this._return = _return;
    }

    public int getPassenger() {
        return passenger;
    }

    public void setPassenger(int passenger) {
        this.passenger = passenger;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getFlight_number() {
        return flight_number;
    }

    public void setFlight_number(String flight_number) {
        this.flight_number = flight_number;
    }

    public Flight(int idFlight, String origin, String destination, Date depart, Date _return, int passenger, String brand, String flight_number) {
        this.idFlight = idFlight;
        this.origin = origin;
        this.destination = destination;
        this.depart = depart;
        this._return = _return;
        this.passenger = passenger;
        this.brand = brand;
        this.flight_number = flight_number;
    }
}
