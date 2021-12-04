module io.github.riniwtz {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires net.dv8tion.jda;

    opens io.github.riniwtz to javafx.fxml;
    exports io.github.riniwtz;
    exports io.github.riniwtz.controllers;
    opens io.github.riniwtz.controllers to javafx.fxml;
}
