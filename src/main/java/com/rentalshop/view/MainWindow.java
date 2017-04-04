package com.rentalshop.view;

import com.rentalshop.controller.Controller;
import com.rentalshop.dataparsing.XmlDataParser;
import com.rentalshop.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.Map;


/**
 * The client window with clients and rented sport equipment.
 * In this class I used Border pane to place elements
 * in the window. Methods: "addTopHBox()", "addBottomHBox()",
 * "addCenterView()", addLeftVBox() are describing boxes that placed in
 * different parts of BorderPane.
 */
public class MainWindow {
    private Shop shop;
    private Clients clients;
    private TableView view;
    private XmlDataParser parser;
    private ObservableList<SportEquipment> list;
    private RentedUnits units;
    private boolean switchedToRentedItems;

    public MainWindow(Shop shop, XmlDataParser parser, RentedUnits units, Clients clients) {
        this.shop = shop;
        view = new TableView();
        list = FXCollections.observableArrayList();
        this.parser = parser;
        this.units = units;
        this.clients = clients;
    }

    // Placing elements on main scene and displaying a scene.
    public Scene display() {
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(addTopHBox());
        borderPane.setLeft(addLeftVBox());
        borderPane.setBottom(addBottomHBox());
        borderPane.setCenter(addCenterView());
        return new Scene(borderPane);
    }

    private HBox addTopHBox(){
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(25, 80, 25, 160));
        hBox.setSpacing(20);
        hBox.setStyle("-fx-background-color: #4a4a4a");


        Button addItemButton = new Button();
        addItemButton.setText("Add item");
        addItemButton.setPrefSize(100, 25);
        addItemButton.setOnAction(e -> {
            SportEquipment equipment = AddNewItemWindow.display();
            if (equipment != null) {
                shop.addNewItem(equipment);
                view.setItems(setOList(shop.getGoods()));
            }
        });

        Button removeItemButton = new Button();
        removeItemButton.setText("Remove item");
        removeItemButton.setPrefSize(100, 25);
        removeItemButton.setOnAction(e -> {
            shop.removeItem((SportEquipment) view.getSelectionModel().getSelectedItem());
            view.setItems(setOList(shop.getGoods()));
        });

        Button findItemButton = new Button();
        findItemButton.setText("Find Item");
        findItemButton.setPrefSize(100, 25);
        findItemButton.setOnAction(e -> findItem());

        Button rentItemButton = new Button();
        rentItemButton.setText("Rent item");
        rentItemButton.setPrefSize(100, 25);
        rentItemButton.setOnAction(e -> rentItem((SportEquipment) view.getSelectionModel().getSelectedItem()));

        ChoiceBox<String> choiceBox = new ChoiceBox();
        choiceBox.setPrefWidth(100);
        choiceBox.getItems().addAll( "Available", "Rented");
        choiceBox.setValue("Available");
        switchedToRentedItems = false;
        choiceBox.setOnAction(e -> {
            if (choiceBox.getValue().equals("Available")) {
                setOList(shop.getGoods());
                switchedToRentedItems = false;
                removeItemButton.setDisable(false);
                addItemButton.setDisable(false);
                rentItemButton.setDisable(false);
            } else {
                setOList(units.getRentedUnits());
                switchedToRentedItems = true;
                removeItemButton.setDisable(true);
                addItemButton.setDisable(true);
                rentItemButton.setDisable(true);
            }
        });

        hBox.getChildren().addAll(addItemButton, removeItemButton, findItemButton, choiceBox, rentItemButton);

