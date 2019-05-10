package semesterprojektf19.presentation;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.awt.MouseInfo;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import semesterprojektf19.aquaintance.Column;
import semesterprojektf19.domain.DomainFacade;
import semesterprojektf19.domain.DomainFacadeImpl;
import semesterprojektf19.domain.RegistrationFacade;
import semesterprojektf19.domain.RegistrationFacadeImpl;

public class MainUIController implements Initializable {

    private final DomainFacade domainFacade = new DomainFacadeImpl();
    private final RegistrationFacade registrationFacade = new RegistrationFacadeImpl();
    private final Map<String, String> userDetails;
    private final Map<JFXButton, AnchorPane> btnPaneMap = new HashMap<>();
    private ObservableList<DiaryItem> diarynotesObservable;
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
    @FXML
    private JFXComboBox<String> ccExecutingMuniCB;

    //Diary nodes.
    @FXML
    private JFXComboBox<String> diaryCaseCb;
    @FXML
    private JFXListView<DiaryItem> diarynotesListview;
    @FXML
    private JFXButton diaryCreateNoteBtn;

    //Admin nodes:
    @FXML
    private JFXButton adminCreateUserBtn, adminEditUserBtn, adminDeleteUserBtn, adminCreateInstitutionBtn;

    public MainUIController(Map<String, String> userDetails) {
        this.userDetails = userDetails;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        homeHelloLabel.setText(homeHelloLabel.getText() + userDetails.get("firstname") + " " + userDetails.get("lastname"));
        if (userDetails.get(Column.ROLE.getColumnName()).equalsIgnoreCase("socialworker")) {
            homePlaceLabel.setText(homePlaceLabel.getText() + userDetails.get(Column.INSTITUTION.getColumnName()));
        }
        selectedBtn = homeBtn;
        btnPaneMap.put(homeBtn, homePane);
        btnPaneMap.put(casesBtn, casesPane);
        btnPaneMap.put(diaryBtn, diaryPane);
        if (userDetails.get(Column.ROLE.getColumnName()).equalsIgnoreCase("admin")) {
            btnPaneMap.put(adminBtn, adminPane);
        } else {
            ((HBox) adminBtn.getParent()).getChildren().remove(adminBtn);
        }
        if (userDetails.get(Column.ROLE.getColumnName()).equalsIgnoreCase("caseworker") || userDetails.get(Column.ROLE.getColumnName()).equalsIgnoreCase("admin")) {
            btnPaneMap.put(casesCreateBtn, createCasePane);
        } else {
            ((HBox) casesCreateBtn.getParent()).getChildren().remove(casesCreateBtn);
        }

        btnPaneMap.keySet().forEach(btn -> btn.setOnAction(event -> changePane((JFXButton) event.getSource())));
        ccCreateCitizenBtn.setOnAction(event -> {
            SimpleStageBuilder.create("Opret Borger", "RegisterCitizenUIDocument.fxml").setResizable(false).setCloseOnUnfocused(true).setOnHiding(() -> {
                domainFacade.refresh();
                refresh();
            }).open();
        });

        adminCreateUserBtn.setOnAction(event -> SimpleStageBuilder.create("Opret Bruger", "RegisterEmployeeUIDocument.fxml").setResizable(false).setCloseOnUnfocused(true).open());

        adminCreateInstitutionBtn.setOnAction(event -> SimpleStageBuilder.create("Opret Bosted", "RegisterInstitutionUIDocument.fxml").setResizable(false).setCloseOnUnfocused(true).open());

        diaryCreateNoteBtn.setOnAction(event -> SimpleStageBuilder.create("Opret Notat", "CreateNoteUIDocument.fxml").setResizable(false)
                .setCloseOnUnfocused(true).setControllerFactory(new CreateNoteUIController(diarynotesObservable, String.valueOf(diaryCaseCb.getSelectionModel().getSelectedIndex()), clientList.getSelectionModel().getSelectedItem())).open());

        ccSearchCitizenTextField.textProperty().addListener(listener -> refresh());
        setClientListener();
        ccExecutingMuniCB.getItems().addAll(registrationFacade.getInstitutionNames());

        diarynotesListview.setCellFactory(new DiaryListViewCellFactory(this));
        diaryCaseCb.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            diarynotesListview.getItems().clear();
            diarynotesListview.setCellFactory(new DiaryListViewCellFactory(this));
            List<List<Map<String, String>>> diaryNoteDetails = domainFacade.getDiaryDetails(clientList.getSelectionModel().getSelectedItem(), diaryCaseCb.getSelectionModel().getSelectedIndex());
            diaryNoteDetails.forEach(note -> diarynotesListview.getItems().add(new DiaryItem(note)));
        });
        diarynotesObservable = FXCollections.observableList(diarynotesListview.getItems());
        diarynotesObservable.addListener((ListChangeListener.Change<? extends DiaryItem> event) -> diarynotesListview.setCellFactory(new DiaryListViewCellFactory(this)));

        refresh();
    }

    private void setClientListener() {
        clientList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                Map<String, String> citizenDetails = domainFacade.getCitizenDetails(newValue);
                caseCitizenNameTextField.setText(citizenDetails.get("name"));
                caseCPRTextField.setText(citizenDetails.get("cpr"));
                caseAddressTextField.setText(citizenDetails.get("address"));
                caseCasesCB.getItems().clear();
                caseCasesCB.getItems().setAll(citizenDetails.get("cases").split("\n"));
                diaryCaseCb.getItems().clear();
                diaryCaseCb.getItems().setAll(citizenDetails.get("cases").split("\n"));
            }
        });
    }

    @FXML
    private void onCreateCase(ActionEvent event) {
        Tooltip tooltip = new Tooltip();
        if (ccCitizenListView.getSelectionModel().getSelectedItem() != null
                && ccExecutingMuniCB.getSelectionModel().getSelectedItem() != null
                && !ccRepresentationTextField.getText().isEmpty()
                && !ccPayingMuniTextField.getText().isEmpty()
                && !ccShortInfoTextField.getText().isEmpty()) {
            Map<String, String> caseDetails = new HashMap<>();
            caseDetails.put(Column.CITIZEN.getColumnName(), ccCitizenListView.getSelectionModel().getSelectedItem());
            caseDetails.put(Column.GUARDIAN.getColumnName(), ccGuardianTextField.getText());
            caseDetails.put(Column.EXECUTINGMUNICIPALITY.getColumnName(), ccExecutingMuniCB.getSelectionModel().getSelectedItem());
            caseDetails.put(Column.REPRESENTATION.getColumnName(), ccRepresentationTextField.getText());
            caseDetails.put(Column.PAYINGMUNICIPALITY.getColumnName(), ccPayingMuniTextField.getText());
            caseDetails.put(Column.CONSENTRELEVANT.getColumnName(), String.valueOf(ccConsentRelevantCB.isSelected()));
            caseDetails.put(Column.CONSENTGIVEN.getColumnName(), String.valueOf(ccConsentGivenCB.isSelected()));
            caseDetails.put(Column.RIGHTTOREPRESENTATION.getColumnName(), String.valueOf(ccRightToRepCB.isSelected()));
            caseDetails.put(Column.INFORMEDONELECTRONICINFO.getColumnName(), String.valueOf(ccInformedECardCB.isSelected()));
            caseDetails.put(Column.AGREEMENTSONFURTHERPROCESS.getColumnName(), ccProcessAgreementsTextArea.getText());
            caseDetails.put(Column.SPECIALCURCUMSTANCES.getColumnName(), ccSpecialCircumstancesTextArea.getText());
            caseDetails.put(Column.SHORTINFO.getColumnName(), ccShortInfoTextField.getText());
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
        if (!userDetails.get(Column.ROLE.getColumnName()).equals("admin")) {
            String selectedItem = clientList.getSelectionModel().getSelectedItem();
            clientList.getItems().setAll(domainFacade.getUserCitizens());
            if (clientList.getItems().contains(selectedItem)) {
                clientList.getSelectionModel().select(selectedItem);
            }
        }
        homeCitizenCountLabel.setText(homeCitizenCountLabel.getText() + domainFacade.getUserCitizens().size());
        ccCitizenListView.getItems().setAll(domainFacade.matchCitizens(ccSearchCitizenTextField.getText()));
    }

    @FXML
    private void onLogout(ActionEvent event) {
        SimpleStageBuilder.create("EGBoosted", "LoginUIDocument.fxml").closeOpenWindow(homeBtn).setResizable(false).open();
    }

    public DomainFacade getDomainFacade() {
        return domainFacade;
    }

    public JFXComboBox<String> getDiaryCaseCb() {
        return diaryCaseCb;
    }

    public JFXListView<String> getClientList() {
        return clientList;
    }
}
