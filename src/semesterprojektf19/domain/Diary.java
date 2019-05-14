package semesterprojektf19.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Diary implements Serializable {

    private Case caseFile;
    private List<DiaryNote> list;
    private Person editor;
    private UUID uuid;
    private static final long serialVersionUID = 5184679964539683569L;

    public Diary(UUID caseID) {
        list = new ArrayList<>();
        this.uuid = UUID.randomUUID();
    }

    public UUID getUuid() {
        return uuid;
    }

    public void createNote(Person person, String note, String titel, String dateOfObservation) {
        DiaryNote diaryNote = new DiaryNote(UUID.randomUUID(), person, note, titel, dateOfObservation);
        diaryNote.addNoteVersion(diaryNote);
        list.add(diaryNote);
    }

    public void editNote(int index, Person person, String note) {
        DiaryNote diaryNote = list.get(index);
        diaryNote.setNote(note);
        diaryNote.setDate(new Date());
        diaryNote.setCreator(editor);
        diaryNote.addNoteVersion(diaryNote);
    }

    public void addTopic(int index, Topic topic) {
        DiaryNote diaryNote = list.get(index);
        diaryNote.addTopic(topic);
    }

    public List<DiaryNote> searchTopic(Topic topic) {
        List<DiaryNote> searchResult = new ArrayList<>();
        for (DiaryNote diaryNote : list) {
            if (diaryNote.getTopicList().contains(topic)) {
                searchResult.add(diaryNote);
            }
        }
        return searchResult;
    }

    public List<DiaryNote> getNotes() {
        return list;
    }
}
