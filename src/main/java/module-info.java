module io.github.riniwtz {
    requires java.desktop;
    requires net.dv8tion.jda;
    requires javafx.base;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.controls;

    opens io.github.riniwtz to javafx.fxml;
    exports io.github.riniwtz;
    exports io.github.riniwtz.controllers;
    opens io.github.riniwtz.controllers to javafx.fxml;
}
