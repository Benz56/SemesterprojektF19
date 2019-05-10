/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojektf19.domain;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Glumby
 */
public class DiaryNoteFacadeImpl implements DiaryNoteFacade {

    @Override
    public Map<String, String> createNote(Map<String, String> noteDetails) {
        Citizen citizen = CitizenManager.INSTANCE.getCitizen(noteDetails.get("citizenInfo"));
        int index = Integer.parseInt(noteDetails.get("index"));
        Case casefile = citizen.getCase(index);
        casefile.getDiary().createNote((Worker) UserContainer.getUser(), noteDetails.get("note"), noteDetails.get("titel"), noteDetails.get("dateOfObservation"));
        citizen.saveToFile();
        DiaryNote diaryNote = casefile.getDiary().getNotes().get(casefile.getDiary().getNotes().size() - 1);
        Map<String, String> content = new HashMap<>();
        content.put("uuid", diaryNote.getUuid().toString());
        content.put("title", diaryNote.getTitel());
        content.put("obsDate", diaryNote.getDateOfObservation());
        content.put("noteDate", diaryNote.getDate().toString());
        content.put("content", diaryNote.getNote());
        content.put("creator", diaryNote.getCreator().getFirstName() + " " + diaryNote.getCreator().getLastName());
        return content;
        //For debugging
        //System.out.println("Note created for: "+ citizen.getFirstName() + "on case index: " +index);
        //System.out.println(citizen.getCase(index).getDiary().getNotes());
    }

}
