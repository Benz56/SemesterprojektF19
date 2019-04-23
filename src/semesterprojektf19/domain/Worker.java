package semesterprojektf19.domain;

import semesterprojektf19.domain.accesscontrol.Role;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class Worker extends Person {

    private final List<Case> cases = new ArrayList<>();

    public Worker(UUID uuid, String firstName, String lastName, String birthday, int controlNumber, String address, int phoneNumber, Role role) {
        super(uuid, firstName, lastName, birthday, controlNumber, address, phoneNumber, role);
    }

    public Case addCase(Case c) {
        cases.add(c);
        return c;
    }

    public List<Case> getCases() {
        return cases;
    }

    public List<Citizen> getCitizens() {
        return cases.stream().map(c -> c.getCitizen()).distinct().collect(Collectors.toList());
    }
}
