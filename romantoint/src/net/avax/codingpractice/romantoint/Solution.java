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

// Third attempt: Try to keep the speed but improve clarity.
//
// 3999 / 3999 test cases passed.
// Status: Accepted
// Runtime: 3 ms, faster than 100.00% of Java online submissions for Roman to
// Integer.
// Memory Usage: 36 MB, less than 100.00% of Java online submissions for Roman
// to Integer.
//
// https://leetcode.com/submissions/detail/251694454/

@SuppressWarnings("WeakerAccess")
public class Solution {

    // I can be placed before V (5) and X (10) to make 4 and 9.
    // X can be placed before L (50) and C (100) to make 40 and 90.
    // C can be placed before D (500) and M (1000) to make 400 and 900.

    private boolean canMerge(char c1, char c2) {
        return (c1 == 'I' && (c2 == 'V' || c2 == 'X'))
                || (c1 == 'X' && (c2 == 'L' || c2 == 'C'))
                || (c1 == 'C' && (c2 == 'D' || c2 == 'M'));
    }

    public int romanToInt(String s) {
        int value = 0;
        char prevCh = '\0';
        int prevValue = 0;

        for (char thisCh : s.toCharArray()) {
            int thisValue;

            // If the previous character can be subtracted from the current
            // one, then convert the previous addition into a subtraction by
            // subtracting twice the value of the previous character; for
            // example:
            //
            // XL = 10 - (2 * 10) + 50 = 40

            if (canMerge(prevCh, thisCh)) {
                value -= 2 * prevValue;
            }

            // Roman numerals are represented by seven different symbols:
            // I, V, X, L, C, D and M.

            switch (thisCh) {
                case 'I':
                    thisValue = 1;
                    break;
                case 'V':
                    thisValue = 5;
                    break;
                case 'X':
                    thisValue = 10;
                    break;
                case 'L':
                    thisValue = 50;
                    break;
                case 'C':
                    thisValue = 100;
                    break;
                case 'D':
                    thisValue = 500;
                    break;
                case 'M':
                    thisValue = 1000;
                    break;
                default:
                    thisValue = 0;
                    break;
            }

            value += thisValue;

            prevCh = thisCh;
            prevValue = thisValue;
        }

        return value;
    }
}
