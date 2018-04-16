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
}
