package net.avax.codingpractice.romantoint;

// 13. Roman to Integer
// Easy
//
// https://leetcode.com/problems/roman-to-integer/

// First attempt: Try first simple solution that comes to mind.
//
// 3999 / 3999 test cases passed.
// Status: Accepted
// Runtime: 17 ms, faster than 5.13% of Java online submissions for Roman to
// Integer.
// Memory Usage: 37.5 MB, less than 31.14% of Java online submissions for Roman
// to Integer.
//
// https://leetcode.com/submissions/detail/249809371/

// Second attempt: Try to optimize for speed and memory usage.
//
// 3999 / 3999 test cases passed.
// Status: Accepted
// Runtime: 3 ms, faster than 100.00% of Java online submissions for Roman to
// Integer.
// Memory Usage: 35.9 MB, less than 100.00% of Java online submissions for Roman
// to Integer.
//
// https://leetcode.com/submissions/detail/251655084/

@SuppressWarnings("WeakerAccess")
public class Solution {
    public int romanToInt(String s) {
        int value = 0;
        int prevValue = 0;

        for (char thisCh : s.toCharArray()) {
            int thisValue;

            // Roman numerals are represented by seven different symbols:
            // I, V, X, L, C, D and M.

            switch (thisCh) {
                case 'I':
                    thisValue = 1;

                    break;
                case 'V':
                    thisValue = 5;

                    // I can be placed before V (5) and X (10) to make 4 and 9.

                    if (prevValue == 1) {
                        thisValue -= 2 * prevValue;
                    }

                    break;
                case 'X':
                    thisValue = 10;

                    // I can be placed before V (5) and X (10) to make 4 and 9.

                    if (prevValue == 1) {
                        thisValue -= 2 * prevValue;
                    }

                    break;
                case 'L':
                    thisValue = 50;

                    // X can be placed before L (50) and C (100) to make 40 and
                    // 90.

                    if (prevValue == 10) {
                        thisValue -= 2 * prevValue;
                    }

                    break;
                case 'C':
                    thisValue = 100;

                    // X can be placed before L (50) and C (100) to make 40 and
                    // 90.

                    if (prevValue == 10) {
                        thisValue -= 2 * prevValue;
                    }

                    break;
                case 'D':
                    thisValue = 500;

                    // C can be placed before D (500) and M (1000) to make 400
                    // and 900.

                    if (prevValue == 100) {
                        thisValue -= 2 * prevValue;
                    }

                    break;
                case 'M':
                    thisValue = 1000;

                    // C can be placed before D (500) and M (1000) to make 400
                    // and 900.

                    if (prevValue == 100) {
                        thisValue -= 2 * prevValue;
                    }

                    break;
                default:
                    thisValue = 0;

                    break;
            }

            value += thisValue;

            prevValue = thisValue;
        }

        return value;
    }
}
