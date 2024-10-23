package com.game.itgame.entity;

import com.game.itgame.map.Map;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public abstract class Moveable extends Entity{
    double newX, newY;
    // tọa độ sau khi thử di chuyển, nếu di chuyển được thì sẽ update còn không thì quay trở lại
    // luôn update newX trước sau đấy gọi hàm updateLocation, nếu không lỗi bể mặt
    double ver = 5;
    final double COEFFICTION = 1 / Math.sqrt(2); // hệ số tốc độ khi di chuyển chéo, hôm nào chế thêm, giờ lười
    boolean isCrossMove = false; // kiểm tra có di chuyển chéo hay không, nếu có thì tốc độ nhân thêm với hệ số
    int dame = 1;
    int Hp = 10;
    public Rectangle border; // khung của thực thể, dùng để kiểm tra va chạm
    int direction; // hướng di chuyển, dùng để kiểm tra va chạm sau này
    Map map;

    void moveLeft(double ver) {
        this.newX = this.newX + ver;
    }

    void moveRight(double ver) {
        this.newX = this.newX + ver;
        System.out.println(this.X);
    }

    void moveUp(double ver) {
        this.newY = this.newY + ver;
    }

    void moveDown(double ver) {
        this.newY = this.newY + ver;
    }

    void updateLocation() {
        if (!isCollision()) {
            X = newX;
            Y = newY;
        } else { // nếu không thì quay lại vị trí ban đầu
            newX = X;
            newY = Y;
        }
    }

    // hàm kiểm tra va chạm với map sinh bởi chatGPT, không cần check đúng sai :)
    public boolean isCollision() {
        int colTopLeft = (int) (newX / map.TILE_SIZE);
        int rowTopLeft = (int) (newY / map.TILE_SIZE);

        int colBottomRight = (int) ((newX + border.getWidth()) / map.TILE_SIZE);
        int rowBottomRight = (int) ((newY + border.getHeight()) / map.TILE_SIZE);

        // In ra thông tin để kiểm tra
        //System.out.println("Top-left: (" + colTopLeft + ", " + rowTopLeft + "), Bottom-right: (" + colBottomRight + ", " + rowBottomRight + ")");

        // Kiểm tra xem vị trí mới có nằm trong giới hạn của bản đồ hay không
        if (rowTopLeft < 0 || rowBottomRight >= map.row || colTopLeft < 0 || colBottomRight >= map.col) {
            return true; // Nằm ngoài giới hạn, không cho phép di chuyển
        }

        // Kiểm tra loại ô tại vị trí các góc của thực thể
        if (map.map_matrix[rowTopLeft][colTopLeft] != 1 || // Góc trên trái
                map.map_matrix[rowTopLeft][colBottomRight] != 1 || // Góc trên phải
                map.map_matrix[rowBottomRight][colTopLeft] != 1 || // Góc dưới trái
                map.map_matrix[rowBottomRight][colBottomRight] != 1) { // Góc dưới phải
            return true; // Va chạm với tường
        }

        return false; // Không có va chạm, có thể di chuyển
    }
}
