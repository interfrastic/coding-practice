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

import java.util.StringJoiner;

class Solution {
    private static void numUnderTwentyToWords(int num, StringJoiner sj) {
        final String[] underTwentyName = {"Zero", "One", "Two", "Three", "Four",
                "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven",
                "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen",
                "Seventeen", "Eighteen", "Nineteen"};

        sj.add(underTwentyName[num]);
    }

    private static void numTwentyToNinetyNineToWords(int num, StringJoiner sj) {
        final String[] multipleOfTenNames = {"Twenty", "Thirty", "Forty",
                "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

        int tens = num / 10;
        int remainder = num % 10;

        sj.add(multipleOfTenNames[tens - 2]);

        if (remainder != 0 || sj.length() == 0) {
            numUnderTwentyToWords(remainder, sj);
        }
    }

    private static void numUnderThousandToWords(int num, StringJoiner sj) {
        final String hundredName = "Hundred";
        int hundreds = num / 100;
        int remainder = num % 100;

        if (hundreds != 0) {
            numUnderTwentyToWords(hundreds, sj);
            sj.add(hundredName);
        }

        if (remainder != 0 || sj.length() == 0) {
            if (remainder < 20) {
                numUnderTwentyToWords(remainder, sj);
            } else {
                numTwentyToNinetyNineToWords(remainder, sj);
            }
        }
    }

    private static void numToWords(int num, StringJoiner sj) {
        final String[] powerValueNames = {"Billion", "Million", "Thousand"};
        int powerValue = 1_000_000_000;

        for (String powerValueName : powerValueNames) {
            int powerValueNumPart = num / powerValue % 1_000;

            if (powerValueNumPart != 0) {
                numUnderThousandToWords(powerValueNumPart, sj);
                sj.add(powerValueName);
            }

            powerValue /= 1_000;
        }

        int underThousandNumPart = num % 1_000;

        if (underThousandNumPart != 0 || sj.length() == 0) {
            numUnderThousandToWords(underThousandNumPart, sj);
        }
    }

    @SuppressWarnings({"WeakerAccess"})
    public String numberToWords(int num) {
        StringJoiner sj = new StringJoiner(" ");

        numToWords(num, sj);

        return sj.toString();
    }
}
