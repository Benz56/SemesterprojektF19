package semesterprojektf19.domain;

import java.io.IOException;
import java.util.UUID;
import semesterprojektf19.persistence.Storage;
import semesterprojektf19.persistence.StorageImpl;

public class InteractionHandlerImpl implements InteractionHandler {

    private Storage dataAccess;
    private CitizenManager citizens;
    private Person person;

    public InteractionHandlerImpl() {
        try {
            dataAccess = new StorageImpl();
        } catch (IOException ex) {
        }
        citizens = new CitizenManager();
    }

    @Override
    public boolean login(String username, String password) {
        String[] tokens = dataAccess.authenticate(username, password);
        if (tokens != null) {
            Role role = Role.valueOf(tokens[3]);
            switch (role) {
                case EMPLOYEE:
                    break;
                case ADMIN:
                    break;
                default:
                    throw new AssertionError(role.name());

            }
            // Sæt brugerens rolle som er tokens[2];
        }
        return tokens != null;
    }

    @Override
    public boolean register(String username, String password, String firstName, String lastName) {
        Person person = new Person(UUID.randomUUID(), firstName, lastName, 0, "", Role.EMPLOYEE);
        return dataAccess.register(username, password, person.getUuid(), person);
    }

    @Override
    public void createCase(String firstName, String lastName, String birthday, String controlNumber, int phoneNumber, String address, String shortInfo) {
        Citizen citizen = citizens.createCitizen(new Citizen(firstName, lastName, birthday, controlNumber, phoneNumber, address));
        Case case1 = new Case(new Inquiry(shortInfo));
        citizen.addCase(case1);
        // Tilføj sag til aktuel sagsbehandlers liste
    }
}
