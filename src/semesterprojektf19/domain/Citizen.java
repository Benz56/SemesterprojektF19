/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojektf19.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hala_
 */
public class Citizen implements Comparable<Citizen>, Serializable {

    private String firstName;
    private String lastName;
    private String birthDateDDMMYY;
    private String controlNumber;
    private String cpr;
    private int phoneNumber;
    private String address;
    private List<Case> cases;

    public Citizen(String firstName, String lastName, String birthDateDDMMYY, String controlNumber, int phoneNumber, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDateDDMMYY = birthDateDDMMYY;
        this.controlNumber = controlNumber;
        this.cpr = this.birthDateDDMMYY + "-" + controlNumber;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.cases = new ArrayList<>();
    }

    public void openCase(Inquiry inquiry) {
        cases.add(new Case(inquiry));
    }

    public List<Case> getCases() {
        return cases;
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

    public String getBirthDateDDMMYY() {
        return birthDateDDMMYY;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCpr() {
        return cpr;
    }

    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public int compareTo(Citizen o) {
        int r = this.birthDateDDMMYY.compareTo(o.birthDateDDMMYY);
        if (r == 0) {
            r = this.lastName.compareTo(o.lastName);
        }
        if (r == 0) {
            r = this.firstName.compareTo(o.firstName);
        }
        if (r == 0) {
            r = Integer.compare(this.phoneNumber, o.phoneNumber);
        }
        return r;
    }

    public String toString() {
        String output;
        output = firstName + " " + lastName + "(Birthday: " + birthDateDDMMYY + ")\n";
        return output;
    }

}
