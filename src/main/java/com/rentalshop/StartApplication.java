package com.rentalshop;

import com.rentalshop.controller.Controller;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Entry point into the application
 */
public class StartApplication extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        Controller controller = new Controller();
        controller.start(primaryStage);
    }
}
