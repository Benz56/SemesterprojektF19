/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojektf19.domain;

import java.util.List;

/**
 *
 * @author Benjamin Staugaard | Benz56
 */
public interface RegistrationFacade {

    void registerCitizen(String firstName, String lastName, String birthday, String controlNumber, String address, String phoneNumber);

    boolean registerEmployee(String username, String password, String firstName, String lastName, String role, String institution);

    void registerInstitution(String name, String adress);

    List<String> getInstitutionNames();
}
