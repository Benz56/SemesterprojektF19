/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojektf19.presentation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 *
 * @author Benjamin Staugaard | Benz56
 */
public class DiaryItem {

    private final List<NoteVersion> diaryVersions = new ArrayList<>();

    public DiaryItem(List<Map<String, String>> note) {
        note.forEach(entry -> {
            diaryVersions.add(new NoteVersion(entry));
        });
    }

    public List<NoteVersion> getDiaryVersions() {
        return diaryVersions;
    }

    public void addNewVersion(Map<String, String> content) {
        diaryVersions.add(0, new NoteVersion(content));
    }

    public class NoteVersion {

        private final UUID uuid;
        private final String title, dateOfObs, dateOfEdit, content, creator;

        public NoteVersion(Map<String, String> note) {
            uuid = UUID.fromString(note.get("uuid"));
            title = note.get("title");
            dateOfObs = note.get("dateofobs");
            dateOfEdit = note.get("dateofedit");
            content = note.get("content");
            creator = note.get("creator");
        }

        public UUID getUuid() {
            return uuid;
        }

        public String getTitle() {
            return title;
        }

        public String getDateOfObs() {
            return dateOfObs;
        }

        public String getDateOfEdit() {
            return dateOfEdit;
        }

        public String getContent() {
            return content;
        }

        public String getCreator() {
            return creator;
        }

    }
}
