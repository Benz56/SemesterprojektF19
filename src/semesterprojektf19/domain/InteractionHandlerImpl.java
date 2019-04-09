/* 
 * Developed by SDU OOP E18 SE/ST grp 21
 * Frederik Alexander Hounsvad, Andreas Kaer Lauritzen,  Patrick Nielsen, Oliver Lind Nordestgaard, Benjamin Eichler Staugaard
 * The use of this work is limited to educational purposes
 */
package semesterprojektf19.domain;

import java.io.IOException;
import semesterprojektf19.persistence.Storage;
import semesterprojektf19.persistence.StorageImpl;

/**
 *
 * A handler for the domain layer of world of safety
 *
 */
public class InteractionHandlerImpl implements InteractionHandler {

    private Storage dataAccess;
    private CitizenManager citizens;
    private Worker worker;

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
            // Sæt brugerens rolle som er tokens[2];
        }
        return tokens != null;
    }

    @Override
    public boolean register(String username, String password, String firstName, String lastName) {
        return dataAccess.register(username, password, Role.EMPLOYEE.toString(), firstName, lastName);
    }

    @Override
    public void createCase(String firstName, String lastName, String birthday, String controlNumber, int phoneNumber, String address, String shortInfo) {
        Citizen citizen = citizens.createCitizen(new Citizen(firstName, lastName, birthday, controlNumber, phoneNumber, address));
        Case case1 = new Case(new Inquiry(shortInfo));
        citizen.addCase(case1);
        //tilføj sag til aktuel sagsbehandlers liste
    }
}
