package com.game.itgame.test;

class MultipleCatch1 {
    public static void main(String args[])
    {
        try {
            String num = "12";
            int numValue = Integer.parseInt(num);
            System.out.println("Dien tich hv la: "
                    + numValue * numValue);
        } catch(ArrayIndexOutOfBoundsException e1) {
            System.out.println("Hay nhap canh cua hv!");
        } catch(NumberFormatException e2){
            System.out.println("Hay nhap 1 so!");
        }
    }
}