/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojektf19.presentation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public class NoteVersion {

        private final String title, obsDate, noteDate, content;

        public NoteVersion(Map<String, String> note) {
            title = note.get("title");
            obsDate = note.get("obsDate");
            noteDate = note.get("noteDate");
            content = note.get("content");

        }

        public String getTitle() {
            return title;
        }

        public String getObsDate() {
            return obsDate;
        }

        public String getNoteDate() {
            return noteDate;
        }

        public String getContent() {
            return content;
        }

    }
}
