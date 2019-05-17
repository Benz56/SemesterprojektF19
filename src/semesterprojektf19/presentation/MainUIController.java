package semesterprojektf19.presentation;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import semesterprojektf19.acquaintance.Column;
import semesterprojektf19.domain.DomainFacade;
import semesterprojektf19.domain.DomainFacadeImpl;

public class MainUIController implements Initializable {

    private final DomainFacade domainFacade = new DomainFacadeImpl();
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
    private Label homeHelloLabel, homePlaceLabel, homeCitizenCountLabel;

    // Case nodes:
    @FXML
    private JFXTextField caseCitizenNameTextField, caseCPRTextField, caseAddressTextField;
    @FXML
    private JFXComboBox<String> caseCasesCB;

    //Create case nodes:
    @FXML
    private JFXButton ccCreateCitizenBtn, ccEditCitizenBtn;
    @FXML
    private JFXListView<String> ccCitizenListView;
    @FXML
    private JFXTextField ccSearchCitizenTextField, casePhoneTextField;

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
    @FXML
    private JFXButton ccCreateCaseBtn;

    public MainUIController(Map<String, String> userDetails) {
        this.userDetails = userDetails;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        homeHelloLabel.setText(homeHelloLabel.getText() + userDetails.get(Column.FNAME.getColumnName()) + " " + userDetails.get(Column.LNAME.getColumnName()));
        if (userDetails.get(Column.ROLE.getColumnName()).equalsIgnoreCase("socialworker")) {
            homePlaceLabel.setVisible(true);
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
                ccCreateCaseBtn.setDisable(true);
                ccEditCitizenBtn.setDisable(true);
            }).open();
        });

        adminCreateUserBtn.setOnAction(event -> SimpleStageBuilder.create("Opret Bruger", "RegisterEmployeeUIDocument.fxml").setResizable(false).setCloseOnUnfocused(true).open());

        adminCreateInstitutionBtn.setOnAction(event -> SimpleStageBuilder.create("Opret Bosted", "RegisterInstitutionUIDocument.fxml").setResizable(false).setCloseOnUnfocused(true).open());

        diaryCreateNoteBtn.setOnAction(event -> SimpleStageBuilder.create("Opret Notat", "CreateNoteUIDocument.fxml").setResizable(false)
                .setCloseOnUnfocused(true).setControllerFactory(new CreateNoteUIController(diarynotesObservable, String.valueOf(diaryCaseCb.getSelectionModel().getSelectedIndex()), clientList.getSelectionModel().getSelectedItem())).open());

        ccEditCitizenBtn.setOnAction(event -> SimpleStageBuilder.create("Rediger Borger", "EditCitizenUIDocument.fxml").setResizable(false)
                .setCloseOnUnfocused(true).setControllerFactory(new EditCitizenUIController(ccCitizenListView.getSelectionModel().getSelectedItem())).open());

        ccSearchCitizenTextField.textProperty().addListener(listener -> refresh());
        setClientListener();
        diarynotesListview.setCellFactory(new DiaryListViewCellFactory(this));
        diaryCaseCb.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            diarynotesListview.getItems().clear();
            diarynotesListview.setCellFactory(new DiaryListViewCellFactory(this));
            if (diaryCaseCb.getSelectionModel().getSelectedIndex() != -1) {
                List<List<Map<String, String>>> diaryNoteDetails = domainFacade.getDiaryDetails(clientList.getSelectionModel().getSelectedItem(), diaryCaseCb.getSelectionModel().getSelectedIndex());
                diaryNoteDetails.forEach(note -> diarynotesListview.getItems().add(new DiaryItem(note)));
            }
        });
        diarynotesObservable = FXCollections.observableList(diarynotesListview.getItems());
        diarynotesObservable.addListener((ListChangeListener.Change<? extends DiaryItem> event) -> diarynotesListview.setCellFactory(new DiaryListViewCellFactory(this)));
        setSearchClientListener();
        refresh();
    }

    private void setClientListener() {
        clientList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                diaryCreateNoteBtn.setDisable(false);
                diaryCaseCb.setDisable(false);
                Map<String, String> citizenDetails = domainFacade.getCitizenDetails(newValue);
                caseCitizenNameTextField.setText(citizenDetails.get(Column.NAME.getColumnName()));
                caseCPRTextField.setText(citizenDetails.get(Column.BDAY.getColumnName()));
                caseAddressTextField.setText(citizenDetails.get(Column.ADDR.getColumnName()));
                caseCasesCB.getItems().clear();
                caseCasesCB.getItems().setAll(citizenDetails.get(Column.CASES.getColumnName()).split("\n"));
                diaryCaseCb.getItems().clear();
                diaryCaseCb.getItems().setAll(citizenDetails.get(Column.CASES.getColumnName()).split("\n"));
                if (!diaryCaseCb.getItems().isEmpty()) {
                    diaryCaseCb.getSelectionModel().select(0);
                }
            }
        });
    }

    private void setSearchClientListener() {
        ccCitizenListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                ccCreateCaseBtn.setDisable(false);
                ccEditCitizenBtn.setDisable(false);
            }
        });
    }

    @FXML
    private void onCreateCase(ActionEvent event) {
        SimpleStageBuilder.create("Opret sag", "CreateCaseUIDocument.fxml")
                .setResizable(false)
                .setControllerFactory(new CreateCaseUIController(ccCitizenListView.getSelectionModel().getSelectedItem()))
                .setOnHiding(() -> refresh())
                .open();
        ccCreateCaseBtn.setDisable(true);
        ccEditCitizenBtn.setDisable(true);
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
        if (!userDetails.get(Column.ROLE.getColumnName()).equalsIgnoreCase("admin")) {
            String selectedItem = clientList.getSelectionModel().getSelectedItem();
            List<String> connectedCitizens = domainFacade.getConnectedCitizens();
            clientList.getItems().setAll(connectedCitizens);
            if (clientList.getItems().contains(selectedItem)) {
                clientList.getSelectionModel().select(selectedItem);
            }
            homeCitizenCountLabel.setVisible(true);
            homeCitizenCountLabel.setText(homeCitizenCountLabel.getText() + domainFacade.getConnectedCitizens().size());
                    ccCitizenListView.getItems().setAll(domainFacade.matchCitizens(ccSearchCitizenTextField.getText()));
        }
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
