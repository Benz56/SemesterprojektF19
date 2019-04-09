package semesterprojektf19.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Citizen implements Comparable<Citizen>, Serializable {

    private String firstName, lastName, birthday, controlNumber, cpr;
    private int phoneNumber;
    private String address;
    private List<Case> cases;

    public Citizen(String firstName, String lastName, String birthday, String controlNumber, int phoneNumber, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.controlNumber = controlNumber;
        this.cpr = this.birthday + controlNumber;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.cases = new ArrayList<>();
    }

    public void addCase(Case case1) {
        cases.add(case1);
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

    public String getBirthday() {
        return birthday;
    }

    public String getControlNumber() {
        return controlNumber;
    }

    public String getCpr() {
        return cpr;
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

    @Override
    public int compareTo(Citizen o) {
        int r = this.birthday.compareTo(o.birthday);
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

    @Override
    public String toString() {
        String output;
        output = firstName + " " + lastName + "(Birthday: " + birthday + ")\n";
        return output;
    }
}
