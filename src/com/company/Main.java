

package com.company;

import java.util.Random;

public class Main {

    public static void main(String[] args) {

        startTerrainChange(4);


    }


    public static void startTerrainChange(int arraySize) {
        //(int arraySize)
        //int[][] array = {{1, 2, 3, 4}, {2, 3, 4, 4}, {3, 3, 3, 3}, {2, 1, 2, 3}};
        int[][] array = generateTerrain(arraySize);
        int min = findMinValue(array);
        springItUp(array, min);
        printArray(array);
        System.out.println();
        for (int i = 0; i < 18; i++) {
            int[] minCoordinates = getMinCoordinates(array);
            int minCoordinatesI = minCoordinates[0];
            int minCoordinatesJ = minCoordinates[1];
            changeSmallestNeighborsValue(array, minCoordinatesI, minCoordinatesJ);
            updateWaterLevelOnTerrain(array);
            printArray(array);
            System.out.println();
        }
    }



    public static void changeSmallestNeighborsValue(int[][] array, int currentNumberI, int currentNumberJ) {
        int smallestNeighborValue = findSmallestNeighborValue(array, currentNumberI, currentNumberJ);
        int top = moveUp(array, currentNumberI, currentNumberJ);
        int bottom = moveDown(array, currentNumberI, currentNumberJ);
        int left = moveLeft(array, currentNumberI, currentNumberJ);
        int right = moveRight(array, currentNumberI, currentNumberJ);
        int newNum;
        if (smallestNeighborValue > Math.abs(array[currentNumberI][currentNumberJ])) {
            array[currentNumberI][currentNumberJ] = array[currentNumberI][currentNumberJ] - 1;
        } else {

            if (top == smallestNeighborValue || (top > 0 && top < Math.abs( array[currentNumberI][currentNumberJ]))) {
                newNum = changeValue(array[currentNumberI][currentNumberJ], top);
                if (top < Math.abs(array[currentNumberI][currentNumberJ]) || top > array[currentNumberI][currentNumberJ]) {
                    array[currentNumberI - 1][currentNumberJ] = newNum;
                }
            }
            if (bottom == smallestNeighborValue || (bottom > 0 && bottom < Math.abs( array[currentNumberI][currentNumberJ]))) {
                newNum = changeValue(array[currentNumberI][currentNumberJ], bottom);
                if (bottom > array[currentNumberI][currentNumberJ] || bottom < Math.abs(array[currentNumberI][currentNumberJ])) {
                    array[currentNumberI + 1][currentNumberJ] = newNum;
                }
            }
            if (right == smallestNeighborValue || (right > 0 && right < Math.abs( array[currentNumberI][currentNumberJ]))) {
                newNum = changeValue(array[currentNumberI][currentNumberJ], right);
                if (right > array[currentNumberI][currentNumberJ] || right < Math.abs(array[currentNumberI][currentNumberJ])) {
                    array[currentNumberI][currentNumberJ + 1] = newNum;
                }
            }
            if (left == smallestNeighborValue || (left > 0 && left < Math.abs( array[currentNumberI][currentNumberJ]))) {
                newNum = changeValue(array[currentNumberI][currentNumberJ], left);
                if (left < Math.abs(array[currentNumberI][currentNumberJ]) || left > Math.abs(array[currentNumberI][currentNumberJ])) {
                    array[currentNumberI][currentNumberJ - 1] = newNum;
                }
            }
        }

    }


    public static int findSmallestNeighborValue(int[][] array, int iIndex, int jIndex) {
        int right;
        int left;
        int top;
        int bottom;
        int waterLevel = findMinValue(array);

        if (moveDown(array, iIndex, jIndex) > waterLevel) {
            bottom = moveDown(array, iIndex, jIndex);
        } else {
            bottom = 10;
        }
        if (moveUp(array, iIndex, jIndex) > waterLevel) {
            top = moveUp(array, iIndex, jIndex);
        } else {
            top = 10;
        }
        if (moveLeft(array, iIndex, jIndex) > waterLevel) {
            left = moveLeft(array, iIndex, jIndex);
        } else {
            left = 10;
        }
        if (moveRight(array, iIndex, jIndex) > waterLevel) {
            right = moveRight(array, iIndex, jIndex);
        } else {
            right = 10;
        }

        int[] neighbors = {top, bottom, left, right};
        return getSmallestNeighborValue(neighbors);

    }

