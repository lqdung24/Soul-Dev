package com.game.itgame.map;

import javafx.scene.image.Image;

import java.util.Objects;

public class MapImage {
    protected Image[] mapImageIndex = {
            new Image(Objects.requireNonNull(MapImage.class.getResourceAsStream("/images/map/Map/0.png"))),
            new Image(Objects.requireNonNull(MapImage.class.getResourceAsStream("/images/map/Map/1.png"))),
            new Image(Objects.requireNonNull(MapImage.class.getResourceAsStream("/images/map/Map/2.png"))),
            new Image(Objects.requireNonNull(MapImage.class.getResourceAsStream("/images/map/Map/3.png"))),
            new Image(Objects.requireNonNull(MapImage.class.getResourceAsStream("/images/map/Map/4.png"))),
            new Image(Objects.requireNonNull(MapImage.class.getResourceAsStream("/images/map/Map/5.png"))),
            new Image(Objects.requireNonNull(MapImage.class.getResourceAsStream("/images/map/Map/6.png"))),
            new Image(Objects.requireNonNull(MapImage.class.getResourceAsStream("/images/map/Map/7.png"))),
            new Image(Objects.requireNonNull(MapImage.class.getResourceAsStream("/images/map/Map/8.png"))),
    };
}
