package semesterprojektf19.domain;

/**
 *
 * @author Gruppe 22 på SE/ST E19, MMMI, Syddansk Universitet
 */
import semesterprojektf19.domain.accesscontrol.Role;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class Worker extends Person {
    Worker(UUID uuid, String firstName, String lastName, Role role, Institution institution) {
        super(uuid, firstName, lastName, role, institution);
    }

    Worker(UUID uuid, String firstName, String lastName, Role role) {
        super(uuid, firstName, lastName, role);
    }

    public Case addCase(Case c) {
        getCases().add(c);
        return c;
    }

    public List<Citizen> getCitizens() {
        return getCases().stream().map(c -> c.getCitizen()).distinct().collect(Collectors.toList());
    }
}
