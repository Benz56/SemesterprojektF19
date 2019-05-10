package semesterprojektf19.domain;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import semesterprojektf19.aquaintance.Column;
import semesterprojektf19.domain.accesscontrol.Role;
import semesterprojektf19.persistence.Persistence;

public class Citizen extends Person {

    private String cpr;
    private String address, phoneNumber, controlNumber, birthday;
    private static final long serialVersionUID = 4813878493760830975L;

    public Citizen(UUID uuid, String firstName, String lastName, String address, String phoneNumber, String controlNumber, String birthday) {
        super(uuid, firstName, lastName, Role.CITIZEN);
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.controlNumber = controlNumber;
        this.birthday = birthday;
        formatCpr();
    }

    public Citizen(Map<String, String> details) {
        super(UUID.fromString(details.get(Column.UUID.getColumnName())),
                details.get(Column.FNAME.getColumnName()),
                details.get(Column.LNAME.getColumnName()),
                Role.CITIZEN);
        formatCpr();
    }

    public void addCase(Case c) {
        getCases().add(c);
    }

    public Case getCase(int index) {
        System.out.println(index);
        return getCases().get(index);
    }

    public String getCpr() {
        return cpr;
    }

    @Override
    public String toString() {
        return getFirstName() + " " + getLastName() + " (Birthday: " + birthday + ")\n";
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.cpr);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this == obj || this.cpr.equals(((Citizen) obj).cpr);
    }

    private String formatCpr() {
        String birthdayDDMMYY = "";
        birthdayDDMMYY += birthday.substring(8, 10);
        birthdayDDMMYY += birthday.substring(5, 7);
        birthdayDDMMYY += birthday.substring(2, 4);
        this.cpr = birthdayDDMMYY + controlNumber;
        return cpr;
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
        Persistence.INSTANCE.writeObjectToFile("citizens/" + cpr + ".ser", this, false);
    }

}
