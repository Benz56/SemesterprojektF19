package semesterprojektf19.domain;

import java.util.HashMap;
import semesterprojektf19.acquaintance.UserContainer;
import java.util.Map;
import semesterprojektf19.acquaintance.Column;
import semesterprojektf19.persistence.PersistenceFacade;
import semesterprojektf19.persistence.PersistenceFacadeImpl;

public class DiaryNoteFacadeImpl implements DiaryNoteFacade {

    PersistenceFacade persistence = new PersistenceFacadeImpl();

    @Override
    public Map<String, String> createNote(Map<String, String> noteDetails) {
        Citizen citizen = CitizenManager.INSTANCE.getCitizen(noteDetails.get("citizenInfo"));
        int index = Integer.parseInt(noteDetails.get("index"));
        Case casefile = citizen.getCase(index);
        casefile.getDiary().createNote((Worker) UserContainer.getUser(), noteDetails.get(Column.CONTENT.getColumnName()), noteDetails.get(Column.TITLE.getColumnName()), noteDetails.get(Column.DATE_OF_OBS.getColumnName()));
        DiaryNote diaryNote = casefile.getDiary().getNotes().get(casefile.getDiary().getNotes().size() - 1);
        Map<String, String> content = new HashMap<>();
        content.put(Column.UUID.getColumnName(), diaryNote.getUuid().toString());
        content.put(Column.TITLE.getColumnName(), diaryNote.getTitle());
        content.put(Column.DATE_OF_OBS.getColumnName(), diaryNote.getDateOfObservation());
        content.put(Column.DATE_OF_EDIT.getColumnName(), diaryNote.getDate().toString());
        content.put(Column.CONTENT.getColumnName(), diaryNote.getNote());
        content.put(Column.CREATOR.getColumnName(), diaryNote.getCreator().getFirstName() + " " + diaryNote.getCreator().getLastName());
        persistence.createDiaryNote(diaryNote.getUuid(), casefile.getDiary().getUuid(),
                UserContainer.getUser().getUuid(), diaryNote.getDateOfObservation(),
                diaryNote.getDate().toString(), diaryNote.getTitle(), diaryNote.getNote());
        return content;
    }
}
