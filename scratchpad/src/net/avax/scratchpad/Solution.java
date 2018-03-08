package net.avax.scratchpad;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

        // Keep going until the result is complete; impossible input strings
        // are handled as a special case.

        StringBuilder result = new StringBuilder();

        while (result.length() < stringLength) {

            // Separate the letters into piles of each letter, sorted from
            // largest to smallest.

            List<Map.Entry<Character, Integer>> piles
                    = countForLetter.entrySet().stream()
                    .sorted(Comparator.comparing(Map.Entry::getValue,
                            Comparator.reverseOrder()))
                    .collect(Collectors.toList());

            // Pick out the largest pile, which should always exist and have
            // letters in it as long as the result is incomplete.

            Map.Entry<Character, Integer> largestPile = piles.get(0);

            // If the largest pile has N letters in it, we will need to put
            // N - 1 different letters in between those letters.  If there are
            // not enough empty positions remaining to hold that many letters,
            // then we should give up now because there is no possible solution.

            int largestPileSize = largestPile.getValue();
            int positionsRequired = 2 * largestPileSize - 1;
            int positionsRemaining = stringLength - result.length();

            if (positionsRequired > positionsRemaining) {
                return "";
            }

            // Draw a letter from the largest pile and append it the result.

            drawAndAppendLetter(largestPile, result);

            // If there is a second-largest pile, attempt to draw a letter from
            // it and append it to the result.

            if (piles.size() >= 2) {
                Map.Entry<Character, Integer> secondLargestPile = piles.get(1);

                drawAndAppendLetter(secondLargestPile, result);
            }
        }

        return result.toString();
    }

    // Draw a letter from the specified pile and append it to the result; if
    // the pile is empty, do nothing.

    private static void drawAndAppendLetter(
            Map.Entry<Character, Integer> pile, StringBuilder result) {
        int count = pile.getValue();

        if (count > 0) {
            result.append(pile.getKey());
            pile.setValue(count - 1);
        }
    }
}
