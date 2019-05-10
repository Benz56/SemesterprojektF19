/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojektf19.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import semesterprojektf19.domain.accesscontrol.Role;
import semesterprojektf19.persistence.PersistenceFacade;
import semesterprojektf19.persistence.PersistenceFacadeImpl;

/**
 *
 * @author Benjamin Staugaard | Benz56
 */
public class RegistrationFacadeImpl implements RegistrationFacade {

    PersistenceFacade persistenceFacade = new PersistenceFacadeImpl();

    @Override
    public void registerCitizen(String firstName, String lastName, String birthday, String controlNumber, String address, String phoneNumber) {
        Citizen citizen = new Citizen(UUID.randomUUID(), firstName, lastName, birthday, controlNumber, address, phoneNumber);
        persistenceFacade.registerCitizen(citizen.getMap());
    }

    @Override
    public boolean registerEmployee(String username, String password, String firstName, String lastName, String birthday, String controlNumber, String address, String phoneNumber, String role, String institution) {
        Person person;
        Role r = Role.valueOf(role);
        
        switch (r) {
            case CASEWORKER:
                person = new Worker(UUID.randomUUID(), firstName, lastName,Role.valueOf(role));
                break;
            case SOCIALWORKER:
                person = new Worker(UUID.randomUUID(), firstName, lastName,Role.valueOf(role), getInstitution(institution));
                break;
            case ADMIN:
                person = new Person(UUID.randomUUID(), firstName, lastName, Role.valueOf(role));
                break;
            default:
                throw new AssertionError(Role.valueOf(role).name());
        }
        return persistenceFacade.registerEmployee(username, password, person.getUuid(), person.getMap());
    }

    private Institution getInstitution(String institution) {
        Institution inst = null;
        if (institution != null){
            inst = new Institution(institution, persistenceFacade.getInstitutions().get(institution));
        }
        return inst;
    }

    @Override
    public void registerInstitution(String name, String adress) {
        Institution institution = new Institution(name, adress);
//        Persistence.INSTANCE.writeObjectToFile("institutions/" + institution.getName() + ".ser", institution, false);
    }

    @Override
    public List<String> getInstitutionNames() {
        return new ArrayList<>(persistenceFacade.getInstitutions().keySet());
    }
}
