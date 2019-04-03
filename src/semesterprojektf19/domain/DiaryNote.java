package semesterprojektf19.domain;

import java.util.Date;

public class DiaryNote {

    private String note;
    private int workerID; // Kan fjernes hvis det ikke passer med logning af systemet.
    private Date d;

    public DiaryNote(String note, int workerID) {
        this.note = note;
        this.workerID = workerID;
        Date d = new Date();
    }
    
    
    
    
}
