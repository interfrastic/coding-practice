package net.avax.codingpractice.nqueens;

// 2018-04-18 techlet problem:
//
// https://leetcode.com/problems/n-queens/description/
//
// 9 / 9 test cases passed.
// Status: Accepted
// Runtime: 231 ms
// [No message about beating any other submissions . . . is it the worst?!]
//
// https://leetcode.com/submissions/detail/150482405/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> boards = new ArrayList<>();

        int[] integers = new int[n];

        for (int i = 0; i < n; i++) {
            integers[i] = i;
        }

        for (int[] permutation : permute(integers)) {
            if (isSafe(permutation)) {
                List<String> board = new ArrayList<>();

                for (Integer rowCode : permutation) {
                    StringBuilder sb = new StringBuilder();

                    for (int i = 0; i < n; i++) {
                        sb.append((i == rowCode) ? 'Q' : '.');
                    }

                    board.add(sb.toString());
                }

                boards.add(board);
            }
        }

        return boards;
    }

    public List<List<String>> solveNQueensGeneric(int n) {
        List<List<String>> boards = new ArrayList<>();

        List<Integer> integers = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            integers.add(i);
        }

        for (List<Integer> permutation : genericPermute(integers)) {
            if (isSafe(permutation.stream().mapToInt(i -> i).toArray())) {
                List<String> board = new ArrayList<>();

                for (Integer rowCode : permutation) {
                    StringBuilder sb = new StringBuilder();

                    for (int i = 0; i < n; i++) {
                        sb.append((i == rowCode) ? 'Q' : '.');
                    }

                    board.add(sb.toString());
                }

                boards.add(board);
            }
        }

        return boards;
    }

    private boolean isSafe(int[] board) {
        int n = board.length;

        if (n <= 1) {
            return true;
        }

        int m = (n - 1);
        int o = 1 + 2 * m;

        int[] qcl = new int[o];
        int[] qcr = new int[o];

        for (int i = 0; i < n; i++) {
            int r = board[i];

            for (int j = 0; j < o; j++) {
                int vl = (0 + i) + (j - m);

                if (vl == r) {
                    if (++qcl[j] >= 2) {
                        return false;
                    }
                }

                int vr = (m - i) + (j - m);

                if (vr == r) {
                    if (++qcr[j] >= 2) {
                        return false;
                    }
                }
            }
        }

        return true;
    }


    private static List<int[]> permute(int[] nums) {
        int n = nums.length;
        List<int[]> permutations = new ArrayList<>();

        if (n == 1) {
            permutations.add(new int[]{nums[0]});

            return permutations;
        }

        for (int i = 0; i < n; i++) {
            int firstNum = nums[i];
            int remainingNumCount = n - 1;
            int[] remainingNums = new int[remainingNumCount];

            for (int j = 0; j < i; j++) {
                remainingNums[j] = nums[j];
            }

            for (int j = i + 1; j < n; j++) {
                remainingNums[j - 1] = nums[j];
            }

            for (int[] remainingPermuation : permute(remainingNums)) {
                int[] permutation = new int[n];

                permutation[0] = firstNum;

                for (int j = 0; j < remainingNumCount; j++) {
                    permutation[1 + j] = remainingPermuation[j];
                }

                permutations.add(permutation);
            }
        }

        return permutations;
    }

    private static <T> List<List<T>> genericPermute(List<T> things) {
        if (things.size() == 1) {
            return new ArrayList<List<T>>(Arrays.asList(
                    new ArrayList<T>(Arrays.asList(things.get(0)))));
        }

        return things.stream().flatMap(
                firstThing -> genericPermute(
                        things.stream().filter(
                                remainingThing -> remainingThing !=
                                        firstThing
                        ).collect(Collectors.toList())
                ).stream().map(
                        permutation -> Stream.concat(
                                Stream.of(firstThing), permutation.stream()
                        ).collect(Collectors.toList())
                )
        ).collect(Collectors.toList());
    }
}
