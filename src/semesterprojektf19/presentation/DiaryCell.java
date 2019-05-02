/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojektf19.presentation;

import javafx.scene.control.ListCell;

/**
 *
 * @author Benjamin Staugaard | Benz56
 */
public class DiaryCell extends ListCell<DiaryItem> {

    @Override
    protected void updateItem(DiaryItem item, boolean empty) {
        super.updateItem(item, empty);
        setGraphic(!empty && item != null ? item.getItem(isSelected()) : null);
    }

}
