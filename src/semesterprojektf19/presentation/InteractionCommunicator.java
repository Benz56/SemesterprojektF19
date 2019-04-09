package semesterprojektf19.presentation;

import semesterprojektf19.domain.InteractionHandler;
import semesterprojektf19.domain.InteractionHandlerImpl;

public class InteractionCommunicator {

    private final InteractionHandler interactionHandler = new InteractionHandlerImpl();

    public boolean login(String username, String password) {
        return interactionHandler.login(username, password);
    }

    public boolean register(String username, String password, String firstName, String lastName) {
        return interactionHandler.register(username, password, firstName, lastName);
    }

    public void createCase(String firstName, String lastName, String birthday,
            String controlNumber, int phoneNumber, String address, String shortInfo) {
        interactionHandler.createCase(firstName, lastName, birthday, controlNumber,
                phoneNumber, address, shortInfo);
    }
}
