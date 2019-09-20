package com.company;

public class Main {

    public static void main(String[] args) {

        int[][] array = new int[][]{{-1, 2, 3, 4}, {2, 3, 4, 4}, {3, 3, 3, 3}, {2, 1, 2, 3}};


        System.out.println("1st step");
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("2nd step");

        int waterDepth = -1;
        int[][] newArray = new int[4][4];

        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1; j++) {

                int nextI = array[i + 1][j];
                int nextJ = array[i][j + 1];


                if (Math.abs(waterDepth) < nextJ && (nextJ == nextI)) {
                    waterDepth--;
                    newArray[i][j] = waterDepth;
                }

            }
        }


        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if(newArray[i][j] != array[i][j]){
                    System.out.print(newArray[i][j] + " ");
                }else {
                    System.out.print(array[i][j] + " ");

                }
            }
            System.out.println();
        }

    }
}

