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
 * Created by Pavel Davydenko on 31.03.2017.
 */
public class AllertBox {

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

        VBox box = new VBox(15);
        box.setPadding(new Insets(15));

        box.setAlignment(Pos.CENTER);
        box.getChildren().addAll(label, okButton);


        Scene scene = new Scene(box);
        window.setScene(scene);
        window.showAndWait();
    }
}
