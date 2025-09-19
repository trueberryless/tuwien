package org.trueberryless.ep1.k2.einstufungstest;

public class TestExample3 {
    public static void main(String[] args) {
        short result = (short) sumUp(4, 9, 320_300);
        String test = "all: hallo hall";

        System.out.println(sumUp(2, 5, 11));
        System.out.println(sumUp(8, 8, 40));
        System.out.println(sumUp(5, 1, 2000));

        System.out.println(addMark(test, "allo", 6));
        System.out.println(addMark(test, "all", 3));
        System.out.println(addMark(test, "hall", 3));
        System.out.println(addMark(test, "@all", 3));

        System.out.println(digitsToDistance("12oder34"));
        System.out.println(digitsToDistance("Ich bin 1 Berliner!11"));
        System.out.println(digitsToDistance("Heute ist der 4.März 2022"));
        System.out.println(digitsToDistance("Vier*mal*vier_=_0"));

        printPattern(6, 2);
        printPattern(7, 3);
        printPattern(1, 1);
        printPattern(8, 5);
    }

    public static int sumUp(int d, int s, int t) {
        int zeroCount = 0;
        while (s < t) {
            var modulo = s % d;
            if (modulo == 0) {
                zeroCount++;
                s += 1;
            } else {
                s += modulo;
            }
        }
        return zeroCount;
    }

    public static String addMark(String a, String pattern, int pos) {
        if (a.substring(pos, pos + pattern.length()).equals(pattern)) {
            return a.substring(pos);
        }
        if (a.substring(0, pattern.length()).equals(pattern)) {
            return "--" + a;
        }
        if (a.substring(a.length() - pattern.length()).equals(pattern)) {
            return a + "--";
        }
        return "--";
    }

    public static String digitsToDistance(String text) {
        String result = "";
        int lastNumber = 0;
        for (int i = 0; i < text.length(); i++) {
            char current = text.charAt(i);
            if (Character.isDigit(current)) {
                result += lastNumber;
                lastNumber = 0;
            } else {
                result += current;
                lastNumber++;
            }
        }
        return result;
    }

    public static void printPattern(int lineLength, int patternLength) {
        int rowCount = lineLength / patternLength;
        for (int i = 0; i < rowCount; i++) {
            int lowerBound = i * patternLength;
            int upperBound = i * patternLength + patternLength - 1;
            for (int j = 0; j < lineLength; j++) {
                if (lowerBound <= j && j <= upperBound) {
                    System.out.print("?");
                }
                if (upperBound < j) {
                    System.out.print("y");
                }
                if (j < lowerBound) {
                    System.out.print("x");
                }
            }
            System.out.println();
        }
    }
}
