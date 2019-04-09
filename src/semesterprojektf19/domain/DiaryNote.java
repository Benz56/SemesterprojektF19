package semesterprojektf19.domain;

import java.util.Date;

public class DiaryNote {

    private String note;
    private Date date;

    public DiaryNote(String note) {
        this.note = note;
        this.date = new Date();
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

    @Override
    public String toString() {
        return this.note + "\n" + " Note last edited on: " + this.date + " by: " + "\n";
        //TODO log fornavn og efternavn
    }

}
