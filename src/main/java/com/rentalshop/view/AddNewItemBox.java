package com.rentalshop.view;

import com.rentalshop.model.Category;
import com.rentalshop.model.SportEquipment;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by Pavel Davydenko on 31.03.2017.
 */
public class AddNewItemBox {
    private static SportEquipment equipment;

    public static SportEquipment display() {
        equipment = new SportEquipment();

        // Creating window
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Add new equipment");
        window.setMinWidth(200);
        window.setMinHeight(300);
        window.setResizable(false);

        // Creating window elements

        Label label1 = new Label();
        label1.setText("Category");

        Label label2 = new Label();
        label2.setText("Title");

        Label label3 = new Label();
        label3.setText("Price");

        Label label4 = new Label();
        label4.setText("Quantity");

        ChoiceBox<Category> category = new ChoiceBox<>();
        category.getItems().addAll(Category.BICYCLE, Category.ROLLER_SKATES, Category.SKATES, Category.SKIING, Category.SNOWBOARD);
        category.setValue(Category.BICYCLE);

        TextField title = new TextField();
        TextField price = new TextField();
        TextField quantity = new TextField();



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
        GridPane.setConstraints(label4, 0, 3);
        GridPane.setConstraints(yesButton, 0, 4);
        GridPane.setConstraints(category, 1, 0);
        GridPane.setConstraints(title, 1, 1);
        GridPane.setConstraints(price, 1, 2);
        GridPane.setConstraints(quantity, 1, 3);
        GridPane.setConstraints(noButton, 1, 4);

        pane.getChildren().addAll(label1, label2, label3, label4, category, title, price, quantity, yesButton, noButton);


        yesButton.setOnAction(event -> {
            equipment.setCategory(category.getValue());
            equipment.setTitle(title.getText());
            try {
                equipment.setPrice(Integer.parseInt(price.getText()));
                equipment.setQuantity(Integer.parseInt(quantity.getText()));
                if ("".equals(equipment.getTitle()) || "".equals(equipment.getPrice()) || "".equals(equipment.getQuantity())) {
                    AllertBox.display("You entered wrong data!");
                    window.close();
                }
            } catch (NumberFormatException e) {
                AllertBox.display("You entered wrong data!");
                window.close();
            }

            window.close();
        });
        noButton.setOnAction(event -> {
            equipment = null;
            window.close();
        });

        Scene scene = new Scene(pane);
        window.setScene(scene);
        window.showAndWait();
        return equipment;
    }
}
