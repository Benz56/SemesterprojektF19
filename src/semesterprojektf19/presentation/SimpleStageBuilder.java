package semesterprojektf19.presentation;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * 
 * @author Gruppe 22 på SE/ST E19, MMMI, Syddansk Universitet
 */
public final class SimpleStageBuilder {

    private static final Image ICON = new Image(SimpleStageBuilder.class.getResource("resources/images/windowlogo.png").toExternalForm());

    public static SimpleStageBuilder create(String windowTitle, String fxmlDocument) {
        return new SimpleStageBuilder(windowTitle, fxmlDocument);
    }

    private final String windowTitle, fxmlDocument;
    private boolean resizable = true, closeOnUnfocused;
    private Initializable controllerFactory;
    private Runnable onHiding;
    private Node windowNode;

    private SimpleStageBuilder(String windowTitle, String fxmlDocument) {
        this.windowTitle = windowTitle;
        this.fxmlDocument = fxmlDocument;
    }

    public SimpleStageBuilder setResizable(boolean resizable) {
        this.resizable = resizable;
        return this;
    }

    public SimpleStageBuilder setCloseOnUnfocused(boolean closeOnUnfocused) {
        this.closeOnUnfocused = closeOnUnfocused;
        return this;
    }

    public SimpleStageBuilder setControllerFactory(Initializable controllerFactory) {
        this.controllerFactory = controllerFactory;
        return this;
    }

    public SimpleStageBuilder setOnHiding(Runnable runnable) {
        onHiding = runnable;
        return this;
    }

    public SimpleStageBuilder closeOpenWindow(Node windowNode) {
        this.windowNode = windowNode;
        return this;
    }

    public void open() {
        Stage stage = new Stage();
        stage.setTitle(windowTitle);
        stage.setResizable(resizable);
        stage.getIcons().add(ICON);

        if (closeOnUnfocused) {
            stage.focusedProperty().addListener((observable, oldFocus, newFocus) -> {
                if (!newFocus) {
                    stage.close();
                }
            });
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlDocument));
            if (controllerFactory != null) {
                loader.setControllerFactory(controllerFactory -> this.controllerFactory);
            }
            stage.setScene(new Scene(loader.load()));
            stage.show();
            if (onHiding != null) {
                stage.setOnHiding(value -> onHiding.run());
            }
            if (windowNode != null) {
                ((Stage) windowNode.getScene().getWindow()).close();
            }
        } catch (IOException ex) {
            Logger.getLogger(SimpleStageBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
