package net.avax.codingpractice.nqueens;

// https://leetcode.com/problems/n-queens/description/

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();

        List<List<String>> boards = solution.solveNQueens(4);

        printBoard(boards);

        boards = solution.solveNQueens(6);

        printBoard(boards);

        long startTime = System.currentTimeMillis();
        if (false) {
            solution.solveNQueensGeneric(9);
        } else {
            solution.solveNQueens(9);
        }
        System.out.println(System.currentTimeMillis() - startTime);
    }

    private static void printBoard(List<List<String>> boards) {
        for (List<String> board : boards) {
            for (String row : board) {
                System.out.println(row);
            }

            System.out.println();
        }
    }
}
