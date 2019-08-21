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

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

@SuppressWarnings("WeakerAccess")
public class Solution {
    public int compareVersion(String version1, String version2) {
        List<Integer> fields1 = Arrays.stream(version1.split("[.]"))
                .map(Integer::parseInt).collect(toList());
        List<Integer> fields2 = Arrays.stream(version2.split("[.]"))
                .map(Integer::parseInt).collect(toList());
        int size1 = fields1.size();
        int size2 = fields2.size();
        int indexLimit = Math.max(size1, size2);

        for (int index = 0; index < indexLimit; index++) {
            int field1 = (index < size1) ? fields1.get(index) : 0;
            int field2 = (index < size2) ? fields2.get(index) : 0;

            if (field1 != field2) {
                return (field1 > field2) ? 1 : -1;
            }
        }

        return 0;
    }
}
