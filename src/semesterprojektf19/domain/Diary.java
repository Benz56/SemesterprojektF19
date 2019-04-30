package semesterprojektf19.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Diary implements Serializable {

    private Case caseFile;
    private List<DiaryNote> list;
    private Person editor;
    private static final long serialVersionUID =  5184679964539683569L;
    

    public Diary() {
        list = new ArrayList<>();
    }

    public void createNote(Person person, String note, String titel, String dateOfObservation) {
        list.add(new DiaryNote(person, note, titel, dateOfObservation));
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
    
    public void addTopic(int index, Topic topic){
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
    
    public List<DiaryNote> getNotes(){
        return list;
    }
}
