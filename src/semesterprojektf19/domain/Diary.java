package semesterprojektf19.domain;

import java.util.ArrayList;
import java.util.List;

public class Diary {

    private List<DiaryNote> list;

    public Diary() {
        list = new ArrayList<>();
    }
    
    public static void main(String[] args) {
        
        Diary d1 = new Diary();
        
        DiaryNote n1 = new DiaryNote("En note her", 1);
        DiaryNote n2 = new DiaryNote("En anden note", 2);
        
        d1.list.add(n1);
        d1.list.add(n2);
        
        System.out.println(d1.list.toString());
        
        
    }
    
    
    
    
    
}
