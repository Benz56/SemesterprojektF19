/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojektf19.domain;

import java.util.List;
import java.util.UUID;
import semesterprojektf19.domain.accesscontrol.Role;
import semesterprojektf19.persistence.Persistence;

/**
 *
 * @author Benjamin Staugaard | Benz56
 */
public class RegistrationFacadeImpl implements RegistrationFacade {

    @Override
    public void registerCitizen(String firstName, String lastName, String birthday, String controlNumber, String address, String phoneNumber) {
//        Persistence.INSTANCE.writeObjectToFile("citizens/" + birthday + "-" + controlNumber + ".ser", new Citizen(UUID.randomUUID(), firstName, lastName, birthday, controlNumber, address, phoneNumber, Role.CITIZEN), false);
    }

    @Override
    public boolean registerEmployee(String username, String password, String firstName, String lastName, String birthday, String controlNumber, String address, String phoneNumber, String role, String institution) {
        Person person;
        Role r = Role.valueOf(role);
        switch (r) {
            case CASEWORKER:
                person = new Worker(UUID.randomUUID(), firstName, lastName, birthday, controlNumber, address, phoneNumber, Role.valueOf(role));
                
                break;
            case SOCIALWORKER:
                person = new Worker(UUID.randomUUID(), firstName, lastName, birthday, controlNumber, address, phoneNumber, Role.valueOf(role));
                break;
            case ADMIN:
                person = new Person(UUID.randomUUID(), firstName, lastName, birthday, controlNumber, address, phoneNumber, Role.valueOf(role));
                break;
            default:
                throw new AssertionError(Role.valueOf(role).name());
        }
        if(Persistence.INSTANCE.register(username, password, person.getUuid(), person)){
            if(r == Role.SOCIALWORKER){
                addWorkerToInstitution(institution, (Worker) person);
            }
            return true;
        }
        return false;
    }

    @Override
    public void registerInstitution(String name, String adress) {
        Institution institution = new Institution(name, adress);
        Persistence.INSTANCE.writeObjectToFile("institutions/" + institution.getName() + ".ser", institution, false);
    }
    
    @Override
    public List<String> getInstitutionNames(){
        return Persistence.INSTANCE.readFileNamesInDir("institutions");
    }

    private void addWorkerToInstitution(String name, Worker worker) {
        Institution institution = (Institution) Persistence.INSTANCE.readObjectFromFile("institutions/" + name + ".ser");
        institution.getWorkers().put(worker.getUuid(), worker);
        Persistence.INSTANCE.writeObjectToFile("institutions/" + institution.getName() + ".ser", institution, false);
    }
}
