module com.game.itgame {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires java.desktop;
    requires annotations;

    exports com.game.itgame.test;
    exports com.game.itgame.main;
    opens com.game.itgame.main to javafx.fxml;
    exports com.game.itgame.controller;
    opens com.game.itgame.controller to javafx.fxml;
}