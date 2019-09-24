package com.company;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        int[][] randomArray = terrainGenerator(4);
        int min = inverseAddition(returnMin(randomArray));
        int waterLevel = Math.abs(min) * (-1);
        printTerrain(randomArray);
        System.out.println();
        submerge(randomArray, min);
        printTerrain(randomArray);
        System.out.println();
        int smallestNeighborValue = 0;

        for (int i = 0; i < randomArray.length; i++) {
            for (int j = 0; j < randomArray.length; j++) {
                if (randomArray[i][j] == min) {
                    smallestNeighborValue = findSmallestNeighborsValue(i, j, randomArray);
                    changeSmallNeighbors(randomArray, smallestNeighborValue, min);
                }
            }
        }
        adjustWaterLevel(randomArray, waterLevel, min);
        printTerrain(randomArray);


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

    public static void changeSmallNeighbors(int[][] terrainArray, int nextMin, int min) {
        for (int i = 0; i < terrainArray.length; i++) {
            for (int j = 0; j < terrainArray.length; j++) {

// 4 db if nem jó, mert bemegy, de nem lesz egyenlő nextMin-nel
                if (i > 0) {
                    if (terrainArray[i - 1][j] == nextMin) {
                        if (nextMin > 0 && nextMin > Math.abs(min)) {
                            terrainArray[i][j] = decreaseNumber(terrainArray[i][j]);
                        } else if (nextMin > 0 && nextMin <= Math.abs(min)) {
                            terrainArray[i - 1][j] = inverseAddition(terrainArray[i - 1][j]);
                        } else if (nextMin < 0) {
                            terrainArray[i - 1][j] = decreaseNumber(terrainArray[i - 1][j]);
                        }
                    }
                } else if (i < terrainArray.length - 1) {

                    if (terrainArray[i + 1][j] == nextMin) {
                        if (nextMin > 0 && nextMin > Math.abs(min)) {
                            terrainArray[i][j] = decreaseNumber(terrainArray[i][j]);
                        } else if (nextMin > 0 && nextMin <= Math.abs(min)) {
                            terrainArray[i + 1][j] = inverseAddition(terrainArray[i + 1][j]);
                        } else if (nextMin < 0) {
                            terrainArray[i + 1][j] = decreaseNumber(terrainArray[i + 1][j]);
                        }
                    }
                } else if (j > 0) {
                    if (terrainArray[i][j - 1] == nextMin && j > 0) {
                        if (nextMin > 0 && nextMin > Math.abs(min)) {
                            terrainArray[i][j] = decreaseNumber(terrainArray[i][j]);
                        } else if (nextMin > 0 && nextMin <= Math.abs(min)) {
                            terrainArray[i][j - 1] = inverseAddition(terrainArray[i][j - 1]);
                        } else if (nextMin < 0) {
                            terrainArray[i][j - 1] = decreaseNumber(terrainArray[i][j - 1]);
                        }
                    }
                } else if (j < terrainArray.length - 1) {

                    if (terrainArray[i][j + 1] == nextMin) {
                        if (nextMin > 0 && nextMin > Math.abs(min)) {
                            terrainArray[i][j] = decreaseNumber(terrainArray[i][j]);
                        } else if (nextMin > 0 && nextMin <= Math.abs(min)) {
                            terrainArray[i][j + 1] = inverseAddition(terrainArray[i][j + 1]);
                        } else if (nextMin < 0) {
                            terrainArray[i][j + 1] = decreaseNumber(terrainArray[i][j + 1]);
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


    public static int findSmallestNeighborsValue(int iCoordinate, int jCoordinate, int[][] numbers) {

        int right = 10;
        int left = 10;
        int top = 10;
        int bottom = 10;

        if (jCoordinate > 0) {
            left = numbers[iCoordinate][jCoordinate - 1];
        } else {
            left = 10;
        }
        if (iCoordinate > 0) {
            top = numbers[iCoordinate - 1][jCoordinate];
        } else {
            top = 10;
        }
        if (iCoordinate < numbers.length - 1) {
            bottom = numbers[iCoordinate + 1][jCoordinate];
        } else {
            bottom = 10;
        }
        if (jCoordinate < numbers.length - 1) {
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

    public static int decreaseNumber(int number) {
        return number--;
    }

    public static int increaseNumber(int number) {
        return number++;
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


    public static void printTerrain(int[][] arr) {

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }


}


