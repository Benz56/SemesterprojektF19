/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojektf19.presentation;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.awt.MouseInfo;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import semesterprojektf19.acquaintance.Column;
import semesterprojektf19.domain.DomainFacade;
import semesterprojektf19.domain.DomainFacadeImpl;

/**
 * FXML Controller class
 *
 * @author sofielouise
 */
public class CreateCaseUIController implements Initializable {

    @FXML
    private AnchorPane createCasePane1, createCasePane2;
    @FXML
    private JFXTextField shortInfo;
    @FXML
    private JFXTextArea background;
    @FXML
    private JFXCheckBox citizenSeekYes, citizenSeekNo;
    //ydelser
    @FXML
    private JFXCheckBox y105, y101, y102, y85, y84, y108, y80, y87, y89;
    //tilbud
    @FXML
    private JFXCheckBox t1, t2, t3, t4, t5, t6, t7, t8, t9;
    @FXML
    private JFXButton next, back;
    @FXML
    private Label lblPageNumber;
    @FXML
    private JFXButton createCase;

    private DomainFacade domainFacade = new DomainFacadeImpl();
    private String citizenInfo;
    @FXML
    private JFXCheckBox inquiryOriginCitizen, inquiryOriginRelative, inquiryOriginDoctor;
    @FXML
    private JFXCheckBox citizenAgreeYes, citizenAgreeNo;
    @FXML
    private JFXCheckBox guardianship, reprensentation;
    @FXML
    private JFXCheckBox electronicAgree, electronicDisagree;
    @FXML
    private JFXTextArea furtherProcess;
    @FXML
    private JFXCheckBox rightToRepYes, rightToRepNo;
    @FXML
    private JFXTextField txtGuardian, txtRep;

    private int currentPageNumber;
    private final Map<Integer, AnchorPane> anchorPanes;
    @FXML
    private Label txtCitizen;

    public CreateCaseUIController(String citizenInfo) {
        this.anchorPanes = new TreeMap<>();
        this.citizenInfo = citizenInfo;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        currentPageNumber = 1;
        anchorPanes.put(1, createCasePane1);
        anchorPanes.put(2, createCasePane2);
        showCurrentPane();
        txtCitizen.setText("Borger: " + citizenInfo);
        lblPageNumber.setText("Side " + currentPageNumber + "/" + anchorPanes.size());
    }

    @FXML
    private void next(ActionEvent event) {
        moveToPane(true);
    }

    @FXML
    private void back(ActionEvent event) {
        moveToPane(false);
    }

    private void moveToPane(boolean forward) {
        if (forward) {
            currentPageNumber++;
        } else {
            currentPageNumber--;
        }
        showCurrentPane();
        back.setDisable(currentPageNumber == 1);
        next.setVisible(currentPageNumber < anchorPanes.size());
        createCase.setVisible(currentPageNumber == anchorPanes.size());
        lblPageNumber.setText("Side " + currentPageNumber + "/" + anchorPanes.size());
    }

    private void showCurrentPane() {
        anchorPanes.values().forEach(pane -> pane.setVisible(false));
        if (anchorPanes.containsKey(currentPageNumber)) {
            anchorPanes.get(currentPageNumber).setVisible(true);
        }
    }

    @FXML
    private void onCreateCase(ActionEvent event) {
        Tooltip tooltip = new Tooltip();
        if (!shortInfo.getText().isEmpty()) {
            Map<String, String> caseDetails = new HashMap<>();
            caseDetails.put(Column.SHORTINFO.getColumnName(), shortInfo.getText());
            caseDetails.put(Column.BACKGROUND.getColumnName(), background.getText());
            caseDetails.put(Column.CITIZEN.getColumnName(), citizenInfo);
            caseDetails.put(Column.GUARDIAN.getColumnName(), txtGuardian.getText());
            caseDetails.put(Column.REPRESENTATION.getColumnName(), txtRep.getText());
            caseDetails.put(Column.RIGHTTOREPRESENTATION.getColumnName(), String.valueOf(rightToRepYes.isSelected()));
            caseDetails.put(Column.INFORMEDONELECTRONICINFO.getColumnName(), String.valueOf(electronicAgree.isSelected()));
            caseDetails.put(Column.AGREEMENTSONFURTHERPROCESS.getColumnName(), furtherProcess.getText());
//            caseDetails.put(Column.SPECIALCURCUMSTANCES.getColumnName(), ccSpecialCircumstancesTextArea.getText());
//            caseDetails.put(Column.PAYINGMUNICIPALITY.getColumnName(), ccPayingMuniTextField.getText());
//            caseDetails.put(Column.CONSENTRELEVANT.getColumnName(), String.valueOf(ccConsentRelevantCB.isSelected()));
//            caseDetails.put(Column.CONSENTGIVEN.getColumnName(), String.valueOf(ccConsentGivenCB.isSelected()));
//            caseDetails.put(Column.EXECUTINGMUNICIPALITY.getColumnName(), ccExecutingMuniCB.getSelectionModel().getSelectedItem());

            domainFacade.createCase(caseDetails);
            tooltip.setText("Sag oprettet!");
        } else {
            tooltip.setText("Mangler informationer!");
        }
        tooltip.show(((JFXButton) event.getSource()).getScene().getWindow(), MouseInfo.getPointerInfo().getLocation().getX(), MouseInfo.getPointerInfo().getLocation().getY());
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.schedule(() -> Platform.runLater(() -> tooltip.hide()), 2, TimeUnit.SECONDS);
        executor.shutdown();
    }

    @FXML
    private void onCancel(ActionEvent event) {
        ((Stage) next.getScene().getWindow()).close();
    }

}
