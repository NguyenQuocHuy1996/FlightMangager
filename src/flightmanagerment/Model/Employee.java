/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightmanagerment.Model;

import java.sql.Blob;
import java.sql.Date;

/**
 *
 * @author Admin
 */
public class Employee {

    private int idAccount;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String ic_Card;
    private String education_level;
    private String department;
    private String homeTown;
    private Boolean sex;
    private String phoneNumber;
    private Blob image;
    private String address_number;
    private String address_street;
    private String address_district;
    private String address_city;

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

    public String getEducation_level() {
        return education_level;
    }

    public void setEducation_level(String education_level) {
        this.education_level = education_level;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getHomeTown() {
        return homeTown;
    }

    public void setHomeTown(String homeTown) {
        this.homeTown = homeTown;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress_number() {
        return address_number;
    }

    public void setAddress_number(String address_number) {
        this.address_number = address_number;
    }

    public String getAddress_street() {
        return address_street;
    }

    public void setAddress_street(String address_street) {
        this.address_street = address_street;
    }

    public String getAddress_district() {
        return address_district;
    }

    public void setAddress_district(String address_district) {
        this.address_district = address_district;
    }

    public String getAddress_city() {
        return address_city;
    }

    public void setAddress_city(String address_city) {
        this.address_city = address_city;
    }

    public Employee() {
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

    public Employee(int idAccount, String email, String password, String firstName, String lastName, Date dateOfBirth, String ic_Card, String education_level, String department, String homeTown, Boolean sex, String phoneNumber, Blob image, String address_number, String address_street, String address_district, String address_city) {
        this.idAccount = idAccount;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.ic_Card = ic_Card;
        this.education_level = education_level;
        this.department = department;
        this.homeTown = homeTown;
        this.sex = sex;
        this.phoneNumber = phoneNumber;
        this.image = image;
        this.address_number = address_number;
        this.address_street = address_street;
        this.address_district = address_district;
        this.address_city = address_city;
    }

}
