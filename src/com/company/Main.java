package com.company;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        //int[][] randomArray = terrainGenerator(4);
        int[][] randomArray = {{-3, -3, -3, 4}, {-3, -3, 4, 4}, {-3, -3, -3, -3}, {-3, -3, -3, -3}};
        int min = inverseAddition(returnMin(randomArray));
        int waterLevel = inverseAddition(Math.abs(min));
        printTerrain(randomArray);
        System.out.println();


        // submerge(randomArray, min);
        printTerrain(randomArray);


        System.out.println();
        int smallestNeighborValue;


        smallestNeighborValue = findSmallestNeighborsValue(randomArray, min);
        changeSmallNeighbors(randomArray, smallestNeighborValue, min, waterLevel);
        adjustWaterLevel(randomArray, waterLevel, min);
        printTerrain(randomArray);
        System.out.println();


    }


    public static void submerge(int[][] array, int min) {
        boolean flag = true;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (array[i][j] == Math.abs(min) && flag) {
                    array[i][j] = inverseAddition(array[i][j]);
                    flag = false;
                }
            }
        }
    }

    public static void changeSmallNeighbors(int[][] terrainArray, int nextMin, int min, int waterLevel) {
        for (int i = 0; i < terrainArray.length; i++) {
            for (int j = 0; j < terrainArray.length; j++) {
                if (terrainArray[i][j] == min || (terrainArray[i][j] < Math.abs(min) && terrainArray[i][j] > 0)) {


                    if (i != 0 && (terrainArray[i - 1][j] == nextMin)) {
                        if (nextMin > 0 && nextMin > Math.abs(min)) {
                            terrainArray[i][j]--;
                            adjustWaterLevel(terrainArray, waterLevel, terrainArray[i][j]);
                            break;
                        } else if (nextMin > 0 && nextMin <= Math.abs(min)) {
                            terrainArray[i - 1][j] = inverseAddition(terrainArray[i - 1][j]);
                        } else if (nextMin < 0) {
                            terrainArray[i - 1][j]--;
                        }
                    }
                    if (i != terrainArray.length - 1 && (terrainArray[i + 1][j] == nextMin)) {
                        if (nextMin > 0 && nextMin > Math.abs(min)) {
                            terrainArray[i][j]--;
                            adjustWaterLevel(terrainArray, waterLevel, terrainArray[i][j]);
                            break;
                        } else if (nextMin > 0 && nextMin <= Math.abs(min)) {
                            terrainArray[i + 1][j] = inverseAddition(terrainArray[i + 1][j]);
                        } else if (nextMin < 0) {
                            terrainArray[i + 1][j]--;
                        }
                    }
                    if (j != 0 && (terrainArray[i][j - 1] == nextMin)) {
                        if (nextMin > 0 && nextMin > Math.abs(min)) {
                            terrainArray[i][j]--;
                            adjustWaterLevel(terrainArray, waterLevel, terrainArray[i][j]);
                            break;
                        } else if (nextMin > 0 && nextMin <= Math.abs(min)) {
                            terrainArray[i][j - 1] = inverseAddition(terrainArray[i][j - 1]);
                        } else if (nextMin < 0) {
                            terrainArray[i][j - 1]--;
                        }
                    }
                    if (j != terrainArray.length - 1 && (terrainArray[i][j + 1] == nextMin)) {
                        if (nextMin > 0 && nextMin > Math.abs(min)) {
                            terrainArray[i][j]--;
                            adjustWaterLevel(terrainArray, waterLevel, terrainArray[i][j]);
                            break;
                        } else if (nextMin > 0 && nextMin <= Math.abs(min)) {
                            terrainArray[i][j + 1] = inverseAddition(terrainArray[i][j + 1]);
                        } else if (nextMin < 0) {
                            terrainArray[i][j + 1]--;
                        }
                    }
                }
            }
        }
    }


    public static void adjustWaterLevel(int[][] array, int oldWaterLevel, int newWaterLevel) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (array[i][j] == oldWaterLevel && oldWaterLevel > newWaterLevel) {
                    array[i][j] = newWaterLevel;
                }
            }
        }
    }

    public static int[][] terrainGenerator(int size) {
        Random rd = new Random();
        int[][] terrainArray = new int[size][size];
        for (int i = 0; i < terrainArray.length; i++) {
            for (int j = 0; j < terrainArray.length; j++) {
                terrainArray[i][j] = rd.nextInt(3) + 1;
            }
        }
        return terrainArray;
    }


    public static int findSmallestNeighborsValue(int[][] numbers, int min) {

        int[] minIAndJ = returnMinCoordinates(numbers);
        int iCoordinate = minIAndJ[0];
        int jCoordinate = minIAndJ[1];

        int right = 10;
        int left = 10;
        int top = 10;
        int bottom = 10;

        if (jCoordinate > 0 && numbers[iCoordinate][jCoordinate - 1] > min) {
            left = numbers[iCoordinate][jCoordinate - 1];
        } else {
            left = 10;
        }
        if (iCoordinate > 0 && numbers[iCoordinate - 1][jCoordinate] > min) {
            top = numbers[iCoordinate - 1][jCoordinate];
        } else {
            top = 10;
        }
        if (iCoordinate < numbers.length - 1 && numbers[iCoordinate + 1][jCoordinate] > min) {
            bottom = numbers[iCoordinate + 1][jCoordinate];
        } else {
            bottom = 10;
        }
        if (jCoordinate < numbers.length - 1 && numbers[iCoordinate][jCoordinate + 1] > min) {
            right = numbers[iCoordinate][jCoordinate + 1];
        } else {
            right = 10;
        }

        int[] neighbors = {top, bottom, left, right};
        return getSmallestNumber(neighbors);

    }

    public static int getSmallestNumber(int[] neighbors) {
        int smallest = neighbors[0];
        for (int i = 0; i < neighbors.length; i++) {
            if (neighbors[i] < smallest) {
                smallest = neighbors[i];
            }
        }
        return smallest;
    }

    public static int inverseAddition(int number) {
        if (number > 0) {
            return number * (-1);
        } else {
            return number;
        }
    }

    public static int returnMin(int[][] array) {
        int min = array[0][0];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] < min) {
                    min = array[i][j];
                }
            }
        }
        return min;
    }

    public static int[] returnMinCoordinates(int[][] array) {
        int min = array[0][0];
        int[] minCoordinates = new int[2];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] <= min) {
                    minCoordinates[0] = i;
                    minCoordinates[1] = j;
                }
            }
        }
        return minCoordinates;
    }


    public static void printTerrain(int[][] arr) {

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }


}


