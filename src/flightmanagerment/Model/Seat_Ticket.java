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
public class Seat_Ticket {

    private int idSeat;
    private String code;
    private Boolean status;
    private String firstName;
    private String lastName;
    private String ic_Card;
    private int old;
    private int idFlight;
    private int idAccount;

    public int getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(int idAccount) {
        this.idAccount = idAccount;
    }

    public Seat_Ticket() {
    }

    public Seat_Ticket(String code, Boolean status, String firstName, String lastName, String ic_Card,
            int idFlight) {
        super();
        this.code = code;
        this.status = status;
        this.firstName = firstName;
        this.lastName = lastName;
        this.ic_Card = ic_Card;
        this.idFlight = idFlight;
    }

    public Seat_Ticket(int idSeat, String code, Boolean status, String firstName, String lastName, String ic_Card, int old,
            int idFlight, int idAccount) {
        super();
        this.idSeat = idSeat;
        this.code = code;
        this.status = status;
        this.firstName = firstName;
        this.lastName = lastName;
        this.ic_Card = ic_Card;
        this.old = old;
        this.idFlight = idFlight;
        this.idAccount = idAccount;
    }

    public int getIdSeat() {
        return idSeat;
    }

    public void setIdSeat(int idSeat) {
        this.idSeat = idSeat;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
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

    public String getIc_Card() {
        return ic_Card;
    }

    public void setIc_Card(String ic_Card) {
        this.ic_Card = ic_Card;
    }

    public int getOld() {
        return old;
    }

    public void setOld(int old) {
        this.old = old;
    }

    public int getIdFlight() {
        return idFlight;
    }

    public void setIdFlight(int idFlight) {
        this.idFlight = idFlight;
    }
}
