package semesterprojektf19.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DiaryNote {

    private String titel, note;
    private Date date, dateOfObservation;
    private Person creator;
    private List<Topic> topics; // til at tilføje emne til note.
    private List<DiaryNote> noteVersions; // til at holde de forskellige versioner af noten. 

    // TODO Implementer date picker i javaFX. skal holde dato for observation som ikke nødvendigvis er den samme som oprettelses dato 
    // TODO implementere 
    
    public DiaryNote(Person creator, String note) {
        this.note = note;
        this.date = new Date();
        this.creator = creator;
        this.topics = new ArrayList<>();
        this.noteVersions = new ArrayList<>();
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

    public void addNoteVersion(DiaryNote diaryNote) {
        noteVersions.add(diaryNote);
    }

    public DiaryNote getNoteVersion(int index) {
        return noteVersions.get(index);
    }
    
    public void addTopic(Topic topic){
        topics.add(topic);
    }
    
    public Topic getTopic(int index){
        return topics.get(index);
    }
    
    public List<Topic> getTopicList(){
        return topics;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public void setDateOfObservation(Date dateOfObservation) {
        this.dateOfObservation = dateOfObservation;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

    public void setNoteVersions(List<DiaryNote> noteVersions) {
        this.noteVersions = noteVersions;
    }
    
    
    

    @Override
    public String toString() {
        return this.titel + "\n\n" + "Observations dato: " + this.dateOfObservation +  
                "\n\n" + this.note + "\n" + " Sidst redigeret: " + this.date +
                " af: " + creator.getFirstName() + " " + creator.getLastName() + "\n";
    }

}
