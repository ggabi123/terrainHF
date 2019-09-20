package com.company;

public class Main {

    public static void main(String[] args) {

        int[][] array = new int[][]{{-3, -2, 3, 4}, {1, 3, 4, 4}, {3, 3, 3, 3}, {2, 1, 2, 3}};


        System.out.println("1st step");
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("2nd step");


        int waterDepth = -3;

        if (Math.abs(waterDepth) < Math.abs(array[0][1]) && array[0][1] == array[1][0]) {
            waterDepth--;
            array[0][0] = waterDepth;
        } else if (Math.abs(waterDepth) == Math.abs(array[0][1]) && array[0][1] == array[1][0]) {
            if (array[0][1] > 0) {
                array[0][1] *= (-1);
                array[1][0] *= (-1);
            }
        } else if (Math.abs(waterDepth) > Math.abs(array[0][1]) && array[0][1] == array[1][0]) {
            if (array[0][1] > 0) {
                array[0][1] *= (-1);
                array[1][0] *= (-1);
            }else {
                array[1][0]--;
                array[0][1]--;
            }
        } else if (array[0][1] > array[1][0]) {
            if (Math.abs(waterDepth) < Math.abs(array[1][0])) {
                waterDepth--;
                array[0][0] = waterDepth;
            }else if(Math.abs(waterDepth) > Math.abs(array[1][0])){
                if(array[1][0] > 0){
                    array[1][0] *= (-1);
                }else {
                    array[1][0]--;
                }
            }else {
                array[1][0] *= (-1);
            }
        }else if (array[0][1] < array[1][0]) {
            if (Math.abs(waterDepth) < Math.abs(array[0][1])) {
                waterDepth--;
                array[0][0] = waterDepth;
            }else if(Math.abs(waterDepth) > Math.abs(array[0][1])){
                if(array[0][1] > 0){
                    array[0][1] *= (-1);
                }else {
                    array[0][1]--;
                }
            }else {
                array[0][1] *= (-1);
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

