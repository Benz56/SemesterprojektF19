package semesterprojektf19.domain;

import java.util.Date;
import java.util.List;

public class DiaryNote {

    private String note;
    private Date date;
    private Person creator;
    private List<DiaryNote> diaryNotes;

    public DiaryNote(Person creator, String note) {
        this.note = note;
        this.date = new Date();
        this.creator = creator;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    public Person getCreator() {
        return creator;
    }
    
    public void setCreator(Person creator) {
        this.creator = creator;
    }
    
    public void addDiaryNote(DiaryNote diaryNote) {
        diaryNotes.add(diaryNote);
    }

    @Override
    public String toString() {
        return this.note + "\n" + " Note last edited on: " + this.date + " "
                + "by: " + creator.getFirstName() + " " + creator.getLastName() + "\n";
        //TODO log fornavn og efternavn
    }

}
