package com.company;

public class Main {

    public static void main(String[] args) {

        int[][] array = new int[][]{{-2, -2, 3, 4}, {-2, 3, 4, 4}, {3, 3, 3, 3}, {2, 1, 2, 3}};


        System.out.println("1st step");
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("2nd step");


        int waterDepth = -2;


        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 1; j++) {




                if (Math.abs(waterDepth) < array[i][j + 1] && (array[i][j + 1] == array[i + 1][j])) {
                    waterDepth--;
                    array[i][j] = waterDepth;
                } else if (Math.abs(waterDepth) == array[i][j + 1] && (array[i][j + 1] == array[i + 1][j])) {
                    array[i + 1][j] *= (-1);
                    array[i][j + 1] *= (-1);
                }else if(Math.abs(waterDepth) > array[i][j + 1] && ((array[i][j + 1] < array[i + 1][j]))){
                    array[i][j + 1] *= (-1);
                    if(waterDepth < array[i][j + 1]){
                        array[i][j + 1]--;
                    }
                }else if(Math.abs(waterDepth) > array[i + 1][j] && ((array[i][j + 1] > array[i + 1][j]))){
                    array[i + 1][j] *= (-1);
                    if(waterDepth < array[i + 1][j]){
                        array[i + 1][j]--;
                    }
                }

            }
        }

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }


    }
}

