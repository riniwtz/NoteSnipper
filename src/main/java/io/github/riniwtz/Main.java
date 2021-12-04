package io.github.riniwtz;

import io.github.riniwtz.auth.Authentication;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Main extends Application {

    public static final String WINDOW_TITLE = "NoteSnipper";

    @Override
    public void start(Stage stage) throws IOException {

        // Initializes Authentication
        Authentication authentication = new Authentication();
        authentication.initializeStart();

        // Scene Loader
        if (authentication.getBotStatus())
            stage.setScene(new Scene(loadFXML("main")));
        else
            stage.setScene(new Scene(loadFXML("authentication")));

        // Stage Configurations

        stage.setTitle(WINDOW_TITLE);
        stage.setResizable(false);
        stage.show();

        // Closes the application and resources when exiting the application
        stage.setOnCloseRequest(t -> {
            Platform.exit();
            System.exit(0);
        });
    }

    // Window Loader
    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}