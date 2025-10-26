package org.trueberryless.ep1.k3.einstufungstest;

import java.util.Arrays;

public class TestExample8 {
    public static void main(String[] args) {
        int[][] test1 = {{1}, {1, 2, 3}, {1, 2, 3, 4}, {1, 2}};
        int[][] test2 = {{1, 2, 3}, {0}, {1, 2}, {0}, {1}};
        int[] seq = {2, 8, 8, 5, 2, 5, 7, 3};

        int[][] result1 = repeat(test1, new int[]{1, -2, 1, 0});
        System.out.println(Arrays.deepToString(result1));
        int[][] result2 = repeat(test2, new int[]{1, 0, -3, 2, 0});
        System.out.println(Arrays.deepToString(result2));

        rasp(test1);
        System.out.println(Arrays.deepToString(test1));
        rasp(test2);
        System.out.println(Arrays.deepToString(test2));

        System.out.println(hasNOrderedPairs(seq, 4, 0));
        System.out.println(hasNOrderedPairs(seq, 2, 2));
        System.out.println(hasNOrderedPairs(seq, 1, 3));
        System.out.println(hasNOrderedPairs(seq, 3, 4));
        System.out.println(hasNOrderedPairs(seq, 0, 6));
    }

    public static int[][] repeat(int[][] input, int[] reps) {
        int[][] result = new int[input.length][];

        for (int i = 0; i < input.length; i++) {
            int[] currentLine = input[i];
            int currentReps = Math.abs(reps[i]) + 1;
            result[i] = new int[currentLine.length * currentReps];

            for (int j = 0; j < currentReps; j++) {
                if (reps[i] >= 0) {
                    for (int k = 0; k < currentLine.length; k++) {
                        result[i][j * currentLine.length + k] = currentLine[k];
                    }
                }
                else {
                    for (int k = 0; k < currentLine.length; k++) {
                        result[i][j * currentLine.length + k] = currentLine[currentLine.length - k - 1];
                    }
                }
            }
        }

        return result;
    }

    public static void rasp(int[][] input) {
        int tempChar = 0;
        int[] tempLine;
        for (int i = 0; i < input.length; i++) {
            tempLine = input[i];
            if (!(i == input.length - 1 && input.length % 2 != 0)) {
                if (i % 2 == 0) {
                    tempChar = input[i][tempLine.length - 1];
                    input[i] = new int[tempLine.length - 1];
                    for (int j = 0; j < tempLine.length - 1; j++) {
                        input[i][j] = tempLine[j];
                    }
                }
                else {
                    input[i] = new int[tempLine.length + 1];
                    for (int j = 0; j < tempLine.length + 1; j++) {
                        if (j < tempLine.length)
                            input[i][j] = tempLine[j];
                        else
                            input[i][j] = tempChar;
                    }
                }
            }
        }
    }

    public static boolean hasNOrderedPairs(int[] seq, int n, int index) {
        if (index != 0) return hasNOrderedPairs(sublist(seq, index, seq.length), n, 0);
        if (seq.length == 1) return n == 0;
        boolean currentPair = seq[0] <= seq[1];
        if (seq.length == 2) return (currentPair && n == 1) || (!currentPair && n == 0);
        return hasNOrderedPairs(sublist(seq, 1, seq.length), currentPair ? n - 1 : n, index);
    }

    private static int[] sublist(int[] list, int start, int end) {
        int[] result = new int[end - start];
        for (int i = start; i < end; i++) {
            result[i - start] = list[i];
        }
        return result;
    }
}
