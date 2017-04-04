package com.rentalshop.view;

import com.rentalshop.controller.Controller;
import com.rentalshop.dataparsing.XmlDataParser;
import com.rentalshop.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * The client window with clients and rented sport equipment.
 * In this class I used Border pane to place elements
 * in the window. Methods: "addTopHBox()", "addBottomHBox()",
 * "addCenterView()" are describing boxes that placed in
 * different parts of BorderPane.
 */
public class ClientsWindow {
    private Shop shop;
    private Clients clients;
    private TableView view;
    private XmlDataParser parser;
    private RentedUnits units;
    private ObservableList<Renter> list;

    public ClientsWindow(Shop shop, XmlDataParser parser, RentedUnits units, Clients clients) {
        this.shop = shop;
        view = new TableView();
        list = FXCollections.observableArrayList();
        this.parser = parser;
        this.units = units;
        this.clients = clients;

    }

    // Placing elements on main scene and displaying a scene.
    public Scene display(){
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(addTopHBox());
        borderPane.setBottom(addBottomHBox());
        borderPane.setCenter(addCenterView());
        return new Scene(borderPane);
    }

    private HBox addTopHBox(){
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(25, 100, 25, 160));
        hBox.setSpacing(50);
        hBox.setStyle("-fx-background-color: #4a4a4a");


        Button addClientButton = new Button();
        addClientButton.setText("Add client");
        addClientButton.setPrefSize(100, 25);
        addClientButton.setOnAction(e -> {
            Renter renter = AddClientWindow.display();
            if (renter != null) {
                clients.AddClient(renter);
                view.setItems(setOList());
            }
        });

        Button removeClientButton = new Button();
        removeClientButton.setText("Remove client");
        removeClientButton.setPrefSize(100, 25);
        removeClientButton.setOnAction(e -> removeClient());

        Button returnItemsButton = new Button();
        returnItemsButton.setText("Return items");
        returnItemsButton.setPrefSize(100, 25);
        returnItemsButton.setOnAction(e -> returnItem((Renter) view.getSelectionModel().getSelectedItem()));


        hBox.getChildren().addAll(addClientButton, removeClientButton, returnItemsButton);

        return hBox;
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
        goodsButton.setOnAction(e -> Controller.setMainScene());

        Button clientButton = new Button();
        clientButton.setText("Clients");
        clientButton.setPrefSize(120, 25);


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

        TableColumn<SportEquipment, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(100);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<SportEquipment, String> surnameColumn = new TableColumn<>("Surname");
        surnameColumn.setMinWidth(120);
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));

        TableColumn<SportEquipment, String> passportColumn = new TableColumn<>("Passport");
        passportColumn.setMinWidth(100);
        passportColumn.setCellValueFactory(new PropertyValueFactory<>("passport"));

        TableColumn<SportEquipment, String> firstItemColumn = new TableColumn<>("Rented item");
        firstItemColumn.setMinWidth(170);
        firstItemColumn.setCellValueFactory(new PropertyValueFactory<>("firstItem"));

        TableColumn<SportEquipment, String> secondItemColumn = new TableColumn<>("Rented item");
        secondItemColumn.setMinWidth(170);
        secondItemColumn.setCellValueFactory(new PropertyValueFactory<>("secondItem"));

        TableColumn<SportEquipment, String> thirdItemColumn = new TableColumn<>("Rented item");
        thirdItemColumn.setMinWidth(170);
        thirdItemColumn.setCellValueFactory(new PropertyValueFactory<>("thirdItem"));


        view.getColumns().addAll(nameColumn, surnameColumn, passportColumn, firstItemColumn, secondItemColumn,
                thirdItemColumn);

        view.setItems(setOList());
        return view;
    }

    private ObservableList<Renter> setOList() {
        list.clear();
        if (clients.getClients() == null) {
            return list;
        }
        list.addAll(clients.getClients());
        return list;
    }

    private void removeClient() {
        for (int i = 0; i < clients.getClients().size(); i++) {
            if (clients.getClients().get(i).equals(view.getSelectionModel().getSelectedItem())) {
                if (clients.getClients().get(i).hasRentedItems()) {
                    AlertBox.displayMessage("You can't remove client with rented inventory!");
                    return;
                }
            }
        }
        clients.getClients().remove(view.getSelectionModel().getSelectedItem());
        view.setItems(setOList());
    }

    private void returnItem(Renter renter) {
        if (renter == null) {
            AlertBox.displayMessage("Please, select a renter!");
            return;
        }
        String[] returningItems = ReturnItemWindow.display(renter);

        if (returningItems[0] != null) {
            renter.setFirstItem(null);
            renter.incAvailableItems();
            units.removeUnit(returningItems[0]);
            shop.returnRentedItem(shop.getUnitForName(returningItems[0]));
        }
        if (returningItems[1] != null) {
            renter.setSecondItem(null);
            renter.incAvailableItems();
            units.removeUnit(returningItems[1]);
            units.removeUnit(returningItems[1]);
            shop.returnRentedItem(shop.getUnitForName(returningItems[1]));
        }
        if (returningItems[2] != null) {
            renter.setThirdItem(null);
            renter.incAvailableItems();
            units.removeUnit(returningItems[2]);
            units.removeUnit(returningItems[2]);
            shop.returnRentedItem(shop.getUnitForName(returningItems[2]));

        }
        setOList();
    }

    private void save() {
        parser.writeData("src/main/resources/equipment.xml", shop);
        parser.writeData("src/main/resources/rentedunits.xml", units);
        parser.writeData("src/main/resources/clients.xml", clients);
    }
}
