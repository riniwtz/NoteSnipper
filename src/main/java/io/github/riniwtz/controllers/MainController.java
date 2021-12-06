package io.github.riniwtz.controllers;

import io.github.riniwtz.audio.Audio;
import io.github.riniwtz.auth.Authentication;
import io.github.riniwtz.subjects.Subjects;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    public Button SCREENSHOT_BUTTON = new Button();
    public ChoiceBox<String> SUBJECT_SELECTION_CHOICE_BOX = new ChoiceBox<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Subjects subjects = new Subjects();
        SUBJECT_SELECTION_CHOICE_BOX.setValue("Select");
        SUBJECT_SELECTION_CHOICE_BOX.getItems().addAll(subjects.getSubjects());
        SUBJECT_SELECTION_CHOICE_BOX.setOnAction(this::enableScreenshot);
        SCREENSHOT_BUTTON.setDisable(true);
    }

    public void enableScreenshot(ActionEvent event) {
        SCREENSHOT_BUTTON.setDisable(false);
    }

    // Screenshot Button
    public void screenshot() {
        // Gets the Main Window
        Stage stage = (Stage) SCREENSHOT_BUTTON.getScene().getWindow();
        stage.setIconified(true);

        // Delays for a second to prevent capturing window
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) { e.printStackTrace(); }

        // Date API
        LocalDateTime LOCAL_DATE_TIME = LocalDateTime.now();
        LOCAL_DATE_TIME.format(DateTimeFormatter.ofPattern("yyyy-MM-dd H:mm:ss a"));

        try {
            Robot robot = new Robot();

            final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
            final BufferedImage SCREENSHOT = robot.createScreenCapture(new Rectangle(0,
                    (int) (SCREEN_SIZE.getHeight() - SCREEN_SIZE.getHeight() * 0.9635416666666667),
                    (int) SCREEN_SIZE.getWidth(), (int) (SCREEN_SIZE.getHeight() * 0.85)));
            Graphics2D graphics = SCREENSHOT.createGraphics();
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics.setColor(Color.WHITE);
            graphics.fillRect(25, 25, 180, 40);
            graphics.setColor(Color.RED);
            graphics.drawString(String.valueOf(LOCAL_DATE_TIME), 50, 50);

            // Initializing screenshot location
            final File SCREENSHOT_LOCATION = new File(Authentication.FOLDER_LOCATION + "/screenshot.png");
            ImageIO.write(SCREENSHOT, "png", SCREENSHOT_LOCATION);

            // Discord API
            Subjects subjects = new Subjects();
            Guild server = Authentication.jda.getGuildsByName("St. Mary's Academy", true).get(0);
            TextChannel channel = server.getTextChannelsByName(subjects.getSubject().get(SUBJECT_SELECTION_CHOICE_BOX.getValue()), true).get(0);
            channel.sendFile(SCREENSHOT_LOCATION).queue();

            // Play Screenshot Audio
            new Audio().play("screenshot", false);
        } catch (AWTException | IOException e) {
            e.printStackTrace();
        }
    }
}
