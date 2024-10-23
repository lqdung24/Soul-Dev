package com.game.itgame.entity;

import com.game.itgame.UI_things.UI;
import com.game.itgame.map.Map;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public abstract class Entity implements UI {
    double X = 0, Y = 0; // tọa độ tuyệt đối của vật
    double height, witdh;
    int dame = 1;
    int Hp = 10;
    Image image; // lưu các khung ảnh, sau biến tấu thêm
    public Rectangle border; // khung của thực thể, dùng để kiểm tra va chạm
    int numState;
    int currentState;
    Map map;
    Canvas canvas;
    abstract protected void getResource();
}
