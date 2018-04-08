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
    private Date arrival;
    private int passenger;
    private String brand;
    private String flight_number;
    private String flight_arrival;
    private String flight_depart;
    private Double price;

    public Flight() {
    }

    public Flight(int idFlight, String origin, String destination, Date depart, Date arrival, int passenger,
            String brand, String flight_number, String flight_arrival, String flight_depart, Double price) {
        super();
        this.idFlight = idFlight;
        this.origin = origin;
        this.destination = destination;
        this.depart = depart;
        this.arrival = arrival;
        this.passenger = passenger;
        this.brand = brand;
        this.flight_number = flight_number;
        this.flight_arrival = flight_arrival;
        this.flight_depart = flight_depart;
        this.price = price;
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

    public Date getArrival() {
        return arrival;
    }

    public void setArrival(Date arrival) {
        this.arrival = arrival;
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

    public String getFlight_arrival() {
        return flight_arrival;
    }

    public void setFlight_arrival(String flight_arrival) {
        this.flight_arrival = flight_arrival;
    }

    public String getFlight_depart() {
        return flight_depart;
    }

    public void setFlight_depart(String flight_depart) {
        this.flight_depart = flight_depart;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
