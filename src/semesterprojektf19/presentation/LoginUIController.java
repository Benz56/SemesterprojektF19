package semesterprojektf19.presentation;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
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
    private static final int IDLE_DURATION = 30;
    
    private IdleMonitor mainIdleMonitor;

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
    @FXML
    private FontAwesomeIconView usernameIcon, passwordIcon;

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
                SimpleStageBuilder.create("EGBoosted", "MainUIDocument.fxml").closeOpenWindow(loginBtn).setControllerFactory(new MainUIController(userDetails)).open();
            } else {
                usernameTextField.requestFocus();
                usernameTextField.setText("");
                loginPasswordField.setText("");
            }
        });
    }

    public void startIdleMonitor(Stage stage) {
        mainIdleMonitor = new IdleMonitor(Duration.minutes(IDLE_DURATION), () -> {
            SimpleStageBuilder.create("EGBoosted", "LoginUIDocument.fxml").setResizable(false).open();
            mainIdleMonitor.stopMonitoring();
        }, true);
        mainIdleMonitor.register(loginBtn.getScene(), Event.ANY);
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
