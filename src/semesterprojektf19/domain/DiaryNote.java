package semesterprojektf19.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class DiaryNote implements Serializable {

    private static final long serialVersionUID = 2266792567973358800L;

    private final UUID uuid;
    private String title, note, dateOfObservation;
    private Date date;
    private Person creator;
    private List<Topic> topics;
    private List<DiaryNote> noteVersions;

    // TODO Implementer date picker i javaFX. skal holde dato for observation som ikke nødvendigvis er den samme som oprettelses dato 
    // TODO implementere 
    public DiaryNote(UUID uuid, Person creator, String note, String titel, String dateOfObservation) {
        this.uuid = UUID.randomUUID();
        this.creator = creator;
        this.note = note;
        this.title = title;
        this.dateOfObservation = dateOfObservation;
        this.date = new Date();
        this.topics = new ArrayList<>();
        this.noteVersions = new ArrayList<>();
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getNote() {
        return note;
    }

    public String getDateOfObservation() {
        return dateOfObservation;
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

    public void addTopic(Topic topic) {
        topics.add(topic);
    }

    public Topic getTopic(int index) {
        return topics.get(index);
    }

    public List<Topic> getTopicList() {
        return topics;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setDateOfObservation(String dateOfObservation) {
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
        return "Titel: " + this.title + "\n"
                + "Observation foretaget: " + this.dateOfObservation + "\n\n"
                + this.note + "\n\n\n"
                + " Sidst redigeret: " + this.date
                + " af: " + creator.getFirstName() + " " + creator.getLastName() + "\n\n\n\n";
    }

    public List<DiaryNote> getVersions() {
        return noteVersions;
    }
}
