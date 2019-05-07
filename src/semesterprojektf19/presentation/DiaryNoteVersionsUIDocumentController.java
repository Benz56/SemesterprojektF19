/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojektf19.presentation;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

/**
 * FXML Controller class
 *
 * @author Jacob
 */
public class DiaryNoteVersionsUIDocumentController implements Initializable {

    @FXML
    private JFXButton closeBtn;
    private final DiaryItem diaryItem;

    public DiaryNoteVersionsUIDocumentController(DiaryItem diaryItem) {
        this.diaryItem = diaryItem;
        
    }

    
    @FXML
    private ListView<DiaryItem.NoteVersion> diaryNotesLV;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        diaryNotesLV.setCellFactory(new DiaryNoteVersionListViewFactory());
        
        diaryNotesLV.getItems().setAll(diaryItem.getDiaryVersions());

    }    
    
}