    public static int getSmallestNeighborValue(int[] neighbors) {
        int smallest = neighbors[0];
        for (int i = 0; i < neighbors.length; i++) {
            if (neighbors[i] < smallest) {
                smallest = neighbors[i];
            }
        }
        return smallest;
    }

    public static int moveUp(int[][] array, int i, int j) {
        int result = 10;
        if (i > 0 && i < array.length) {
            result = array[i - 1][j];
        }
        return result;
    }

    public static int moveDown(int[][] array, int i, int j) {
        int result = 10;
        if (i >= 0 && i < array.length - 1) {
            result = array[i + 1][j];
        }
        return result;
    }

    public static int moveLeft(int[][] array, int i, int j) {
        int result = 10;
        if (j > 0 && i < array.length) {
            result = array[i][j - 1];
        }
        return result;
    }

    public static int moveRight(int[][] array, int i, int j) {
        int result = 10;
        if (j >= 0 && j < array.length - 1) {
            result = array[i][j + 1];
        }
        return result;
    }



    public static int changeValue(int examinedNumber, int neighborNumber) {
        int result = 0;
        if (examinedNumber > 0) {
            result = examinedNumber * (-1);
        } else if (Math.abs(examinedNumber) < neighborNumber && examinedNumber < 0) {
            result = examinedNumber - 1;
        } else if (neighborNumber < 0 ) {
            result = neighborNumber - 1;
        } else if (neighborNumber > 0) {
            result = neighborNumber * (-1);
        }
        return result;
    }

    public static int[] getMinCoordinates(int[][] array) {
        int min = findMinValue(array);
        int[] minCoordinates = new int[2];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (array[i][j] == min) {
                    minCoordinates[0] = i;
                    minCoordinates[1] = j;
                }
            }
        }
        return minCoordinates;
    }


    public static int findSecondSmallestInArray(int[][] array) {
        int smallestNeighbor = 10;
        int min = findMinValue(array);
        int[][] checkedNeighbors = new int[array.length][array.length];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (array[i][j] == min) {
                    checkedNeighbors[i][j] = findSmallestNeighborValue(array, i, j);
                } else {
                    checkedNeighbors[i][j] = 10;
                }
            }
        }
        for (int i = 0; i < checkedNeighbors.length; i++) {
            for (int j = 0; j < checkedNeighbors.length; j++) {
                if (checkedNeighbors[i][j] < 10) {
                    smallestNeighbor = checkedNeighbors[i][j];
                }
            }
        }
        return smallestNeighbor;
    }

    public static int[][] updateWaterLevelOnTerrain(int[][] array) {
        int secondSmallestValue = findSecondSmallestInArray(array);
        int min = findMinValue(array);
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                int smallestNeighbor = findSmallestNeighborValue(array, i, j);
                if (array[i][j] < 0 && array[i][j] == secondSmallestValue  && smallestNeighbor > Math.abs(min)) {
                    array[i][j]--;
                }
            }
        }
        return array;
    }

    public static void springItUp(int[][] array, int min) {
        boolean flag = true;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (array[i][j] == min && flag) {
                    array[i][j] = Math.abs(min) * (-1);
                    flag = false;
                }
            }
        }
    }


    public static int findMinValue(int[][] array) {
        int minNumber = Integer.MAX_VALUE;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (array[i][j] < minNumber) {
                    minNumber = array[i][j];
                }
            }
        }
        return minNumber;
    }

    public static int[][] generateTerrain(int size) {
        int[][] terrain = new int[size][size];
        Random rd = new Random();
        for (int i = 0; i < terrain.length; i++) {
            for (int j = 0; j < terrain.length; j++) {
                terrain[i][j] = rd.nextInt(3) + 1;
            }
        }
        return terrain;
    }

    public static void printArray(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }
}

