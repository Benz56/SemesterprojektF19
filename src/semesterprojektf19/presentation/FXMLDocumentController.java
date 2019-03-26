/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojektf19.presentation;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

/**
 *
 * @author Benjamin Staugaard | Benz56
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private ListView<String> citizenListView;
    @FXML
    private ListView<MenuItem> menuListView;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        menuListView.setItems(FXCollections.observableArrayList(Arrays.asList(new MenuItem("Home", "Face"), new MenuItem("Opret Notat", "Diary"))));
        menuListView.setCellFactory(listview -> new ListCell<MenuItem>() {
            @Override
            protected void updateItem(final MenuItem menuItem, final boolean empty) {
                super.updateItem(menuItem, empty);
                setGraphic(!empty && menuItem != null ? menuItem.getItem() : null);
            }
        });
    }

}
