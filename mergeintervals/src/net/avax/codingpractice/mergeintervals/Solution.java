package net.avax.codingpractice.mergeintervals;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("WeakerAccess")

////////////////////////////////////////////////////////////////////////////////
//
// LeetCode Merge Intervals problem:
//
// https://leetcode.com/problems/merge-intervals/
//
// First attempt.
//
// Status: Runtime Error
//
// https://leetcode.com/submissions/detail/137725785/
//
// Second attempt: reduce memory usage.
//
// 16 / 169 test cases passed.
// Status:   Wrong Answer
// Input:    [[1,4],[0,4]]
// Output:   [[1,4]]
// Expected: [[0,4]]
//
// https://leetcode.com/submissions/detail/200806841/
//
// 25 / 169 test cases passed.
// Status:   Wrong Answer
// Input:    [[1,4],[0,5]]
// Output:   [[1,4],[0,5]]
// Expected: [[0,5]]
//
// https://leetcode.com/submissions/detail/200809098/
//
// 28 / 169 test cases passed.
// Status:   Wrong Answer
// Input:    [[1,4],[0,2],[3,5]]
// Output:   [[0,4],[3,5]]
// Expected: [[0,5]]
//
// https://leetcode.com/submissions/detail/200812389/
//
// 32 / 169 test cases passed.
// Status:   Wrong Answer
// Input:    [[4,5],[1,4],[0,1]]
// Output:   [[0,4]]
// Expected: [[0,5]]
//
// https://leetcode.com/submissions/detail/200815471/
//
// 48 / 169 test cases passed.
// Status:   Wrong Answer
// Input:    [[2,3],[4,5],[6,7],[8,9],[1,10]]
// Output:   [[2,3],[4,5],[6,7],[1,10]]
// Expected: [[1,10]]

class Solution {
    public List<Interval> merge(List<Interval> intervals) {
        int size = intervals.size();
        List<Interval> mergedIntervals = new ArrayList<>();
        int leftIndex = 0;

        while (leftIndex < size) {
            Interval left = intervals.get(leftIndex);
            int start = left.start;
            int rightIndex = leftIndex + 1;
            Interval right;
            int end = left.end;

            while (rightIndex < size
                    && (end >= (right = intervals.get(rightIndex)).start
                    && start <= right.end)) {
                if (right.start < start) {
                    start = right.start;
                }

                if (right.end > end) {
                    end = right.end;
                }

                leftIndex = rightIndex;
                rightIndex++;
            }

            if (start == left.start && end == left.end) {
                mergedIntervals.add(left);
            } else {
                mergedIntervals.add(new Interval(start, end));
            }

            leftIndex++;
        }

        return mergedIntervals;
    }
}
