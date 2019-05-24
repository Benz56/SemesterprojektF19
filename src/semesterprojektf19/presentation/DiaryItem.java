package semesterprojektf19.presentation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import semesterprojektf19.acquaintance.Column;

/**
 *
 * @author Gruppe 22 på SE/ST E19, MMMI, Syddansk Universitet
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
            uuid = UUID.fromString(note.get(Column.UUID.getColumnName()));
            title = note.get(Column.TITLE.getColumnName());
            dateOfObs = note.get(Column.DATE_OF_OBS.getColumnName());
            dateOfEdit = note.get(Column.DATE_OF_EDIT.getColumnName());
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
