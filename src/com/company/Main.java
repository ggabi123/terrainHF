package com.company;

public class Main {

    public static void main(String[] args) {

        int[][] testArray = {{1, 2, 3, 4}, {2, 3, 4, 4}, {3, 3, 3, 3}, {2, 1, 2, 3}};


        int waterDepth = -1;
        int[] minValueAndCoordinates = returnMinAndCoordinates(testArray);
        int min = minValueAndCoordinates[0];
        int i = minValueAndCoordinates[1];
        int j = minValueAndCoordinates[2];
        int right = 10;
        int left = 10;
        int top = 10;
        int bottom = 10;


        if (min == Math.abs(waterDepth) && min > 0) {
            testArray[i][j] = inverseAddition(testArray[i][j]);
        } else if (j > 0) {
            if (i > 0) {
                if (i < testArray.length - 1) {
                    right = testArray[i][j + 1];
                } else {
                    top = testArray[i - 1][j];
                }
            } else {
                bottom = testArray[i + 1][j];
            }


        } else if (i > 0) {
            if (j > 0) {
                if (i < testArray.length - 1) {
                    right = testArray[i][j + 1];
                } else {
                    top = testArray[i - 1][j];
                }
            } else {
                left = testArray[i][j - 1];
            }

        } else if (j < testArray.length - 1) {
            if (i < testArray.length - 1) {
                if (i > 0) {
                    left = testArray[i][j - 1];
                } else {
                    top = testArray[i - 1][j];
                }
            } else {
                bottom = testArray[i + 1][j];
            }

        } else if (i < testArray.length - 1) {
            if (j < testArray.length - 1) {
                if (j > 0) {
                    right = testArray[i][j + 1];
                } else {
                    left = testArray[i][j - 1];
                }
            } else {
                bottom = testArray[i + 1][j];

            }
        }


        int smallestNeighbor = findSmallestNeighbor(right, left, top, bottom);


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


        printTerrain(testArray);


    }


//    public static int checkNeighbors(int... nums) {
//        int smallestNextNeighbor = 0;
//        for (int i = 0; i < 2; i++) {
//            for (int j = 0; j < 2; j++) {
//                if (i != j && (i + j) != 2) {
//                    if (nums[0] > 0) {
//                        if (){
//
//                        }
//                    } else if (nums[1] > 0) {
//
//                    } else if (nums[0] < nums[2]) {
//
//                    } else if (nums[1] < nums[2]) {
//
//                    }
//                }
//            }
//        }
//        return smallestNextNeighbor;
//    }

    public static int findSmallestNeighbor(int... numbers) {
        int smallestNeighbor = numbers[0];
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] < smallestNeighbor) {
                smallestNeighbor = numbers[i];
            }
        }
        return smallestNeighbor;
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
        int waterDepth = Math.abs(min) * (-1);
        return waterDepth;
    }


    public static int[] returnMinAndCoordinates(int[][] array) {
        int[] minAndCoordinates = new int[3];
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] < min) {
                    min = minAndCoordinates[0];
                    i = minAndCoordinates[1];
                    j = minAndCoordinates[2];
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


