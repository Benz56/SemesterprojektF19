package semesterprojektf19.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Diary implements Serializable {

    private Case caseFile;
    private List<DiaryNote> list;
    private Person editor;

    public Diary() {
        list = new ArrayList<>();
    }

    public void createNote(Person person, String note) {
        list.add(new DiaryNote(person, note));
        //TODO implementere worker firstname, lastname eller andet.
    }

    public void editNote(int index, Person person, String note) {
        DiaryNote diaryNote = list.get(index);
        diaryNote.setNote(note);
        Date tempDate = diaryNote.getDate();
        diaryNote.setDate(tempDate = new Date());
        Person tempPerson = diaryNote.getCreator();
        diaryNote.setCreator(tempPerson = editor);
    }

    // TODO implementer at gemme i fil
    // TODO implementer i UI.
}
