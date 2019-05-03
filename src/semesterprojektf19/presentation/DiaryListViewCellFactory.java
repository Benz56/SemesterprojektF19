/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojektf19.presentation;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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

        HTMLEditor diaryNoteEditor = new HTMLEditor();
        JFXToggleButton toggleEdit = new JFXToggleButton();
        toggleEdit.setText("Slå redigering til ved at klikke her");
        diaryNoteEditor.setOnKeyPressed(value -> {
            if (!toggleEdit.isSelected()) {
                diaryNoteEditor.setHtmlText(diaryNoteEditor.getHtmlText());
                toggleEdit.requestFocus();
            }
        });
        toggleEdit.setPadding(new Insets(0, 0, 0, -4));
        diaryNoteEditor.getStylesheets().clear();
        diaryNoteEditor.setPrefHeight(250);
        AnchorPane.setTopAnchor(diaryNoteEditor, 10D);
        AnchorPane.setLeftAnchor(diaryNoteEditor, 10D);
        AnchorPane.setRightAnchor(diaryNoteEditor, 10D);
        AnchorPane.setBottomAnchor(toggleEdit, -6D);
        AnchorPane.setLeftAnchor(toggleEdit, 260D);

        JFXButton editButton = new JFXButton("Gem Ændringer"), versionsButton = new JFXButton("Se Versioner");
        toggleEdit.selectedProperty().addListener((observable, oldValue, newValue) -> editButton.setDisable(!newValue));
        editButton.setDisable(true);
        editButton.setPrefWidth(120);
        versionsButton.setPrefWidth(120);
        VBox buttons = new VBox(-8, new HBox(10, editButton, versionsButton), toggleEdit);
        AnchorPane.setBottomAnchor(buttons, -8D);
        AnchorPane.setLeftAnchor(buttons, 10D);

        TextField observationDate = new TextField(), originDate = new TextField();
        observationDate.setPrefWidth(200);
        observationDate.setEditable(false);
        originDate.setPrefWidth(200);
        originDate.setEditable(false);

        GridPane dates = new GridPane();
        dates.setHgap(10);
        dates.setVgap(10);
        dates.add(new Label("Observations dato: "), 0, 0);
        dates.add(observationDate, 1, 0);
        dates.add(new Label("Seneste version: "), 0, 1);
        dates.add(originDate, 1, 1);
        AnchorPane.setBottomAnchor(dates, 10D);
        AnchorPane.setRightAnchor(dates, 10D);

        AnchorPane anchorPane = new AnchorPane(diaryNoteEditor, buttons, dates);
        anchorPane.getChildren().addAll(diaryNoteEditor, buttons, dates);

        //opens Version Windows
        versionsButton.setOnAction(event -> SimpleStageBuilder.create("Note versioner", "DiaryNoteVersionsUIDocument.fxml").setResizable(false).setCloseOnUnfocused(true).open());
        anchorPane.setPrefHeight(370);

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
