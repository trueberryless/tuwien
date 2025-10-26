package org.trueberryless.ep1.k3.einstufungstest;

public class TestExample7 {
    public static void main(String[] args) {
        int[][] test1 = {{5, 7, 5, 7}, {5}, {0, 1, 1, 0}};
        int[][] test2 = {{5, 7, 9}, {5}, {8, 5}, {2}, {3}};
        int[][] test3 = {{1, 2}, {1, 2, 3}, {}};
        String seq = "1(234)67";

        System.out.println(clean(seq));
        System.out.println(clean("123(45))"));
        System.out.println(clean("x)"));
        System.out.println(clean(")x("));
    }

    public static String clean(String seq) {
        if(seq.isEmpty()) return "";
        if(seq.charAt(0) == '(' && seq.charAt(seq.length() - 1) == ')') return seq.substring(1, seq.length() - 1);
        if(seq.charAt(0) == '(') return clean(seq.substring(0, seq.length() - 1));
        return clean(seq.substring(1));
    }
}
