package org.trueberryless.ep1.k3.einstufungstest;

public class TestExample3 {
    public static void main(String[] args) {
        System.out.println(insertMiddle("XY", "abc"));
        System.out.println(insertMiddle("01234", "abc"));
        System.out.println(insertMiddle("01234567890123", "./-"));
    }

    public static String insertMiddle(String input, String seps) {
        if (input.length() < 2 || seps.isEmpty()) return input;
        return insertMiddle(input.substring(0, input.length() / 2), seps.substring(1)) +
                seps.charAt(0) +
                insertMiddle(input.substring(input.length() / 2), seps.substring(1));
    }
}
