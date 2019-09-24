package com.company;

public class Main {

    public static void main(String[] args) {

        int[][] testArray = {{-2, -2, 3, 4}, {-2, 3, 4, 4}, {3, 3, 3, 3}, {2, 1, 2, 3}};
        printTerrain(testArray);


        int[] minInfo = returnMinAndCoordinates(testArray);
        int min = minInfo[0];


        int waterLevel = -2;
        int i = 0;
        int j = 1;
        int smallestNeighbor = findNeighbors(i, j, testArray, waterLevel);




        if (testArray[i][j] == Math.abs(waterLevel) && testArray[i][j] > 0) {
            testArray[i][j] = inverseAddition(testArray[i][j]);
        } else {


            if (j < testArray.length - 1) {
                if (testArray[i][j + 1] == smallestNeighbor) {
                    if (testArray[i][j + 1] > Math.abs(testArray[i][j])) {
                        testArray[i][j] = decreaseNumber(testArray[i][j]);
                        if (waterLevel > testArray[i][j]) {
                            waterLevel--;
                        }
                    } else if (testArray[i][j + 1] == Math.abs(testArray[i][j]) || testArray[i][j + 1] < Math.abs(testArray[i][j])) {
                        testArray[i][j + 1] = inverseAddition(testArray[i][j + 1]);
                        j++;
                    }
                }




            } else if (j > 0) {
                if (testArray[i][j - 1] == smallestNeighbor) {
                    if (testArray[i][j - 1] > Math.abs(testArray[i][j])) {
                        testArray[i][j] = decreaseNumber(testArray[i][j]);
                        if (waterLevel > testArray[i][j]) {
                            waterLevel--;
                        }
                    } else if (testArray[i][j - 1] == Math.abs(testArray[i][j]) || testArray[i][j - 1] < Math.abs(testArray[i][j])) {
                        testArray[i][j - 1] = inverseAddition(testArray[i][j - 1]);
                        j--;
                    }
                }




            } else if (i > 0) {
                if (testArray[i - 1][j] == smallestNeighbor) {
                    if (testArray[i - 1][j] > Math.abs(testArray[i][j])) {
                        testArray[i][j] = decreaseNumber(testArray[i][j]);
                        if (waterLevel > testArray[i][j]) {
                            waterLevel--;
                        }
                    } else if (testArray[i - 1][j] == Math.abs(testArray[i][j]) || testArray[i - 1][j] < Math.abs(testArray[i][j])) {
                        testArray[i - 1][j] = inverseAddition(testArray[i - 1][j]);
                        j--;
                    }
                }



            } else if (i < testArray.length - 1) {

                if (testArray[i + 1][j] == smallestNeighbor) {
                    if (testArray[i + 1][j] > Math.abs(testArray[i][j])) {
                        testArray[i][j] = decreaseNumber(testArray[i][j]);
                        if (waterLevel > testArray[i][j]) {
                            waterLevel--;
                        }
                    } else if (testArray[i + 1][j] == Math.abs(testArray[i][j]) || testArray[i + 1][j] < Math.abs(testArray[i][j])) {
                        testArray[i + 1][j] = inverseAddition(testArray[i + 1][j]);
                        j--;
                    }
                }


            }
        }
        System.out.println("Next phase");
        printTerrain(testArray);


    }


    public static int findNeighbors(int iCoordinate, int jCoordinate, int[][] numbers, int waterL) {

        int right = 10;
        int left = 10;
        int top = 10;
        int bottom = 10;

        if (jCoordinate > 0 && numbers[iCoordinate][jCoordinate-1] > waterL) {
            left = numbers[iCoordinate][jCoordinate - 1];
        }
        if (iCoordinate > 0 && numbers[iCoordinate - 1][jCoordinate] > waterL) {
            top = numbers[iCoordinate - 1][jCoordinate];
        }
        if (iCoordinate < numbers.length - 1 && numbers[iCoordinate][jCoordinate+1] > waterL) {
            bottom = numbers[iCoordinate + 1][jCoordinate];
        }
        if (jCoordinate < numbers.length - 1 && numbers[iCoordinate + 1][jCoordinate] > waterL) {
            right = numbers[iCoordinate][jCoordinate + 1];

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


    public static void printTerrain(int[][] testArray) {

        for (int i = 0; i < testArray.length; i++) {
            for (int j = 0; j < testArray.length; j++) {
                System.out.print(testArray[i][j] + " ");
            }
            System.out.println();
        }
    }


}


