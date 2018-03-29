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
    // https://leetcode.com/submissions/detail/147592868/

    private static int getPossibleRowIndex(int[][] matrix, int target,
                                           int rowBeginIndex, int rowEndIndex) {

        // If the search window is not valid, then the target cannot be in it.

        int maxRowEndIndex = matrix.length;
        int maxRowIndex = maxRowEndIndex - 1;

        if (rowBeginIndex < 0
                || rowBeginIndex > maxRowIndex
                || rowEndIndex <= rowBeginIndex
                || rowEndIndex > maxRowEndIndex) {
            return -1;
        }

        // Find the row in the middle of the search window.

        int middleRowIndex = rowBeginIndex + (rowEndIndex - rowBeginIndex) / 2;
        int[] row = matrix[middleRowIndex];
        int maxColIndex = row.length - 1;

        // If the middle row is empty, the target could be either below or
        // above it.

        if (maxColIndex < 0) {

            // First try looking below.  (The ending index is exclusive.)

            int lowerRowIndex = getPossibleRowIndex(matrix, target,
                    rowBeginIndex, middleRowIndex);

            if (lowerRowIndex >= 0) {
                return lowerRowIndex;
            }

            // Now try looking above.  (The beginning index is inclusive.)

            return getPossibleRowIndex(matrix, target,
                    middleRowIndex + 1, rowEndIndex);
        }

        // If this is the bottom row and the target is smaller than the value in
        // the initial column, then the target is not in the search window.

        int initialColValue = row[0];

        if (middleRowIndex == 0 && target < initialColValue) {
            return -1;
        }

        // If this is the top row and the target is larger than the value in
        // the final column, then the target is not in the search window.

        int finalColValue = row[maxColIndex];

        if (middleRowIndex == maxRowIndex && target > finalColValue) {
            return -1;
        }

        // We know the target could be in the middle row if it falls within the
        // bounds of its initial and final column values.

        if (target >= initialColValue && target <= finalColValue) {
            return middleRowIndex;
        }

        if (target > finalColValue) {

            // If the target is larger than the value in the final column of the
            // middle row, then look for it somewhere between the row after the
            // middle row and the top of the search window.  (The beginning
            // index is inclusive.)

            rowBeginIndex = middleRowIndex + 1;
        } else {

            // Otherwise, the target must be smaller than the value in the
            // initial column of the middle row, so look for it somewhere
            // between the bottom of the search window and the row before the
            // middle row.  (The ending index is exclusive.)

            rowEndIndex = middleRowIndex;
        }

        return getPossibleRowIndex(matrix, target, rowBeginIndex, rowEndIndex);
    }

    private static boolean isTargetInRow(int[] row, int target,
                                         int beginIndex, int endIndex) {

        // If the search window is not valid, then the target cannot be in it.

        int maxEndIndex = row.length;
        int maxIndex = row.length - 1;

        if (beginIndex < 0
                || beginIndex > maxIndex
                || endIndex <= beginIndex
                || endIndex > maxEndIndex) {
            return false;
        }

        // Find the column in the middle of the row.

        int middleColIndex = beginIndex + (endIndex - beginIndex) / 2;

        // If the middle column is out of range, give up.

        if (middleColIndex < 0 || middleColIndex > maxIndex) {
            return false;
        }

        // Check for a match.

        int middleColValue = row[middleColIndex];

        if (target == middleColValue) {

            // Bingo!

            return true;
        }

        if (target > middleColValue) {

            // If the target is larger than the value in the middle column, then
            // look for it somewhere between the column after the middle column
            // and the end of the search window.  (The beginning index is
            // inclusive.)

            beginIndex = middleColIndex + 1;
        } else {

            // Otherwise, the target must be smaller than the value in the
            // initial column of the middle row, so look for it somewhere
            // between the bottom of the search window and the row before the
            // middle row.  (The ending index is exclusive.)

            endIndex = middleColIndex;
        }

        return isTargetInRow(row, target, beginIndex, endIndex);
    }

    public boolean searchMatrix(int[][] matrix, int target) {

        // First do a binary search through the rows to see if one of them could
        // contain the target.

        int possibleRowIndex = getPossibleRowIndex(matrix, target,
                0, matrix.length);

        if (possibleRowIndex < 0) {

            // No dice.

            return false;
        }

        // Now do a binary search through the columns of the possible row to
        // see if it contains the target.

        int[] possibleRow = matrix[possibleRowIndex];

        return isTargetInRow(possibleRow, target, 0, possibleRow.length);
    }
}
