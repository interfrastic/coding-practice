package net.avax.scratchpad;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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
        Map<Character, Integer> countForChr = new HashMap<>();

        for (char chr : S.toCharArray()) {
            countForChr.put(chr, countForChr.getOrDefault(chr, 0) + 1);
        }

        StringBuilder result = new StringBuilder();
        Character prevChr = null;

        while (true) {
            Iterator<Map.Entry<Character, Integer>> iter = countForChr
                    .entrySet()
                    .stream()
                    .sorted((k1, k2) -> -k1.getValue().compareTo(k2.getValue()))
                    .iterator();

            if (iter.hasNext()) {
                Map.Entry<Character, Integer> mostFreq = iter.next();
                char mostFreqChr = mostFreq.getKey();
                int mostFreqCount = mostFreq.getValue();

                result.append(mostFreqChr);
                mostFreqCount--;
                prevChr = mostFreqChr;

                if (mostFreqCount > 0) {
                    countForChr.put(mostFreqChr, mostFreqCount);
                } else {
                    countForChr.remove(mostFreqChr);
                }

                if (iter.hasNext()) {
                    Map.Entry<Character, Integer> secondMostFreq = iter.next();
                    char secondMostFreqChr = secondMostFreq.getKey();
                    int secondMostFreqCount = secondMostFreq.getValue();

                    result.append(secondMostFreqChr);
                    secondMostFreqCount--;
                    prevChr = secondMostFreqChr;

                    if (secondMostFreqCount > 0) {
                        countForChr.put(secondMostFreqChr, secondMostFreqCount);
                    } else {
                        countForChr.remove(secondMostFreqChr);
                    }
                } else {
                    break;
                }
            } else {
                break;
            }
        }

        Iterator<Map.Entry<Character, Integer>> iter = countForChr
                .entrySet()
                .stream()
                .sorted((k1, k2) -> -k1.getValue().compareTo(k2.getValue()))
                .iterator();

        if (iter.hasNext()) {
            Map.Entry<Character, Integer> leftover = iter.next();
            char leftoverChr = leftover.getKey();
            int leftoverCount = leftover.getValue();

            if (leftoverChr == prevChr) {
                return  "";
            }

            result.append(leftoverChr);
            leftoverCount--;

            if (leftoverCount > 0) {
                countForChr.put(leftoverChr, leftoverCount);
            } else {
                countForChr.remove(leftoverChr);
            }
        }

        return result.toString();
    }
}
