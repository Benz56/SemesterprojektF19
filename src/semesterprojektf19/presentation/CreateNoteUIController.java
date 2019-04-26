/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojektf19.presentation;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import semesterprojektf19.domain.DiaryNoteFacade;
import semesterprojektf19.domain.DiaryNoteFacadeImpl;
import semesterprojektf19.domain.DomainFacade;
import semesterprojektf19.domain.DomainFacadeImpl;

/**
 * FXML Controller class
 *
 * @author Jacob
 */
public class CreateNoteUIController implements Initializable {

    private final MainUIController mainController;
    private final DiaryNoteFacade diaryNoteFacade = new DiaryNoteFacadeImpl();

    @FXML
    private JFXTextField titleTextField;
    @FXML
    private JFXTextArea noteTextArea;
    @FXML
    private JFXComboBox<?> topicCB;
    @FXML
    private JFXDatePicker datePicker;
    @FXML
    private JFXButton createNoteBtn;
    @FXML
    private JFXButton cancelBtn;

    public CreateNoteUIController(MainUIController mainUIController) {
        this.mainController = mainUIController;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    @FXML
    private void onCreateNote(ActionEvent event) { // TODO implementer emne.
        if(!titleTextField.getText().isEmpty()  
                && !noteTextArea.getText().isEmpty()
                && datePicker.getValue() != null) {
            Map<String,String> noteDetails = new HashMap<>();
            noteDetails.put("titel", titleTextField.getText());
            noteDetails.put("note", noteTextArea.getText());
            noteDetails.put("dateOfObservation", String.valueOf(datePicker.getValue()));
            diaryNoteFacade.createNote(noteDetails);
            
        }
    }
    
    

}
