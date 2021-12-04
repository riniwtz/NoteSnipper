package io.github.riniwtz.controllers;

import io.github.riniwtz.Main;
import io.github.riniwtz.auth.Authentication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AuthenticationController {
    @FXML
    public TextField TOKEN_TEXT_FIELD = new TextField();
    public Button SIGN_IN_BUTTON = new Button();
    public CheckBox KEEP_SIGNED_IN_CHECKBOX = new CheckBox();

    // Authentication
    public void signToken(ActionEvent event) {
        try {
            Authentication authentication = new Authentication();
            authentication.build(TOKEN_TEXT_FIELD.getText());
            Stage stage = (Stage) SIGN_IN_BUTTON.getScene().getWindow();
            if (authentication.getBotStatus())
                stage.setScene(new Scene(Main.loadFXML("main")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Keep Signed In Option
    public void setKeepSignedIn(ActionEvent event) {
        Authentication.keepSignedIn = KEEP_SIGNED_IN_CHECKBOX.isSelected();
    }
}
