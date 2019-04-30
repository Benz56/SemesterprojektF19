package semesterprojektf19.presentation;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.awt.MouseInfo;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import semesterprojektf19.domain.DomainFacade;
import semesterprojektf19.domain.DomainFacadeImpl;

public class MainUIController implements Initializable {

    private final DomainFacade domainFacade = new DomainFacadeImpl();
    private final Map<String, String> userDetails;
    private final Map<JFXButton, AnchorPane> btnPaneMap = new HashMap<>();
    private JFXButton selectedBtn;

    //Global nodes:
    @FXML
    private JFXButton homeBtn, casesBtn, adminBtn, casesCreateBtn, diaryBtn;
    @FXML
    private AnchorPane homePane, casesPane, adminPane, createCasePane, diaryPane;

    @FXML
    private JFXListView<String> clientList;

    //Home nodes:
    @FXML
    private Label homeHelloLabel, homePlaceLabel, homeCitizenCountLabel, homeTargetAreasLabel;

    // Case nodes:
    @FXML
    private JFXTextField caseCitizenNameTextField, caseCPRTextField, caseAddressTextField;
    @FXML
    private JFXComboBox<String> caseCasesCB;
    @FXML
    private JFXListView<?> caseLatestNotesListView;

    //Create case nodes:
    @FXML
    private JFXButton ccCreateCitizenBtn;
    @FXML
    private JFXListView<String> ccCitizenListView;
    @FXML
    private JFXTextField ccSearchCitizenTextField, ccGuardianTextField, ccRepresentationTextField, ccExecutingMuniTextField, ccPayingMuniTextField, ccShortInfoTextField;
    @FXML
    private JFXTextArea ccSpecialCircumstancesTextArea, ccProcessAgreementsTextArea;
    @FXML
    private JFXCheckBox ccRightToRepCB, ccInformedECardCB, ccConsentRelevantCB, ccConsentGivenCB;

    //Diary nodes.
    @FXML
    private JFXComboBox<?> diaryCaseCb;
    @FXML
    private JFXListView<?> diarynotesListview;
    @FXML
    private JFXButton diaryCreateNoteBtn;
    
    //Admin nodes:
    @FXML
    private JFXButton adminCreateUserBtn, adminEditUserBtn, adminDeleteUserBtn;
    

    public MainUIController(Map<String, String> userDetails) {
        this.userDetails = userDetails;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        homeHelloLabel.setText(homeHelloLabel.getText() + userDetails.get("firstname") + " " + userDetails.get("lastname"));
        selectedBtn = homeBtn;
        btnPaneMap.put(homeBtn, homePane);
        btnPaneMap.put(casesBtn, casesPane);
        btnPaneMap.put(diaryBtn, diaryPane);
        if (userDetails.get("role").equals("admin")) {
            btnPaneMap.put(adminBtn, adminPane);
        } else {
            ((HBox) adminBtn.getParent()).getChildren().remove(adminBtn);
        }
        if(userDetails.get("role").equals("caseworker") || userDetails.get("role").equals("admin")){
            btnPaneMap.put(casesCreateBtn, createCasePane);
        } else {
            ((HBox) casesCreateBtn.getParent()).getChildren().remove(casesCreateBtn);
        }
        
        btnPaneMap.keySet().forEach(btn -> btn.setOnAction(event -> changePane((JFXButton) event.getSource())));
        ccCreateCitizenBtn.setOnAction(event -> {
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
                stage.setOnHiding(listener -> {
                    domainFacade.refresh();
                    refresh();
                });
            } catch (IOException ex) {
                Logger.getLogger(MainUIController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        adminCreateUserBtn.setOnAction(event -> {
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
        
        diaryCreateNoteBtn.setOnAction(event -> {
            Stage stage = new Stage();
            stage.setTitle("Opret Notat");
            stage.setResizable(false);
            stage.focusedProperty().addListener((observable, oldFocus, newFocus) -> {
                if (!newFocus) {
                    stage.close();
                }
            });
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CreateNoteUIDocument.fxml"));
                fxmlLoader.setControllerFactory(controllerFactory -> new CreateNoteUIController(this));
                stage.setScene(new Scene(fxmlLoader.load()));
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(MainUIController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        ccSearchCitizenTextField.textProperty().addListener(listener -> refresh());

        clientList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                Map<String, String> citizenDetails = domainFacade.getCitizenDetails(newValue);
                caseCitizenNameTextField.setText(citizenDetails.get("name"));
                caseCPRTextField.setText(citizenDetails.get("cpr"));
                caseAddressTextField.setText(citizenDetails.get("address"));
                caseCasesCB.getItems().clear();
                caseCasesCB.getItems().setAll(citizenDetails.get("cases").split("\n"));
            }
        });

        refresh();
    }

    @FXML
    private void onCreateCase(ActionEvent event) {
        Tooltip tooltip = new Tooltip();
        if (ccCitizenListView.getSelectionModel().getSelectedItem() != null
                && !ccExecutingMuniTextField.getText().isEmpty()
                && !ccRepresentationTextField.getText().isEmpty()
                && !ccPayingMuniTextField.getText().isEmpty()
                && !ccShortInfoTextField.getText().isEmpty()) {
            Map<String, String> caseDetails = new HashMap<>();
            caseDetails.put("citizen", ccCitizenListView.getSelectionModel().getSelectedItem());
            caseDetails.put("guardian", ccGuardianTextField.getText());
            caseDetails.put("executingMunicipality", ccExecutingMuniTextField.getText());
            caseDetails.put("representation", ccRepresentationTextField.getText());
            caseDetails.put("payingMunicipality", ccPayingMuniTextField.getText());
            caseDetails.put("consentRelevant", String.valueOf(ccConsentRelevantCB.isSelected()));
            caseDetails.put("consentGiven", String.valueOf(ccConsentGivenCB.isSelected()));
            caseDetails.put("rightToRepresentation", String.valueOf(ccRightToRepCB.isSelected()));
            caseDetails.put("informedOnElectronicInfo", String.valueOf(ccInformedECardCB.isSelected()));
            caseDetails.put("agreementsAboutFurtherProcess", ccProcessAgreementsTextArea.getText());
            caseDetails.put("specialCircumstances", ccSpecialCircumstancesTextArea.getText());
            caseDetails.put("shortInfo", ccShortInfoTextField.getText());
            domainFacade.createCase(caseDetails);
            refresh();
            tooltip.setText("Sag oprettet!");
        } else {
            tooltip.setText("Mangler informationer!");
        }
        tooltip.show(((JFXButton) event.getSource()).getScene().getWindow(), MouseInfo.getPointerInfo().getLocation().getX(), MouseInfo.getPointerInfo().getLocation().getY());
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.schedule(() -> Platform.runLater(() -> tooltip.hide()), 2, TimeUnit.SECONDS);
        executor.shutdown(); 
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

    private void refresh() {
        if (!userDetails.get("role").equals("admin")) {
            String selectedItem = clientList.getSelectionModel().getSelectedItem();
            clientList.getItems().setAll(domainFacade.getUserCitizens());
            if (clientList.getItems().contains(selectedItem)) {
                clientList.getSelectionModel().select(selectedItem);
            }
        }
        homeCitizenCountLabel.setText(homeCitizenCountLabel.getText() + domainFacade.getUserCitizens().size());
        ccCitizenListView.getItems().setAll(domainFacade.matchCitizens(ccSearchCitizenTextField.getText()));
    }

    public DomainFacade getDomainFacade() {
        return domainFacade;
    }
}
