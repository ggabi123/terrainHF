package com.company;

public class Main {

    public static void main(String[] args) {

        int[][] array = new int[][]{{-3, -3, -3, 4}, {-3, -3, 4, 4}, {-3, 3, 3, 3}, {-2, 1, 2, 3}};


        System.out.println("1st step");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("2nd step");


        int[] coordinates = {3, 1};
        int waterDepth = -3;
        int min = 1;

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if ((i <= coordinates[0] && j <= coordinates[1]) && (array[i][j] <= min) || array[i][j] < 0) {
                    if (i == array.length - 1 && j < array.length - 1) {
                        if (array[i][j + 1] > min && array[i][j + 1] < 0) {
                            array[i][j + 1]--;
                        } else if (array[i][j + 1] > 0 && Math.abs(array[i][j]) > array[i][j + 1] && array[i][j] < 0) {
                            array[i][j + 1] *= (-1);

                        }
                        else if (Math.abs(array[i][j]) < array[i][j + 1] || Math.abs(array[i][j]) < min) {
                            array[i][j]--;
                        } else if (array[i][j] == min && array[i][j] < 0) {
                            array[i][j]--;
                        }else if(array[i][j] > 0 && array[i][j] > array[i][j + 1]){
                            array[i][j] *= (-1);
                        }
                    } else if (j == array.length - 1 && i < array.length - 1) {
                        if (array[i + 1][j] > min && array[i + 1][j] < 0) {
                            array[i + 1][j]--;
                        } else if (array[i + 1][j] > 0 && j < array.length - 1) {
                            array[i + 1][j] *= (-1);
                        } else if (Math.abs(array[i][j]) < array[i + 1][j]) {
                            array[i][j]--;
                        } else if (array[i][j] > 0 && array[i + 1][j] < Math.abs(min) && array[i][j] < min) {
                            array[i][j] *= (-1);
                        } else if (Math.abs(array[i][j]) < min && array[i][j] < 0) {
                            array[i][j]--;
                        }else if(array[i][j] == Math.abs(min) && array[i][j] > 0){
                            array[i][j] *= (-1);
                        }
                    } else if ((i < array.length - 1 && j < array.length - 1) || (i == array.length - 1 && j == array.length - 1)) {


                        if (Math.abs(min) > Math.abs(waterDepth) && min < 0) {
                            array[i][j]--;

                        } else if (waterDepth != array[i][j] && min > 0 && array[i][j] > 0 && Math.abs(waterDepth) >= min) {
                            array[i][j] *= (-1);

                        } else if (waterDepth == array[i][j] && min > Math.abs(array[i][j])) {
                            array[i][j]--;
                        } else if (Math.abs(min) < Math.abs(waterDepth) && array[i][j] != waterDepth) {
                            if (min > 0 && Math.abs(array[i][j]) < Math.abs(min)) {
                                array[i][j] *= (-1);
                            } else if (min < array[i][j] && waterDepth < array[i][j]) {
                                array[i][j]--;
                            }
                        }

                    }
                } else if ((i == coordinates[0] && j == coordinates[1]) && array[i][j] > min && min < 0) {
                    if (array[i][j] > 0) {
                        array[i][j] *= (-1);
                    } else if (array[i][j] < 0) {

                        array[i][j]--;
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


