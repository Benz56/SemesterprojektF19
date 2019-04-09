package semesterprojektf19.domain;

/**
 *
 * Contract detailing methods needed for communication between the presentation
 * and domain layer
 *
 */
public interface InteractionHandler {

    boolean login(String username, String password);

    boolean register(String username, String password, String firstName, String lastName);
    
    void addCitizen(String firstName, String lastName, String birthday, String controlNumber, int phoneNumber, String address); 
}
