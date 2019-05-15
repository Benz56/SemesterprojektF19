package semesterprojektf19.presentation;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import semesterprojektf19.domain.DomainFacade;
import semesterprojektf19.domain.DomainFacadeImpl;
import semesterprojektf19.domain.RegistrationFacade;
import semesterprojektf19.domain.RegistrationFacadeImpl;

public class EditCitizenUIController implements Initializable {

    private String uuid, citizenString;
    DomainFacade domainFacade = new DomainFacadeImpl();
    RegistrationFacade registrationFacade = new RegistrationFacadeImpl();

    private boolean valid = true;

    @FXML
    private JFXTextField phonenumberTextField, addressTextField, firstNameTextField, lastNameTextField;
    @FXML
    private TextField statusTextField;
    
    public EditCitizenUIController(String citizenString) {
        this.citizenString = citizenString;
        System.out.println(this.citizenString);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        firstNameTextField.setText(domainFacade.getCitizenDetails(citizenString).get("firstName"));
        lastNameTextField.setText(domainFacade.getCitizenDetails(citizenString).get("lastName"));
        phonenumberTextField.setText(domainFacade.getCitizenDetails(citizenString).get("phoneNumber"));
        addressTextField.setText(domainFacade.getCitizenDetails(citizenString).get("address"));
        uuid = domainFacade.getCitizenDetails(citizenString).get("uuid");
    }

    @FXML
    private void onUpdate() {
        if (validate()) {
            registrationFacade.editCitizen(
                    firstNameTextField.getText(),
                    lastNameTextField.getText(),
                    addressTextField.getText(),
                    phonenumberTextField.getText(),
                    uuid);
        }
        ((Stage) phonenumberTextField.getScene().getWindow()).close();
    }

    @FXML
    private void onCancel() {
        ((Stage) phonenumberTextField.getScene().getWindow()).close();
    }

    private boolean validate() {
        statusTextField.setStyle("-fx-text-fill: green;");
        statusTextField.setText("Borger data godkendt!");
        if (firstNameTextField.getText().isEmpty()) {
            statusTextField.setText("Fornavn mangler!");
            statusTextField.setStyle("-fx-text-fill: red;");
            return false;
        }
        if (lastNameTextField.getText().isEmpty()) {
            statusTextField.setText("Efternavn mangler!");
            statusTextField.setStyle("-fx-text-fill: red;");
            return false;
        }
        try {
            if (phonenumberTextField.getText().isEmpty()) {
                statusTextField.setText("Telefonnummer mangler!");
                statusTextField.setStyle("-fx-text-fill: red;");
                return false;
            }
            if (phonenumberTextField.getText().length() != 8) {
                statusTextField.setText("Telefonnummer skal være 8 tal!");
                statusTextField.setStyle("-fx-text-fill: red;");
                return false;
            }
            Integer.parseInt(phonenumberTextField.getText());
        } catch (NumberFormatException e) {
            statusTextField.setText("Telefonnummer må kun indeholde tal!");
            statusTextField.setStyle("-fx-text-fill: red;");
            return false;
        }
        if (addressTextField.getText().isEmpty()) {
            statusTextField.setText("Adresse mangler!");
            statusTextField.setStyle("-fx-text-fill: red;");
            return false;
        }
        statusTextField.setStyle("-fx-text-fill: green;");
        statusTextField.setText("Borger data godkendt!");
        return true;
    }
}
