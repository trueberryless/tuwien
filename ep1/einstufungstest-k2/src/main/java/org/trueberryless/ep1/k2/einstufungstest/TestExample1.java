package org.trueberryless.ep1.k2.einstufungstest;

public class TestExample1 {
    public static void main(String[] args) {
        short result = (short)countDivisors(299, 305);
        String test = "Teststring_Einstufungstest";

        System.out.println(countDivisors(1, 28));
        System.out.println(countDivisors(101, 2001));
        System.out.println(countDivisors(8, 8));

        System.out.println(findDoubles(test));
        System.out.println(findDoubles("Haarspangenaal"));
        System.out.println(findDoubles("The Black Beast of Aaaaargh!"));
        System.out.println(findDoubles("Schokoladenkuchen"));

        System.out.println(reverseInsert(test, '.'));
        System.out.println(reverseInsert("qwerty", '-'));
        System.out.println(reverseInsert("Pinkie Pie", '!'));

        printPattern(4, '!');
        printPattern(5, '*');
    }

    public static int countDivisors(int x, int y) {
        int count = 0;
        for (int i = x; i <= y; i++) {
            if (i % 4 == 0 && i % 6 != 0) {
                count++;
            }
        }
        return count;
    }

    public static int findDoubles(String text) {
        int count = 0;
        for (int i = 0; i < text.length() - 1; i++) {
            char current = text.charAt(i);
            char next = text.charAt(i + 1);
            final char a = 'a';
            if (current == a && next == a) {
                count++;
            }
        }
        return count;
    }

    public static String reverseInsert(String text, char character) {
        String result = "";
        for (int i = text.length() - 1; i >= 0; i--) {
            result += text.charAt(i);
            if (i != 0) {
                result += character;
            }
        }
        return result;
    }

    public static void printPattern(int n, char character) {
        var row = 1;
        for (int i = row; i <= n; i++) {
            if (i % 2 == 0) {
                for (int j = 0; j < 2 * n; j++) {
                    System.out.print(character);
                }
            } else {
                for (int j = 0; j < n; j++) {
                    System.out.print(character + ".");
                }
            }
            System.out.println(i);
        }
    }
}
