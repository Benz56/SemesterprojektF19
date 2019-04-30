/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojektf19.presentation;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import semesterprojektf19.domain.RegistrationFacade;
import semesterprojektf19.domain.RegistrationFacadeImpl;

/**
 *
 * @author Benjamin Staugaard | Benz56
 */
public class RegisterEmployeeUIController implements Initializable {

    private final RegistrationFacade registrationFacade = new RegistrationFacadeImpl();

    private boolean valid = false;

    @FXML
    private JFXTextField firstnameTextField, lastnameTextField, controlNumberTextField, phoneNumberTextField, addressTextField, usernameTextField;
    @FXML
    private JFXDatePicker birthdayDatePicker;
    @FXML
    private JFXPasswordField passwordPasswordField, confirmPasswordField;
    @FXML
    private TextField statusTextField;
    @FXML
    private JFXComboBox<String> roleComboBox, institutionComboBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        roleComboBox.getItems().addAll("Caseworker", "Socialworker", "Admin");
        institutionComboBox.getItems().addAll(registrationFacade.getInstitutionNames());
        
        firstnameTextField.textProperty().addListener(listener -> valid = validate());
        lastnameTextField.textProperty().addListener(listener -> valid = validate());
        birthdayDatePicker.valueProperty().addListener(listener -> valid = validate());
        controlNumberTextField.textProperty().addListener(listener -> valid = validate());
        phoneNumberTextField.textProperty().addListener(listener -> valid = validate());
        addressTextField.textProperty().addListener(listener -> valid = validate());
        usernameTextField.textProperty().addListener(listener -> valid = validate());
        passwordPasswordField.textProperty().addListener(listener -> valid = validate());
        confirmPasswordField.textProperty().addListener(listener -> valid = validate());
        roleComboBox.valueProperty().addListener(listener -> valid = validate());
        institutionComboBox.valueProperty().addListener(listener -> valid = validate());
    }

    @FXML
    private void onCreate() {
        if (valid) {
            if (registrationFacade.registerEmployee(
                    usernameTextField.getText(),
                    passwordPasswordField.getText(),
                    firstnameTextField.getText(),
                    lastnameTextField.getText(),
                    birthdayDatePicker.getValue().toString(),
                    Integer.parseInt(controlNumberTextField.getText()),
                    addressTextField.getText(),
                    Integer.parseInt(phoneNumberTextField.getText()),
                    roleComboBox.getSelectionModel().getSelectedItem().toUpperCase(),
                    institutionComboBox.getSelectionModel().getSelectedItem()
            )) {
                onCancel();
            } else {
                statusTextField.setStyle("-fx-text-fill: red;");
                statusTextField.setText("Brugernavn findes allerede!");
            }
        }
    }

    @FXML
    private void onCancel() {
        ((Stage) firstnameTextField.getScene().getWindow()).close();
    }

    private boolean validate() {
        statusTextField.setStyle("-fx-text-fill: red;");
        if (firstnameTextField.getText().isEmpty()) {
            statusTextField.setText("Fornavn mangler!");
            return false;
        } else if (lastnameTextField.getText().isEmpty()) {
            statusTextField.setText("Efternavn mangler!");
            return false;
        } else if (birthdayDatePicker.getValue() == null) {
            statusTextField.setText("Fødselsdato ikke udfyldt!");
            return false;
        }
        try {
            if (controlNumberTextField.getText().isEmpty()) {
                statusTextField.setText("CPR Løbenummer mangler!");
                return false;
            } else if (controlNumberTextField.getText().length() != 4) {
                statusTextField.setText("CPR Løbenummer skal være 4 tal!");
                return false;
            }
            Integer.parseInt(controlNumberTextField.getText());
        } catch (NumberFormatException e) {
            statusTextField.setText("CPR Løbenummer må kun indeholde tal!");
            return false;
        }
        try {
            if (phoneNumberTextField.getText().isEmpty()) {
                statusTextField.setText("Telefonnummer mangler!");
                return false;
            } else if (phoneNumberTextField.getText().length() != 8) {
                statusTextField.setText("Telefonnummer skal være 8 tal!");
                return false;
            }
            Integer.parseInt(phoneNumberTextField.getText());
        } catch (NumberFormatException e) {
            statusTextField.setText("Telefonnummer må kun indeholde tal!");
            return false;
        }
        if (addressTextField.getText().isEmpty()) {
            statusTextField.setText("Addresse mangler!");
            return false;
        } else if (usernameTextField.getText().isEmpty()) {
            statusTextField.setText("Brugernavn mangler!");
            return false;
        } else if (passwordPasswordField.getText().isEmpty()) {
            statusTextField.setText("Adgangskode mangler!");
            return false;
        } else if (confirmPasswordField.getText().isEmpty()) {
            statusTextField.setText("Bekræft adgangskode mangler!");
            return false;
        } else if (!passwordPasswordField.getText().equals(confirmPasswordField.getText())) {
            statusTextField.setText("Adganskoder ikke ens!");
            return false;
        } else if (roleComboBox.getSelectionModel().getSelectedItem() == null) {
            statusTextField.setText("Rolle ikke valgt!");
            return false;
        } else if(roleComboBox.getSelectionModel().getSelectedItem().toUpperCase().equals("SOCIALWORKER")){
            institutionComboBox.setDisable(false);
        } else {
            institutionComboBox.setDisable(true);
        }
        statusTextField.setStyle("-fx-text-fill: green;");
        statusTextField.setText("Bruger data godkendt!");
        return true;
    }
}
