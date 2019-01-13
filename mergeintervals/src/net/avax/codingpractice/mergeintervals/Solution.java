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
//
// Third attempt: revert to in-place list manipulations, but reduce memory
// usage by avoiding recursion.
//
// 169 / 169 test cases passed.
// Status: Accepted
// Runtime: 23 ms, faster than 68.50% of Java online submissions for Merge
// Intervals.
//
// https://leetcode.com/submissions/detail/200881981/

class Solution {
    private boolean overlap(Interval a, Interval b) {
        return (a.end >= b.start && a.start <= b.end)
                || (b.end >= a.start && b.start <= a.end);
    }

    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> mergedIntervals = new ArrayList<>(intervals);
        int mergeCount;

        do {
            mergeCount = 0;

            for (int i = 0; i < mergedIntervals.size(); i++) {
                Interval left = mergedIntervals.get(i);

                for (int j = i + 1; j < mergedIntervals.size(); j++) {
                    Interval right = mergedIntervals.get(j);

                    if (overlap(left, right)) {
                        left = new Interval(Math.min(left.start, right.start),
                                Math.max(left.end, right.end));
                        mergedIntervals.set(i, left);
                        mergedIntervals.remove(j);
                        mergeCount++;
                    }
                }
            }
        } while (mergeCount > 0);

        return mergedIntervals;
    }
}
