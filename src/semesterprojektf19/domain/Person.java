package semesterprojektf19.domain;

import semesterprojektf19.domain.accesscontrol.Role;
import java.io.Serializable;
import java.util.UUID;

public class Person implements Comparable<Person>, Serializable {

    private final UUID uuid;
    private final int controlNumber;
    private final String birthday;
    private String firstName, lastName, address;
    private int phoneNumber;
    private Role role;

    public Person(UUID uuid, String firstName, String lastName, String birthday, int controlNumber, String address, int phoneNumber, Role role) {
        this.uuid = uuid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.controlNumber = controlNumber;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.role = role;
    }

    public UUID getUuid() {
        return uuid;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getBirthday() {
        return birthday;
    }

    public int getControlNumber() {
        return controlNumber;
    }

    @Override
    public int compareTo(Person o) {
        int r = birthday.compareTo(o.birthday);
        if (r == 0) {
            r = lastName.compareTo(o.lastName);
        }
        if (r == 0) {
            r = firstName.compareTo(o.firstName);
        }
        if (r == 0) {
            r = Integer.compare(phoneNumber, o.phoneNumber);
        }
        return r;
    }
}
