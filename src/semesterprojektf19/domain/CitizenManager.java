package semesterprojektf19.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CitizenManager implements Serializable {

    private Map<String, Citizen> citizensMap;

    public CitizenManager() {
        citizensMap = new TreeMap<>();
    }

    public Citizen createCitizen(Citizen citizen) {
        citizensMap.putIfAbsent(citizen.getCpr(), citizen);
        return citizensMap.get(citizen.getCpr());
    }

    public void removeCitizen(Citizen citizen) {
        citizensMap.remove(citizen.getCpr());
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

    public List<Citizen> searchCitizensByBirthday(String birthday) {
        List<Citizen> citizensWithSameBirthday = new ArrayList<>();
        for (Citizen citizen : citizensMap.values()) {
            if (citizen.getBirthday().equals(birthday)) {
                citizensWithSameBirthday.add(citizen);
            }
        }
        return citizensWithSameBirthday;
    }

    public List<Citizen> searchCitizensByName(String name) {
        List<Citizen> citizensWithSameName = new ArrayList<>();
        for (Citizen citizen : citizensMap.values()) {
            if (name.contains(citizen.getLastName()) || name.contains(citizen.getFirstName())) {
                citizensWithSameName.add(citizen);
            }
        }
        return citizensWithSameName;
    }

    public Map<String, Citizen> getCitizens() {
        return citizensMap;
    }

    public boolean isCitizenCreated(String birthday, String controlNumber) {
        for (Citizen citizen : citizensMap.values()) {
            if (citizen.getBirthday().equals(birthday) && citizen.getControlNumber().equals(controlNumber)) {
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
