package net.avax.codingpractice.lettercasepermutation;

import java.util.ArrayList;
import java.util.List;

// 64 / 64 test cases passed.
// Status: Accepted
// Runtime: 3 ms, faster than 100.00% of Java online submissions for Letter Case
// Permutation.
// Memory Usage: 38.7 MB, less than 61.57% of Java online submissions for
// Letter Case Permutation.
//
// https://leetcode.com/submissions/detail/216370588/

class Solution {
    private void permute(char[] characters, int currentIndex,
                         List<String> results) {

        // If there is no current character, we are done exploring; record the
        // result and return.

        if (currentIndex >= characters.length) {
            results.add(String.valueOf(characters));

            return;
        }

        // Look at the current character and its upper- and lowercase variants.

        char ch = characters[currentIndex];
        char uc = Character.toUpperCase(ch);
        char lc = Character.toLowerCase(ch);

        // Look at the next remaining character.

        int nextIndex = currentIndex + 1;

        // First we choose to explore with the current character as-is.

        permute(characters, nextIndex, results);

        // If the case cannot be flipped, then there is nothing more to explore.

        if (uc == lc) {
            return;
        }

        // Now choose the current character with its case flipped.

        characters[currentIndex] = (ch == uc) ? lc : uc;

        // Explore with the flipped case.

        permute(characters, nextIndex, results);

        // "Un-choose" the flipped case.

        characters[currentIndex] = ch;
    }

    public List<String> letterCasePermutation(String S) {
        List<String> results = new ArrayList<>();

        permute(S.toCharArray(), 0, results);

        return results;
    }
}
