package net.avax.codingpractice.mergeintervals;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Solution solution = new Solution();

        List<Interval> intervals = new ArrayList<>();

        intervals.add(new Interval(1, 3));
        intervals.add(new Interval(2, 6));
        intervals.add(new Interval(8, 10));
        intervals.add(new Interval(15, 18));

        intervals = solution.merge(intervals);

        for (Interval interval : intervals) {
            System.out.println("[" + interval.start + "," + interval.end + "]");
        }
    }
}
