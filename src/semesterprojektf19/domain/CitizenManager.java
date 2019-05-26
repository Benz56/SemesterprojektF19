package semesterprojektf19.domain;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;
import java.util.stream.Collectors;
import semesterprojektf19.acquaintance.Column;
import semesterprojektf19.persistence.PersistenceFacade;
import semesterprojektf19.persistence.PersistenceFacadeImpl;

/**
 *
 * @author Gruppe 22 på SE/ST E19, MMMI, Syddansk Universitet
 */
public enum CitizenManager {
    INSTANCE;

    private final Map<String, Citizen> citizensMap;
    private final PersistenceFacade persistenceFacade = new PersistenceFacadeImpl();

    private CitizenManager() {
        citizensMap = new TreeMap<>();
        refresh();
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

    public Citizen getCitizen(UUID uuid) {
        return citizensMap.values().stream().filter(citizen -> citizen.getUuid().equals(uuid)).findFirst().orElse(null);
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

    public void refresh() {
        citizensMap.clear();
        List<Map<String, String>> cases = persistenceFacade.getCases();
        for (Map<String, String> citizenMap : persistenceFacade.getCitizens()) {
            Citizen citizen = new Citizen(citizenMap);
            addCitizen(citizen);
            citizen.setCases(cases.stream().filter(details -> details.get(Column.CITIZEN.getColumnName()).equals(citizen.getUuid().toString())).map(details -> new Case(details, citizen)).collect(Collectors.toList()));
        }
    }
}
