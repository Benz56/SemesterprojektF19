package semesterprojektf19.presentation;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;
import semesterprojektf19.acquaintance.Column;
import semesterprojektf19.domain.DiaryNoteFacade;
import semesterprojektf19.domain.DiaryNoteFacadeImpl;

/**
 * FXML Controller Class
 *
 * @author Gruppe 22 på SE/ST E19, MMMI, Syddansk Universitet
 */
public class CreateNoteUIController implements Initializable {

    private final ObservableList<DiaryItem> diarynotesObservable;
    private final String index;
    private final String citizenInfo;
    private final DiaryNoteFacade diaryNoteFacade;

    @FXML
    private JFXTextField titleTextField;
    @FXML
    private JFXComboBox<?> topicCB;
    @FXML
    private JFXDatePicker datePicker;
    @FXML
    private JFXButton createNoteBtn, cancelBtn;
    @FXML
    private HTMLEditor noteEditor;

    public CreateNoteUIController(ObservableList<DiaryItem> diarynotesObservable, String index, String citizenInfo) {
        this.diarynotesObservable = diarynotesObservable;
        this.index = index;
        this.citizenInfo = citizenInfo;
        this.diaryNoteFacade = new DiaryNoteFacadeImpl();
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void onCreateNote(ActionEvent event) { // TODO implementer emne.
        Map<String, String> noteDetails = new HashMap<>();
        if (!titleTextField.getText().isEmpty()
                && !noteEditor.getHtmlText().isEmpty()
                && datePicker.getValue() != null) {
            noteDetails.put("index", index);
            noteDetails.put("citizenInfo", citizenInfo);
            noteDetails.put(Column.TITLE.getColumnName(), titleTextField.getText());
            noteDetails.put(Column.CONTENT.getColumnName(), noteEditor.getHtmlText());
            String date = datePicker.getValue().toString();
            noteDetails.put(Column.DATE_OF_OBS.getColumnName(), date.substring(8, 10) + "-" + date.substring(5, 7) + "-" + date.substring(0, 4));
            Map<String, String> savedNote = diaryNoteFacade.createNote(noteDetails);
            System.out.println(noteDetails.get(Column.DATE_OF_OBS.getColumnName()));
            diarynotesObservable.add(0, new DiaryItem(new ArrayList<>(Arrays.asList(savedNote))));

            // For closing the stage
            ((Stage) noteEditor.getScene().getWindow()).close();
        }
    }

    @FXML
    private void onCancelNote(ActionEvent event) {
        // For closing the stage
        ((Stage) noteEditor.getScene().getWindow()).close();
    }
}
