package net.avax.codingpractice.integertoenglishwords;

////////////////////////////////////////////////////////////////////////////////
//
// LeetCode Integer to English Words problem:
//
// https://leetcode.com/problems/integer-to-english-words/
//
// First attempt: simple solution with some blocks of very similar code.
//
// 601 / 601 test cases passed.
// Status: Accepted
// Runtime: 8 ms, faster than 12.32% of Java online submissions for Integer to
// English Words.
//
// https://leetcode.com/submissions/detail/202133600/
//
// Second attempt: use StringJoiner to increase speed.
//
// 601 / 601 test cases passed.
// Status: Accepted
// Runtime: 6 ms, faster than 16.86% of Java online submissions for Integer to
// English Words.
//
// https://leetcode.com/submissions/detail/202245051/
//
// Third attempt: try eliminating recursion.
//
// 601 / 601 test cases passed.
// Status: Accepted
// Runtime: 7 ms, faster than 14.27% of Java online submissions for Integer to
// English Words.
//
// https://leetcode.com/submissions/detail/202280327/
//
// Fourth attempt: revert to recursive solution, changing method to static.
//
// 601 / 601 test cases passed.
// Status: Accepted
// Runtime: 6 ms, faster than 16.86% of Java online submissions for Integer to
// English Words.
//
// https://leetcode.com/submissions/detail/202284815/

import java.util.StringJoiner;

class Solution {
    private static final int powerBase = 1_000;
    private static final int maxPowerValue = powerBase * powerBase * powerBase;
    private static final String[] powerValueNames = {"Thousand", "Million",
            "Billion"};
    private static final String hundredName = "Hundred";
    private static final String[] multipleOfTenNames = {"Twenty", "Thirty",
            "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    private static final String[] underTwentyNames = {"Zero", "One", "Two",
            "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
            "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen",
            "Seventeen", "Eighteen", "Nineteen"};

    private static void numToWords(int num, StringJoiner sj) {
        if (num >= powerBase) {
            int powerValue = maxPowerValue;
            int powerValueNameIndex = powerValueNames.length;

            do {
                powerValueNameIndex--;

                int powerValueNumPart = num / powerValue % powerBase;

                if (powerValueNumPart != 0) {
                    numToWords(powerValueNumPart, sj);

                    if (powerValueNameIndex >= 0) {
                        String powerValueName
                                = powerValueNames[powerValueNameIndex];

                        sj.add(powerValueName);
                    }
                }

                powerValue /= powerBase;
            } while (powerValue > 0);
        } else if (num >= 100) {
            int quotient = num / 100;
            int remainder = num % 100;

            numToWords(quotient, sj);
            sj.add(hundredName);

            if (remainder != 0) {
                numToWords(remainder, sj);
            }
        } else if (num >= 20) {
            int quotient = num / 10;
            int remainder = num % 10;

            sj.add(multipleOfTenNames[quotient - 2]);

            if (remainder != 0) {
                numToWords(remainder, sj);
            }
        } else if (num >= 0) {
            sj.add(underTwentyNames[num]);
        }
    }

    @SuppressWarnings({"WeakerAccess"})
    public String numberToWords(int num) {
        StringJoiner sj = new StringJoiner(" ");

        numToWords(num, sj);

        return sj.toString();
    }
}
