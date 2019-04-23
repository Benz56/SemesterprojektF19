package semesterprojektf19.presentation;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import semesterprojektf19.domain.LoginFacadeImpl;
import semesterprojektf19.domain.LoginFacade;

public class LoginUIController implements Initializable {

    /**
     * An instance of the InteractionCommunicator used to communicate the events
     * received from the various listeners in this controller.
     */
    private final LoginFacade loginFacade = new LoginFacadeImpl();

    /**
     * Determines whether the logon menu is selected or the register menu.
     */
    private boolean isLoginMenu = true;
    @FXML
    private JFXButton loginMenuBtn, registerMenuBtn, loginBtn, forgotPasswordBtn;
    @FXML
    private AnchorPane loginPane;
    @FXML
    private JFXTextField usernameTextField;
    @FXML
    private JFXPasswordField loginPasswordField;
    @FXML
    private AnchorPane registerPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loginMenuBtn.setOnAction(event -> togglePane(true));
        registerMenuBtn.setOnAction(event -> togglePane(false));
        forgotPasswordBtn.setOnAction(event -> togglePane(false));
        loginBtn.setOnAction(event -> {
            Map<String, String> userDetails = loginFacade.login(usernameTextField.getText(), loginPasswordField.getText());
            if (userDetails != null) {
                ((Stage) loginBtn.getScene().getWindow()).close();
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("MainUIDocument.fxml"));
                    loader.setControllerFactory(controllerFactory -> new MainUIController(userDetails));
                    Stage stage = new Stage();
                    stage.setScene(new Scene(loader.load()));
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(LoginUIController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                usernameTextField.requestFocus();
                usernameTextField.setText("");
                loginPasswordField.setText("");
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
