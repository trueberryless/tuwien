package org.trueberryless.ep1.k2.einstufungstest;

public class TestExample2 {
    public static void main(String[] args) {
        short result = (short) getIntegerRoot(25);
        String test = "Blaukraut";

        System.out.println(getIntegerRoot(144));
        System.out.println(getIntegerRoot(13));
        System.out.println(getIntegerRoot(1));

        System.out.println(getThird("toss", "a", "coin"));
        System.out.println(getThird("Blaukraut", "bleibt", test));
        System.out.println(getThird("badger", "badger", "badger"));

        System.out.println(replaceA("TU Wien"));
        System.out.println(replaceA("Hubba bubba!"));
        System.out.println(replaceA("aaaa"));

        printBars(2);
        printBars(19);
        printBars(20);
        printBars(21);
    }

    public static int getIntegerRoot(int k) {
        for (int i = 0; i <= (int) Math.max(k / 2, 1); i++) {
            if (i * i == k)
                return i;
        }
        return -1;
    }

    public static String getThird(String a, String b, String c) {
        if (a.equals(b)){
            if (a.equals(c)) {
                return "alle gleich";
            } else {
                return c;
            }
        } else if (b.equals(c)) {
            return a;
        } else if (a.equals(c)) {
            return b;
        }
        return "alle unterschiedlich";
    }

    public static String replaceA(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);
            if (current == 'a') {
                count++;
                s = s.substring(0, i) + count + s.substring(i + 1);
            }
        }
        return s;
    }

    public static void printBars(int i) {
        char symbol = '-';
        for (int j = 1; j <= i; j++) {
            if (j % 3 == 0) continue;
            System.out.print(j + "" + symbol);
            symbol = symbol == '-' ? '+' : '-';
        }
        if (i > 0) System.out.println();
        symbol = '+';
        for (int j = 1; j <= i; j++) {
            if (j % 3 != 0) continue;
            System.out.print(j + "" + symbol);
            symbol = symbol == '-' ? '+' : '-';
        }
        if (i >= 3) System.out.println();
    }
}
