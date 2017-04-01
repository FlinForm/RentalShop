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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Created by Pavel Davydenko on 01.04.2017.
 */
public class ClientsWindow {
    private Shop shop;
    Clients clients;
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

    public Scene display(){


        BorderPane borderPane = new BorderPane();
        borderPane.setTop(addTopHBox());
        borderPane.setBottom(addBottomHBox());
        borderPane.setLeft(addLeftVBox());
        borderPane.setCenter(addCenterView());


        Scene scene = new Scene(borderPane);
        return scene;
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
        removeClientButton.setOnAction(e -> {
            clients.getClients().remove(view.getSelectionModel().getSelectedItem());
            view.setItems(setOList());
        });


        hBox.getChildren().addAll(addClientButton, removeClientButton);

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
        firstItemColumn.setMinWidth(130);
        firstItemColumn.setCellValueFactory(new PropertyValueFactory<>("firstItem"));

        TableColumn<SportEquipment, String> secondItemColumn = new TableColumn<>("Rented item");
        secondItemColumn.setMinWidth(130);
        secondItemColumn.setCellValueFactory(new PropertyValueFactory<>("secondItem"));

        TableColumn<SportEquipment, String> thirdItemColumn = new TableColumn<>("Rented item");
        thirdItemColumn.setMinWidth(130);
        thirdItemColumn.setCellValueFactory(new PropertyValueFactory<>("thirdItem"));


        view.getColumns().addAll(nameColumn, surnameColumn, passportColumn, firstItemColumn, secondItemColumn,
                thirdItemColumn);

        view.setItems(setOList());
        return view;
    }

    private ObservableList<Renter> setOList() {
        list.clear();
        if (clients.getClients() == null) {
            System.out.println(units.getRentedUnits() == null);
            return list;
        }
        list.addAll(clients.getClients());
        return list;
    }

    private ObservableList<Renter> setOList(Category category) {
        /*list.clear();
        if (clients.getClients() == null) {
            return list;
        }
        for (Map.Entry entry : units.getRentedUnits().entrySet()) {
            SportEquipment equipment = (SportEquipment) entry.getKey();
            if (equipment.getCategory().equals(category)) {
                list.add((SportEquipment) entry.getKey());
            }
        }*/
        return list;
    }

    private VBox addLeftVBox() {
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(25));
        vBox.setSpacing(20);

        Text titleText = new Text();
        titleText.setText("Rented Inventory:");
        titleText.setFont(new Font("Arial", 14));

        Hyperlink allGoods = new Hyperlink("All goods");
        //allGoods.setOnAction(e -> view.setItems(setOList()));
        allGoods.setVisited(true);

        Hyperlink bicycles = new Hyperlink("Bicycles");
        //bicycles.setOnAction(e -> view.setItems(setOList(Category.BICYCLE)));
        bicycles.setVisited(true);

        Hyperlink skates = new Hyperlink("Skates");
        //skates.setOnAction(e -> view.setItems(setOList(Category.SKATES)));
        skates.setVisited(true);

        Hyperlink rollerSkates = new Hyperlink("Roller skates");
        //rollerSkates.setOnAction(e -> view.setItems(setOList(Category.ROLLER_SKATES)));
        rollerSkates.setVisited(true);

        Hyperlink snowboards = new Hyperlink("Snowboards");
        //snowboards.setOnAction(e -> view.setItems(setOList(Category.SNOWBOARD)));
        snowboards.setVisited(true);

        Hyperlink skiing = new Hyperlink("Skiing");
        //skiing.setOnAction(event -> view.setItems(setOList(Category.SKIING)));
        skiing.setVisited(true);

        vBox.getChildren().addAll(titleText, allGoods, bicycles, skates, rollerSkates, snowboards, skiing);
        return vBox;
    }

    private void save() {
        parser.writeData("src/main/resources/equipment.xml", shop);
        parser.writeData("src/main/resources/rentedunits.xml", units);
        parser.writeData("src/main/resources/clients.xml", clients);
    }
}
