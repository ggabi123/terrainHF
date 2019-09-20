package com.company;

public class Main {

    public static void main(String[] args) {

        int[][] array = new int[][]{{-3, -3, -3, 4}, {-3, -3, 4, 4}, {-3, 3, 3, 3}, {-2, -1, 2, 3}};


        System.out.println("1st step");
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("2nd step");


        //((waterDepth != array[i][j + 1] || waterDepth != array[i + 1][j]) && (i != j) && (array[i][j + 1] > Math.abs(min) || array[i + 1][j] > Math.abs(min)))

        int waterDepth = -3;
        int min = 1;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {


                if ((array[i][j + 1] == Math.abs(min) && array[i + 1][j] == Math.abs(min)) && i != j) {
                    if (Math.abs(waterDepth) < Math.abs(array[i][j + 1]) && array[i][j + 1] == array[i + 1][j]) {
                        waterDepth--;
                        array[i][j] = waterDepth;

                    } else if (Math.abs(waterDepth) == Math.abs(array[i][j + 1]) && array[i][j + 1] == array[i + 1][j]) {
                        if (array[i][j + 1] > 0) {
                            array[i][j + 1] *= (-1);
                            array[i + 1][j] *= (-1);
                        }

                    } else if (Math.abs(waterDepth) > Math.abs(array[i][j + 1]) && array[i][j + 1] == array[i + 1][j]) {
                        if (array[i][j + 1] > 0) {
                            array[i][j + 1] *= (-1);
                            array[i + 1][j] *= (-1);
                        } else {
                            array[i + 1][j]--;
                            array[i][j + 1]--;
                        }

                    } else if (array[i][j + 1] > array[i + 1][j]) {
                        if (Math.abs(waterDepth) < Math.abs(array[i + 1][j])) {
                            waterDepth--;
                            array[i][j] = waterDepth;
                        } else if (Math.abs(waterDepth) > Math.abs(array[i + 1][j])) {
                            if (array[i + 1][j] > 0) {
                                array[i + 1][j] *= (-1);
                            } else {
                                array[i + 1][j]--;
                            }
                        } else {
                            array[i + 1][j] *= (-1);
                        }

                    } else if (array[i][j + 1] < array[i + 1][j]) {
                        if (Math.abs(waterDepth) < Math.abs(array[i][j + 1])) {
                            waterDepth--;
                            array[i][j] = waterDepth;
                        } else if (Math.abs(waterDepth) > Math.abs(array[i][j + 1])) {
                            if (array[i][j + 1] > 0) {
                                array[i][j + 1] *= (-1);
                            }
                        } else if (array[i][j + 1] == waterDepth) {
                            if (array[i + 1][j] > 0) {
                                array[i + 1][j] *= (-1);
                            } else {
                                array[i + 1][j]--;

                            }
                        } else if (array[i][j + 1] > 0) {
                            array[i][j + 1] *= (-1);
                        }

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


