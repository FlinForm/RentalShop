package com.rentalshop.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;



/**
 * Window that displays alert message. String parameter
 * of "display" method is the message you want to show
 * to user.
 */
class AlertBox {

    public static void displayMessage(String text) {
        // Creating window
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Warning!");

        window.setResizable(false);

        Label label = new Label();
        label.setText(text);

        Button okButton = new Button();
        okButton.setText("Ok");
        okButton.setOnAction(e -> window.close());

        // Creating panes and placing elements
        VBox box = new VBox(15);
        box.setPadding(new Insets(15));

        box.setAlignment(Pos.CENTER);
        box.getChildren().addAll(label, okButton);

        window.setScene(new Scene(box));
        window.showAndWait();
    }
}
