package semesterprojektf19.presentation;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import semesterprojektf19.domain.RegistrationFacade;
import semesterprojektf19.domain.RegistrationFacadeImpl;

/**
 * FXML Controller Class
 * 
 * @author Gruppe 22 på SE/ST E19, MMMI, Syddansk Universitet
 */
public class RegisterCitizenUIController implements Initializable {

    private final RegistrationFacade registrationFacade = new RegistrationFacadeImpl();

    @FXML
    private JFXTextField firstnameTextField, lastnameTextField, controlNumberTextField, phoneNumberTextField, addressTextField;
    @FXML
    private JFXDatePicker birthdayDatePicker;
    @FXML
    private TextField statusTextField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void onCreate() {
        if (validate()) {
            registrationFacade.registerCitizen(
                    firstnameTextField.getText(),
                    lastnameTextField.getText(),
                    new SimpleDateFormat("dd-MM-yyyy").format(Date.from(birthdayDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant())),
                    controlNumberTextField.getText(),
                    addressTextField.getText(),
                    phoneNumberTextField.getText());
            onCancel();
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
        }
        if (lastnameTextField.getText().isEmpty()) {
            statusTextField.setText("Efternavn mangler!");
            return false;
        }
        if (birthdayDatePicker.getValue() == null) {
            statusTextField.setText("Fødselsdato ikke udfyldt!");
            return false;
        }
        try {
            if (controlNumberTextField.getText().isEmpty()) {
                statusTextField.setText("CPR Løbenummer mangler!");
                return false;
            }
            if (controlNumberTextField.getText().length() != 4) {
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
            }
            if (phoneNumberTextField.getText().length() != 8) {
                statusTextField.setText("Telefonnummer skal være 8 tal!");
                return false;
            }
            Integer.parseInt(phoneNumberTextField.getText());
        } catch (NumberFormatException e) {
            statusTextField.setText("Telefonnummer må kun indeholde tal!");
            return false;
        }
        if (addressTextField.getText().isEmpty()) {
            statusTextField.setText("Adresse mangler!");
            return false;
        }
        statusTextField.setStyle("-fx-text-fill: green;");
        statusTextField.setText("Borger data godkendt!");
        return true;
    }
}
