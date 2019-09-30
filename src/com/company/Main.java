package com.company;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        startTerrain();
    }

    public static void startTerrain() {
        int[][] array = generateTerrain(4);
        printArray(array);
        printArray(springUp(array));
        while (findPositive(array)) {
            printArray(replaceOldArray(array, progressMaker(array)));

        }
    }


    public static int[][] progressMaker(int[][] array) {
        int[][] newArray = new int[array.length][array.length];
        int min = findMinValue(array);
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                int top = moveUp(array, i, j);
                int bottom = moveDown(array, i, j);
                int left = moveLeft(array, i, j);
                int right = moveRight(array, i, j);
                int secondSmallest = findSmallestNextToNegatives(array);
                int newNum;
                if (array[i][j] < 0) {
                    if (secondSmallest > Math.abs(array[i][j])) {
                        newArray[i][j] = array[i][j] - 1;
                    } else {

                        if (top == secondSmallest && top < 10 && top > min) {
                            newNum = changeValue(array[i][j], top);
                            if (top < Math.abs(array[i][j]) || top > array[i][j]) {
                                newArray[i - 1][j] = newNum;
                            }
                        }
                        if (bottom == secondSmallest && bottom < 10 && bottom > min) {
                            newNum = changeValue(array[i][j], bottom);
                            if (bottom > array[i][j] || bottom < Math.abs(array[i][j])) {
                                newArray[i + 1][j] = newNum;
                            }
                        }
                        if (right == secondSmallest && right < 10 && right > min) {
                            newNum = changeValue(array[i][j], right);
                            if (right > array[i][j] || right < Math.abs(array[i][j])) {
                                newArray[i][j + 1] = newNum;
                            }
                        }
                        if (left == secondSmallest && left < 10 && left > min) {
                            newNum = changeValue(array[i][j], left);
                            if (left < Math.abs(array[i][j]) || left > Math.abs(array[i][j])) {
                                newArray[i][j - 1] = newNum;
                            }
                        }
                    }
                }
            }
        }
        return newArray;
    }

    public static boolean findPositive(int[][] array) {
        boolean flag = false;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (array[i][j] > 0) {
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }

    public static int[][] replaceOldArray(int[][] array, int[][] newArr) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (array[i][j] != newArr[i][j] && newArr[i][j] < 0) {
                    array[i][j] = newArr[i][j];
                }
            }
        }
        return array;
    }


    public static int changeValue(int examinedNumber, int neighborNumber) {
        int result = 0;
        if (examinedNumber > 0) {
            result = examinedNumber * (-1);
        } else if (Math.abs(examinedNumber) < neighborNumber && examinedNumber < 0) {
            result = examinedNumber - 1;
        } else if (neighborNumber < 0) {
            result = neighborNumber - 1;
        } else if (neighborNumber > 0) {
            result = neighborNumber * (-1);
        }
        return result;
    }


    public static int findSmallestNextToNegatives(int[][] array) {
        int secondSmallest = 10;
        int min = findMinValue(array);
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                int smallestNeighbor = findSmallestNeighborValue(array, i, j);
                if (smallestNeighbor < 0 && array[i][j] > min && array[i][j] <= Math.abs(secondSmallest) && Math.abs(array[i][j]) <= Math.abs(smallestNeighbor)) {
                    secondSmallest = array[i][j];
                }
            }
        }
        return secondSmallest;
    }

    public static int findSmallestNeighborValue(int[][] array, int iIndex, int jIndex) {
        int right;
        int left;
        int top;
        int bottom;
        int waterLevel = findMinValue(array);

        if (moveDown(array, iIndex, jIndex) >= waterLevel) {
            bottom = moveDown(array, iIndex, jIndex);
        } else {
            bottom = 10;
        }
        if (moveUp(array, iIndex, jIndex) >= waterLevel) {
            top = moveUp(array, iIndex, jIndex);
        } else {
            top = 10;
        }
        if (moveLeft(array, iIndex, jIndex) >= waterLevel) {
            left = moveLeft(array, iIndex, jIndex);
        } else {
            left = 10;
        }
        if (moveRight(array, iIndex, jIndex) >= waterLevel) {
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


    public static int moveLeft(int[][] array, int iCoord, int jCoord) {
        int result = 10;
        if (jCoord > 0) {
            result = array[iCoord][jCoord - 1];
        }
        return result;
    }

    public static int moveRight(int[][] array, int iCoord, int jCoord) {
        int result = 10;
        if (jCoord < array.length - 1) {
            result = array[iCoord][jCoord + 1];
        }
        return result;
    }

    public static int moveUp(int[][] array, int iCoord, int jCoord) {
        int result = 10;
        if (iCoord > 0) {
            result = array[iCoord - 1][jCoord];
        }
        return result;
    }

    public static int moveDown(int[][] array, int iCoord, int jCoord) {
        int result = 10;
        if (iCoord < array.length - 1) {
            result = array[iCoord + 1][jCoord];
        }
        return result;
    }

    public static int[][] springUp(int[][] array) {
        int min = findMinValue(array);
        boolean flag = true;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (array[i][j] == min && flag) {
                    array[i][j] *= (-1);
                    flag = false;
                }
            }
        }
        return array;
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
        System.out.println();
    }
}
