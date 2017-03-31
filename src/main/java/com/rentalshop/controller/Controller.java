package com.rentalshop.controller;

import com.rentalshop.dataparsing.XmlDataParser;
import com.rentalshop.model.Shop;
import com.rentalshop.view.MainWindow;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Created by Pavel Davydenko on 30.03.2017.
 */
public class Controller extends Application {
    public void start(Stage primaryStage) throws Exception {
        XmlDataParser parser = new XmlDataParser();
        Shop shop = parser.getData("src/main/resources/equipment.xml");
        MainWindow mainWindow = new MainWindow(shop, parser);




        primaryStage.setScene(mainWindow.getMainWindow());
        primaryStage.setMinWidth(900);
        primaryStage.setMinHeight(600);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Rental Shop");
        primaryStage.show();
    }
}
