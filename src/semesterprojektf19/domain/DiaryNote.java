package semesterprojektf19.domain;

import java.util.Date;

public class DiaryNote {

    private String note;
    private int workerID; // Kan fjernes hvis det ikke passer med logning af systemet.
    private Date date;

    public DiaryNote(String note, int workerID) {
        this.note = note;
        this.workerID = workerID;
        Date date = new Date();
    }
    
        public void setNote(String note) {
        this.note = note;
    }

    public int getWorkerID() {
        return workerID;
    }

    public void setWorkerID(int workerID) {
        this.workerID = workerID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
}
