package com.game.itgame.map;

public class Room {
    public int top, left, right, bottom;
    public Room(int left, int right, int top, int bottom) {
        this.top = top;
        this.left = left;
        this.right = right;
        this.bottom = bottom;
    }
    public boolean checkInRoom(int x, int y) {
        return left <= x && x <= right && top <= y && y <= bottom;
    }
}
