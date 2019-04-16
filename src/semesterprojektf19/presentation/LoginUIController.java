package semesterprojektf19.presentation;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import semesterprojektf19.domain.LoginFacadeImpl;
import semesterprojektf19.domain.LoginFacade;

public class LoginUIController implements Initializable {

    /**
     * An instance of the InteractionCommunicator used to communicate the events
     * received from the various listeners in this controller.
     */
    private final LoginFacade loginController = new LoginFacadeImpl();

    /**
     * Determines whether the logon menu is selected or the register menu.
     */
    private boolean isLoginMenu = true;

    @FXML
    private AnchorPane loginPane, registerPane;
    @FXML
    private JFXButton loginMenuBtn, registerMenuBtn, loginBtn, registerBtn, forgotPasswordBtn;
    @FXML
    private JFXTextField usernameTextField, firstnameTextField, lastnameTextField, registerUsernameTextField;
    @FXML
    private JFXPasswordField loginPasswordField, registerPasswordField, confirmPasswordField;
    @FXML
    private Label registerLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loginMenuBtn.setOnAction(event -> togglePane(true));
        registerMenuBtn.setOnAction(event -> togglePane(false));
        loginBtn.setOnAction(event -> {
            if (loginController.login(usernameTextField.getText(), loginPasswordField.getText())) {
                ((Stage) loginBtn.getScene().getWindow()).close();
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("MainUIDocument.fxml"));
                    Stage stage = new Stage();
                    stage.setScene(new Scene(loader.load()));
                    stage.show();
                } catch (IOException e) {
                }
            } else {
                usernameTextField.requestFocus();
                usernameTextField.setText("");
                loginPasswordField.setText("");
            }
        });
        registerLabel.setStyle("-fx-text-fill: red;");
        registerBtn.setOnAction(event -> {
            if (!registerUsernameTextField.getText().isEmpty() && !registerPasswordField.getText().isEmpty() && !firstnameTextField.getText().isEmpty() && !lastnameTextField.getText().isEmpty()) {
                if (registerPasswordField.getText().equals(confirmPasswordField.getText())) {
                    if (loginController.register(registerUsernameTextField.getText(), registerPasswordField.getText(), firstnameTextField.getText(), lastnameTextField.getText())) {
                        Arrays.asList(registerUsernameTextField, firstnameTextField, lastnameTextField).forEach(field -> field.clear());
                        Arrays.asList(registerPasswordField, confirmPasswordField).forEach(field -> field.clear());
                        togglePane(true);
                    } else {
                        registerLabel.setText("Brugernavn findes!");
                    }
                } else {
                    registerLabel.setText("Kodeord ikke ens!");
                }
            } else {
                registerLabel.setText("Udfyld venligst alt!");
            }
        });
    }

    public void togglePane(boolean isLoginMenu) {
        if (this.isLoginMenu != isLoginMenu) {
            this.isLoginMenu = isLoginMenu;
            String temp = loginMenuBtn.getStyle();
            loginMenuBtn.setStyle(registerMenuBtn.getStyle());
            registerMenuBtn.setStyle(temp);
            loginPane.setVisible(isLoginMenu);
            registerPane.setVisible(!isLoginMenu);
        }
    }
}
