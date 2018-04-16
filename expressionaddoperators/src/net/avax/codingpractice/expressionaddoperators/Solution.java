package net.avax.codingpractice.expressionaddoperators;

// 2018-04-06 Rupert's "LeetCode problems for rainy days," bonus problem:
//
// https://leetcode.com/problems/expression-add-operators/description/
//
// 20 / 20 test cases passed.
// Status: Accepted
// Runtime: 784 ms
// [No message about beating any other submissions . . . is it the worst?!]
//
// https://leetcode.com/submissions/detail/150319549/

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> addOperators(String num, int target) {
        List<String> matches = new ArrayList<>();
        int length = num.length();

        if (length > 0) {
            int opPosCount = length - 1;
            int opComboBitCount = opPosCount * Op.BIT_COUNT;
            long opComboCount = 1L << opComboBitCount;
            int maxBitIndex = opComboBitCount - Op.BIT_COUNT;

            OP_COMBO_LOOP:
            for (long opCombo = 0; opCombo < opComboCount; opCombo++) {
                long mask = Op.MASK << maxBitIndex;
                int bitIndex = maxBitIndex;
                int beginIndex = 0;
                Op prevOp = Op.NONE;
                long accumulator = 0;
                long opComboVal = 0;
                StringBuilder opComboSb = new StringBuilder();

                for (int endIndex = 1; endIndex <= length; endIndex++) {
                    Op op = Op.values()[(int) ((opCombo & mask) >> bitIndex)];

                    mask >>= Op.BIT_COUNT;
                    bitIndex -= Op.BIT_COUNT;

                    if (op == Op.NONE && endIndex < length) {
                        continue;
                    }

                    String str = num.substring(beginIndex, endIndex);

                    if (str.length() > 1 && str.charAt(0) == '0') {
                        continue OP_COMBO_LOOP;
                    }

                    long val = Long.parseLong(str);

                    if (prevOp == Op.TIMES) {
                        accumulator *= val;
                    } else {
                        opComboVal += accumulator;
                        accumulator = (prevOp == Op.MINUS) ? -val : val;
                    }

                    opComboSb.append(str);

                    if (endIndex < length) {
                        opComboSb.append(op);
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

    private enum Op {
        NONE(""),
        TIMES("*"),
        PLUS("+"),
        MINUS("-");

        final static int BIT_COUNT = 2;
        final static long MASK = (1L << Op.BIT_COUNT) - 1;

        final String symbol;

        Op(String symbol) {
            this.symbol = symbol;
        }

        public String toString() {
            return symbol;
        }
    }
}
