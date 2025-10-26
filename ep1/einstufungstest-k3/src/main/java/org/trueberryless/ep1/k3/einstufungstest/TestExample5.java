package org.trueberryless.ep1.k3.einstufungstest;

public class TestExample5 {
    public static void main(String[] args) {
        int[][] test1 = {{0, 2, 4}, {2, 0, 0}, {0, 0, 1}};
        int[][] test2 = {{1, 2, 3}, {1, 2, 3, 4, 5}, {1, 2, 3}, {1, 2, 3, 4, 5}};
        int[][] test3 = {{2}, {0, 7}, {6, 7, 8}, {6, 0}, {0, 0}};
        String seq1 = "ABA";

        System.out.println(replicateCharacters(seq1, "010"));
        System.out.println(replicateCharacters("SAMBA", "10001"));
    }

    public static String replicateCharacters(String sequence, String repSequence) {
        if (sequence.isEmpty()) return "";
        String currentSymbol = "" + sequence.charAt(0);
        if (repSequence.charAt(0) == '1')
            currentSymbol =  "" + sequence.charAt(0) + sequence.charAt(0);
        if (sequence.length() == 1) return currentSymbol;
        return currentSymbol + replicateCharacters(sequence.substring(1), repSequence.substring(1));
    }
}
