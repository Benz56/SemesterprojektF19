package semesterprojektf19.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Diary implements Serializable {

    private Case caseFile;
    private List<DiaryNote> list;

    public Diary() {
        list = new ArrayList<>();
    }

    public void createNote(String note) {
        list.add(new DiaryNote(note));
        //TODO implementere worker firstname, lastname eller andet.
    }

    public void editNote(int index, String note) {
        DiaryNote diaryNote = list.get(index);
        diaryNote.setNote(note);
        Date tempDate = diaryNote.getDate();
        diaryNote.setDate(tempDate = new Date());
    }

    // TODO implementer at gemme i fil
    // TODO implementer i UI.
}
