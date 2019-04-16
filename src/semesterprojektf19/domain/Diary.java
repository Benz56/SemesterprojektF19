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

    public Diary() {
        list = new ArrayList<>();
    }

    public void createNote(Person person, String note) {
        list.add(new DiaryNote(person, note));
    }

    public void editNote(int index, Person person, String note) {
        DiaryNote diaryNote = list.get(index);
        diaryNote.setNote(note);
        Date tempDate = diaryNote.getDate();
        diaryNote.setDate(tempDate = new Date());
        Person tempPerson = diaryNote.getCreator();
        diaryNote.setCreator(tempPerson = editor);
        diaryNote.addNoteVersion(diaryNote);
    }
    
    // Spaghetti kode, ved ikke om det vil virke i praksis. 
    public List<DiaryNote> searchTopic(Topic topic) {
        List<DiaryNote> searchResult = new ArrayList<>();
        for (DiaryNote diaryNote : list) {
            for (int i = 0; i < diaryNote.getTopicList().size(); i++) {
                if (diaryNote.getTopic(i) == topic) {
                    searchResult.add(diaryNote);
                }
            }
        }
        return searchResult;
    }

}
