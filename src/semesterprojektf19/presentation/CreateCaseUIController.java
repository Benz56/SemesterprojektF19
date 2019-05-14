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
    private AnchorPane createCasePane1;
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

    public CreateCaseUIController(String citizenInfo) {
        this.citizenInfo = citizenInfo;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void next(ActionEvent event) {
    }

    @FXML
    private void back(ActionEvent event) {
    }

    @FXML
    private void onCreateCase(ActionEvent event) {
        Tooltip tooltip = new Tooltip();
        if (!shortInfo.getText().isEmpty()
//                && ccCitizenListView.getSelectionModel().getSelectedItem() != null
//                && ccExecutingMuniCB.getSelectionModel().getSelectedItem() != null
//                && !ccRepresentationTextField.getText().isEmpty()
//                && !ccPayingMuniTextField.getText().isEmpty()
//                && !ccShortInfoTextField.getText().isEmpty()
                ) {
            Map<String, String> caseDetails = new HashMap<>();
            caseDetails.put(Column.CITIZEN.getColumnName(), citizenInfo);
//            caseDetails.put(Column.GUARDIAN.getColumnName(), ccGuardianTextField.getText());
//            caseDetails.put(Column.EXECUTINGMUNICIPALITY.getColumnName(), ccExecutingMuniCB.getSelectionModel().getSelectedItem());
//            caseDetails.put(Column.REPRESENTATION.getColumnName(), ccRepresentationTextField.getText());
//            caseDetails.put(Column.PAYINGMUNICIPALITY.getColumnName(), ccPayingMuniTextField.getText());
//            caseDetails.put(Column.CONSENTRELEVANT.getColumnName(), String.valueOf(ccConsentRelevantCB.isSelected()));
//            caseDetails.put(Column.CONSENTGIVEN.getColumnName(), String.valueOf(ccConsentGivenCB.isSelected()));
//            caseDetails.put(Column.RIGHTTOREPRESENTATION.getColumnName(), String.valueOf(ccRightToRepCB.isSelected()));
//            caseDetails.put(Column.INFORMEDONELECTRONICINFO.getColumnName(), String.valueOf(ccInformedECardCB.isSelected()));
//            caseDetails.put(Column.AGREEMENTSONFURTHERPROCESS.getColumnName(), ccProcessAgreementsTextArea.getText());
//            caseDetails.put(Column.SPECIALCURCUMSTANCES.getColumnName(), ccSpecialCircumstancesTextArea.getText());
            caseDetails.put(Column.SHORTINFO.getColumnName(), shortInfo.getText());
            caseDetails.put(Column.BACKGROUND.getColumnName(),background.getText());
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

}
