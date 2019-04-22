/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojektf19.domain;

import java.util.UUID;
import semesterprojektf19.domain.accesscontrol.Role;
import semesterprojektf19.persistence.Persistence;

/**
 *
 * @author Benjamin Staugaard | Benz56
 */
public class RegistrationFacadeImpl implements RegistrationFacade {

    @Override
    public void registerCitizen(String firstName, String lastName, String birthday, int controlNumber, String address, int phoneNumber) {
        Persistence.INSTANCE.writeObjectToFile("citizens/" + birthday + "-" + controlNumber, new Person(UUID.randomUUID(), firstName, lastName, birthday, controlNumber, address, phoneNumber, Role.CITIZEN), false);
    }

    @Override
    public boolean registerEmployee(String username, String password, String firstName, String lastName, String birthday, int controlNumber, String address, int phoneNumber, String role) {
        Person person;
        switch (Role.valueOf(role)) {
            case EMPLOYEE:
                person = new Worker(UUID.randomUUID(), firstName, lastName, birthday, controlNumber, address, phoneNumber, Role.valueOf(role));
                break;
            case ADMIN:
                person = new Person(UUID.randomUUID(), firstName, lastName, birthday, controlNumber, address, phoneNumber, Role.valueOf(role));
                break;
            default:
                throw new AssertionError(Role.valueOf(role).name());
        }
        return Persistence.INSTANCE.register(username, password, person.getUuid(), person);
    }
}
