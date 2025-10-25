package org.trueberryless.ep1.k3.einstufungstest;

import java.util.Arrays;

public class TestExample2 {
    public static void main(String[] args) {
        int[][] test1 = { {1, 2, 0, -1, -2, 3}, {-1, 2, 3}, {0, 0}, {}, {4, 5, -1} };
        int[][] test2 = { {1, 2, 3}, {4, 5, 2}, {-2, -3, 2, -1}, {3, 2, 1, 5}, {4, 5, 1, 4} };
        int[][] test3 = { {1, -1, 2, -2, 3}, {1, 2, 3}, {-3, -1, -2} };

        char[] age1 = {'d', 'u', '-', 'd', 'u', '-', 'd', 'a', '-', 'd', 'a'};
        char[] age2 = {'m', 'a', 'm', 'a', '!', 'n', 'e', 'i', 'n'};

        int[][] result1 = generate(test1);
        int[][] result2 = generate(test2);
        System.out.println(Arrays.deepToString(result1));
        System.out.println(Arrays.deepToString(result2));

        fill(test1, test2[3], test2[4]);
        System.out.println(Arrays.deepToString(test1));
        fill(result1, test2[2], test2[4]);
        System.out.println(Arrays.deepToString(result1));
        fill(test3, test2[2], test2[3]);
        System.out.println(Arrays.deepToString(test3));

        System.out.println(extractString(age1, 0, age1.length, '-'));
        System.out.println(extractString(age1, 1, 7, 'u'));
        System.out.println(extractString(age2, 0, 5, 'a'));
        System.out.println(extractString(age2, 5, age2.length, 'n'));
    }

    public static int[][] generate(int[][] input) {
        int[][] result = new int[input.length][];
        int resultIndex = 0;

        for (int i = 0; i < input.length; i++) {
            result[i] = new int[input[i].length];
            for (int j = 0; j < input[i].length; j++) {
                if (input[i][j] < 0) {
                    result[i][resultIndex] = input[i][j];
                    resultIndex++;
                }
            }
            for (int j = 0; j < input[i].length; j++) {
                if (input[i][j] >= 0) {
                    result[i][resultIndex] = input[i][j];
                    resultIndex++;
                }
            }
            resultIndex = 0;
        }

        return result;
    }

    public static void fill(int[][] target, int[] values, int[] times) {
        int currentValue = values[0];
        int currentCount = 1;
        int timesIndex = 0;
        for (int i = 0; i < target.length; i++) {
            for (int j = 0; j < target[i].length; j++) {
                target[i][j] = currentValue;
                currentCount++;
                if (currentCount > times[timesIndex]) {
                    timesIndex++;
                    currentCount = 1;
                    if (timesIndex < times.length) currentValue = values[timesIndex];
                }
            }
        }
    }

    public static String extractString(char[] sequence, int start, int end, char omit) {
        if (sequence.length == 1 && sequence[0] == omit) return "";
        if (sequence.length == 1) return sequence[0] + "";
        if (start > 0 || sequence[0] == omit) {
            char[] smallerSequence = substring(sequence, 1, sequence.length);
            return extractString(smallerSequence, start - 1, end - 1, omit);
        }
        else if (end < sequence.length) {
            char[] smallerSequence = substring(sequence, 0, sequence.length - 1);
            return extractString(smallerSequence, start, end, omit);
        }
        else {
            char[] smallerSequence = substring(sequence, 1, sequence.length);
            return sequence[0] + extractString(smallerSequence, start - 1, end - 1, omit);
        }
    }

    private static char[] substring(char[] sequence, int start, int end) {
        char[] result = new char[end - start];
        for (int i = 0; i < end - start; i++) {
            result[i] = sequence[i + start];
        }
        return result;
    }
}
