package net.avax.codingpractice.integertoenglishwords;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"WeakerAccess"})

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

class Solution {
    private static int powerBase = 1_000;
    private static int maxPowerValue = powerBase * powerBase * powerBase;
    private static String[] powerValueNames = {"Thousand", "Million",
            "Billion"};
    private static String hundredName = "Hundred";
    private static String[] multipleOfTenNames = {"Twenty", "Thirty", "Forty",
            "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    private static String[] underTwentyNames = {"Zero", "One", "Two", "Three",
            "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven",
            "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen",
            "Eighteen", "Nineteen"};

    private List<String> numToWords(int num) {
        List<String> words = new ArrayList<>();

        if (num >= powerBase) {
            int powerValue = maxPowerValue;
            int powerValueNameIndex = powerValueNames.length;

            do {
                powerValueNameIndex--;

                int powerValueNumPart = num / powerValue % powerBase;

                if (powerValueNumPart != 0) {
                    words.addAll(numToWords(powerValueNumPart));

                    if (powerValueNameIndex >= 0) {
                        String powerValueName
                                = powerValueNames[powerValueNameIndex];

                        words.add(powerValueName);
                    }
                }

                powerValue /= powerBase;
            } while (powerValue > 0);
        } else if (num >= 100) {
            int quotient = num / 100;
            int remainder = num % 100;

            words.addAll(numToWords(quotient));
            words.add(hundredName);

            if (remainder != 0) {
                words.addAll(numToWords(remainder));
            }
        } else if (num >= 20) {
            int quotient = num / 10;
            int remainder = num % 10;

            words.add(multipleOfTenNames[quotient - 2]);

            if (remainder != 0) {
                words.addAll(numToWords(remainder));
            }
        } else if (num >= 0) {
            words.add(underTwentyNames[num]);
        }

        return words;
    }

    public String numberToWords(int num) {
        return String.join(" ", numToWords(num));
    }
}
