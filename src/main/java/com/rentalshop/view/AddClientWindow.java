package com.rentalshop.view;

import com.rentalshop.model.Renter;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by Pavel Davydenko on 01.04.2017.
 */
public class AddClientWindow {
    private static Renter renter;

    public static Renter display() {
        renter = new Renter();

        // Creating window
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Add new client");
        window.setMinWidth(200);
        window.setMinHeight(250);
        window.setResizable(false);

        // Creating window elements

        Label label1 = new Label();
        label1.setText("Name:");

        Label label2 = new Label();
        label2.setText("Surname:");

        Label label3 = new Label();
        label3.setText("Passport:");

        TextField name = new TextField();
        TextField surname = new TextField();
        TextField passport = new TextField();


        Button yesButton = new Button();
        Button noButton = new Button();
        yesButton.setText("Add");
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
        GridPane.setConstraints(name, 1, 0);
        GridPane.setConstraints(surname, 1, 1);
        GridPane.setConstraints(passport, 1, 2);
        GridPane.setConstraints(noButton, 1, 3);

        pane.getChildren().addAll(label1, label2, label3, name, surname, passport, yesButton, noButton);


        yesButton.setOnAction(event -> {
            renter.setName(name.getText());
            renter.setSurname(surname.getText());
            renter.setPassport(passport.getText());
            renter.setAvailableItems(3);
            window.close();
        });
        noButton.setOnAction(event -> {
            renter = null;
            window.close();
        });

        window.setScene(new Scene(pane));
        window.showAndWait();
        return renter;
    }
}
