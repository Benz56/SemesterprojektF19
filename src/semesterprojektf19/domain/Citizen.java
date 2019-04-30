package semesterprojektf19.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import semesterprojektf19.domain.accesscontrol.Role;
import semesterprojektf19.persistence.Persistence;

public class Citizen extends Person {

    private final String cpr;
    private final List<Case> cases = new ArrayList<>();
    private static final long serialVersionUID = 4813878493760830975L;

    public Citizen(UUID uuid, String firstName, String lastName, String birthday, int controlNumber, String address, int phoneNumber, Role role) {
        super(uuid, firstName, lastName, birthday, controlNumber, address, phoneNumber, role);
        this.cpr = birthday + "-" + controlNumber;
    }

    public void addCase(Case c) {
        cases.add(c);
    }

    public List<Case> getCases() {
        return cases;
    }
    
    public Case getCase(int index){
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
        return getFirstName() + " " + getLastName() + " (Birthday: " + getBirthday() + ")\n";
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

}
