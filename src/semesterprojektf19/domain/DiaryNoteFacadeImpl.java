package semesterprojektf19.domain;

import java.util.HashMap;
import semesterprojektf19.acquaintance.UserContainer;
import java.util.Map;
import semesterprojektf19.persistence.PersistenceFacade;
import semesterprojektf19.persistence.PersistenceFacadeImpl;

public class DiaryNoteFacadeImpl implements DiaryNoteFacade {
    
    PersistenceFacade persistence = new PersistenceFacadeImpl();

    @Override
    public Map<String, String> createNote(Map<String, String> noteDetails) {
        Citizen citizen = CitizenManager.INSTANCE.getCitizen(noteDetails.get("citizenInfo"));
        int index = Integer.parseInt(noteDetails.get("index"));
        Case casefile = citizen.getCase(index);
        casefile.getDiary().createNote((Worker) UserContainer.getUser(), noteDetails.get("note"), noteDetails.get("title"), noteDetails.get("dateOfObservation"));
        DiaryNote diaryNote = casefile.getDiary().getNotes().get(casefile.getDiary().getNotes().size() - 1);
        Map<String, String> content = new HashMap<>();
        content.put("uuid", diaryNote.getUuid().toString());
        content.put("title", diaryNote.getTitle());
        content.put("dateofobs", diaryNote.getDateOfObservation());
        content.put("dateofedit", diaryNote.getDate().toString());
        content.put("content", diaryNote.getNote());
        content.put("creator", diaryNote.getCreator().getFirstName() + " " + diaryNote.getCreator().getLastName());
        persistence.createDiaryNote(diaryNote.getUuid(), casefile.getDiary().getUuid(), 
                UserContainer.getUser().getUuid(), diaryNote.getDateOfObservation(),
                diaryNote.getDate().toString(), diaryNote.getTitle(), diaryNote.getNote());
        return content;
        //For debugging
        //System.out.println("Note created for: "+ citizen.getFirstName() + "on case index: " +index);
        //System.out.println(citizen.getCase(index).getDiary().getNotes());
    }

}
