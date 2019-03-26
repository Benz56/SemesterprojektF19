/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojektf19.presentation;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import semesterprojektf19.EGBoosted;

/**
 *
 * @author Benjamin Staugaard | Benz56
 */
public class MenuItem {

    private final String title;

    private final String imageName;

    public MenuItem(String title, String imageName) {
        this.title = title;
        this.imageName = imageName;
    }

    public VBox getItem() {
        final ImageView imageView = new ImageView(EGBoosted.class.getResource("resources/images/" + imageName + ".png").toExternalForm());
        imageView.setFitHeight(75);
        imageView.setFitWidth(75);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setCache(true);

        Label title = new Label(this.title);
        title.setStyle("-fx-font-weight: bold");
        final VBox vBox = new VBox(imageView, title);
        vBox.setAlignment(Pos.CENTER);
        return vBox;
    }
}
