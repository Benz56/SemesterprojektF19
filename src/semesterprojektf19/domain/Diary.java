package semesterprojektf19.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Diary {
    private Case caseFile;
    private List<DiaryNote> list;
    
    public Diary() {
        list = new ArrayList<>();
    }
    
    public void createNote(String note){
        list.add(new DiaryNote(note)); 
        //TODO implementere worker firstname, lastname eller andet.
    }
    
    public void editNote(int index,String note){
        DiaryNote diaryNote = list.get(index);
        diaryNote.setNote(note);
        Date tempDate = diaryNote.getDate();
        diaryNote.setDate(tempDate = new Date());
    }
    
    
    // TODO implementer at gemme i fil
    // TODO implementer i UI.
    
    
    public static void main(String[] args) throws InterruptedException {
        
        Diary d1 = new Diary();
        
        d1.createNote("hej med dig");
        d1.createNote("en anden besked");
        System.out.println(d1.list.toString());
        
        Thread.sleep(1000);
        d1.editNote(0,"ikke længere hej med dig");
        System.out.println(d1.list.toString());   
    }
    
    
    
    
    
}
