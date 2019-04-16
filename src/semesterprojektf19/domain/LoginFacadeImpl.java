package semesterprojektf19.domain;

import java.util.Arrays;
import java.util.List;
import semesterprojektf19.domain.accesscontrol.Role;
import java.util.UUID;
import semesterprojektf19.persistence.Persistence;

public class LoginFacadeImpl implements LoginFacade {

    private final CitizenManager citizens;
    private Person person;

    public LoginFacadeImpl() {
        citizens = new CitizenManager();
    }

    @Override
    public List<String> login(String username, String password) {
        Object person = Persistence.INSTANCE.authenticate(username, password);
        this.person = person != null ? (Person) person : null;
        if (this.person != null) {
            return Arrays.asList(this.person.getFirstName() + " " + this.person.getLastName()); //Her skal tilføjes bogsted og fagområder.
        } else return null;
    }

    @Override
    public boolean register(String username, String password, String firstName, String lastName) {
        Person person = new Person(UUID.randomUUID(), firstName, lastName, 0, "", Role.EMPLOYEE);
        return Persistence.INSTANCE.register(username, password, person.getUuid(), person);
    }

    /*@Override
    public void createCase(String firstName, String lastName, String birthday, String controlNumber, int phoneNumber, String address, String shortInfo) {
        Citizen citizen = citizens.createCitizen(new Citizen(firstName, lastName, birthday, controlNumber, phoneNumber, address));
        Case case1 = new Case(new Inquiry(shortInfo));
        citizen.addCase(case1);
        // Tilføj sag til aktuel sagsbehandlers liste
    }*/
}
