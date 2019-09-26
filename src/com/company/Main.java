package com.company;

import java.util.Random;

public class Main {

    public static void main(String[] args) {

        startTerrainChange(4);


    }


    // find smallest neighbor around min
    // if Math.abs(smallestNeighbor) <= Math.abs(min)
    // then turn smallest neighbor(s) negative/submerge

    //if Math.abs(smallestNeighbor) > Math.abs(min)
    // then min--

    // update waterLevel - waterLevel needs to be the same as deepest point/smallest num

    // repeat it until all numbers reach the smallest num/waterLevel (negative version of the highest absolute num in the array)


    public static void startTerrainChange(int arraySize) {
        int[][] array = generateTerrain(arraySize);
        int min = findMinValue(array);
        springItUp(array, min);
        printArray(array);
        System.out.println();
        int waterLevel = Math.abs(min) * (-1);
        for (int i = 0; i < 15; i++) {
            changeSmallestNeighbors(array, waterLevel);
            updateWaterLevelOnTerrain(array, findMinValue(array), waterLevel);
            printArray(array);
            System.out.println();
        }
    }

    public static int[] getWaterBoarderCoordinates(int[][] array) {
        // give back the coordinates of the number that is boardering the smallest number that is not < 0
        int[] coordinates = new int[2];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (array[i][j] < 0) {
                    if ((moveLeft(array, i, j) > 0 && j > 0) || (moveRight(array, i, j) > 0 && j < array.length - 1) ||
                            (moveDown(array, i, j) > 0 && i < array.length - 1) || (moveUp(array, i, j) > 0 && i > 0)){
                        coordinates[0] = i;
                        coordinates[1] = j;
                    }
                }
            }
        }
        return coordinates;
    }


    public static int[][] changeSmallestNeighbors(int[][] array, int waterLevel) {
        // updated i, j needs to be computed
        int smallestNeighbor = findSmallestNeighborValue(array, waterLevel);
        int result;
        int[]coordinates = getWaterBoarderCoordinates(array);
        int i = coordinates[0];
        int j = coordinates[1];
        if (i >= 0 && i < array.length - 1 && smallestNeighbor == moveDown(array, i, j)) {
            result = changeValue(array[i][j], moveDown(array, i, j));
            if (result > 0) {
                array[i][j] = result;
            } else {
                array[i + 1][j] = result;
            }
        }
        if (i > 0 && i < array.length && smallestNeighbor == moveUp(array, i, j)) {
            result = changeValue(array[i][j], moveUp(array, i, j));
            if (result > 0) {
                array[i][j] = result;
            } else {
                array[i - 1][j] = result;
            }
        }
        if (j >= 0 && j < array.length - 1 && smallestNeighbor == moveRight(array, i, j)) {
            result = changeValue(array[i][j], moveRight(array, i, j));
            if (result > 0) {
                array[i][j] = result;
            } else {
                array[i][j + 1] = result;
            }
        }
        if (j > 0 && i < array.length && smallestNeighbor == moveLeft(array, i, j)) {
            result = changeValue(array[i][j], moveLeft(array, i, j));
            if (result > 0) {
                array[i][j] = result;
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
        if (i > 0 && i < array.length) {
            array[i][j] = array[i--][j];
        }
        return array[i][j];
    }

    public static int moveDown(int[][] array, int i, int j) {
        if (i > -1 && i < array.length - 1) {
            array[i][j] = array[i++][j];
        }
        return array[i][j];
    }

    public static int moveLeft(int[][] array, int i, int j) {
        if (j > 0 && i < array.length) {
            array[i][j] = array[i][j--];
        }
        return array[i][j];
    }

    public static int moveRight(int[][] array, int i, int j) {

        if (j > -1 && j < array.length - 1) {
            array[i][j] = array[i][j++];
        }
        return array[i][j];
    }


    public static int changeValue(int alreadyUnderWater, int neighborNumber) {
        int result = 0;
        if (Math.abs(alreadyUnderWater) < neighborNumber) {
            alreadyUnderWater--;
            result = alreadyUnderWater;
        } else if (Math.abs(alreadyUnderWater) >= neighborNumber) {
            neighborNumber *= (-1);
            result = neighborNumber;
        } else if (alreadyUnderWater < neighborNumber && neighborNumber < 0) {
            neighborNumber--;
            result = neighborNumber;
        }
        return result;
    }

    public static int[][] updateWaterLevelOnTerrain(int[][] array, int newWaterLevel, int oldWaterLevel) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (array[i][j] == oldWaterLevel && newWaterLevel < oldWaterLevel) {
                    array[i][j] = newWaterLevel;
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
