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

// Third attempt: Fix bugs in optimized version.
//
// 72 / 72 test cases passed.
// Status: Accepted
// Runtime: 0 ms, faster than 100.00% of Java online submissions for Compare
// Version Numbers.
// Memory Usage: 34.2 MB, less than 100.00% of Java online submissions for
// Compare Version Numbers.
//
// https://leetcode.com/submissions/detail/258372095/

// Fourth attempt: See if use of Integer.parseInt() reduces speed.
//
// 72 / 72 test cases passed.
// Status: Accepted
// Runtime: 1 ms, faster than 90.94% of Java online submissions for Compare
// Version Numbers.
// Memory Usage: 34 MB, less than 100.00% of Java online submissions for
// Compare Version Numbers.
//
// https://leetcode.com/submissions/detail/258376233/

import static java.lang.Character.isDigit;

@SuppressWarnings("WeakerAccess")
public class Solution {
    private int compareNumbers(String str1, int beginIndex1, int endIndex1,
                               String str2, int beginIndex2, int endIndex2) {
        int result = 0;
        int num1 = 0;
        int num2 = 0;

        if (endIndex1 > beginIndex1) {
            num1 = Integer.parseInt(str1.substring(beginIndex1, endIndex1));
        }

        if (endIndex2 > beginIndex2) {
            num2 = Integer.parseInt(str2.substring(beginIndex2, endIndex2));
        }

        if (num1 != num2) {
            result = (num1 > num2) ? 1 : -1;
        }

        return result;
    }

    public int compareVersion(String version1, String version2) {
        int result = 0;
        int index1 = 0;
        int index2 = 0;

        while ((index1 < version1.length() || index2 < version2.length())
                && result == 0) {
            int numBeginIndex1 = findNumBeginIndex(version1, index1);
            int numEndIndex1 = findNumEndIndex(version1, numBeginIndex1);
            int numBeginIndex2 = findNumBeginIndex(version2, index2);
            int numEndIndex2 = findNumEndIndex(version2, numBeginIndex2);

            result = compareNumbers(version1, numBeginIndex1, numEndIndex1,
                    version2, numBeginIndex2, numEndIndex2);

            index1 = numEndIndex1;
            index2 = numEndIndex2;
        }

        return result;
    }

    private int findNumBeginIndex(String str, int beginIndex) {
        int numBeginIndex = beginIndex;

        while (numBeginIndex < str.length()
                && !isDigit(str.charAt(numBeginIndex))) {
            numBeginIndex++;
        }

        return numBeginIndex;
    }

    private int findNumEndIndex(String str, int beginIndex) {
        int numEndIndex = beginIndex;

        while (numEndIndex < str.length()
                && isDigit(str.charAt(numEndIndex))) {
            numEndIndex++;
        }

        return numEndIndex;
    }
}
