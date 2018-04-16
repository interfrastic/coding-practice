package net.avax.codingpractice.expressionaddoperators;

// 2018-04-06 Rupert's "LeetCode problems for rainy days," bonus problem:
//
// https://leetcode.com/problems/expression-add-operators/description/
//
// 20 / 20 test cases passed.
// Status: Accepted
// Runtime: 588 ms
// Your runtime beats 1.94 % of java submissions.
//
// https://leetcode.com/submissions/detail/150316068/

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

class Solution {
    private final int OP_BIT_COUNT = 2;
    private final int OP_NONE = 0;
    private final int OP_TIMES = 1;
    private final int OP_PLUS = 2;
    private final int OP_MINUS = 3;
    private final long BEGIN_MASK = (1L << OP_BIT_COUNT) - 1;
    private final char[] SYMBOL_FOR_OP = new char[]{'_', '*', '+', '-'};

    public List<String> addOperators(String num, int target) {
        List<String> matches = new ArrayList<>();
        int length = num.length();

        if (length > 0) {
            int opPosCount = length - 1;
            int opComboBitCount = opPosCount * OP_BIT_COUNT;
            long opComboCount = 1L << opComboBitCount;
            int maxBitIndex = opComboBitCount - OP_BIT_COUNT;

            OP_COMBO_LOOP:
            for (long opCombo = 0; opCombo < opComboCount; opCombo++) {
                long mask = BEGIN_MASK << maxBitIndex;
                int bitIndex = maxBitIndex;
                int beginIndex = 0;
                int prevOp = OP_NONE;
                long accumulator = 0;
                long opComboVal = 0;
                StringBuilder opComboSb = new StringBuilder();

                OP_POS_LOOP:
                for (int endIndex = 1; endIndex <= length; endIndex++) {
                    int op = (int) ((opCombo & mask) >> bitIndex);

                    mask >>= OP_BIT_COUNT;
                    bitIndex -= OP_BIT_COUNT;

                    if (op == OP_NONE && endIndex < length) {
                        continue OP_POS_LOOP;
                    }

                    String str = num.substring(beginIndex, endIndex);

                    if (str.length() > 1 && str.charAt(0) == '0') {
                        continue OP_COMBO_LOOP;
                    }

                    long val = Long.parseLong(str);

                    if (prevOp == OP_TIMES) {
                        accumulator *= val;
                    } else {
                        opComboVal += accumulator;
                        accumulator = (prevOp == OP_MINUS) ? -val : val;
                    }

                    opComboSb.append(str);

                    if (endIndex < length) {
                        opComboSb.append(SYMBOL_FOR_OP[op]);
                    } else {
                        opComboVal += accumulator;
                    }

                    beginIndex = endIndex;
                    prevOp = op;
                }

                if (opComboVal == target) {
                    matches.add(opComboSb.toString());
                }
            }
        }

        return matches;
    }

    public List<String> addOperatorsOriginal(String num, int target) {
        return getMatches(num, BigInteger.valueOf(target));
    }

    public List<String> addOperatorsRefactoredBigInt(String num, int target) {
        List<String> matches = new ArrayList<>();

        int length = num.length();

        if (length > 0) {
            BigInteger targetVal = BigInteger.valueOf(target);
            int opPosCount = length - 1;

            int comboBitCount = opPosCount * OP_BIT_COUNT;
            long comboCount = 1L << comboBitCount;
            int maxBitIndex = (opPosCount - 1) * OP_BIT_COUNT;

            COMBO_LOOP:
            for (long combo = 0; combo < comboCount; combo++) {
                BigInteger comboVal = BigInteger.ZERO;
                int prevOp = OP_NONE;
                BigInteger termVal = BigInteger.ZERO;
                long mask = BEGIN_MASK << maxBitIndex;
                int bitIndex = maxBitIndex;
                int beginIndex = 0;
                StringBuilder sb = new StringBuilder();

                for (int endIndex = 1; endIndex <= length; endIndex++) {
                    int op = (int) ((combo & mask) >> bitIndex);

                    if (op != OP_NONE || endIndex == length) {
                        String str = num.substring(beginIndex, endIndex);

                        if (str.length() > 1 && str.charAt(0) == '0') {
                            continue COMBO_LOOP;
                        }

                        sb.append(str);

                        BigInteger val = new BigInteger(str);

                        switch (prevOp) {
                            case OP_TIMES:
                                termVal = termVal.multiply(val);
                                break;
                            case OP_MINUS:
                                comboVal = comboVal.add(termVal);
                                termVal = val.negate();
                                break;
                            default:
                                comboVal = comboVal.add(termVal);
                                termVal = val;
                                break;
                        }

                        if (endIndex < length) {
                            sb.append(SYMBOL_FOR_OP[op]);
                        } else {
                            comboVal = comboVal.add(termVal);
                        }

                        beginIndex = endIndex;
                        prevOp = op;
                    }

                    mask >>= OP_BIT_COUNT;
                    bitIndex -= OP_BIT_COUNT;
                }

                String comboStr = sb.toString();

                if (comboVal.equals(targetVal)) {
                    matches.add(comboStr);
                }
            }
        }

        return matches;
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
