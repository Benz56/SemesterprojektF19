package semesterprojektf19.presentation;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class MainUIController implements Initializable {

    //En instans af et modul i domænelaget mangler her. (Ligesom i Login)

    @FXML
    private JFXButton homeBtn, createCaseBtn, casesBtn, adminBtn;
    @FXML
    private AnchorPane homePane, createNotePane, casesPane, adminPane;
    private final Map<JFXButton, AnchorPane> btnPaneMap = new HashMap<>();

    @FXML
    private JFXListView<String> clientList;

    private JFXButton selectedBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        selectedBtn = homeBtn;
        btnPaneMap.put(homeBtn, homePane);
        btnPaneMap.put(createCaseBtn, createNotePane);
        btnPaneMap.put(casesBtn, casesPane);
        btnPaneMap.put(adminBtn, adminPane);
        homeBtn.setOnAction(event -> changePane(homeBtn));
        createCaseBtn.setOnAction(event -> changePane(createCaseBtn));
        casesBtn.setOnAction(event -> changePane(casesBtn));
        adminBtn.setOnAction(event -> changePane(adminBtn));

        clientList.getItems().add("Jan Jansen");
        clientList.getItems().add("Fisayo Et Eller Andet");
        clientList.getItems().add("Lone Borgersen");
    }

    private void changePane(JFXButton clickedBtn) {
        if (!selectedBtn.equals(clickedBtn)) {
            String temp = selectedBtn.getStyle();
            selectedBtn.setStyle(clickedBtn.getStyle());
            clickedBtn.setStyle(temp);
            selectedBtn = clickedBtn;
            btnPaneMap.values().forEach(pane -> pane.setVisible(false));
            btnPaneMap.get(selectedBtn).setVisible(true);
        }
    }
}
