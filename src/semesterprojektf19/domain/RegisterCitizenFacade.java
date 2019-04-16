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
public interface RegisterCitizenFacade {

    void register(String firstName, String lastName, String birthday, String controlNumber, int phoneNumber);

}
