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
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

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

}
