package semesterprojektf19.domain;

import semesterprojektf19.domain.accesscontrol.Role;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Worker extends Person {

    // TODO implementer rolle.
    private final List<Case> cases = new ArrayList<>();

    public Worker(UUID uuid, String firstName, String lastName, int phoneNumber, String address) {
        super(uuid, firstName, lastName, phoneNumber, address, Role.EMPLOYEE);
    }

    public Case addCase(Case case1) {
        cases.add(case1);
        return case1;
    }
}
