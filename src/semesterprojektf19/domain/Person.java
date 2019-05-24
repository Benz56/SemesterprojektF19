package semesterprojektf19.domain;

import semesterprojektf19.domain.accesscontrol.Role;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import semesterprojektf19.acquaintance.Column;

/**
 *
 * @author Gruppe 22 på SE/ST E19, MMMI, Syddansk Universitet
 */
public class Person implements Comparable<Person>, Serializable {

    private final UUID uuid;
    private String firstName, lastName;
    private Role role;
    private Institution institution;
    private List<Case> cases = new ArrayList<>();

    public Person(UUID uuid, String firstName, String lastName, Role role) {
        this.uuid = uuid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    public Person(UUID uuid, String firstName, String lastName, Role role, Institution institution) {
        this.uuid = uuid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.institution = institution;
    }

    public Map<String, String> getMap() {
        Map<String, String> personMap = new HashMap<>();
        personMap.put(Column.UUID.getColumnName(), uuid.toString());
        personMap.put(Column.FNAME.getColumnName(), firstName);
        personMap.put(Column.LNAME.getColumnName(), lastName);
        personMap.put(Column.ROLE.getColumnName(), role.toString());
        personMap.put(Column.INSTITUTION.getColumnName(), institution == null ? null : institution.getName());
        return personMap;
    }

    @Override
    public int compareTo(Person o) {
        int r = lastName.compareTo(o.lastName);
        if (r == 0) {
            r = firstName.compareTo(o.firstName);
        }
        if (r == 0) {
            r = uuid.compareTo(o.uuid);
        }
        return r;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }

    public List<Case> getCases() {
        return cases;
    }

    public void setCases(List<Case> cases) {
        this.cases = cases;
    }

}
