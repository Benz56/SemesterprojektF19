package semesterprojektf19.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import semesterprojektf19.domain.accesscontrol.Role;

public class Citizen extends Person {

    private final String cpr;
    private final List<Case> cases = new ArrayList<>();

    public Citizen(UUID uuid, String firstName, String lastName, String birthday, int controlNumber, String address, int phoneNumber, Role role) {
        super(uuid, firstName, lastName, birthday, controlNumber, address, phoneNumber, role);
        this.cpr = birthday + controlNumber;
    }

    public void addCase(Case case1) {
        cases.add(case1);
    }

    public List<Case> getCases() {
        return cases;
    }

    public String getCpr() {
        return cpr;
    }

    @Override
    public String toString() {
        return getFirstName() + " " + getLastName() + " (Birthday: " + getBirthday() + ")\n";
    }
}
