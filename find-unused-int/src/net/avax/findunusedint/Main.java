package net.avax.findunusedint;

import java.util.BitSet;
import java.util.Scanner;

public class Main {
    private static final int BITS_PER_BIN = Character.BYTES * Byte.SIZE;
    private static final int BINS_SIZE = (Integer.MAX_VALUE / BITS_PER_BIN) + 1;

    private static void recordUsedInt(BitSet bins, int n) {
        bins.set(n);
    }

    private static int findLowestUnusedInt(BitSet bins) {
        return bins.nextClearBit(0);
    }

    public static void main(String[] args) {
        BitSet bins = new BitSet();
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
