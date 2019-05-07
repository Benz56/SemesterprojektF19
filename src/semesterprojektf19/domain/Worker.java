package semesterprojektf19.domain;

import semesterprojektf19.domain.accesscontrol.Role;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class Worker extends Person {

    private final List<Case> cases = new ArrayList<>();

    //String ID + institution
    public Worker(String id, String firstName, String lastName, String birthday,
            String controlNumber, String address, String phoneNumber, Role role, Institution institution) {
        super(id, firstName, lastName, birthday, controlNumber, address, phoneNumber, role, institution);
    }
    
    //UUID - institution
    public Worker(UUID id, String firstName, String lastName, String birthday,
            String controlNumber, String address, String phoneNumber, Role role) {
        super(id, firstName, lastName, birthday, controlNumber, address, phoneNumber, role);
    }
    
    public Worker(UUID uuid, String firstName, String lastName, String birthday,
            String controlNumber, String address, String phoneNumber, Role role, Institution institution) {
        super(uuid, firstName, lastName, birthday, controlNumber, address, phoneNumber, role, institution);
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
