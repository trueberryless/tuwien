package org.trueberryless.ep1.k3.einstufungstest;

public class TestExample4 {
    public static void main(String[] args) {
        int[][] test1 = {{5, 2, 4}, {8, 5, 4}, {9, 6, 8, 7}};
        int[][] test2 = {{0, 1, 2}, {0, 1, 2}, {0, 1, 2}};
        int[][] test3 = {{6}, {2, 4}, {2, 4}, {2, 4}, {4, 2}};
        int[] seq1 = {4, 3, 2, 1, 10, 5, 5, 5};

        System.out.println(findMaxOppositeSum(seq1, 0, 7));
        System.out.println(findMaxOppositeSum(seq1, 0, 5));
        System.out.println(findMaxOppositeSum(seq1, 4, 7));
    }

    public static int findMaxOppositeSum(int[] sequence, int start, int end) {
        if (start != 0 || end != sequence.length - 1) return findMaxOppositeSum(sublist(sequence, start, end + 1), 0, sublist(sequence, start, end).length);
        int currentSum = sequence[0] + sequence[sequence.length - 1];
        if (sequence.length <= 2) return currentSum;
        return Math.max(currentSum, findMaxOppositeSum(sublist(sequence, 1, sequence.length - 1), 0, sequence.length - 3));
    }

    private static int[] sublist(int[] list, int start, int end) {
        int[] result = new int[end - start];
        for (int i = start; i < end; i++) {
            result[i - start] = list[i];
        }
        return result;
    }
}
