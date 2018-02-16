package net.avax.findunusedint;

// From Programming Pearls, Second Edition, by Jon Bentley, Addison-Wesley,
// Inc., 2000, ISBN 0-201-65788-0:
//
// Given a sequential file that contains at most four billion integers in
// random order, find a 32-bit integer that isn't in the file (and there must
// be at least one missing--why?).  How would you solve this problem with ample
// quantities of main memory? How would you solve it if you could use several
// external "scratch" files but only a few hundred bytes of main memory?

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final long COUNT_MAX = 4000000000L;
    private static final int INPUT_BITS = 32;
    private static final long INPUT_SIZE = 1L << INPUT_BITS;
    private static final int INPUT_BITS_PER_BIT_SET = 28;
    private static final long BIT_SET_SIZE = 1L << INPUT_BITS_PER_BIT_SET;
    private static final int BIT_SET_COUNT = (int) (INPUT_SIZE / BIT_SET_SIZE);

    private static BitSet activeBitSet = null;
    private static int activeBitSetNum = -1;
    private static List<Path> bitSetPaths = null;

    private static void saveActiveBitSet() throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(
                        bitSetPaths.get(activeBitSetNum).toFile()))) {
            oos.writeObject(activeBitSet);
        }
    }

    private static void activateBitSet(int bitSetNum) throws
            IOException, ClassNotFoundException {
        if (bitSetNum == activeBitSetNum) {
            return;
        }

        saveActiveBitSet();

        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(
                        bitSetPaths.get(bitSetNum).toFile()))) {
            activeBitSet = (BitSet) ois.readObject();
        }

        activeBitSetNum = bitSetNum;
    }

    private static void recordUsedInt(long n) throws IOException,
            ClassNotFoundException {
        int bitSetNum = (int) (n / BIT_SET_SIZE);
        int bitNum = (int) (n % BIT_SET_SIZE);

        activateBitSet(bitSetNum);
        activeBitSet.set(bitNum);
    }

    private static long findLowestUnusedInt() throws IOException,
            ClassNotFoundException {
        for (int bitSetNum = 0; bitSetNum < bitSetPaths.size(); bitSetNum++) {
            activateBitSet(bitSetNum);

            int clearBit = activeBitSet.nextClearBit(0);

            if (clearBit >= 0) {
                return bitSetNum * BIT_SET_SIZE + clearBit;
            }
        }

        return -1;
    }

    public static void main(String[] args) throws IOException,
            ClassNotFoundException {
        bitSetPaths = new ArrayList<>();

        for (int bitSetNum = 0; bitSetNum < BIT_SET_COUNT; bitSetNum++) {
            Path path = Files.createTempFile("find-unused-int-", ".tmp");

            path.toFile().deleteOnExit();
            bitSetPaths.add(path);

            activeBitSet = new BitSet();
            activeBitSetNum = bitSetNum;

            saveActiveBitSet();
        }

        long n;
        long count = 0;

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLong() && (n = sc.nextLong()) >= 0 && n < INPUT_SIZE
                && count < COUNT_MAX) {
            recordUsedInt(n);
            count++;
        }

        System.out.println("Read " + count + " " + INPUT_BITS
                + "-bit unsigned integer(s).");

        n = findLowestUnusedInt();

        System.out.println(n + " is the lowest unused integer.");
    }
}
