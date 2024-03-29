package semesterprojektf19.presentation;

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
 * FXML Controller Class
 * 
 * @author Gruppe 22 på SE/ST E19, MMMI, Syddansk Universitet
 */
public class RegisterInstitutionUIController implements Initializable {

    private final RegistrationFacade registrationFacade = new RegistrationFacadeImpl();

    @FXML
    private JFXTextField addressTextField;
    @FXML
    private TextField statusTextField;
    @FXML
    private JFXTextField nameTextField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void onCreate() {
        if (validate()) {
            registrationFacade.registerInstitution(nameTextField.getText(), addressTextField.getText());
            onCancel();
        }
    }

    @FXML
    private void onCancel() {
        ((Stage) nameTextField.getScene().getWindow()).close();
    }

    public boolean validate() {
        statusTextField.setStyle("-fx-text-fill: red;");
        if (nameTextField.getText().isEmpty()) {
            statusTextField.setText("Navn mangler!");
            return false;
        } else if (addressTextField.getText().isEmpty()) {
            statusTextField.setText("Adresse mangler!");
            return false;
        }
        statusTextField.setStyle("-fx-text-fill: green;");
        statusTextField.setText("Bostedets data godkendt!");
        return true;
    }
}
