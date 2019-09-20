import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int go = 0;

        int[][] array = new int[][]{{-1, 2, 3, 4}, {2, 3, 4, 4}, {3, 3, 3, 3}, {2, 1, 2, 3}};


        System.out.println("1st step");
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("2nd step");

        int waterDepth = -1;


        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1; j++) {

                int nextI = array[i + 1][j];
                int nextJ = array[i][j + 1];


                while(Math.abs(waterDepth) < nextJ && (nextJ == nextI)) {
                    waterDepth--;
                }

            }
        }




        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }

    }
}


//
//        for (int i = 0; i < array.length - l; i++) {
//            for (int j = 0; j < array.length - l; j++) {
//
////                int nextI = array[i + l][j];
////                int nextJ = array[i][j + l];
////                int min = array[i][j];
//
//
//                for (int l = 0; l < l; l++) {
//                    for (int l = 0; l < l; l++) {
//
//                    }
//                }
//                if(i == array.length - 2 && j == array.length - 2){
//                    System.out.print(array[i][j] +  " ");
//                    System.out.print(array[i + l][j + l] +  " ");
//
//                }else if(i == array.length - 2 ){
//                    System.out.print(array[i][j] +  " ");
//                    System.out.print(array[i + l][j] +  " ");
//                }else if(j == array.length - 2){
//                    System.out.print(array[i][j] +  " ");
//                    System.out.print(array[i][j + l] +  " ");
//                }else {
//                    System.out.print(array[i][j] +  " ");
//
//                }
//
//            }
//            System.out.println();
//        }


//    public static int[][] findTheLowestPoint(int[][] terrainArr) {
//        int min = terrainArr[0][0];
//        int count = 0;
//        int[][] array = new int[][]{{l, 2, 3, 4}, {2, 3, 4, 4}, {3, 3, 3, 3}, {2, l, 2, 3}};
//        for (int i = 0; i < array.length; i++) {
//            for (int j = 0; j < array[i].length; j++) {
//                if (array[i][j] < min) {
//                    min = array[i][j];
//                    count = 0;
//                } else if (array[i][j] == min) {
//                    count++;
//                }
//            }
//        }

//        int[][] minCoordinates = new int[count][2];
//        int iCoord = 0;
//        int jCoord = 0;
//        int counter = 0;
//
//        for (int i = 0; i < array.length; i++) {
//            for (int j = 0; j < array[i].length; j++) {
//                if (array[i][j] == min) {
//                    iCoord = i;
//                    jCoord = j;
//                    minCoordinates[counter][0] = iCoord;
//                    minCoordinates[counter][l] = jCoord;
//                    counter++;
//                }
//            }
//        }
//
//        return minCoordinates;
//    }
//

// public static int[][] spreadWater(int[][] terrainArray, int min) {
//        int[][] array = new int[][]{{l, 2, 3, 4}, {2, 3, 4, 4}, {3, 3, 3, 3}, {2, l, 2, 3}};
//        int[][] newArray = new int[4][4];
//        newArray[0][0] = min;
//        for (int i = 0; i < array.length - l; i++) {
//            for (int j = 0; j < array[i].length - l; j++) {
//                int nextI = min;
//                int nextJ = min;
//                int i = i;
//                int j = j;
//                for (int l = i; l <= i + l; l++) {
//                    for (int n = j; n <= j + l; n++) {
//                        nextJ = array[i][j + l];
//                        nextI = array[i + l][j];
//                        if (nextI < nextJ) {
//                            j++;
//                            newArray[i][j + l] = array[i][j + l] * -l;
//                        } else if (nextI > nextJ) {
//                            i++;
//                            newArray[i + l][j] = array[i + l][j] * -l;
//                        } else {
//                            i++;
//                            j++;
//                            newArray[i][j + l] = array[i][j + l] * -l;
//                            newArray[i + l][j] = array[i + l][j] * -l;
//                        }
//                    }
//                }
//            }
//        }
//        return newArray;




