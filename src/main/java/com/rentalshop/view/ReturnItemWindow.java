package com.rentalshop.view;

import com.rentalshop.model.Renter;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by Pavel Davydenko on 02.04.2017.
 */
public class ReturnItemWindow {
    public static String[] display(Renter renter) {
        String[] returningItems = {null, null, null};

        // Creating window
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Return items");
        window.setResizable(false);


        // Creating window elements
        Label label1 = new Label();
        label1.setText("First item:");

        Label label2 = new Label();
        label2.setText("Second item:");

        Label label3 = new Label();
        label3.setText("Third item:");

        ChoiceBox<String> firstItemBox = new ChoiceBox<>();
        firstItemBox.setMinWidth(170);
        if (renter.getFirstItem() != null) {
            firstItemBox.getItems().addAll(renter.getFirstItem());
        }

        ChoiceBox<String> secondItemBox = new ChoiceBox<>();
        secondItemBox.setMinWidth(170);
        if (renter.getSecondItem() != null) {
            secondItemBox.getItems().addAll(renter.getSecondItem());
        }

        ChoiceBox<String> thirdItemBox = new ChoiceBox<>();
        thirdItemBox.setMinWidth(170);
        if (renter.getThirdItem() != null) {
            thirdItemBox.getItems().addAll(renter.getThirdItem());
        }



        Button yesButton = new Button();
        Button noButton = new Button();
        yesButton.setText("Return");
        noButton.setText("Cancel");

        // Creating panes and placing elements
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(25));
        pane.setVgap(25);
        pane.setHgap(25);
        GridPane.setConstraints(label1, 0, 0);
        GridPane.setConstraints(label2, 0, 1);
        GridPane.setConstraints(label3, 0, 2);
        GridPane.setConstraints(yesButton, 0, 3);
        GridPane.setConstraints(firstItemBox, 1, 0);
        GridPane.setConstraints(secondItemBox, 1, 1);
        GridPane.setConstraints(thirdItemBox, 1, 2);
        GridPane.setConstraints(noButton, 1, 3);

        pane.getChildren().addAll(label1, label2, label3, yesButton, firstItemBox, secondItemBox, thirdItemBox, noButton);


        yesButton.setOnAction(event -> {
            returningItems[0] = firstItemBox.getValue();
            returningItems[1] = secondItemBox.getValue();
            returningItems[2] = thirdItemBox.getValue();
            window.close();
        });
        noButton.setOnAction(event -> window.close());

        window.setScene(new Scene(pane));
        window.showAndWait();

        return returningItems;
    }
}
