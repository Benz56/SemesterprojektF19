package semesterprojektf19.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import semesterprojektf19.acquaintance.Column;
import semesterprojektf19.domain.accesscontrol.Role;
import semesterprojektf19.persistence.Persistence;

public class Citizen extends Person {

    private final String cpr, controlNumber, birthday;
    private String address, phoneNumber;
    private static final long serialVersionUID = 4813878493760830975L;

    public Citizen(UUID uuid, String firstName, String lastName, String birthday, String controlNumber, String address, String phoneNumber) {
        super(uuid, firstName, lastName, Role.CITIZEN);
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.controlNumber = controlNumber;
        this.birthday = birthday;
        this.cpr = birthday + controlNumber;
    }

    public Citizen(Map<String, String> details) {
        super(UUID.fromString(details.get(Column.UUID.getColumnName())),
                details.get(Column.FNAME.getColumnName()),
                details.get(Column.LNAME.getColumnName()),
                Role.CITIZEN);
        this.address = details.get(Column.ADDR.getColumnName());
        this.phoneNumber = details.get(Column.PHONE.getColumnName());
        this.controlNumber = details.get(Column.CNUMBER.getColumnName());
        this.birthday = details.get(Column.BDAY.getColumnName());
        this.cpr = birthday + controlNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void addCase(Case c) {
        getCases().add(c);
    }

    public Case getCase(int index) {
        return getCases().get(index);
    }

    public String getCpr() {
        return cpr;
    }

    @Override
    public String toString() {
        return getFirstName() + " " + getLastName() + " (Birthday: " + birthday + ")\n";
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getControlNumber() {
        return controlNumber;
    }

    public void saveToFile() {
        Persistence.INSTANCE.writeObjectToFile("citizens/" + birthday + controlNumber + ".ser", this, false);
    }

    @Override
    public Map<String, String> getMap() {
        Map<String, String> personMap = new HashMap<>(super.getMap());
        personMap.put(Column.BDAY.getColumnName(), birthday);
        personMap.put(Column.CNUMBER.getColumnName(), controlNumber);
        personMap.put(Column.ADDR.getColumnName(), address);
        personMap.put(Column.PHONE.getColumnName(), phoneNumber);
        return personMap;
    }
}
