/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojektf19.presentation;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import semesterprojektf19.domain.RegisterCitizenFacade;
import semesterprojektf19.domain.RegisterCitizenFacadeImpl;

/**
 * FXML Controller class
 *
 * @author Benjamin Staugaard | Benz56
 */
public class RegisterCitizenUIDocumentController implements Initializable {

    private final RegisterCitizenFacade registerCitizenFacade = new RegisterCitizenFacadeImpl();
    
    @FXML
    private JFXTextField firstnameTextField;
    @FXML
    private JFXTextField lastnameTextField;
    @FXML
    private JFXTextField birthdayTextField;
    @FXML
    private JFXTextField controlNumberTextField;
    @FXML
    private JFXTextField phoneNumberTextField;
    @FXML
    private Button createCitizenBtn;
    @FXML
    private Button cancelBtn;
    @FXML
    private Label registerLabel;


    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        createCitizenBtn.setOnAction(event -> {
            if (!firstnameTextField.getText().isEmpty() && !lastnameTextField.getText().isEmpty() && !birthdayTextField.getText().isEmpty() && !controlNumberTextField.getText().isEmpty() && !phoneNumberTextField.getText().isEmpty()) {
                registerCitizenFacade.register(firstnameTextField.getText(), lastnameTextField.getText(), birthdayTextField.getText(), controlNumberTextField.getText(),Integer.parseInt(phoneNumberTextField.getText()));
                //TODO Tjek på alle fields for format / korrekt indtastning.
                ((Stage) registerLabel.getScene().getWindow()).close();
            } else {
                registerLabel.setText("Udfyld venligst alt!");
            }
        });
        cancelBtn.setOnAction(event -> {
            ((Stage) cancelBtn.getScene().getWindow()).close();
        });
        
        
        
    }    
    
    
    
}
