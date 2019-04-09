/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojektf19.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hala_
 */
public class CitizenManager implements Serializable {

    private List<Citizen> citizens;

    public CitizenManager() {
        citizens = new ArrayList<>();
    }

    public void createCitizen(Citizen citizen) {
        citizens.add(citizen);
    }

    public void removeCitizen(Citizen citizen) {
        citizens.remove(citizen);
    }

    /**
     *
     * @param birthday is the users birthday in the format DDMMYY
     * @param name is part of the users name. For example: "Peter", "Andersen"
     * or "Peter Andersen"
     * @return a list of citizens that match the search
     */
    public List<Citizen> searchCitizens(String birthday, String name) {
        List<Citizen> citizensResult = new ArrayList<>();

        if (!birthday.isEmpty() && !name.isEmpty()) {
            citizensResult.addAll(searchCitizensByBirthday(birthday));
            citizensResult.retainAll(searchCitizensByName(name));
        } else if (!birthday.isEmpty() && name.isEmpty()) {
            citizensResult.addAll(searchCitizensByBirthday(birthday));
        } else if (birthday.isEmpty() && !name.isEmpty()) {
            citizensResult.addAll(searchCitizensByName(name));
        }
        return citizensResult;
    }

    public List<Citizen> searchCitizensByBirthday(String birthDateDDMMYY) {
        List<Citizen> citizensWithSameBirthday = new ArrayList<>();
        for (Citizen citizen : citizens) {
            if (citizen.getBirthday().equals(birthDateDDMMYY)) {
                citizensWithSameBirthday.add(citizen);
            }
        }
        return citizensWithSameBirthday;
    }

    public List<Citizen> searchCitizensByName(String name) {
        List<Citizen> citizensWithSameName = new ArrayList<>();
        for (Citizen citizen : citizens) {
            if (name.contains(citizen.getLastName()) || name.contains(citizen.getFirstName())) {
                citizensWithSameName.add(citizen);
            }
        }
        return citizensWithSameName;
    }

    public List<Citizen> getCitizens() {
        return citizens;
    }

    public boolean isCitizenCreated(String cprNumber) {
        for (Citizen citizen : citizens) {
            if (citizen.getCpr().equals(cprNumber)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Citizen c1 = new Citizen("Hala", "Al-Janabi", "280195", "1111", 00000000, "HC Ørstedskollegiet");
        Citizen c2 = new Citizen("Soffi", "Madsen", "290698", "2222", 00000000, "Cortex Park");
        Citizen c3 = new Citizen("Soffi", "Madsen", "280195", "3333", 00000000, "HC Ørstedskollegiet");

        CitizenManager cm = new CitizenManager();
        cm.createCitizen(c1);
        cm.createCitizen(c2);
        cm.createCitizen(c3);

        System.out.println(cm.getCitizens());
        System.out.println("--------------------------------------");
        System.out.println(cm.searchCitizens("280195", "Hala"));
        System.out.println(cm.searchCitizens("", "Soffi"));
        System.out.println(cm.searchCitizens("280195", ""));

    }
}
