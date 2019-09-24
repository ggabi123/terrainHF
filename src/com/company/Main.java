package com.company;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        int[][] randomArray = terrainGenerator(4);
        int[] minInfo = returnMinAndCoordinates(randomArray);
        int waterLevel = Math.abs(minInfo[0]) * (-1);
        int iCoordinate = minInfo[1];
        int jCoordinate = minInfo[2];
        int min = minInfo[0];
        printTerrain(randomArray);


        for (int i = 0; i < randomArray.length; i++) {
            for (int j = 0; j < randomArray.length; j++) {

                int smallestNeighbor = findNeighbors(i, j, randomArray, waterLevel);

                if (randomArray[i][j] == Math.abs(waterLevel) && randomArray[i][j] > 0 && randomArray[i][j] == min) {
                    randomArray[i][j] = inverseAddition(randomArray[i][j]);
                } else if (randomArray[i][j] == smallestNeighbor) {
                    if (j < randomArray.length - 1 && randomArray[i][j + 1] > waterLevel) {

                        if (randomArray[i][j + 1] == smallestNeighbor) {
                            if (randomArray[i][j + 1] > Math.abs(randomArray[i][j])) {
                                randomArray[i][j] = decreaseNumber(randomArray[i][j]);
                                if (waterLevel > randomArray[i][j]) {
                                    decreaseNumber(waterLevel);
                                }
                            } else if (randomArray[i][j + 1] == Math.abs(randomArray[i][j]) || randomArray[i][j + 1] < Math.abs(randomArray[i][j])) {
                                randomArray[i][j + 1] = inverseAddition(randomArray[i][j + 1]);
                                increaseNumber(j);
                            }
                        } else if ((j < randomArray.length - 1 && j > 0 && randomArray[i][j - 1] > waterLevel)) {
                            if (randomArray[i][j - 1] == smallestNeighbor) {
                                if (randomArray[i][j - 1] > Math.abs(randomArray[i][j])) {
                                    randomArray[i][j] = decreaseNumber(randomArray[i][j]);
                                } else if (randomArray[i][j - 1] == Math.abs(randomArray[i][j]) || randomArray[i][j - 1] < Math.abs(randomArray[i][j])) {
                                    randomArray[i][j - 1] = inverseAddition(randomArray[i][j - 1]);
                                    decreaseNumber(j);
                                }
                            }
                        } else if (i < randomArray.length - 1 && randomArray[i + 1][j] > waterLevel) {

                            if (randomArray[i + 1][j] == smallestNeighbor) {
                                if (randomArray[i + 1][j] > Math.abs(randomArray[i][j])) {
                                    randomArray[i][j] = decreaseNumber(randomArray[i][j]);

                                } else if (randomArray[i + 1][j] == Math.abs(randomArray[i][j]) || randomArray[i + 1][j] < Math.abs(randomArray[i][j])) {
                                    randomArray[i + 1][j] = inverseAddition(randomArray[i + 1][j]);
                                    increaseNumber(i);
                                }
                            } else if ((i < randomArray.length - 1 && i > 0 && randomArray[i - 1][j] > waterLevel)) {
                                if (randomArray[i - 1][j] == smallestNeighbor) {
                                    if (randomArray[i - 1][j] > Math.abs(randomArray[i][j])) {
                                        randomArray[i][j] = decreaseNumber(randomArray[i][j]);

                                    } else if (randomArray[i - 1][j] == Math.abs(randomArray[i][j]) || randomArray[i - 1][j] < Math.abs(randomArray[i][j])) {
                                        randomArray[i - 1][j] = inverseAddition(randomArray[i - 1][j]);
                                        decreaseNumber(i);
                                    }
                                }
                            }

                        }

                    }
                }
                if (waterLevel > randomArray[i][j]) {
                    adjustWaterLevel(randomArray, waterLevel, randomArray[i][j]);
                    decreaseNumber(waterLevel);
                }

                min = returnMin(randomArray);
                System.out.println("Next phase");
                printTerrain(randomArray);

            }
        }


    }

    public static void adjustWaterLevel(int[][] array, int oldWaterLevel, int newWaterLevel) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (array[i][j] == oldWaterLevel) {
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


    public static int findNeighbors(int iCoordinate, int jCoordinate, int[][] numbers, int waterL) {

        int right = 10;
        int left = 10;
        int top = 10;
        int bottom = 10;

        if (jCoordinate > 0 && numbers[iCoordinate][jCoordinate - 1] > waterL) {
            left = numbers[iCoordinate][jCoordinate - 1];
        } else {
            left = 10;
        }
        if (iCoordinate > 0 && numbers[iCoordinate - 1][jCoordinate] > waterL) {
            top = numbers[iCoordinate - 1][jCoordinate];
        } else {
            top = 10;
        }
        if (iCoordinate < numbers.length - 1 && numbers[iCoordinate + 1][jCoordinate] > waterL) {
            bottom = numbers[iCoordinate + 1][jCoordinate];
        } else {
            bottom = 10;
        }
        if (jCoordinate < numbers.length - 1 && numbers[iCoordinate][jCoordinate + 1] > waterL) {
            right = numbers[iCoordinate][jCoordinate + 1];
        } else {
            right = 10;
        }

        int[] neighbors = {top, bottom, left, right};
        return getSmallestNeighbor(neighbors);

    }

    public static int getSmallestNeighbor(int[] neighbors) {
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
        if (number < 0) {
            number--;
        }
        return number;
    }

    public static int increaseNumber(int number) {
        return number++;
    }


    public static int waterDepthTracker(int min) {
        return Math.abs(min) * (-1);
    }


    public static int[] returnMinAndCoordinates(int[][] array) {
        int[] minAndCoordinates = new int[3];
        int min = 5;
        int waterLevel = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] < min && array[i][j] > waterLevel) {
                    min = array[i][j];
                    minAndCoordinates[0] = array[i][j];
                    minAndCoordinates[1] = i;
                    minAndCoordinates[2] = j;
                    waterLevel = waterDepthTracker(min);
                }

            }
        }
        return minAndCoordinates;
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


