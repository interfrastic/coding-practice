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

@SuppressWarnings("WeakerAccess")
public class Solution {
    final int[] valueForSymbol;
    final boolean[][] canSubtract;

    Solution() {
        valueForSymbol = new int[128];
        canSubtract = new boolean[128][128];

        // Roman numerals are represented by seven different symbols:
        // I, V, X, L, C, D and M.

        valueForSymbol['I'] = 1;
        valueForSymbol['V'] = 5;
        valueForSymbol['X'] = 10;
        valueForSymbol['L'] = 50;
        valueForSymbol['C'] = 100;
        valueForSymbol['D'] = 500;
        valueForSymbol['M'] = 1000;

        // I can be placed before V (5) and X (10) to make 4 and 9.

        canSubtract['I']['V'] = true;
        canSubtract['I']['X'] = true;

        // X can be placed before L (50) and C (100) to make 40 and 90.

        canSubtract['X']['L'] = true;
        canSubtract['X']['C'] = true;

        // C can be placed before D (500) and M (1000) to make 400 and 900.

        canSubtract['C']['D'] = true;
        canSubtract['C']['M'] = true;
    }

    public int romanToInt(String s) {
        int iMax = s.length() - 1;
        int value = 0;

        for (int i = 0; i <= iMax; i++) {
            char c = s.charAt(i);
            int symbolValue = valueForSymbol[c];

            if (i < iMax && canSubtract[c][s.charAt(i + 1)]) {
                value -= symbolValue;
            } else {
                value += symbolValue;
            }
        }

        return value;
    }
}
