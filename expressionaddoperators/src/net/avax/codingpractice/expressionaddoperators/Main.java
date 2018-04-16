package net.avax.codingpractice.expressionaddoperators;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        int bitCount = 3;
//        BigInteger digitCount = BigInteger.valueOf(2).pow(bitCount);
//        int placeCount = 3;
        boolean isOutputEnabled = true;
//
//        long beginTime = System.nanoTime();
//        List<String> combos = getCombos(digitCount, placeCount);
//        long runTime = System.nanoTime() - beginTime;
//        if (isOutputEnabled) {
//            System.out.println(combos);
//        }
//        System.out.println(runTime / 1_000_000_000.0);
//
//        beginTime = System.nanoTime();
//        combos = getCombosQuickly(bitCount, placeCount);
//        runTime = System.nanoTime() - beginTime;
//        if (isOutputEnabled) {
//            System.out.println(combos);
//        }
//        System.out.println(runTime / 1_000_000_000.0);

        Solution solution = new Solution();

//        String num = "1234567890123";
//        int target = 9191;
//        String num = "10737418231073741823";
//        int target = 2147483646;
        String num = "214748360740";
        int target = 2_147_483_647;
        boolean useRefactored = true;


        if (useRefactored) {
            System.out.println("Refactored:");
        } else {
            System.out.println("Original:");
        }
        long beginTime = System.nanoTime();
        List<String> matches = useRefactored
                ? solution.addOperatorsRefactored(num, target)
                : solution.addOperators(num, target);
        long runTime = System.nanoTime() - beginTime;
        if (isOutputEnabled) {
            System.out.println(matches);
        }
        System.out.println(runTime / 1_000_000_000.0);

//        System.out.println(solution.addOperators("123", 6));
//        System.out.println(solution.addOperators("232", 8));
//        System.out.println(solution.addOperators("105", 5));
//        System.out.println(solution.addOperators("00", 0));
//        System.out.println(solution.addOperators("3456237490", 9191));
//        System.out.println(solution.addOperators("123", 24));
//        System.out.println("Original:");
//        beginTime = System.nanoTime();
//        List<String> matches = solution.addOperators("10737418231073741823",
//                2147483646);
//        runTime = System.nanoTime() - beginTime;
//        System.out.println(runTime / 1_000_000_000.0);
//        beginTime = System.nanoTime();
//        matches = solution.addOperators("10737418231073741823",
//                2147483646);
//        runTime = System.nanoTime() - beginTime;
//        System.out.println(runTime / 1_000_000_000.0);
//        beginTime = System.nanoTime();
//        matches = solution.addOperators("10737418231073741823",
//                2147483646);
//        runTime = System.nanoTime() - beginTime;
//        System.out.println(runTime / 1_000_000_000.0);
//        System.out.println("Refactored:");
//        long beginTimeRefactored = System.nanoTime();
//        List<String> matchesRefactored = solution.addOperatorsRefactored
//                ("10737418231073741823",
//                        2147483646);
//        long runTimeRefactored = System.nanoTime() - beginTimeRefactored;
//        System.out.println(runTimeRefactored / 1_000_000_000.0);
//        beginTimeRefactored = System.nanoTime();
//        matchesRefactored = solution.addOperatorsRefactored
//                ("10737418231073741823",
//                        2147483646);
//        runTimeRefactored = System.nanoTime() - beginTimeRefactored;
//        System.out.println(runTimeRefactored / 1_000_000_000.0);
//        System.out.println(runTimeRefactored / 1_000_000_000.0);
//        beginTimeRefactored = System.nanoTime();
//        matchesRefactored = solution.addOperatorsRefactored
//                ("10737418231073741823",
//                        2147483646);
//        runTimeRefactored = System.nanoTime() - beginTimeRefactored;
//        System.out.println(runTimeRefactored / 1_000_000_000.0);
//        System.out.println("Change: " + (((double) runTimeRefactored -
//                (double) runTime) / (double) runTime * 100.0) + "%");
    }

    private static List<String> getCombos(BigInteger digitCount, int
            placeCount) {
        List<String> combos = new ArrayList<>();

        BigInteger comboCount = digitCount.pow(placeCount);

        for (BigInteger combo = BigInteger.ZERO;
             combo.compareTo(comboCount) < 0;
             combo = combo.add(BigInteger.ONE)) {
            StringBuilder sb = new StringBuilder();

            int place = placeCount;
            while (--place >= 0) {
                sb.append(combo.divide(digitCount.pow(place))
                        .remainder(digitCount));
            }
            combos.add(sb.toString());
        }

        return combos;
    }

    private static List<String> getCombosQuickly(int bitCount, int placeCount) {
        List<String> combos = new ArrayList<>();

        long symbolCount = (1L << bitCount);
        long comboCount = 1;

        for (int i = 0; i < placeCount; i++) {
            comboCount *= symbolCount;
        }

        for (long combo = 0; combo < comboCount; combo++) {
            StringBuilder sb = new StringBuilder();

            int place = placeCount;
            while (--place >= 0) {
                sb.append((combo / (1L << (place * bitCount))) % symbolCount);
            }

            combos.add(sb.toString());
        }

        return combos;
    }
}
