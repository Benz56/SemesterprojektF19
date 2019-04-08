package semesterprojektf19.domain;

import java.util.Date;

public class DiaryNote {

    private String note;
    private int workerID; // Kan fjernes hvis det ikke passer med logning af systemet.
    private Date date;
    
    public DiaryNote(String note, int workerID) {
        this.note = note;
        this.workerID = workerID;
        this.date = new Date();

    }    
    
    public void setNote(String note, int workerID) {
        this.note = note;
        this.date = new Date();
        this.workerID = workerID;
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


    @Override
    public String toString() {
        return this.note + "\n" + " Note last edited on: " + this.date + " by: " + this.workerID + "\n";
    }

}
