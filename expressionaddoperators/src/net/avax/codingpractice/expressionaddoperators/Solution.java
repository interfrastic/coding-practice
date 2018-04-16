package net.avax.codingpractice.expressionaddoperators;

// 2018-04-06 Rupert's "LeetCode problems for rainy days," bonus problem:
//
// https://leetcode.com/problems/expression-add-operators/description/
//
// 3 / 20 test cases passed.
// Status: Time Limit Exceeded
// Last executed input:
// "3456237490"
// 9191
//
// https://leetcode.com/submissions/detail/149570235/

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Solution {
    public List<String> addOperators(String num, int target) {
        return getTimesCombos(num).stream().map(c -> c.str).collect
                (Collectors.toList());
//        return getTimesMatches(num, BigInteger.valueOf(target));
//        return getMatches(num, BigInteger.valueOf(target));
    }

    public List<String> addOperatorsRefactored(String num, int target) {
        return getTimesCombosRefactored(num).stream().map(c -> c.str).collect
                (Collectors.toList());
    }

    private List<String> getMatches(String num, BigInteger target) {
        List<String> matches = new ArrayList<>();

        int length = num.length();

        if (length > 0) {
            for (int endIndex = 1; endIndex <= length; endIndex++) {
                String str1 = num.substring(0, endIndex);

                if (endIndex == length) {
                    matches.addAll(getTimesMatches(str1, target));
                } else {
                    String str2 = num.substring(endIndex);

                    for (char operator : new char[]{'+', '-'}) {
                        for (Combo combo1 : getTimesCombos(str1)) {
                            BigInteger newTarget = (operator == '+')
                                    ? target.subtract(combo1.val)
                                    : combo1.val.subtract(target);

                            for (String match : getMatches(str2, newTarget)) {
                                matches.add(combo1.str + operator + match);
                            }
                        }
                    }
                }
            }
        }

        return matches;
    }

    private List<String> getTimesMatches(String str, BigInteger target) {
        List<String> matches = new ArrayList<>();

        for (Combo combo : getTimesCombos(str)) {
            if (combo.val.equals(target)) {
                matches.add(combo.str);
            }
        }

        return matches;
    }

    private List<Combo> getTimesCombos(String str) {
        List<Combo> combos = new ArrayList<>();

        int length = str.length();

        if (length > 0) {
            int maxEndIndex = (str.charAt(0) == '0') ? 1 : length;

            for (int endIndex = 1; endIndex <= maxEndIndex; endIndex++) {
                String str1 = str.substring(0, endIndex);
                BigInteger val1 = new BigInteger(str1);

                if (endIndex == length) {
                    combos.add(new Combo(str1, val1));
                } else {
                    String str2 = str.substring(endIndex);

                    for (Combo combo : getTimesCombos(str2)) {
                        String comboStr = str1 + "*" + combo.str;
                        BigInteger comboVal = val1.multiply(combo.val);

                        combos.add(new Combo(comboStr, comboVal));
                    }
                }
            }
        }

        return combos;
    }

    private List<Combo> getTimesCombosRefactored(String str) {
        List<Combo> combos = new ArrayList<>();

        int length = str.length();

        if (length > 0) {
            int placeCount = length - 1;
            long comboCount = (1L << placeCount);

            COMBO_LOOP:
            for (long comboIndex = 0; comboIndex < comboCount; comboIndex++) {
                StringBuilder sb = new StringBuilder();
                BigInteger comboVal = BigInteger.ONE;
                int prevOperandIndex = 0;
                int digitIndex = 0;
                char digit = str.charAt(digitIndex);
                boolean isLeadingZeroPresent = digit == '0';
                sb.append(digit);

                int placeIndex = placeCount;

                while (--placeIndex >= 0) {
                    digit = str.charAt(++digitIndex);

                    if ((comboIndex / (1L << placeIndex)) % 2 != 0) {
                        String operandStr = str.substring(
                                prevOperandIndex, digitIndex);
                        BigInteger operandVal
                                = new BigInteger(operandStr);

                        comboVal = comboVal.multiply(operandVal);
                        sb.append("*");
                        prevOperandIndex = digitIndex - 1;
                        isLeadingZeroPresent = digit == '0';
                    } else if (isLeadingZeroPresent) {
                        continue COMBO_LOOP;
                    }

                    sb.append(digit);
                }

                String comboStr = sb.toString();
                String remainingStr = str.substring(prevOperandIndex);
                BigInteger remainingVal = new BigInteger(remainingStr);

                comboVal = comboVal.multiply(remainingVal);

                combos.add(new Combo(comboStr, comboVal));
            }
        }

        return combos;
    }

    private static class Combo {
        final String str;
        final BigInteger val;

        Combo(String str, BigInteger val) {
            this.str = str;
            this.val = val;
        }
    }
}
