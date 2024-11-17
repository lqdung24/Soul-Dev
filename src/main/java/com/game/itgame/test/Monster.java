package com.game.itgame.test;

class Monster {
    private double x, y;
    static final int MONSTER_SIZE = 16;

    public Monster(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() { return x; }
    public double getY() { return y; }

    // Di chuyển quái vật (có thể thêm logic di chuyển tùy ý)
    public void moveTowards(double targetX, double targetY) {
        double dx = targetX - x;
        double dy = targetY - y;
        double distance = Math.sqrt(dx * dx + dy * dy);
        double speed = 1.5;

        if (distance > 0) {
            x += speed * (dx / distance);
            y += speed * (dy / distance);
        }
    }
}
