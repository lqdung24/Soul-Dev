module com.game.itgame {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires java.desktop;
    requires annotations;
    requires jdk.incubator.vector;

    opens com.game.itgame.controller to javafx.fxml;
    opens com.game.itgame.gameStart to javafx.fxml;
    opens com.game.itgame.entity to javafx.fxml;
    opens com.game.itgame.eventHandle to javafx.fxml;
    exports com.game.itgame.controller;
    exports com.game.itgame.gameStart;
    exports com.game.itgame.entity;
    exports com.game.itgame.eventHandle;
}