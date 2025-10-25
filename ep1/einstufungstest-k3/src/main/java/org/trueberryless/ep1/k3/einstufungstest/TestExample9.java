package org.trueberryless.ep1.k3.einstufungstest;

import java.util.Arrays;

public class TestExample9 {
    public static void main(String[] args) {
        int[] test1 = { 3, 0, 6, -1, 1};
        int[][] test2 = {{0}, {6, -5}, {0, 0}, {0, 1, 2, 0}};
        int[][] test3 = {{1, 2, 7, 3, 0}, {-8}, {0, 2}, {1, 4, -2, 1}};

        boolean[][] result1 = create(new int[]{3});
        System.out.println(Arrays.deepToString(result1));
        boolean[][] result2 = create(new int[]{-2, 0});
        System.out.println(Arrays.deepToString(result2));
        boolean[][] result3 = create(new int[]{});
        System.out.println(Arrays.deepToString(result3));

        move(test2);
        System.out.println(Arrays.deepToString(test2));
        move(test3);
        System.out.println(Arrays.deepToString(test3));

        System.out.println(oddOccurrences("This is not a test!", 's'));
        System.out.println(oddOccurrences("This is not a test!", 'T'));
        System.out.println(oddOccurrences("This is not a test!", 't'));
        System.out.println(oddOccurrences("This is not a test!", ' '));
        System.out.println(oddOccurrences("", 'x'));
    }

    public static boolean[][] create(int[] input) {
        boolean[][] result = new boolean[input.length][];

        for (int i = 0; i < input.length; i++) {
            if (input[i] < 0) {
                result[i] = new boolean[3];
                continue;
            }
            if (input[i] > 2) {
                result[i] = new boolean[input[i] + 1];
                result[i][input[i]] = true;
                continue;
            }
            result[i] = new boolean[3];
            result[i][input[i]] = true;
        }

        return result;
    }

    public static void move(int[][] input) {
        for (int i = 0; i < input.length; i++) {
            if (input[i][input[i].length - 1] == 0) {
                for (int j = input[i].length - 1; j > 0; j--) {
                    input[i][j] = input[i][j - 1];
                }
                input[i][0] = 0;
            }
            else {
                int[] temp = input[i];
                input[i] = new int[input[i].length + 1];
                for (int j = input[i].length - 1; j > 0; j--) {
                    input[i][j] = temp[j - 1];
                }
                input[i][0] = 0;
            }
        }
    }

    public static boolean oddOccurrences(String s, char ch) {
        if (s.isEmpty()) return false;
        if (s.length() == 1) return s.charAt(0) == ch;
        return oddOccurrences(s.substring(1), ch) != (s.charAt(0) == ch);
    }
}
