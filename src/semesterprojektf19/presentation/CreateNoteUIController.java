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
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import semesterprojektf19.domain.DiaryNoteFacade;
import semesterprojektf19.domain.DiaryNoteFacadeImpl;

/**
 * FXML Controller class
 *
 * @author Jacob
 */
public class CreateNoteUIController implements Initializable {

   
    private final DiaryNoteFacade diaryNoteFacade;
    
    private String index;
    private String citizenInfo;
    
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

    public CreateNoteUIController(String index, String citizenInfo) {

        this.index = index;
        this.citizenInfo = citizenInfo;
        this.diaryNoteFacade = new DiaryNoteFacadeImpl();
        
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
    }

    @FXML
    private void onCreateNote(ActionEvent event) { // TODO implementer emne.
        Map<String, String> noteDetails = new HashMap<>();
        if(!titleTextField.getText().isEmpty()  
                && !noteTextArea.getText().isEmpty()
                && datePicker.getValue() != null) {
            noteDetails.put("index", index);
            noteDetails.put("citizenInfo", citizenInfo);    
            noteDetails.put("titel", titleTextField.getText());
            noteDetails.put("note", noteTextArea.getText());
            noteDetails.put("dateOfObservation", datePicker.getValue().toString());
            diaryNoteFacade.createNote(noteDetails);
            
            // For closing the stage
            ((Stage) noteTextArea.getScene().getWindow()).close();
        }
    }
    
    
    @FXML
    private void onCancelNote(ActionEvent event) {
        // For closing the stage
        ((Stage) noteTextArea.getScene().getWindow()).close();
    }
    
    
   

    
    

}
