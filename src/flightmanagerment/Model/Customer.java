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
import java.sql.Date;

public class Customer {

    private int idAccount;
    private String email;
    private String password;
    private boolean isActive;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String ic_Card;
    private String homeTown;
    private String sex;
    private String phoneNumber;

    public Customer() {
    }
    public Customer(String email, String password, String firstName, String lastName){
    this.email = email;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    }
    public Customer(int idAccount, String email, String password, boolean isActive, String firstName, String lastName,
            Date dateOfBirth, String ic_Card, String homeTown, String sex, String phoneNumber) {
        super();
        this.idAccount = idAccount;
        this.email = email;
        this.password = password;
        this.isActive = isActive;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.ic_Card = ic_Card;
        this.homeTown = homeTown;
        this.sex = sex;
        this.phoneNumber = phoneNumber;
    }

    public int getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(int idAccount) {
        this.idAccount = idAccount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getIc_Card() {
        return ic_Card;
    }

    public void setIc_Card(String ic_Card) {
        this.ic_Card = ic_Card;
    }

    public String getHomeTown() {
        return homeTown;
    }

    public void setHomeTown(String homeTown) {
        this.homeTown = homeTown;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
