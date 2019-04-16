package semesterprojektf19.presentation;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainUIController implements Initializable {

    private final List<String> userDetails;
    //En instans af et modul i domænelaget mangler her. (Ligesom i Login)
    @FXML
    private JFXButton homeBtn, createCaseBtn, casesBtn, adminBtn,createCitizenBtn,casesCreateBtn;;
    @FXML
    private AnchorPane homePane, createNotePane, casesPane, adminPane,createCasePane;
    private final Map<JFXButton, AnchorPane> btnPaneMap = new HashMap<>();

    @FXML
    private JFXListView<String> clientList;

    private JFXButton selectedBtn;
    @FXML
    private Label homeHelloLabel, homePlaceLabel, homeCitizenCountLabel, homeTargetAreasLabel;
    @FXML
    private JFXTextField caseCitizenNameLabel, caseCPRLabel, caseAddressLabel;
    @FXML
    private JFXListView<?> caseLatestNotesListView;
 
 

    public MainUIController(List<String> userDetails) {
        this.userDetails = userDetails;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        homeHelloLabel.setText(homeHelloLabel.getText() + userDetails.get(0));
        selectedBtn = homeBtn;
        btnPaneMap.put(homeBtn, homePane);
        btnPaneMap.put(createCaseBtn, createNotePane);
        btnPaneMap.put(casesBtn, casesPane);
        btnPaneMap.put(adminBtn, adminPane);
        btnPaneMap.put(casesCreateBtn, createCasePane);
        homeBtn.setOnAction(event -> changePane(homeBtn));
        casesCreateBtn.setOnAction(event -> changePane(casesCreateBtn));
        casesBtn.setOnAction(event -> changePane(casesBtn));
        adminBtn.setOnAction(event -> changePane(adminBtn));
        createCaseBtn.setOnAction(event -> changePane(createCaseBtn));
        createCitizenBtn.setOnAction(event -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("RegisterCitizenUIDocument.fxml"));
            Stage stage = new Stage();
            try {
                stage.setScene(new Scene(loader.load()));
            } catch (IOException ex) {
                Logger.getLogger(MainUIController.class.getName()).log(Level.SEVERE, null, ex);
            }
            stage.show();
        });

        clientList.getItems().add("Jan Jansen");
        clientList.getItems().add("Fisayo Et Eller Andet");
        clientList.getItems().add("Lone Borgersen");
        
        homeCitizenCountLabel.setText(homeCitizenCountLabel.getText() + clientList.getItems().size());
    }

    private void changePane(JFXButton clickedBtn) {
        if (!selectedBtn.equals(clickedBtn)) {
            String temp = selectedBtn.getStyle();
            selectedBtn.setStyle(clickedBtn.getStyle());
            clickedBtn.setStyle(temp);
            selectedBtn = clickedBtn;
            btnPaneMap.values().forEach(pane -> pane.setVisible(false));
            btnPaneMap.get(selectedBtn).setVisible(true);
        }
    }
}
