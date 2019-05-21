package semesterprojektf19.domain;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import semesterprojektf19.acquaintance.Column;
import semesterprojektf19.domain.accesscontrol.Role;
import semesterprojektf19.acquaintance.UserContainer;
import semesterprojektf19.persistence.PersistenceFacade;
import semesterprojektf19.persistence.PersistenceFacadeImpl;

public class DomainFacadeImpl implements DomainFacade {

    private final PersistenceFacade persistenceFacade = new PersistenceFacadeImpl();

    @Override
    public void createCase(Map<String, String> caseDetails) {
        Citizen citizen = CitizenManager.INSTANCE.getCitizen(caseDetails.get(Column.CITIZEN.getColumnName()));
        Case c = new Case(caseDetails, citizen);
        ((Worker) UserContainer.getUser()).addCase(c);
        citizen.addCase(c);
        UUID citizenUUID = citizen.getUuid();
        UUID caseUUID = c.getUUID();
        UUID diaryUUID = c.getDiary().getUuid();
        persistenceFacade.registerCase(caseDetails, caseUUID, citizenUUID, UserContainer.getUser().getUuid(), diaryUUID);
    }

    @Override
    public List<String> matchCitizens(String contains) {
        return CitizenManager.INSTANCE.getCitizens().values().stream().map(Citizen::toString).filter(citizen -> citizen.toLowerCase().contains(contains.toLowerCase())).collect(Collectors.toList());
    }

    @Override
    public List<String> getConnectedCitizens() {
        Person user = UserContainer.getUser();
        if (user != null) {
            if (user.getRole() == Role.SOCIALWORKER) {
                return CitizenManager.INSTANCE.getCitizens().values().stream().filter(citizen -> citizen.getCases().stream().anyMatch(c -> c.getInstitution().equals(user.getInstitution()))).map(Citizen::toString).collect(Collectors.toList());
            } else if (user.getRole() == Role.CASEWORKER) {
                return CitizenManager.INSTANCE.getCitizens().values().stream().filter(citizen -> citizen.getCases().stream().anyMatch(c -> c.getCaseWorker().equals(user.getUuid()))).map(Citizen::toString).collect(Collectors.toList());
            }
        } else {
            return Collections.emptyList();
        }
        return null;
    }
    
    @Override
    public void refresh() {
        CitizenManager.INSTANCE.refresh();
    }

    @Override
    public Map<String, String> getCitizenDetails(String citizenString) {
        Map<String, String> details = new HashMap<>();
        Citizen citizen = CitizenManager.INSTANCE.getCitizen(citizenString);
        details.put(Column.FNAME.getColumnName(), citizen.getFirstName());
        details.put(Column.LNAME.getColumnName(), citizen.getLastName());
        details.put(Column.PHONE.getColumnName(), citizen.getPhoneNumber());
        details.put(Column.NAME.getColumnName(), citizen.getFirstName() + " " + citizen.getLastName());
        details.put(Column.BDAY.getColumnName(), citizen.getBirthday());
        details.put(Column.ADDR.getColumnName(), citizen.getAddress());
        details.put("cases", citizen.getCases().stream().map(c -> c.getInquiry().getShortInfo()).collect(Collectors.joining("\n")));
        details.put(Column.UUID.getColumnName(), citizen.getUuid().toString());
        details.put(Column.CNUMBER.getColumnName(), citizen.getCpr());
        return details;
    }

    @Override
    public List<List<Map<String, String>>> getDiaryDetails(String citizenString, int caseIndex) {
        return persistenceFacade.getDiaryNotes(CitizenManager.INSTANCE.getCitizen(citizenString).getCase(caseIndex).getDiary().getUuid()).values().stream().sorted((List<Map<String, String>> o1, List<Map<String, String>> o2) -> o2.get(o2.size() - 1).get(Column.DATE_OF_EDIT.getColumnName()).compareTo(o1.get(o1.size() - 1).get(Column.DATE_OF_EDIT.getColumnName()))).collect(Collectors.toList());
    }

    @Override
    public Map<String, String> addDiaryNoteVersion(String citizenString, int caseIndex, Map<String, String> details) {
        Citizen citizen = CitizenManager.INSTANCE.getCitizen(citizenString);
        UUID uuid = UUID.fromString(details.get(Column.UUID.getColumnName()));
        DiaryNote version = new DiaryNote(UUID.fromString(details.get(Column.UUID.getColumnName())), UserContainer.getUser(), details.get(Column.CONTENT.getColumnName()), details.get(Column.TITLE.getColumnName()), details.get(Column.DATE_OF_OBS.getColumnName()));
        citizen.getCase(caseIndex).getDiary().getNotes().stream().filter(note -> note.getUuid().equals(uuid)).findFirst().ifPresent(note -> note.addNoteVersion(version));
        Map<String, String> content = new HashMap<>();
        content.put(Column.UUID.getColumnName(), version.getUuid().toString());
        content.put(Column.TITLE.getColumnName(), version.getTitle());
        content.put(Column.DATE_OF_OBS.getColumnName(), new SimpleDateFormat("yyyy-MM-dd").format(version.getDateOfObservation()));
        content.put(Column.DATE_OF_EDIT.getColumnName(), new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").format(version.getDate()).split(".")[0]);
        content.put(Column.CONTENT.getColumnName(), version.getNote());
        content.put(Column.CREATOR.getColumnName(), version.getCreator().getFirstName() + " " + version.getCreator().getLastName());
        persistenceFacade.createDiaryNote(version.getUuid(), citizen.getCase(caseIndex).getUUID(), UserContainer.getUser().getUuid(),
                version.getDateOfObservation().getTime(), System.currentTimeMillis(), version.getTitle(), version.getNote());
        return content;

    }
}
