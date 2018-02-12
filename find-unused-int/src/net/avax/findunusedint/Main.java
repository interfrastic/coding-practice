package net.avax.findunusedint;

import java.util.Scanner;

public class Main {
    private static void recordUsedInt(int usedInt) {
    }

    private static Integer findLowestUnusedInt() {
        Integer result = null;

        return result;
    }

    public static void main(String[] args) {
        long count = 0;

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextInt()) {
            recordUsedInt(sc.nextInt());
            count++;
        }

        System.out.println("Read " + count + " integer(s).");

        Integer result = findLowestUnusedInt();

        if (result == null) {
            System.out.println("All possible integer values have been used.");
        } else {
            System.out.println("The lowest unused integer is " + result + ".");
        }
    }
}
