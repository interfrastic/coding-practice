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
    // Runtime: 11 ms
    // Your runtime beats 88.36 % of java submissions.
    //
    // https://leetcode.com/submissions/detail/147748553/

    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;

        if (m == 0) {
            return false;
        }

        int n = matrix[0].length;

        if (n == 0) {
            return false;
        }

        long i1 = 0;
        long i2 = m * n;
        long i;
        int value;

        while (target != (value
                = matrix[(int) ((i = (i1 + i2) / 2) / n)][(int) (i % n)])) {
            if (target < value) {
                i2 = i;
            } else {
                i1 = i + 1;
            }

            if (i1 >= i2) {
                return false;
            }
        }

        return true;
    }
}
