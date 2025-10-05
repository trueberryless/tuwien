package org.trueberryless.ep1.k3.einstufungstest;

import java.util.Arrays;

public class TestExample1 {
    public static void main(String[] args) {
        int[][] test1 = {{5, 2, 4}, {2, 7, 3}, {9, 5, 8}};
        int[][] test2 = {{1}};
        int[][] test3 = {{6, 7, 8}, {7, 5, 3, 1}, {2}};

        String seq1 = "ABBAACBA";

        int[][] result1 = generate(test1);
        int[][] result2 = generate(test2);
        System.out.println(Arrays.deepToString(result1));
        System.out.println(Arrays.deepToString(result2));

        reorder(result1);
        reorder(result2);
        reorder(test3);
        System.out.println(Arrays.deepToString(result1));
        System.out.println(Arrays.deepToString(result2));
        System.out.println(Arrays.deepToString(test3));

        System.out.println(isPresentNTimes(seq1, 'A', 4));
        System.out.println(isPresentNTimes(seq1, 'A', 3));
        System.out.println(isPresentNTimes(seq1, 'A', 5));
        System.out.println(isPresentNTimes(seq1, 'Z', 0));
    }

    public static int[][] generate(int[][] input) {
        int[][] result = new int[input.length * 2 - 1][];

        for (int i = 0; i < input.length; i++) {
            int rowLength = input[i].length - i;
            result[i] = new int[rowLength];
            for (int j = 0; j < rowLength; j++) {
                result[i][j] = input[i][j];
            }
        }

        if (input.length >= 2) {
            for (int i = input.length - 2; i >= 0; i--) {
                int rowLength = input[i].length - i;
                int resultIndex = input.length * 2 - 2 - i;
                result[resultIndex] = new int[rowLength];
                for (int j = 0; j < rowLength; j++) {
                    result[resultIndex][j] = input[i][j];
                }
            }
        }

        return result;
    }

    public static void reorder(int[][] input) {
        for (int i = 0; i < input.length; i++) {
            int[] reorderedRow = new int[input[i].length];
            for (int j = input[i].length - 1; j >= 0; j--) {
                reorderedRow[input[i].length - 1 - j] = input[i][j];
            }
            input[i] = reorderedRow;
        }
    }

    public static boolean isPresentNTimes(String sequence, char marker, int count) {
        boolean firstCharIsMarker = sequence.charAt(0) == marker;
        if (sequence.length() == 1)
            return (firstCharIsMarker && count == 1) || (!firstCharIsMarker && count == 0);
        return isPresentNTimes(sequence.substring(1), marker, count - (firstCharIsMarker ? 1 : 0));
    }
}
