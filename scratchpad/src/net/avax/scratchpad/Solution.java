package net.avax.scratchpad;

import java.util.*;
import java.util.stream.Collectors;

class Solution {

    // 2018-02-07 techlet problem:
    //
    // https://leetcode.com/problems/minimum-size-subarray-sum/description/

    public int minSubArrayLen(int s, int[] nums) {
        int result = 0;

        for (int i = 0; i < nums.length; i++) {
            long sum = 0;

            for (int j = i; j < nums.length; j++) {
                sum += nums[j];

                if (sum >= s) {
                    int len = j - i + 1;

                    if (len <= result || result == 0) {
                        result = len;
                    }

                    break;
                }
            }
        }

        return result;
    }

    // 2018-02-07 techlet bonus problem:
    //
    // https://leetcode.com/problems/reorganize-string/description/

    public String reorganizeString(String S) {
        int stringLength = S.length();

        // Determine the number of times each letter appears.

        Map<Character, Integer> countForLetter = new HashMap<>();

        for (char letter : S.toCharArray()) {
            countForLetter.put(letter,
                    countForLetter.getOrDefault(letter, 0) + 1);
        }

        // Separate the letters into piles of each letter, with the piles
        // arranged from smallest to largest.

        Deque<Map.Entry<Character, Integer>> unusedPiles = countForLetter
                .entrySet()
                .stream()
                .sorted(Comparator.comparing(Map.Entry::getValue))
                .collect(Collectors.toCollection(LinkedList::new));

        // Temporary storage for the resulting rearranged string.

        StringBuilder result = new StringBuilder();

        // Primary working pile from which to draw letters to append to the
        // result.

        Map.Entry<Character, Integer> primaryPile = null;

        // Secondary working pile from which to draw different letters to
        // alternate with letters from the primary pile.

        Map.Entry<Character, Integer> secondaryPile = null;

        // Keep going until the result is complete; impossible input strings
        // are handled as a special case.

        while (result.length() < stringLength) {

            // If there is no primary pile, grab the largest one; there should
            // always be at least one available.

            if (primaryPile == null) {
                primaryPile = unusedPiles.removeLast();
            }

            // If there is no secondary pile, grab the largest one, if any.

            if (secondaryPile == null && !unusedPiles.isEmpty()) {
                secondaryPile = unusedPiles.removeLast();

                // The primary pile may have been depleted to the point where
                // it is now smaller than the secondary pile; when this happens,
                // swap the primary and secondary piles to ensure that we keep
                // drawing from the largest pile.

                if (secondaryPile.getValue() > primaryPile.getValue()) {
                    Map.Entry<Character, Integer> oldPrimaryPile = primaryPile;

                    primaryPile = secondaryPile;
                    secondaryPile = oldPrimaryPile;
                }
            }

            // If the primary pile has N letters in it, we will need to put
            // N - 1 different letters in between those letters.  If there are
            // not enough empty positions remaining to hold that many letters,
            // then we should give up now because there is no possible solution.

            int positionsRequired = 2 * primaryPile.getValue() - 1;
            int positionsRemaining = stringLength - result.length();

            if (positionsRequired > positionsRemaining) {
                return "";
            }

            // Draw a letter from the primary pile and append it the result.

            primaryPile = drawAndAppendLetter(primaryPile, result);

            // If there is a secondary pile, Draw a letter from it and append it
            // to the result.

            secondaryPile = drawAndAppendLetter(secondaryPile, result);
        }

        return result.toString();
    }

    // Draw a letter from the specified pile and append it to the result,
    // returning the smaller pile or null if the pile is now empty.  If the
    // specified pile is null, do nothing and return null.

    private static Map.Entry<Character, Integer> drawAndAppendLetter(
            Map.Entry<Character, Integer> pile, StringBuilder result) {
        if (pile == null) {
            return null;
        }

        // Add the letter from the pile to the result.

        result.append(pile.getKey());

        // Take the letter off the pile by lowering the count, removing the
        // entire pile when the count reaches zero.

        int count = pile.getValue();

        if (--count > 0) {
            pile.setValue(count);
        } else {
            pile = null;
        }

        return pile;
    }
}
