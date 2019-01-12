package net.avax.codingpractice.mergeintervals;

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

class Solution {
    private boolean overlap(Interval a, Interval b) {
        return (a.start <= b.start && a.end >= b.start)
                || (a.end >= b.end && a.start <= b.end);
    }

    private Interval merge(Interval a, Interval b) {
        int start = a.start < b.start ? a.start : b.start;
        int end = a.end > b.end ? a.end : b.end;

        return new Interval(start, end);
    }

    public List<Interval> merge(List<Interval> intervals) {
        int size = intervals.size();

        for (int i = 0; i < size - 1; i++) {
            Interval left = intervals.get(i);

            for (int j = i + 1; j < size; j++) {
                Interval right = intervals.get(j);

                if (overlap(left, right)) {
                    intervals.set(i, merge(left, right));
                    intervals.remove(right);

                    return merge(intervals);
                }
            }
        }

        return intervals;
    }
}
