/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojektf19.presentation;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.input.KeyEvent;
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
    public ListCell<DiaryItem.NoteVersion> call(ListView<DiaryItem.NoteVersion> listview) {
        TitledPane titledPane = new TitledPane();
        titledPane.setAnimated(false);
        titledPane.setCollapsible(true);
        titledPane.setExpanded(false);

        WebView webView = new WebView();
        webView.setPrefHeight(250);
        AnchorPane.setTopAnchor(webView, 1D);
        AnchorPane.setLeftAnchor(webView, 1D);
        AnchorPane.setRightAnchor(webView, 1D);
        AnchorPane.setBottomAnchor(webView, 1D);
        AnchorPane webViewWrapper = new AnchorPane(webView);
        webViewWrapper.setStyle("-fx-background-color: #E5E5E5;");
        AnchorPane.setTopAnchor(webViewWrapper, 10D);
        AnchorPane.setLeftAnchor(webViewWrapper, 10D);
        AnchorPane.setRightAnchor(webViewWrapper, 10D);

        Label observationDate = new Label(), originDate = new Label(), creatorLabel = new Label();

        GridPane labels = new GridPane();
        labels.setHgap(10);
        labels.add(new Label("Observations dato: "), 0, 0);
        labels.add(observationDate, 1, 0);
        labels.add(new Label("Seneste version: "), 0, 1);
        labels.add(originDate, 1, 1);
        labels.add(new Label("Redigeret af: "), 0, 2);
        labels.add(creatorLabel, 1, 2);
        AnchorPane.setBottomAnchor(labels, 10D);
        AnchorPane.setLeftAnchor(labels, 10D);

        AnchorPane anchorPane = new AnchorPane(webViewWrapper, labels);

        anchorPane.setPrefHeight(360);

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
                titledPane.setText(noteVersion.getNoteDate());
                webView.getEngine().loadContent(noteVersion.getContent());
                webView.addEventFilter(KeyEvent.KEY_PRESSED, event -> webView.getEngine().loadContent(noteVersion.getContent()));
                observationDate.setText(noteVersion.getObsDate());
                originDate.setText(noteVersion.getNoteDate());
                creatorLabel.setText(noteVersion.getCreator());
                setGraphic(titledPane);
            }
        };
    }

}
