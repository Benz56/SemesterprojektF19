/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojektf19.domain;

import semesterprojektf19.persistence.Persistence;

/**
 *
 * @author Benjamin Staugaard | Benz56
 */
public class RegisterCitizenFacadeImpl implements RegisterCitizenFacade{

    @Override
    public void register(String firstName, String lastName, String birthday, String controlNumber, int phoneNumber) {
        Persistence.INSTANCE.writeObjectToFile("citizens/"+birthday+controlNumber, new Citizen(firstName, lastName, birthday, controlNumber, phoneNumber, lastName), false);
    }
    
    
}
