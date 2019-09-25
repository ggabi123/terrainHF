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
        int waterLevel = Math.abs(min) * (-1);
        printArray(array);
        springItUp(array, min);
        System.out.println();
        printArray(updateWaterLevelOnTerrain(array, -6, waterLevel));
    }

//    public static void moveUp() {
//        i--;
//    }
//
//    public static void moveDown() {
//        i++;
//    }
//
//    public static void moveLeft() {
//        j--;
//    }
//
//    public static void moveRight() {
//        j++;
//    }


    public static void changeValue(int alreadyUnderWater, int neighborNumber) {
        if (Math.abs(alreadyUnderWater) < neighborNumber) {
            alreadyUnderWater--;
        } else if (Math.abs(alreadyUnderWater) >= neighborNumber) {
            neighborNumber *= (-1);
        } else if (alreadyUnderWater < neighborNumber && neighborNumber < 0) {
            neighborNumber--;
        }
    }

    public static int[][] updateWaterLevelOnTerrain(int[][] array, int newWaterLevel, int oldWaterLevel) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (array[i][j] == oldWaterLevel) {
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
