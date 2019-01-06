package net.avax.codingpractice.wordbreakii;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("WeakerAccess")

////////////////////////////////////////////////////////////////////////////////
//
// LeetCode Word Break II problem:
//
// https://leetcode.com/problems/word-break-ii/
//
// First attempt: come up with something that works, regardless of efficiency.
//
// 31 / 39 test cases passed.
// Status: Time Limit Exceeded
//
// https://leetcode.com/submissions/detail/199216935/

class Solution {
    public List<String> wordBreak(String s, List<String> dictWords) {

        // Build a list of possible sentences.

        List<String> sentences = new ArrayList<>();

        // Loop through the words in the dictionary.

        for (String dictWord : dictWords) {

            // If the string starts with the dictionary word, see if there
            // are any characters left in the string.

            if (s.startsWith(dictWord)) {

                // See what remains in the string.

                String remainder = s.substring(dictWord.length());

                if (remainder.isEmpty()) {

                    // If there are no characters left, then the word itself is
                    // the sentence; this terminates the recursion.

                    sentences.add(dictWord);
                } else {

                    // If there are characters left, call this method
                    // recursively to determine the remaining possible sentence
                    // fragments, if any. Loop through the remaining fragments,
                    // combining each with the current dictionary word to form
                    // a possible sentence.

                    for (String fragment : wordBreak(remainder, dictWords)) {
                        sentences.add(dictWord + " " + fragment);
                    }
                }
            }
        }

        return sentences;
    }
}
