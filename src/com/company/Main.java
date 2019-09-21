package com.company;

public class Main {

    public static void main(String[] args) {

        int[][] array = new int[][]{{-3, -3, 3, 4}, {-3, 3, 4, 4}, {3, 3, 3, 3}, {2, 1, 2, 3}};


        System.out.println("1st step");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("2nd step");



        int waterDepth = -3;
        int min = 3;




        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (Math.abs(min) > Math.abs(waterDepth) && min < 0) {
                    array[i][j]--;
                } else if ( Math.abs(waterDepth) == array[i][j] && min > 0) {

                    array[i][j] *= (-1);
                }else if( waterDepth == array[i][j] && min > Math.abs(array[i][j])){
                    array[i][j]--;
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


    public static int findMin(int[][] array) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] < min) {
                    min = array[i][j];
                }
            }
        }
        return min;
    }

    public static int[] findMinWithCoordinates(int[][] array) {
        int[] minInfoArray = new int[3];
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] < min) {
                    minInfoArray[0] = array[i][j];
                    minInfoArray[1] = i;
                    minInfoArray[2] = j;
                }
            }
        }
        return minInfoArray;
    }
}


