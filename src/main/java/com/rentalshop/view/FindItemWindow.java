package com.rentalshop.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by Pavel Davydenko on 03.04.2017.
 */
class FindItemWindow {
    private static String itemToFind;
    public static String display() {
        itemToFind = null;

        // Creating window
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Find item");
        window.setResizable(false);

        // Creating window elements
        Label label = new Label();
        label.setMinWidth(150);
        label.setText("Enter name of the item you want to find:");

        TextField field = new TextField();
        field.setPrefWidth(label.getWidth());

        Button okButton = new Button();
        okButton.setText("Find");
        okButton.setOnAction(e -> {
            itemToFind = field.getText();
            window.close();
        });

        // Creating panes and placing elements
        VBox box = new VBox(15);
        box.setPadding(new Insets(15));

        box.setAlignment(Pos.CENTER);
        box.getChildren().addAll(label, field, okButton);

        window.setScene(new Scene(box));
        window.showAndWait();

        return itemToFind;
    }
}
