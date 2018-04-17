package net.avax.codingpractice.nqueens;

// 2018-04-16 techlet problem:
//
// https://leetcode.com/problems/n-queens/description/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> boards = new ArrayList<>();

        List<Integer> integers = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            integers.add(i);
        }

        System.out.println(permute(integers));

        List<String> strings = Arrays.asList("foo", "bar", "baz");

        System.out.println(permute(strings));

        return boards;
    }

    private static <T> List<List<T>> permute(List<T> things) {
        if (things.size() == 1) {
            return new ArrayList<List<T>>(Arrays.asList(
                    new ArrayList<T>(Arrays.asList(things.get(0)))));
        }

        return things.stream().flatMap(
                firstThing -> permute(
                        things.stream().filter(
                                remainingThing -> remainingThing != firstThing
                        ).collect(Collectors.toList())
                ).stream().map(
                        permutation -> Stream.concat(
                                Stream.of(firstThing), permutation.stream()
                        ).collect(Collectors.toList())
                )
        ).collect(Collectors.toList());
    }
}
