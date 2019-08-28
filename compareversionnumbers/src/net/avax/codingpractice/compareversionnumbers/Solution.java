package net.avax.codingpractice.compareversionnumbers;

// 165. Compare Version Numbers
// Medium
//
// https://leetcode.com/problems/compare-version-numbers/

// First attempt: Try first simple solution that comes to mind.
//
// 72 / 72 test cases passed.
// Status: Accepted
// Runtime: 43 ms, faster than 8.00% of Java online submissions for Compare
// Version Numbers.
// Memory Usage: 35.2 MB, less than 100.00% of Java online submissions for
// Compare Version Numbers.
//
// https://leetcode.com/submissions/detail/253699732/

// Second attempt: Optimize for speed.
//
// 60 / 72 test cases passed.
// Status: Wrong Answer
//
// https://leetcode.com/submissions/detail/253992721/

import static java.lang.Character.isDigit;

@SuppressWarnings("WeakerAccess")
public class Solution {
    public int compareVersion(String version1, String version2) {
        int indexLimit1 = version1.length();
        int indexLimit2 = version2.length();
        int index1 = 0;
        int index2 = 0;

        while (index1 < indexLimit1 || index2 < indexLimit2) {
            index1 = skipLeadingZeroes(version1, index1);
            index2 = skipLeadingZeroes(version2, index2);

            char c1 = '\0';
            char c2 = '\0';
            char cc1;
            char cc2;

            do {
                if (index1 < indexLimit1) {
                    c1 = version1.charAt(index1);
                    cc1 = (c1 == '.') ? '0' : c1;
                    index1++;
                } else {
                    cc1 = '0';
                }

                if (index2 < indexLimit2) {
                    c2 = version2.charAt(index2);
                    cc2 = (c2 == '.') ? '0' : c2;
                    index2++;
                } else {
                    cc2 = '0';
                }

                if (cc1 != cc2) {
                    return (cc1 > cc2) ? 1 : -1;
                }

            } while (!((index1 == indexLimit1 || c1 == '.')
                    && (index2 == indexLimit2 || c2 == '.')));
        }

        return 0;
    }

    private int skipLeadingZeroes(String number, int index) {
        int indexLimit = number.length();
        int newIndex = index;

        while (index < indexLimit && number.charAt(index) == '0'
                && ++index < indexLimit && isDigit(number.charAt(index))) {
            newIndex = index;
        }

        return newIndex;
    }
}
