package semesterprojektf19;

import javafx.application.Application;
import javafx.stage.Stage;
import semesterprojektf19.presentation.SimpleStageBuilder;

public class EGBoosted extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        SimpleStageBuilder.create("EGBoosted", "LoginUIDocument.fxml").setResizable(false).open();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
