package semesterprojektf19.presentation;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
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
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MainUIController implements Initializable {

    private final Map<String, String> userDetails;
    //En instans af et modul i domænelaget mangler her. (Ligesom i Login)
    @FXML
    private JFXButton homeBtn, createCaseBtn, casesBtn, adminBtn, createCitizenBtn, casesCreateBtn, createUserBtn;
    @FXML
    private AnchorPane parentAnchorPane, homePane, createNotePane, casesPane, adminPane, createCasePane;
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

    public MainUIController(Map<String, String> userDetails) {
        this.userDetails = userDetails;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        homeHelloLabel.setText(homeHelloLabel.getText() + userDetails.get("firstname") + " " + userDetails.get("lastname"));
        selectedBtn = homeBtn;
        btnPaneMap.put(homeBtn, homePane);
        btnPaneMap.put(createCaseBtn, createNotePane);
        btnPaneMap.put(casesBtn, casesPane);
        if (userDetails.get("role").equals("admin")) {
            btnPaneMap.put(adminBtn, adminPane);
        } else {
            ((HBox) adminBtn.getParent()).getChildren().remove(adminBtn);
        }
        btnPaneMap.put(casesCreateBtn, createCasePane);
        homeBtn.setOnAction(event -> changePane(homeBtn));
        casesCreateBtn.setOnAction(event -> changePane(casesCreateBtn));
        casesBtn.setOnAction(event -> changePane(casesBtn));
        adminBtn.setOnAction(event -> changePane(adminBtn));
        createCaseBtn.setOnAction(event -> changePane(createCaseBtn));
        createCitizenBtn.setOnAction(event -> {
            Stage stage = new Stage();
            stage.setTitle("Opret Borger");
            stage.setResizable(false);
            stage.focusedProperty().addListener((observable, oldFocus, newFocus) -> {
                if (!newFocus) {
                    stage.close();
                }
            });
            try {
                stage.setScene(new Scene(new FXMLLoader(getClass().getResource("RegisterCitizenUIDocument.fxml")).load()));
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(MainUIController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        createUserBtn.setOnAction(event -> {
            Stage stage = new Stage();
            stage.setTitle("Opret Bruger");
            stage.setResizable(false);
            stage.focusedProperty().addListener((observable, oldFocus, newFocus) -> {
                if (!newFocus) {
                    stage.close();
                }
            });
            try {
                stage.setScene(new Scene(new FXMLLoader(getClass().getResource("RegisterEmployeeUIDocument.fxml")).load()));
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(MainUIController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        if (!userDetails.get("role").equals("admin")) {
            clientList.getItems().addAll(userDetails.get("citizens").split("\n"));
        }
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
