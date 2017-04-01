package com.rentalshop.view;

import com.rentalshop.controller.Controller;
import com.rentalshop.model.Category;
import com.rentalshop.model.Shop;
import com.rentalshop.model.SportEquipment;
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

import java.util.Map;

/**
 * Created by Pavel Davydenko on 01.04.2017.
 */
public class ClientsWindow {
    private Shop shop;
    private TableView view;
    private ObservableList<SportEquipment> list;

    public ClientsWindow(Shop shop) {
        this.shop = shop;
        view = new TableView();
        list = FXCollections.observableArrayList();

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


        Button removeClientButton = new Button();
        removeClientButton.setText("Remove client");
        removeClientButton.setPrefSize(100, 25);



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
        surnameColumn.setMinWidth(200);
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));

        TableColumn<SportEquipment, String> passportColumn = new TableColumn<>("Passport");
        passportColumn.setMinWidth(100);
        passportColumn.setCellValueFactory(new PropertyValueFactory<>("passport"));

        TableColumn<SportEquipment, Integer> ageColumn = new TableColumn<>("Age");
        ageColumn.setMinWidth(100);
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));

        TableColumn<SportEquipment, String> firstItemColumn = new TableColumn<>("Rented item");
        firstItemColumn.setMinWidth(100);
        firstItemColumn.setCellValueFactory(new PropertyValueFactory<>("age"));

        TableColumn<SportEquipment, String> secondItemColumn = new TableColumn<>("Rented item");
        secondItemColumn.setMinWidth(100);
        secondItemColumn.setCellValueFactory(new PropertyValueFactory<>("age"));

        TableColumn<SportEquipment, String> thirdItemColumn = new TableColumn<>("Rented item");
        thirdItemColumn.setMinWidth(100);
        thirdItemColumn.setCellValueFactory(new PropertyValueFactory<>("age"));


        view.getColumns().addAll(nameColumn, surnameColumn, passportColumn, thirdItemColumn);

        view.setItems(getOList());
        return view;
    }

    private ObservableList<SportEquipment> getOList() {
        list.clear();
        for (Map.Entry entry : shop.getGoods().entrySet()) {
            list.add((SportEquipment) entry.getKey());
        }
        return list;
    }

    private ObservableList<SportEquipment> getOList(Category category) {
        list.clear();
        for (Map.Entry entry : shop.getGoods().entrySet()) {
            SportEquipment equipment = (SportEquipment) entry.getKey();
            if (equipment.getCategory().equals(category)) {
                list.add((SportEquipment) entry.getKey());
            }
        }
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
        //allGoods.setOnAction(e -> view.setItems(getOList()));
        allGoods.setVisited(true);

        Hyperlink bicycles = new Hyperlink("Bicycles");
        //bicycles.setOnAction(e -> view.setItems(getOList(Category.BICYCLE)));
        bicycles.setVisited(true);

        Hyperlink skates = new Hyperlink("Skates");
        //skates.setOnAction(e -> view.setItems(getOList(Category.SKATES)));
        skates.setVisited(true);

        Hyperlink rollerSkates = new Hyperlink("Roller skates");
        //rollerSkates.setOnAction(e -> view.setItems(getOList(Category.ROLLER_SKATES)));
        rollerSkates.setVisited(true);

        Hyperlink snowboards = new Hyperlink("Snowboards");
        //snowboards.setOnAction(e -> view.setItems(getOList(Category.SNOWBOARD)));
        snowboards.setVisited(true);

        Hyperlink skiing = new Hyperlink("Skiing");
        //skiing.setOnAction(event -> view.setItems(getOList(Category.SKIING)));
        skiing.setVisited(true);

        vBox.getChildren().addAll(titleText, allGoods, bicycles, skates, rollerSkates, snowboards, skiing);
        return vBox;
    }

    private void save() {

    }
}
