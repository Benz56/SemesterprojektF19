/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojektf19.presentation;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author bemma
 */
public class LoginUIController implements Initializable {

    /**
     * Determines whether the logon menu is selected or the register menu.
     */
    private boolean isLoginMenu = true;

    @FXML
    private AnchorPane loginPane;
    @FXML
    private AnchorPane registerPane;
    @FXML
    private JFXButton loginMenuBtn, registerMenuBtn, loginBtn, registerBtn, forgotPasswordBtn;
    @FXML
    private JFXTextField usernameTextField, firstnameTextField, lastnameTextField, emailTextField;
    @FXML
    private JFXPasswordField loginPasswordField, registerPasswordField, confirmPasswordField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loginMenuBtn.setOnAction(event -> {
            togglePane(true);
        });
        registerMenuBtn.setOnAction(event -> {
            togglePane(false);
        });
        loginBtn.setOnAction(event -> {
            // MANGLER LOGIN CHECK.
            ((Stage) loginBtn.getScene().getWindow()).close();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("MainUIDocument.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(loader.load()));
                stage.show();
            } catch (IOException e) {
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
