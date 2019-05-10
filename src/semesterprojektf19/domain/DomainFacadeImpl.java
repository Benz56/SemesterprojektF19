/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojektf19.domain;

import semesterprojektf19.aquaintance.UserContainer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import semesterprojektf19.aquaintance.Column;
import semesterprojektf19.persistence.Persistence;
import semesterprojektf19.persistence.PersistenceFacade;
import semesterprojektf19.persistence.PersistenceFacadeImpl;

public class DomainFacadeImpl implements DomainFacade {

    PersistenceFacade persistenceFacade = new PersistenceFacadeImpl();

    public DomainFacadeImpl() {
        Persistence.INSTANCE.toString(); //Initialize persistence i.e. create required files.
    }

    @Override
    public void createCase(Map<String, String> caseDetails) {
        Case c = new Case(caseDetails);
        ((Worker) UserContainer.getUser()).addCase(c);
        Citizen citizen = CitizenManager.INSTANCE.getCitizen(caseDetails.get(Column.CITIZEN.getColumnName()));
        citizen.addCase(c);
        UUID citizenUUID = citizen.getUuid();
        UUID caseUUID = c.getUUID();
        persistenceFacade.registerCase(caseDetails, caseUUID, citizenUUID, UserContainer.getUser().getUuid());
    }

    @Override
    public List<String> matchCitizens(String contains) {
        return CitizenManager.INSTANCE.getCitizens().values().stream().map(Citizen::toString).filter(citizen -> citizen.toLowerCase().contains(contains.toLowerCase())).collect(Collectors.toList());
    }

    @Override
    public List<String> getUserCitizens() {
        Person user = UserContainer.getUser();
        if (user != null) {
            return ((Worker) UserContainer.getUser()).getCitizens().stream().map(Citizen::toString).collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public void refresh() {
        CitizenManager.INSTANCE.refresh();
    }

    @Override
    public Map<String, String> getCitizenDetails(String citizenString) {
        Map<String, String> details = new HashMap<>();
        Citizen citizen = CitizenManager.INSTANCE.getCitizen(citizenString);
        details.put("name", citizen.getFirstName() + " " + citizen.getLastName());
        details.put("cpr", citizen.getCpr());
        details.put("birthday", citizen.getBirthday());
        details.put("address", citizen.getAddress());
        details.put("cases", citizen.getCases().stream().map(c -> c.getInquiry().getShortInfo()).collect(Collectors.joining("\n")));
        return details;
    }

    @Override
    public List<List<Map<String, String>>> getDiaryDetails(String citizenString, int caseIndex) {
        List<List<Map<String, String>>> notes = new ArrayList<>();
        Citizen citizen = CitizenManager.INSTANCE.getCitizen(citizenString);
        citizen.getCase(caseIndex).getDiary().getNotes().forEach(note -> {

            List<Map<String, String>> versions = new ArrayList<>();
            note.getVersions().forEach(version -> {
                Map<String, String> content = new HashMap<>();
                content.put("uuid", version.getUuid().toString());
                content.put("title", version.getTitel());
                content.put("obsDate", version.getDateOfObservation());
                content.put("noteDate", version.getDate().toString());
                content.put("content", version.getNote());
                content.put("creator", version.getCreator().getFirstName() + " " + version.getCreator().getLastName());
                versions.add(content);
            });

            Collections.reverse(versions);
            notes.add(versions);
        });
        Collections.reverse(notes);
        return notes;
    }

    @Override
    public Map<String, String> addDiaryNoteVersion(String citizenString, int caseIndex, Map<String, String> details) {
        Citizen citizen = CitizenManager.INSTANCE.getCitizen(citizenString);
        UUID uuid = UUID.fromString(details.get("uuid"));
        DiaryNote version = new DiaryNote(UUID.fromString(details.get("uuid")), UserContainer.getUser(), details.get("content"), details.get("title"), details.get("obsDate"));
        citizen.getCase(caseIndex).getDiary().getNotes().stream().filter(note -> note.getUuid().equals(uuid)).findFirst().ifPresent(note -> note.addNoteVersion(version));
        Map<String, String> content = new HashMap<>();
        content.put("uuid", version.getUuid().toString());
        content.put("title", version.getTitel());
        content.put("obsDate", version.getDateOfObservation());
        content.put("noteDate", version.getDate().toString());
        content.put("content", version.getNote());
        content.put("creator", version.getCreator().getFirstName() + " " + version.getCreator().getLastName());
        //Husk at gemme i persistens herefter.
        return content;

    }
}
