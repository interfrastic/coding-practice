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
    // https://leetcode.com/submissions/detail/147726352/

    private static boolean searchSubMatrix(int[][] matrix, int target,
                                           int rowBeginIndex, int rowEndIndex,
                                           int colBeginIndex, int colEndIndex) {

        // Find the row in the middle of the row window.

        int rowCount = rowEndIndex - rowBeginIndex;
        int midRowIndex = rowBeginIndex + rowCount / 2;
        int[] midRow = matrix[midRowIndex];

        if (rowCount > 1) {

            // There are multiple rows to consider, so attempt to narrow the
            // row window.

            int lowColIndex = colBeginIndex;
            int lowColValue = midRow[lowColIndex];

            if (target == lowColValue) {

                // We got lucky: the lowest column is the target.

                return true;
            }

            int highColIndex = colEndIndex - 1;
            int highColValue = midRow[highColIndex];

            if (target == highColValue) {

                // We got lucky: the highest column is the target.

                return true;
            }

            // Narrow the row window, and possibly also the column window, based
            // on the lowest and highest column values in the middle row.

            if (target < lowColValue) {

                // If the target is smaller than the lowest column value in the
                // middle row, then look for it somewhere between the top of the
                // search window and the row above the middle row.  (The ending
                // index is exclusive.)

                rowEndIndex = midRowIndex;
            } else if (target > highColValue) {

                // If the target is larger than the highest column value in the
                // middle row, then look for it somewhere between the row below
                // the middle row and the bottom of the search window.  (The
                // beginning index is inclusive.)

                rowBeginIndex = midRowIndex + 1;
            } else {

                // If the target is greater than the lowest column value and
                // less than the highest column value, then we can narrow the
                // search to the columns between them on the middle row.
                // (The beginning index is inclusive and the ending index is
                // exclusive.)

                rowBeginIndex = midRowIndex;
                rowEndIndex = midRowIndex + 1;
                colBeginIndex = lowColIndex + 1;
                colEndIndex = highColIndex;
            }
        } else {

            // There is only one row to consider, so check the value in the
            // middle of the current column window.

            int midColIndex = colBeginIndex + (colEndIndex - colBeginIndex) / 2;
            int midColValue = matrix[midRowIndex][midColIndex];

            // Did we find the target in the middle column?

            if (target == midColValue) {
                return true;
            }

            // If not, further narrow the column window based on the middle
            // column value.

            if (target < midColValue) {

                // If the target is smaller than the middle column value,
                // then look for it somewhere between the left edge of the
                // search window and the column to the left of the middle
                // column.  (The ending index is exclusive.)

                colEndIndex = midColIndex;
            } else {

                // If the target is larger than the middle column value, then
                // look for it somewhere between the column to the right of
                // the middle column and the right edge of the search window.
                // (The beginning index is inclusive.)

                colBeginIndex = midColIndex + 1;
            }
        }

        // Now that the row window, the column window, or both have been
        // narrowed, check to make sure they are still open; if either one has
        // closed, then the target must not be in the matrix.

        if (rowBeginIndex >= rowEndIndex || colBeginIndex >= colEndIndex) {
            return false;
        }

        // Repeat the search on the smaller area.

        return searchSubMatrix(matrix, target, rowBeginIndex, rowEndIndex,
                colBeginIndex, colEndIndex);
    }

    public boolean searchMatrix(int[][] matrix, int target) {

        // The target can't be in a 0 x 0 matrix, which contains no values.

        int maxRowEndIndex = matrix.length;

        if (maxRowEndIndex == 0) {
            return false;
        }

        // The target can't be in an m x 0 matrix, which contains no values.

        int maxColEndIndex = matrix[0].length;

        if (maxColEndIndex == 0) {
            return false;
        }

        // Search an m x n matrix containing at least one value.

        return searchSubMatrix(matrix, target, 0, maxRowEndIndex, 0,
                maxColEndIndex);
    }
}
