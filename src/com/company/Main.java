package com.company;

public class Main {

    public static void main(String[] args) {

        int[][] array = new int[][]{{1, 2, 3, 4}, {2, 3, 4, 4}, {3, 3, 3, 3}, {2, 1, 2, 3}};


        System.out.println("1st step");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("2nd step");


        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }


    }

    public static int[][] changeTerrain(int[][] terrain, int[] coordinates) {
        int waterDepth = 0;
        for (int i = 0; i < terrain.length; i++) {
            for (int j = 0; j < terrain.length; j++) {
                if ((i <= coordinates[1] && j <= coordinates[2]) && (terrain[i][j] <= coordinates[0]) || terrain[i][j] < 0) {
                    if (i == terrain.length - 1 && j < terrain.length - 1) {
                        if (terrain[i][j + 1] > coordinates[0] && terrain[i][j + 1] < 0) {
                            terrain[i][j + 1]--;
                        } else if (terrain[i][j + 1] > 0 && Math.abs(terrain[i][j]) > terrain[i][j + 1] && terrain[i][j] < 0) {
                            terrain[i][j + 1] *= (-1);
                            break;
                        } else if ((Math.abs(terrain[i][j]) < terrain[i][j + 1] || Math.abs(terrain[i][j]) < coordinates[0]) && terrain[i][j] < 0) {
                            terrain[i][j]--;
                        } else if (terrain[i][j] == coordinates[0] && terrain[i][j] < 0) {
                            terrain[i][j]--;
                        } else if (terrain[i][j] > 0 && terrain[i][j] > terrain[i][j + 1]) {
                            terrain[i][j] *= (-1);
                        } else if (terrain[i][j] > 0 && terrain[i][j] == Math.abs(coordinates[0])) {
                            terrain[i][j] *= (-1);
                        }
                    } else if (j == terrain.length - 1 && i < terrain.length - 1) {
                        if (terrain[i + 1][j] > coordinates[0] && terrain[i + 1][j] < 0) {
                            terrain[i + 1][j]--;
                        } else if (terrain[i + 1][j] > 0 && j < terrain.length - 1) {
                            terrain[i + 1][j] *= (-1);
                        } else if (Math.abs(terrain[i][j]) < terrain[i + 1][j]) {
                            terrain[i][j]--;
                        } else if (terrain[i][j] > 0 && terrain[i + 1][j] < Math.abs(coordinates[0]) && terrain[i][j] < coordinates[0]) {
                            terrain[i][j] *= (-1);
                        } else if (Math.abs(terrain[i][j]) < coordinates[0] && terrain[i][j] < 0) {
                            terrain[i][j]--;
                        } else if (terrain[i][j] == Math.abs(coordinates[0]) && terrain[i][j] > 0 && Math.abs(waterDepth) >= terrain[i][j]) {
                            terrain[i][j] *= (-1);
                        }
                    } else if ((i < terrain.length - 1 && j < terrain.length - 1) || (i == terrain.length - 1 && j == terrain.length - 1)) {


                        if (Math.abs(coordinates[0]) > Math.abs(waterDepth) && coordinates[0] < 0) {
                            terrain[i][j]--;

                        } else if (waterDepth != terrain[i][j] && coordinates[0] > 0 && terrain[i][j] > 0 && Math.abs(waterDepth) >= coordinates[0]) {
                            terrain[i][j] *= (-1);

                        } else if (waterDepth == terrain[i][j] && coordinates[0] > Math.abs(terrain[i][j])) {
                            terrain[i][j]--;
                        } else if (Math.abs(coordinates[0]) < Math.abs(waterDepth) && terrain[i][j] != waterDepth) {
                            if (coordinates[0] > 0 && Math.abs(terrain[i][j]) < Math.abs(coordinates[0])) {
                                terrain[i][j] *= (-1);
                            } else if (coordinates[0] < terrain[i][j] && waterDepth < terrain[i][j]) {
                                terrain[i][j]--;
                            }
                        }

                    }
                } else if ((i == coordinates[1] && j == coordinates[2]) && terrain[i][j] > coordinates[0] && coordinates[0] < 0) {
                    if (terrain[i][j] > 0) {
                        terrain[i][j] *= (-1);
                    } else if (terrain[i][j] < 0) {

                        terrain[i][j]--;
                    }
                }
            }
        }


        return terrain;
    }


    public static int[] findMinWithCoordinates(int[][] array) {
        int[] minAndCoordinates = new int[3];
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] < min) {
                    minAndCoordinates[0] = array[i][j];
                    minAndCoordinates[1] = i;
                    minAndCoordinates[2] = j;
                }
            }
        }
        return minAndCoordinates;
    }
}


