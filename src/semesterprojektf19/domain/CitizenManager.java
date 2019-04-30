package semesterprojektf19.domain;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import semesterprojektf19.persistence.Persistence;

public enum CitizenManager {
    INSTANCE;

    private final Map<String, Citizen> citizensMap;

    private CitizenManager() {
        citizensMap = new TreeMap<>();
        Arrays.asList(new File("citizens").listFiles()).forEach(file -> addCitizen((Citizen) Persistence.INSTANCE.readObjectFromFile("citizens/" + file.getName())));
    }

    public Citizen addCitizen(Citizen citizen) {
        citizensMap.putIfAbsent(citizen.getCpr(), citizen);
        return citizensMap.get(citizen.getCpr());
    }

    public void removeCitizen(Citizen citizen) {
        citizensMap.remove(citizen.getCpr());
    }

    public Citizen getCitizen(String stringValue) {
        return citizensMap.values().stream().filter(citizen -> citizen.toString().equalsIgnoreCase(stringValue)).findFirst().orElse(null);
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

    public boolean isCitizenCreated(String birthday, int controlNumber) {
        for (Citizen citizen : citizensMap.values()) {
            if (citizen.getBirthday().equals(birthday) && citizen.getControlNumber() == controlNumber) {
                return true;
            }
        }
        return false;
    }



    public void refresh() {
        citizensMap.clear();
        Arrays.asList(new File("citizens").listFiles()).forEach(file -> addCitizen((Citizen) Persistence.INSTANCE.readObjectFromFile("citizens/" + file.getName())));
    }
}
