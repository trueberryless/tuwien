package org.trueberryless.ep1.k3.einstufungstest;

public class TestExample6 {
    public static void main(String[] args) {
        int[][] test1 = {{5}, {5, 7, 9}, {8, 5}, {}};
        int[][] test2 = {{1, 2}, {1, 2, 3}, {1, 2, 3, 4}};
        int[][] test3 = {{}, {1, 2, 3, 4}, {1}};
        int[] seq = {1, 2, -5, 3, -1, 6, -3, 3};

        System.out.println(isAlternating(seq, 0));
        System.out.println(isAlternating(seq, 1));
        System.out.println(isAlternating(seq, 6));
        System.out.println(isAlternating(seq, 7));
    }

    public static boolean isAlternating(int[] seq, int index) {
        if (index != 0) return isAlternating(sublist(seq, index, seq.length), 0);
        if (seq.length < 2) return true;
        boolean currentAlt = seq[0] < 0 != seq[1] < 0;
        if (seq.length == 2) return currentAlt;
        return currentAlt && isAlternating(sublist(seq, 1, seq.length), index);
    }

    private static int[] sublist(int[] list, int start, int end) {
        int[] result = new int[end - start];
        for (int i = start; i < end; i++) {
            result[i - start] = list[i];
        }
        return result;
    }
}
