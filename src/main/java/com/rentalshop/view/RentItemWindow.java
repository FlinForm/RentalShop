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

import java.util.List;

/**
 * Window that displays when renting an item.
 */
class RentItemWindow {
    private static Object[] returnedData;

    public static Object[] display(List<Renter> clients) {
        returnedData = new Object[2];

        // Creating window
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Rent item");
        window.setResizable(false);


        // Creating window elements
        Label label1 = new Label();
        label1.setText("Select a rentor:");

        Label label2 = new Label();
        label2.setText("Number of items:");

        ChoiceBox<Renter> renters = new ChoiceBox<>();
        renters.getItems().addAll(clients);

        ChoiceBox<Integer> numberOfItems = new ChoiceBox<>();
        numberOfItems.getItems().addAll(1, 2, 3);

        Button yesButton = new Button();
        Button noButton = new Button();
        yesButton.setText("Rent");
        noButton.setText("Cancel");
        yesButton.setOnAction(event -> {
            if (renters.getValue() == null || numberOfItems.getValue() == null) {
                AlertBox.displayMessage("Please, make your choice!");
            } else {
                returnedData[0] = renters.getValue();
                returnedData[1] = numberOfItems.getValue();
                window.close();
            }
        });
        noButton.setOnAction(event -> window.close());

        // Creating panes and placing elements
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(25));
        pane.setVgap(25);
        pane.setHgap(25);
        GridPane.setConstraints(label1, 0, 0);
        GridPane.setConstraints(label2, 0, 1);
        GridPane.setConstraints(yesButton, 0, 2);
        GridPane.setConstraints(renters, 1, 0);
        GridPane.setConstraints(numberOfItems, 1, 1);
        GridPane.setConstraints(noButton, 1, 2);

        pane.getChildren().addAll(label1, label2, yesButton, renters, numberOfItems, noButton);

        window.setScene(new Scene(pane));
        window.showAndWait();
        return returnedData;
    }
}
