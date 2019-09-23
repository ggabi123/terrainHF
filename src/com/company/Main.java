package com.company;

public class Main {

    public static void main(String[] args) {

        int[][] array = {{-2, 2, 3, 4}, {2, 3, 4, 4}, {3, 3, 3, 3}, {2, 1, 2, 3}};
        printTerrain(array);
        System.out.println("Next phase");
        updateTerrain(array);


    }


    public static void updateTerrain(int[][] testArray){
        int[] minValueAndCoordinates = returnMinAndCoordinates(testArray);
        int min = minValueAndCoordinates[0];
        int i = minValueAndCoordinates[1];
        int j = minValueAndCoordinates[2];
        int waterDepth = waterDepthTracker(min);
        int smallestNeighbor = findNeighbors(i, j, testArray);


        if (min == Math.abs(waterDepth) && min > 0) {
            testArray[i][j] = inverseAddition(testArray[i][j]);
        }else {


            if (j < testArray.length - 1) {
                if (testArray[i][j + 1] <= smallestNeighbor) {
                    if (testArray[i][j + 1] == Math.abs(waterDepth) && testArray[i][j + 1] > 0) {
                        testArray[i][j + 1] = inverseAddition(testArray[i][j + 1]);
                    } else if (Math.abs(testArray[i][j]) < testArray[i][j + 1]) {
                        testArray[i][j] = decreaseNumber(testArray[i][j]);
                    } else if (Math.abs(testArray[i][j]) > testArray[i][j + 1] && testArray[i][j + 1] > 0) {
                        testArray[i][j + 1] = inverseAddition(testArray[i][j + 1]);
                    } else if (Math.abs(testArray[i][j]) > testArray[i][j + 1] && testArray[i][j + 1] < 0) {
                        testArray[i][j + 1] = decreaseNumber(testArray[i][j + 1]);
                    }
                }
            }

            if (j > 0) {
                if (testArray[i][j - 1] <= smallestNeighbor) {
                    if (testArray[i][j - 1] == Math.abs(waterDepth) && testArray[i][j - 1] > 0) {
                        testArray[i][j - 1] = inverseAddition(testArray[i][j - 1]);
                    } else if (Math.abs(testArray[i][j]) < testArray[i][j - 1]) {
                        testArray[i][j] = decreaseNumber(testArray[i][j]);
                    } else if (Math.abs(testArray[i][j]) > testArray[i][j - 1] && testArray[i][j - 1] > 0) {
                        testArray[i][j - 1] = inverseAddition(testArray[i][j - 1]);
                    } else if (Math.abs(testArray[i][j]) > testArray[i][j - 1] && testArray[i][j - 1] < 0) {
                        testArray[i][j - 1] = decreaseNumber(testArray[i][j - 1]);
                    }
                }
            }

            if (i > 0) {
                if (testArray[i - 1][j] <= smallestNeighbor) {
                    if (testArray[i - 1][j] == Math.abs(waterDepth) && testArray[i - 1][j] > 0) {
                        testArray[i - 1][j] = inverseAddition(testArray[i - 1][j]);
                    } else if (Math.abs(testArray[i][j]) < testArray[i - 1][j]) {
                        testArray[i][j] = decreaseNumber(testArray[i][j]);
                    } else if (Math.abs(testArray[i][j]) > testArray[i - 1][j] && testArray[i - 1][j] > 0) {
                        testArray[i - 1][j] = inverseAddition(testArray[i - 1][j]);
                    } else if (Math.abs(testArray[i][j]) > testArray[i - 1][j] && testArray[i - 1][j] < 0) {
                        testArray[i - 1][j] = decreaseNumber(testArray[i - 1][j]);
                    }
                }
            }

            if (i < testArray.length - 1) {

                if (testArray[i + 1][j] <= smallestNeighbor) {
                    if (testArray[i + 1][j] == Math.abs(waterDepth) && testArray[i + 1][j] > 0) {
                        testArray[i + 1][j] = inverseAddition(testArray[i + 1][j]);
                    } else if (Math.abs(testArray[i][j]) < testArray[i + 1][j]) {
                        testArray[i][j] = decreaseNumber(testArray[i][j]);
                    } else if (Math.abs(testArray[i][j]) > testArray[i + 1][j] && testArray[i + 1][j] > 0) {
                        testArray[i + 1][j] = inverseAddition(testArray[i + 1][j]);
                    } else if (Math.abs(testArray[i][j]) > testArray[i + 1][j] && testArray[i + 1][j] < 0) {
                        testArray[i + 1][j] = decreaseNumber(testArray[i + 1][j]);
                    }
                }
            }
        }

        int[][] newArray = testArray;
        printTerrain(newArray);
    }


    public static int findNeighbors(int iCoordinate, int jCoordinate, int[][] numbers) {

        int right = 10;
        int left = 10;
        int top = 10;
        int bottom = 10;

        if (jCoordinate > 0) {
            left = numbers[iCoordinate][jCoordinate - 1];
        }
        if (iCoordinate > 0) {
            top = numbers[iCoordinate - 1][jCoordinate];
        }
        if (iCoordinate < numbers.length - 1) {
            bottom = numbers[iCoordinate + 1][jCoordinate];
        }
        if (jCoordinate < numbers.length - 1) {
            right = numbers[iCoordinate][jCoordinate + 1];

        }

        int[] neighbors = {top, bottom, left, right};
        return getSmallestNeighbor(neighbors);

    }

    public static int getSmallestNeighbor(int[] neighbors) {
        int smallest = Integer.MAX_VALUE;
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
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] < min) {
                    min = array[i][j];
                    minAndCoordinates[0] = array[i][j];
                    minAndCoordinates[1] = i;
                    minAndCoordinates[2] = j;
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


