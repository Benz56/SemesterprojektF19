package semesterprojektf19.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import semesterprojektf19.domain.accesscontrol.Role;

public class Citizen extends Person implements Comparable<Citizen>, Serializable {

    private final String birthday, controlNumber, cpr;
    private final List<Case> cases = new ArrayList<>();
    private int phoneNumber;

    public Citizen(String firstName, String lastName, String birthday, String controlNumber, int phoneNumber, String address) {
        super(UUID.randomUUID(), firstName, lastName, phoneNumber, address, Role.CITIZEN);
        this.birthday = birthday;
        this.controlNumber = controlNumber;
        this.cpr = this.birthday + controlNumber;
    }

    public void addCase(Case case1) {
        cases.add(case1);
    }

    public List<Case> getCases() {
        return cases;
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

    @Override
    public int compareTo(Citizen o) {
        int r = this.birthday.compareTo(o.birthday);
        if (r == 0) {
            r = getLastName().compareTo(o.getLastName());
        }
        if (r == 0) {
            r = getFirstName().compareTo(o.getFirstName());
        }
        if (r == 0) {
            r = Integer.compare(this.phoneNumber, o.phoneNumber);
        }
        return r;
    }

    @Override
    public String toString() {
        return getFirstName() + " " + getLastName() + "(Birthday: " + birthday + ")\n";
    }
}
