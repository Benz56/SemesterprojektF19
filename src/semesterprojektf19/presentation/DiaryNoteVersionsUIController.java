package semesterprojektf19.presentation;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

/**
 * FXML Controller Class
 * 
 * @author Gruppe 22 på SE/ST E19, MMMI, Syddansk Universitet
 */
public class DiaryNoteVersionsUIController implements Initializable {

    @FXML
    private JFXButton closeBtn;
    @FXML
    private ListView<DiaryItem.NoteVersion> diaryNotesLV;

    private final DiaryItem diaryItem;

    public DiaryNoteVersionsUIController(DiaryItem diaryItem) {
        this.diaryItem = diaryItem;
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        closeBtn.setOnAction(event -> ((Stage) closeBtn.getScene().getWindow()).close());
        diaryNotesLV.setCellFactory(new DiaryNoteVersionListViewFactory());
        diaryNotesLV.getItems().setAll(diaryItem.getDiaryVersions());
    }
}
