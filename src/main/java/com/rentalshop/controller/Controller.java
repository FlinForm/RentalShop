package com.rentalshop.controller;

import com.rentalshop.dataparsing.XmlDataParser;
import com.rentalshop.model.Clients;
import com.rentalshop.model.RentedUnits;
import com.rentalshop.model.Shop;
import com.rentalshop.view.ClientsWindow;
import com.rentalshop.view.MainWindow;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Controller class
 */
public class Controller extends Application {
    private static Stage window;
    private static MainWindow mainWindow;
    private static ClientsWindow clientsWindow;

    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        XmlDataParser parser = new XmlDataParser();
        RentedUnits units = new RentedUnits();
        Clients clients = new Clients();
        Shop shop = new Shop();
        units = (RentedUnits) parser.getData("src/main/resources/rentedunits.xml", units);
        clients = (Clients) parser.getData("src/main/resources/clients.xml", clients);
        shop = (Shop) parser.getData("src/main/resources/equipment.xml", shop);
        mainWindow = new MainWindow(shop, parser, units, clients);
        clientsWindow = new ClientsWindow(shop, parser, units, clients);


        primaryStage.setScene(mainWindow.display());
        primaryStage.setResizable(false);
        primaryStage.setTitle("Rental Shop");
        primaryStage.show();
    }

    public static void setMainScene() {
        window.setScene(mainWindow.display());
    }

    public static void setClientScene() {
        window.setScene(clientsWindow.display());
    }
}
