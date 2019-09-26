package com.company;

import java.util.Random;

public class Main {

    public static void main(String[] args) {

        startTerrainChange();


    }


    public static void startTerrainChange() {
        //(int arraySize)
        //int[][] array = generateTerrain(arraySize);
        int[][] array = {{1, 2, 3, 4}, {2, 3, 4, 4}, {3, 3, 3, 3}, {2, 1, 2, 3}};
        int min = findMinValue(array);
        springItUp(array, min);
        printArray(array);
        System.out.println();
        int waterLevel = findMinValue(array);
        while (waterLevel > -4) {
            waterLevel = findMinValue(array);
            changeSmallestNeighbors(array);
            updateWaterLevelOnTerrain(array, waterLevel);
            printArray(array);
            System.out.println();
        }
    }

    public static int[] getWaterBoarderCoordinates(int[][] array) {
        int[] coordinates = new int[2];
        int min = findMinValue(array);
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (array[i][j] < 0) {
                    if (moveLeft(array, i, j) > min && j > 0) {
                        coordinates[0] = i;
                        coordinates[1] = j;
                    }
                    if (moveRight(array, i, j) > min && j < array.length - 1) {
                        coordinates[0] = i;
                        coordinates[1] = j;
                    }
                    if (moveDown(array, i, j) > min && i < array.length - 1) {
                        coordinates[0] = i;
                        coordinates[1] = j;
                    }
                    if (moveUp(array, i, j) > min && i > 0) {
                        coordinates[0] = i;
                        coordinates[1] = j;
                    }
                }
            }
        }
        return coordinates;
    }


    public static int[][] changeSmallestNeighbors(int[][] array) {
        int waterLevel = findMinValue(array);
        int smallestNeighbor = findSmallestNeighborValue(array, waterLevel);
        int[] coordinates = getWaterBoarderCoordinates(array);
        int i = coordinates[0];
        int j = coordinates[1];
        int examinedNumber = array[i][j];
        int neighbor;
        int result;


        if (i < array.length - 1 && smallestNeighbor == moveDown(array, i, j)) {
            result = changeValue(array[i][j], moveDown(array, i, j));
            neighbor = moveDown(array, i, j);
            if (neighbor > Math.abs(examinedNumber)) {
                array[i][j] = result;
            } else {
                array[i + 1][j] = result;
            }
        }
        if (i > 0 && smallestNeighbor == moveUp(array, i, j)) {
            result = changeValue(array[i][j], moveUp(array, i, j));
            neighbor = moveUp(array, i, j);
            if (neighbor > Math.abs(examinedNumber)) {
                array[i][j] = result;
            } else {
                array[i - 1][j] = result;
            }
        }
        if (j < array.length - 1 && smallestNeighbor == moveRight(array, i, j)) {
            result = changeValue(array[i][j], moveRight(array, i, j));
            neighbor = moveRight(array, i, j);
            if (neighbor > Math.abs(examinedNumber)) {
                array[i][j] = result;
            } else {
                array[i][j + 1] = result;
            }
        }
        if (j > 0 && smallestNeighbor == moveLeft(array, i, j)) {
            result = changeValue(array[i][j], moveLeft(array, i, j));
            neighbor = moveLeft(array, i, j);
            if (neighbor > Math.abs(examinedNumber)) {
            } else {
                array[i][j - 1] = result;

            }
        }

        return array;

    }

    public static int findSmallestNeighborValue(int[][] array, int waterLevel) {
        int right;
        int left;
        int top;
        int bottom;
        int[] coordinates = getWaterBoarderCoordinates(array);
        int i = coordinates[0];
        int j = coordinates[1];

        if (moveDown(array, i, j) > waterLevel) {
            bottom = moveDown(array, i, j);
        } else {
            bottom = 10;
        }
        if (moveUp(array, i, j) > waterLevel) {
            top = moveUp(array, i, j);
        } else {
            top = 10;
        }
        if (moveLeft(array, i, j) > waterLevel) {
            left = moveLeft(array, i, j);
        } else {
            left = 10;
        }
        if (moveRight(array, i, j) > waterLevel) {
            right = moveRight(array, i, j);
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
        int number = Integer.MAX_VALUE;

        if (i > 0 && i < array.length) {
            number = array[i - 1][j];
        }
        return number;
    }

    public static int moveDown(int[][] array, int i, int j) {
        int number = Integer.MAX_VALUE;
        if (i >= 0 && i < array.length - 1) {
            number = array[i + 1][j];
        }
        return number;
    }

    public static int moveLeft(int[][] array, int i, int j) {
        int number = Integer.MAX_VALUE;
        if (j > 0 && i < array.length) {
            number = array[i][j - 1];
        }
        return number;
    }

    public static int moveRight(int[][] array, int i, int j) {
        int number = Integer.MAX_VALUE;
        if (j >= 0 && j < array.length - 1) {
            number = array[i][j + 1];
        }
        return number;
    }


    public static int changeValue(int alreadyUnderWater, int neighborNumber) {
        int result = 0;
        if (Math.abs(alreadyUnderWater) < neighborNumber) {
            alreadyUnderWater--;
            result = alreadyUnderWater;
        } else if (Math.abs(alreadyUnderWater) >= neighborNumber && neighborNumber > 0) {
            neighborNumber *= (-1);
            result = neighborNumber;
        } else if (Math.abs(alreadyUnderWater) >= neighborNumber && neighborNumber < 0) {
            neighborNumber--;
            result = neighborNumber;
        }
        return result;
    }

    public static int[][] updateWaterLevelOnTerrain(int[][] array, int waterLevel) {
        int min = findMinValue(array);
        int smallestNeigbor = findSmallestNeighborValue(array, min);
        if (min < waterLevel) {
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array.length; j++) {
                    if (array[i][j] == smallestNeigbor && smallestNeigbor < 0) {
                        array[i][j] = min;

                    }
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
