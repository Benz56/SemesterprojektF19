/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojektf19.domain;

/**
 *
 * @author Benjamin Staugaard | Benz56
 */
public interface RegistrationFacade {

    void registerCitizen(String firstName, String lastName, String birthday, int controlNumber, String address, int phoneNumber);

    boolean registerEmployee(String username, String password, String firstName, String lastName, String birthday, int controlNumber, String address, int phoneNumber, String role);

}
