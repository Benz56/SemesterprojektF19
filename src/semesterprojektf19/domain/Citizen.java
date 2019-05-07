package semesterprojektf19.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import semesterprojektf19.aquaintance.Column;
import semesterprojektf19.domain.accesscontrol.Role;
import semesterprojektf19.persistence.Persistence;

public class Citizen extends Person {

    private String cpr;
    private final List<Case> cases = new ArrayList<>();
    private static final long serialVersionUID = 4813878493760830975L;

    public Citizen(UUID uuid, String firstName, String lastName, String birthday, String controlNumber, String address, String phoneNumber, Role role) {
        super(uuid, firstName, lastName, birthday, controlNumber, address, phoneNumber, role);
        formatCpr();
    }

    public Citizen(Map<String, String> details) {
        super(UUID.fromString(details.get(Column.UUID.getColumnName())), details.get(Column.FNAME.getColumnName()), details.get(Column.LNAME.getColumnName()), details.get(Column.BDAY.getColumnName()), details.get(Column.CNUMBER.getColumnName()), details.get(Column.ADDR.getColumnName()), details.get(Column.PHONE.getColumnName()), Role.CITIZEN);
        formatCpr();
    }

    public void addCase(Case c) {
        cases.add(c);
    }

    public List<Case> getCases() {
        return cases;
    }

    public Case getCase(int index) {
        System.out.println(index);
        return cases.get(index);
    }

    public String getCpr() {
        return cpr;
    }

    @Override
    public void saveToFile() {
        Persistence.INSTANCE.writeObjectToFile("citizens/" + cpr + ".ser", this, false);
    }

    @Override
    public String toString() {
        return getFirstName() + " " + getLastName() + " (CPR: " + getCpr() + ")\n";
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
    
    private String formatCpr(){
        String birthdayDDMMYY = "";
        birthdayDDMMYY += getBirthday().substring(8, 10);
        birthdayDDMMYY += getBirthday().substring(5, 7);
        birthdayDDMMYY += getBirthday().substring(2, 4);
        this.cpr = birthdayDDMMYY+getControlNumber();
        return cpr;
    }
}
