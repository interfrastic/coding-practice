package net.avax.findunusedint;

import java.util.Scanner;

public class Main {
    private static final int BITS_PER_BIN = Character.BYTES * Byte.SIZE;
    private static final int BINS_SIZE = (Integer.MAX_VALUE / BITS_PER_BIN) + 1;

    private static void recordUsedInt(char[] bins, int n) {
        int bin_index = n / BITS_PER_BIN;
        char bin_mask = (char) (1 << (n % BITS_PER_BIN));

        bins[bin_index] |= bin_mask;
    }

    private static int findLowestUnusedInt(char[] bins) {
        for (int n = 0, bin_index = 0; bin_index < BINS_SIZE; bin_index++) {
            for (int bit = 0; bit < BITS_PER_BIN; bit++, n++) {
                char bin_mask = (char) (1 << bit);

                if ((bins[bin_index] & bin_mask) == 0) {
                    return n;
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        char bins[] = new char[BINS_SIZE];
        int n;
        long count = 0;

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextInt() && (n = sc.nextInt()) >= 0) {
            recordUsedInt(bins, n);
            count++;
        }

        System.out.println("Read " + count + " non-negative integer(s).");

        int found = findLowestUnusedInt(bins);

        if (found >= 0) {
            System.out.println("The lowest unused non-negative integer is " + found + ".");
        } else {
            System.out.println("All possible non-negative integer values have been used.");
        }
    }
}
