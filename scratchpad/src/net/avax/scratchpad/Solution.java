package net.avax.scratchpad;

import java.util.List;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.min;

class Solution {

    // 2018-08-15 techlet easy problem:
    //
    // https://leetcode.com/problems/palindrome-number/description/

    private boolean isPalindrome(String s) {
        int i = 0;
        int j = s.length();

        while (i < --j) {
            if (s.charAt(i++) != s.charAt(j)) {
                return false;
            }
        }

        return true;
    }

    public boolean isPalindrome(int x) {
        return isPalindrome(new Integer(x).toString());
    }
}
