package net.avax.findunusedint;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final long COUNT_MAX = 4000000000L;
    private static final int INPUT_BITS = 32;
    private static final long INPUT_SIZE = 1L << INPUT_BITS;
    private static final int INPUT_BITS_PER_BIT_SET = 31;
    private static final long BIT_SET_SIZE = 1L << INPUT_BITS_PER_BIT_SET;
    private static final int BIT_SET_COUNT = (int) (INPUT_SIZE / BIT_SET_SIZE);

    private static void recordUsedInt(List<BitSet> bitSets, long n) {
        int bitSetNum = (int) (n / BIT_SET_SIZE);
        int bitNum = (int) (n % BIT_SET_SIZE);

        bitSets.get(bitSetNum).set(bitNum);
    }

    private static long findLowestUnusedInt(List<BitSet> bitSets) {
        for (int bitSetIndex = 0; bitSetIndex < bitSets.size(); bitSetIndex++) {
            int clearBit = bitSets.get(bitSetIndex).nextClearBit(0);

            if (clearBit >= 0) {
                return bitSetIndex * BIT_SET_SIZE + clearBit;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        List<BitSet> bitSets = new ArrayList<>();

        for (int i = 0; i < BIT_SET_COUNT; i++) {
            bitSets.add(new BitSet());
        }

        long n;
        long count = 0;

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLong() && (n = sc.nextLong()) >= 0 && n < INPUT_SIZE
                && count < COUNT_MAX) {
            recordUsedInt(bitSets, n);
            count++;
        }

        System.out.println("Read " + count + " " + INPUT_BITS
                + "-bit unsigned integer(s).");

        n = findLowestUnusedInt(bitSets);

        System.out.println(n + " is the lowest unused integer.");
    }
}
