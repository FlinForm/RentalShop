package com.rentalshop.controller;

import com.rentalshop.dataparsing.XmlDataParser;
import com.rentalshop.model.Shop;
import com.rentalshop.view.ClientsWindow;
import com.rentalshop.view.MainWindow;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Created by Pavel Davydenko on 30.03.2017.
 */
public class Controller extends Application {
    private static Stage primaryStage;
    private static MainWindow mainWindow;
    private static ClientsWindow clientsWindow;

    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        XmlDataParser parser = new XmlDataParser();
        Shop shop = parser.getData("src/main/resources/equipment.xml");
        mainWindow = new MainWindow(shop, parser);
        clientsWindow = new ClientsWindow(shop);




        primaryStage.setScene(mainWindow.display());
        primaryStage.setResizable(false);
        primaryStage.setTitle("Rental Shop");
        primaryStage.show();
    }

    public static void setMainScene() {
        primaryStage.setScene(mainWindow.display());
    }

    public static void setClientScene() {
        primaryStage.setScene(clientsWindow.display());
    }
}
