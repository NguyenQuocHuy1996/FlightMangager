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
    private boolean status;

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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Seat_Ticket() {
    }

    public Seat_Ticket(int idSeat, String code, boolean status) {
        this.idSeat = idSeat;
        this.code = code;
        this.status = status;
    }
}
