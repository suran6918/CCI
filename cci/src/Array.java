import java.lang.*;
import java.util.Arrays;

public class Array {

    //Rotate Matrix: Given an image represented by an NxN matrix, where each pixel in the image is 4 bytes,
    //write a method to rotate the image by 90 degrees. Can you do this in place?
    /*
    00 01 02 03 04
    10 11 12 13 14
    20 21 22 23 24
    30 31 32 33 34
    40 41 42 43 44
     */

    public static int[][] rotate(int[][] input){
        int col, row = 0;
        int n = input.length;

        //perform for each layer
        for(int i=0; i<n/2; i++){
            int min = i;
            int max = n-1-i;

            for(int j=min; j<max; j++) {
                int offset = j-min;
                int tmp = input[min][j];
                // left to top
                input[min][j] = input[max-offset][min];

                // bottom to left
                input[max-offset][min] = input[max][max-offset];

                // right to botton
                input[max][max-offset] = input[j][max];

                // top to right
                input[offset][max] = tmp;
            }
        }
        return input;
    }

    public static void printMatrix(int[][] input){
        for (int i=0; i<input.length; i++){
            for (int j=0; j<input[0].length; j++){
                System.out.print(input[i][j]);
            }
            System.out.print("\n");
        }
    }


    /* Zero Matrix:
       Write an algorithm such that if an element in an MxN matrix is 0, its entire row and column are set to 0.
       To improve space efficiency, use the first row and column as the boolean arrays.
     */

    public static int[][] zeroMatrix(int[][] input){
        boolean[] rows = new boolean[input.length];
        boolean[] cols = new boolean[input[0].length];

        Arrays.fill(rows, true);
        Arrays.fill(cols, true);

            for(int i=0; i<input.length; i++){
                for(int j=0; j<input[0].length; j++){
                    if (input[i][j] == 0) {
                        rows[i] = false;
                        cols[j] = false;
                    }
                }
            }

            for(int i=0; i<rows.length; i++){
                for(int j=0; j<cols.length; j++){
                    if (rows[i] == false)
                        input[i][j] = 0;
                }
            }

            for(int i=0; i<cols.length; i++){
                for(int j=0; j<rows.length; j++){
                    if (cols[i] == false)
                        input[j][i] = 0;
                }
            }
        return input;
    }

    /*
    String Rotation:
    Assume you have a method isSubstring which checks if one word is a substring of another.
    Given two strings, sl and s2, write code to check if s2 is a rotation of s1 using only one call to isSubstring
    (e.g.,"waterbottle" is a rotation of"erbottlewat").


    first thought: sort the two strings, and then check if they are the same. Runtime should be O(nlogn).
     */

    public static boolean isRotation(String s1, String s2){

        if (s1.length() != s2.length())
            return false;

        StringBuilder sb = new StringBuilder(s2.length()*2);
        sb.append(s2);
        sb.append(s2);

        return sb.toString().contains(s1);
    }

    public static void main(String args[]){


        int[][] input = new int[][]{
                {1,1,1,0,1},
                {2,2,2,2,2},
                {3,3,0,3,3},
                {4,4,4,4,4},
                {5,5,5,5,5}
        };

        // test for matrix rotation
        //printMatrix(rotate(input));


        // test for zero matrix
        //printMatrix(zeroMatrix(input));


        // test for checkRotation
        String test1 = "aaaabba";
        String test2 = "bbbaaaa";
        String test3 = "aabbaaa";

        if (isRotation(test1, test2))
            System.out.print("Is a rotation");
    }


}
