package semesterprojektf19.domain;

import semesterprojektf19.domain.accesscontrol.Role;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import semesterprojektf19.aquaintance.Column;
import semesterprojektf19.persistence.Persistence;

public class Person implements Comparable<Person>, Serializable {

    private String id;
    private final UUID uuid;
    private final String controlNumber;
    private final String birthday;
    private String firstName, lastName, address;
    private String phoneNumber;
    private Role role;
    private Institution institution;

    //UUID - institution
    public Person(UUID uuid, String firstName, String lastName, String birthday, String controlNumber, String address, String phoneNumber, Role role) {
        this.uuid = uuid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.controlNumber = controlNumber;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.role = role;
    }

    //UUID + institution
    public Person(UUID uuid, String firstName, String lastName, String birthday, String controlNumber, String address, String phoneNumber, Role role, Institution institution) {
        this.uuid = uuid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.controlNumber = controlNumber;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.role = role;
        this.institution = institution;
    }

    public Map<String, String> getMap() {
        Map<String, String> personMap = new HashMap<>();
        personMap.put(Column.UUID.getColumnName(), uuid.toString());
        personMap.put(Column.FNAME.getColumnName(), firstName);
        personMap.put(Column.LNAME.getColumnName(), lastName);
        personMap.put(Column.BDAY.getColumnName(), birthday);
        personMap.put(Column.CNUMBER.getColumnName(), controlNumber);
        personMap.put(Column.PHONE.getColumnName(), phoneNumber);
        personMap.put(Column.ADDR.getColumnName(), address);
        personMap.put(Column.ROLE.getColumnName(), role.toString());
        if (institution != null) {
            personMap.put(Column.INSTITUTION.getColumnName(), institution.getName());
        }
        return personMap;
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
