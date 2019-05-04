package semesterprojektf19.domain;

import semesterprojektf19.domain.accesscontrol.Role;
import java.io.Serializable;
import java.util.UUID;
import semesterprojektf19.persistence.Persistence;

public class Person implements Comparable<Person>, Serializable {

    private final UUID uuid;
    private final String id;
    private final String controlNumber;
    private final String birthday;
    private String firstName, lastName, address;
    private String phoneNumber;
    private Role role;
    private Institution institution;

    //UUID - institution
    public Person(UUID uuid, String firstName, String lastName, String birthday, String controlNumber, String address, String phoneNumber, Role role) {
        this.id = null;
        this.uuid = uuid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.controlNumber = controlNumber;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.role = role;
    }

    //String ID - institution
    public Person(String id, String firstName, String lastName, String birthday, String controlNumber, String address, String phoneNumber, Role role) {
        this.id = id;
        this.uuid = null;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.controlNumber = controlNumber;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.role = role;
    }
    
    //String ID + institution
    public Person(String id, String firstName, String lastName, String birthday, String controlNumber, String address, String phoneNumber, Role role, Institution institution) {
        this.id = id;
        this.uuid = null;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.controlNumber = controlNumber;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.role = role;
        this.institution = institution;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
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

    public String getControlNumber() {
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
            r = phoneNumber.compareTo(o.phoneNumber);
        }
        return r;
    }

    public void saveToFile() {
        Persistence.INSTANCE.writeObjectToFile("persons/" + uuid.toString() + ".ser", this, false);
    }
}
