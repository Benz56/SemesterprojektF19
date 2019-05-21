/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojektf19.presentation;

import com.jfoenix.controls.JFXComboBox;
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

    @FXML
    private JFXTextField firstnameTextField, lastnameTextField, usernameTextField;
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
        roleComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            institutionComboBox.setDisable(!newValue.equalsIgnoreCase("SOCIALWORKER"));
        });
    }

    @FXML
    private void onCreate() {
        if (validate()) {
            if (registrationFacade.registerEmployee(
                    usernameTextField.getText(),
                    passwordPasswordField.getText(),
                    firstnameTextField.getText(),
                    lastnameTextField.getText(),
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
        } else if (roleComboBox.getSelectionModel().getSelectedItem().equalsIgnoreCase("SOCIALWORKER")) {
            institutionComboBox.setDisable(false);
            if (institutionComboBox.getSelectionModel().getSelectedItem() == null) {
                statusTextField.setText("Institution ikke valgt!");
                return false;
            }
        } else {
            institutionComboBox.setDisable(true);
        }
        statusTextField.setStyle("-fx-text-fill: green;");
        statusTextField.setText("Bruger data godkendt!");
        return true;
    }
}
