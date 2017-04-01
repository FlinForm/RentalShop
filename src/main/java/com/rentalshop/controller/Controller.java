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
 * Created by Pavel Davydenko on 30.03.2017.
 */
public class Controller extends Application {
    private static Stage window;
    private static MainWindow mainWindow;
    private static ClientsWindow clientsWindow;
    private XmlDataParser parser;
    private RentedUnits units;
    private Clients clients;
    private Shop shop;


    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        parser = new XmlDataParser();
        units = new RentedUnits();
        clients = new Clients();
        shop = new Shop();
        units = new RentedUnits();
        clients = new Clients();
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
