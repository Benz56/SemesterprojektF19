package semesterprojektf19.domain;

import java.util.List;

/**
 *
 * @author Gruppe 22 på SE/ST E19, MMMI, Syddansk Universitet
 */
public interface RegistrationFacade {

    void registerCitizen(String firstName, String lastName, String birthday, String controlNumber, String address, String phoneNumber);

    boolean registerEmployee(String username, String password, String firstName, String lastName, String role, String institution);

    void registerInstitution(String name, String adress);

    List<String> getInstitutionNames();

    void editCitizen(String firstName, String lastName, String address, String phoneNumber, String uuid);
}