        return hBox;
    }

    private VBox addLeftVBox() {
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(25));
        vBox.setSpacing(20);

        Text titleText = new Text();
        titleText.setText("Sport Inventory:   ");
        titleText.setFont(new Font("Arial", 14));

        Hyperlink allGoods = new Hyperlink("All goods");
        allGoods.setOnAction(e -> {
            if (switchedToRentedItems) {
                view.setItems(setOList(units.getRentedUnits()));
            } else {
                view.setItems(setOList(shop.getGoods()));
            }
        });
        allGoods.setVisited(true);

        Hyperlink bicycles = new Hyperlink("Bicycles");
        bicycles.setOnAction(e -> view.setItems(setOList(Category.BICYCLE)));
        bicycles.setVisited(true);

        Hyperlink skates = new Hyperlink("Skates");
        skates.setOnAction(e -> view.setItems(setOList(Category.SKATES)));
        skates.setVisited(true);

        Hyperlink rollerSkates = new Hyperlink("Roller skates");
        rollerSkates.setOnAction(e -> view.setItems(setOList(Category.ROLLER_SKATES)));
        rollerSkates.setVisited(true);

        Hyperlink snowboards = new Hyperlink("Snowboards");
        snowboards.setOnAction(e -> view.setItems(setOList(Category.SNOWBOARD)));
        snowboards.setVisited(true);

        Hyperlink skiing = new Hyperlink("Skiing");
        skiing.setOnAction(event -> view.setItems(setOList(Category.SKIING)));
        skiing.setVisited(true);

        vBox.getChildren().addAll(titleText, allGoods, bicycles, skates, rollerSkates, snowboards, skiing);
        return vBox;
    }

    private HBox addBottomHBox() {
        HBox rightHBox = new HBox();
        rightHBox.setPadding(new Insets(25, 25, 25, 25));
        rightHBox.setSpacing(30);
        rightHBox.setStyle("-fx-background-color: #4a4a4a");
        rightHBox.setAlignment(Pos.CENTER_RIGHT);

        HBox leftHBOX = new HBox();
        leftHBOX.setPadding(new Insets(25,25,25,20));
        leftHBOX.setSpacing(30);
        leftHBOX.setStyle("-fx-background-color: #4a4a4a");
        leftHBOX.setAlignment(Pos.CENTER_LEFT);

        HBox mergeHBox = new HBox();
        mergeHBox.setSpacing(350);
        mergeHBox.setStyle("-fx-background-color: #4a4a4a");
        mergeHBox.setAlignment(Pos.CENTER);

        Button goodsButton = new Button();
        goodsButton.setText("Sport Inventory");
        goodsButton.setPrefSize(120, 25);

        Button clientButton = new Button();
        clientButton.setText("Clients");
        clientButton.setPrefSize(120, 25);
        clientButton.setOnAction(e -> Controller.setClientScene());


        Button saveButton = new Button();
        saveButton.setText("Save");
        saveButton.setPrefSize(75, 25);
        saveButton.setOnAction(e -> save());

        Button exitButton = new Button();
        exitButton.setText("Exit");
        exitButton.setPrefSize(75, 25);
        exitButton.setOnAction(e -> {
            save();
            System.exit(0);
        });

        rightHBox.getChildren().addAll(saveButton, exitButton);
        leftHBOX.getChildren().addAll(goodsButton, clientButton);
        mergeHBox.getChildren().addAll(leftHBOX, rightHBox);

        return mergeHBox;
    }

    private TableView addCenterView() {
        view = new TableView();
        view.setPrefSize(600, 450);

        TableColumn<SportEquipment, Category> categoryColumn = new TableColumn<>("Category");
        categoryColumn.setMinWidth(100);
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));

        TableColumn<SportEquipment, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setMinWidth(200);
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<SportEquipment, Integer> priceColumn = new TableColumn<>("Price");
        priceColumn.setMinWidth(100);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<SportEquipment, Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setMinWidth(100);
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));


        view.getColumns().addAll(categoryColumn, titleColumn, priceColumn, quantityColumn);

        view.setItems(setOList(shop.getGoods()));
        return view;
    }

    private ObservableList<SportEquipment> setOList(Map<SportEquipment, Integer> map) {
        list.clear();
        if (map == null) {
            return list;
        }
        for (Map.Entry entry : map.entrySet()) {
            list.add((SportEquipment) entry.getKey());
        }
        return list;
    }

    private ObservableList<SportEquipment> setOList(Category category) {
        list.clear();
        if (switchedToRentedItems) {
            if (units.getRentedUnits() == null) {
                return list;
            }
            for (Map.Entry entry : units.getRentedUnits().entrySet()) {
                SportEquipment equipment = (SportEquipment) entry.getKey();
                if (equipment.getCategory().equals(category)) {
                    list.add((SportEquipment) entry.getKey());
                }
            }
        } else {
            if (shop.getGoods() == null) {
                return list;
            }
            for (Map.Entry entry : shop.getGoods().entrySet()) {
                SportEquipment equipment = (SportEquipment) entry.getKey();
                if (equipment.getCategory().equals(category)) {
                    list.add((SportEquipment) entry.getKey());
                }
            }
        }
        return list;
    }

    private void save() {
        parser.writeData("src/main/resources/equipment.xml", shop);
        parser.writeData("src/main/resources/rentedunits.xml", units);
        parser.writeData("src/main/resources/clients.xml", clients);
    }

    private void rentItem(SportEquipment equipment) {
        Object[] data = RentItemWindow.display(clients.getClients());
        if (equipment == null) {
            AlertBox.displayMessage("Please, select item you want to rent!");
            return;
        }
        if (data[0] == null || data[1] == null) {
            return;
        }
        Renter renter = (Renter) data[0];
        int quantityRentItems = (int) data[1];
        if (quantityRentItems > equipment.getQuantity()) {
            AlertBox.displayMessage("You trying to rent so much items... :)");
            return;
        } else if (quantityRentItems > renter.getAvailableItems()) {
            AlertBox.displayMessage("You can't rent more then 3 items!");
            return;
        }
        for (int i = 0; i < quantityRentItems; i++) {
            renter.setItem(equipment);
            equipment.decQuantity();
        }

        units.addUnit(equipment, quantityRentItems);
        shop.refreshItem(equipment);
        setOList(shop.getGoods());
    }

    private void findItem() {
        String itemToFind = FindItemWindow.display();
        for (SportEquipment aList : list) {
            if (aList.getTitle().equalsIgnoreCase(itemToFind)) {
                view.getSelectionModel().select(aList);
            }
        }
    }

}
