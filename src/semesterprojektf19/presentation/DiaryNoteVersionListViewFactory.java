/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojektf19.presentation;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.web.WebView;
import javafx.util.Callback;

/**
 *
 * @author Jacob
 */
public class DiaryNoteVersionListViewFactory implements Callback<ListView<DiaryItem.NoteVersion>, ListCell<DiaryItem.NoteVersion>> {

    @Override
    public ListCell<DiaryItem.NoteVersion> call(ListView<DiaryItem.NoteVersion> param) {
        TitledPane titledPane = new TitledPane();
        titledPane.setAnimated(false);
        titledPane.setCollapsible(true);
        titledPane.setExpanded(false);

        WebView webView = new WebView();

        AnchorPane.setTopAnchor(webView, 1D);
        AnchorPane.setLeftAnchor(webView, 1D);
        AnchorPane.setRightAnchor(webView, 1D);

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

        AnchorPane anchorPane = new AnchorPane(webView);

        anchorPane.setPrefHeight(370);

        return new ListCell<DiaryItem.NoteVersion>() {

            @Override
            protected void updateItem(final DiaryItem.NoteVersion noteVersion, final boolean empty) {
                super.updateItem(noteVersion, empty);
                if (empty) {
                    titledPane.setContent(null);
                    titledPane.setText("");
                    return;
                }
                titledPane.setContent(anchorPane);
                titledPane.setText(noteVersion.getTitle());
                webView.getEngine().loadContent(noteVersion.getContent());
                observationDate.setText(noteVersion.getObsDate());
                originDate.setText(noteVersion.getNoteDate());
                setGraphic(titledPane);
            }

        };
    }

}
