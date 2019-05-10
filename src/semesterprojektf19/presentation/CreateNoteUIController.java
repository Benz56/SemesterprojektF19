/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import semesterprojektf19.domain.DiaryNoteFacade;
import semesterprojektf19.domain.DiaryNoteFacadeImpl;

/**
 * FXML Controller class
 *
 * @author Jacob
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
    private JFXButton createNoteBtn;
    @FXML
    private JFXButton cancelBtn;
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
            noteDetails.put("titel", titleTextField.getText());
            noteDetails.put("note", noteEditor.getHtmlText());
            noteDetails.put("dateOfObservation", datePicker.getValue().toString());
            Map<String, String> savedNote = diaryNoteFacade.createNote(noteDetails);
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
