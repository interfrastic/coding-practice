package net.avax.codingpractice.mergeintervals;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        Solution solution = new Solution();

        List<Interval> biggie = new ArrayList<>();

        for (int i = 0; i < 10000; i++) {
            biggie.add(new Interval(i, i + 1));
        }

        List<List<Interval>> testCases = Stream.of(
                Stream.of(
                        new Interval(1, 3),
                        new Interval(2, 6),
                        new Interval(8, 10),
                        new Interval(15, 18)
                ).collect(Collectors.toList()),
                biggie,
                Stream.of(
                        new Interval(1, 4),
                        new Interval(0, 4)
                ).collect(Collectors.toList()),
                Stream.of(
                        new Interval(1, 4),
                        new Interval(0, 5)
                ).collect(Collectors.toList()),
                Stream.of(
                        new Interval(1, 4),
                        new Interval(0, 2),
                        new Interval(3, 5)
                ).collect(Collectors.toList()),
                Stream.of(
                        new Interval(4, 5),
                        new Interval(1, 4),
                        new Interval(0, 1)
                ).collect(Collectors.toList()),
                Stream.of(
                        new Interval(2, 3),
                        new Interval(4, 5),
                        new Interval(6, 7),
                        new Interval(8, 9),
                        new Interval(1, 10)
                ).collect(Collectors.toList()),
                Stream.of(
                        new Interval(2, 3),
                        new Interval(2, 2),
                        new Interval(3, 3),
                        new Interval(1, 3),
                        new Interval(5, 7),
                        new Interval(2, 2),
                        new Interval(4, 6)
                ).collect(Collectors.toList())
                ).collect(Collectors.toList());

        for (List<Interval> intervals : testCases) {
            List<Interval> mergedIntervals = solution.merge(intervals);

            System.out.println("[" + intervals.stream().map(
                    i -> "[" + i.start + "," + i.end + "]"
            ).collect(Collectors.joining(","))
                    + "] -> [" + mergedIntervals.stream().map(
                    i -> "[" + i.start + "," + i.end + "]"
            ).collect(Collectors.joining(",")) + "]");
        }
    }
}
