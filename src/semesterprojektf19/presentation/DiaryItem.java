/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojektf19.presentation;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Benjamin Staugaard | Benz56
 */
public class DiaryItem {

    private final AnchorPane anchorPane = new AnchorPane();
    private final String diaryDetails;
    private boolean expanded;
    private Node expansion;

    public DiaryItem(String diaryDetails) {
        this.diaryDetails = diaryDetails;
        expansion = new TextArea();
    }

    public AnchorPane getItem(boolean expand) {
        Label title = new Label("Titel: " + diaryDetails);
        title.setStyle("-fx-font-weight: bold");
        anchorPane.getChildren().add(title);
        return anchorPane;
    }

    public void expand() {
        if (!expanded) {
            anchorPane.getChildren().add(expansion);
            expanded = true;
        }
    }

    public void collapse() {
        if (expanded) {
            anchorPane.getChildren().remove(expansion);
            expanded = false;
        }
    }
}
