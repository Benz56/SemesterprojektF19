/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojektf19.domain;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hala_
 */
public class Citizen {
    private String firstName; 
    private String lastName;
    private int birthDateDDMMYY;
    private final int controlNumber; 
    private int phoneNumber;
    private String address;
    private List<Case> cases; 

    public Citizen(String firstName, String lastName, int birthDateDDMMYY, int controlNumber, int phoneNumber, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDateDDMMYY = birthDateDDMMYY;
        this.controlNumber = controlNumber;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.cases = new ArrayList<>();
    }
    
    public void openCase (Inquiry inquiry){
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

    public int getBirthDateDDMMYY() {
        return birthDateDDMMYY;
    }

    public void setBirthDateDDMMYY(int birthDateDDMMYY) {
        this.birthDateDDMMYY = birthDateDDMMYY;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    
}
