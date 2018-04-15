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
public class Seat_Flight {

    private int idFlight;
    private int idSeat;
    private String origin;
    private String destination;
    private String ic_card;

    public Seat_Flight() {

    }

    public Seat_Flight(int idFlight, int idSeat, String origin, String destination, String ic_card, String code, String firstName, String lastName) {
        this.idFlight = idFlight;
        this.idSeat = idSeat;
        this.origin = origin;
        this.destination = destination;
        this.ic_card = ic_card;
        this.code = code;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    private String code;
    private String firstName;
    private String lastName;

    public int getIdFlight() {
        return idFlight;
    }

    public void setIdFlight(int idFlight) {
        this.idFlight = idFlight;
    }

    public int getIdSeat() {
        return idSeat;
    }

    public void setIdSeat(int idSeat) {
        this.idSeat = idSeat;
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

    public String getIc_card() {
        return ic_card;
    }

    public void setIc_card(String ic_card) {
        this.ic_card = ic_card;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
