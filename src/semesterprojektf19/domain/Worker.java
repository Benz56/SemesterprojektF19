package semesterprojektf19.domain;

import semesterprojektf19.domain.accesscontrol.Role;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class Worker extends Person {

    private final List<Case> cases = new ArrayList<>();
    
    //UUID + institution

    Worker(UUID uuid, String firstName, String lastName, Role role, Institution institution) {
        super(uuid, firstName, lastName, role, institution);
    }
    
    Worker(UUID uuid, String firstName, String lastName, Role role) {
        super(uuid, firstName, lastName, role);
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
