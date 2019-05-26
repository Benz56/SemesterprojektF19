/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojektf19.presentation;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import semesterprojektf19.acquaintance.Column;

/**
 *
 * @author Benjamin Staugaard | Benz56
 */
public class DiaryItem {

    private final List<NoteVersion> diaryVersions = new ArrayList<>();

    public DiaryItem(List<Map<String, String>> note) {
        note.forEach(entry -> {
            diaryVersions.add(new NoteVersion(entry, true));
        });
    }

    public List<NoteVersion> getDiaryVersions() {
        return diaryVersions;
    }

    public void addNewVersion(Map<String, String> content) {
        diaryVersions.add(0, new NoteVersion(content, false));
    }

    public class NoteVersion {

        private final UUID uuid;
        private final String title, dateOfObs, dateOfEdit, content, creator;

        public NoteVersion(Map<String, String> note, boolean format) {
            uuid = UUID.fromString(note.get(Column.UUID.getColumnName()));
            title = note.get(Column.TITLE.getColumnName());
            dateOfObs = note.get(Column.DATE_OF_OBS.getColumnName());
            dateOfEdit = !format ? note.get(Column.DATE_OF_EDIT.getColumnName()) : new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(Long.parseLong(note.get(Column.DATE_OF_EDIT.getColumnName()))).split("\\.")[0];
            content = note.get(Column.CONTENT.getColumnName());
            creator = note.get(Column.CREATOR.getColumnName());
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
