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
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Jacob
 */
public class CreateNoteUIController implements Initializable {

    private final MainUIController mainController;

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

}
