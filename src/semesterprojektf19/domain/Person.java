package semesterprojektf19.domain;

import java.io.Serializable;
import java.util.UUID;

public class Person implements Serializable {

    private final UUID uuid;
    private String firstName, lastName, address;
    private int phoneNumber;
    private Role role;

    public Person(UUID uuid, String firstName, String lastName, int phoneNumber, String address, Role role) {
        this.uuid = uuid;
        this.firstName = firstName;
        this.lastName = lastName;
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

}
