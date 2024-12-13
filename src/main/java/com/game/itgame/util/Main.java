package com.game.itgame.util;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("apple");
        list.add("banana");
        list.add("skip");
        list.add("cherry");

        list.forEach(e -> {
            if (e.equals("skip")) {
                return; // Bỏ qua phần tử "skip"
            }
            System.out.println(e); // In các phần tử khác
        });
    }
}
