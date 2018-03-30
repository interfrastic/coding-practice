package net.avax.scratchpad;

import java.util.Arrays;
import java.util.stream.Collectors;

class Solution {

    // 2018-03-28 techlet problem:
    //
    // https://leetcode.com/problems/largest-number/description/
    //
    // 221 / 221 test cases passed.
    // Status: Accepted
    // Runtime: 146 ms
    // Your runtime beats 9.76 % of java submissions.
    //
    // https://leetcode.com/submissions/detail/147427310/

    public String largestNumber(int[] nums) {
        return Arrays.stream(nums).boxed().map(i -> i.toString())
                .sorted((a, b) -> Long.compare(
                        Long.valueOf(b + a),
                        Long.valueOf(a + b)))
                .collect(Collectors.joining())
                .replaceFirst("^0+", "0");
    }

    // 2018-03-28 techlet bonus problem:
    //
    // https://leetcode.com/problems/search-a-2d-matrix/description/
    //
    // 136 / 136 test cases passed.
    // Status: Accepted
    // Runtime: 130 ms
    // Your runtime beats 0.22 % of java submissions.
    //
    // https://leetcode.com/submissions/detail/147733268/

    public boolean searchMatrix(int[][] matrix, int target) {
        return Arrays.stream(matrix).parallel()
                .map(row -> Arrays.stream(row).parallel().boxed()
                        .collect(Collectors.toSet()))
                .flatMap(row -> row.stream())
                .collect(Collectors.toSet()).contains(target);
    }
}
