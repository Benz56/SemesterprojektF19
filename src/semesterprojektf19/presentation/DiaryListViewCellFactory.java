/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojektf19.presentation;

import com.jfoenix.controls.JFXButton;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.web.HTMLEditor;
import javafx.util.Callback;

/**
 *
 * @author Benjamin Staugaard | Benz56
 */
public class DiaryListViewCellFactory implements Callback<ListView<DiaryItem>, ListCell<DiaryItem>> {

    @Override
    public ListCell<DiaryItem> call(ListView<DiaryItem> listView) {
        TitledPane titledPane = new TitledPane();
        titledPane.setAnimated(false);
        titledPane.setCollapsible(true);
        titledPane.setExpanded(false);

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefHeight(335);
        HTMLEditor diaryNoteEditor = new HTMLEditor();
        diaryNoteEditor.getStylesheets().clear();
        diaryNoteEditor.setPrefHeight(250);
        AnchorPane.setTopAnchor(diaryNoteEditor, 10D);
        AnchorPane.setLeftAnchor(diaryNoteEditor, 10D);
        AnchorPane.setRightAnchor(diaryNoteEditor, 10D);

        JFXButton editButton = new JFXButton("Gem Ændringer"), versionsButton = new JFXButton("Se Versioner");
        editButton.setPrefWidth(150);
        versionsButton.setPrefWidth(150);
        HBox buttons = new HBox(20, editButton, versionsButton);
        AnchorPane.setBottomAnchor(buttons, 10D);
        AnchorPane.setLeftAnchor(buttons, 10D);

        TextField observationDate = new TextField(), originDate = new TextField();
        observationDate.setPrefWidth(80);
        observationDate.setEditable(false);
        originDate.setPrefWidth(195);
        originDate.setEditable(false);
        Label observationLabel = new Label("Observations dato: ");
        observationLabel.setPadding(new Insets(3, 0, 0, 0));
        Label originLabel = new Label("Seneste version: ");
        originLabel.setPadding(new Insets(3, 0, 0, 20));
        HBox dates = new HBox(10, observationLabel, observationDate, originLabel, originDate);
        AnchorPane.setBottomAnchor(dates, 10D);
        AnchorPane.setRightAnchor(dates, 10D);

        anchorPane.getChildren().addAll(diaryNoteEditor, buttons, dates);

        return new ListCell<DiaryItem>() {
            @Override
            protected void updateItem(final DiaryItem diaryItem, final boolean empty) {
                super.updateItem(diaryItem, empty);
                if (empty) {
                    titledPane.setContent(null);
                    titledPane.setText("");
                    return;
                }
                titledPane.setUserData(diaryItem);
                titledPane.setContent(anchorPane);
                titledPane.setText(diaryItem.getTitle());
                diaryNoteEditor.setHtmlText(diaryItem.getContent());
                observationDate.setText(diaryItem.getObsDate());
                originDate.setText(diaryItem.getNoteDate());
                setGraphic(titledPane);
            }
        };
    }
}
