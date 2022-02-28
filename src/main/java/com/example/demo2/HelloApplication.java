package com.example.demo2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("TibiaMarket");
        stage.setScene(scene);
        stage.setX(10);
        stage.setY(10);
        stage.show();
        stage.setResizable(false);
        stage.setAlwaysOnTop(true);
    }

    public static void main(String[] args) throws AWTException {
        launch();


    }
}