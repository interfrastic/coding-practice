package net.avax.codingpractice.tictactoe;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.min;

public class Main {
    enum Symbol {
        EMPTY("."),
        X("X"),
        O("O");

        private final String string;

        Symbol(String string) {
            this.string = string;
        }

        public String toString() {
            return string;
        }
    }

    private static List<List<Symbol>> getCombos(int size) {
        if (size <= 0) {
            return List.of();
        }

        List<List<Symbol>> combos = new ArrayList<>();

        if (size == 1) {
            for (Symbol symbol : Symbol.values()) {
                List<Symbol> combo = new ArrayList<>();

                combo.add(symbol);
                combos.add(combo);
            }

            return combos;
        }

        for (List<Symbol> initialCombo : getCombos(1)) {
            for (List<Symbol> finalCombo : getCombos(size - 1)) {
                List<Symbol> combo = new ArrayList<>(initialCombo);

                combo.addAll(finalCombo);
                combos.add(combo);
            }
        }

        return combos;
    }

    private static List<List<List<Symbol>>> getBoards(int size) {
        List<List<List<Symbol>>> boards = new ArrayList<>();

        for (List<Symbol> firstRow : getCombos(size)) {
            boards.addAll(getBoards(List.of(firstRow)));
        }

        return boards;
    }

    private static List<List<List<Symbol>>> getBoards(
            List<List<Symbol>> initialRows) {
        if (initialRows.size() == 0) {
            return List.of();
        }

        int size = initialRows.get(0).size();

        if (initialRows.size() == size) {
            return List.of(initialRows);
        }

        List<List<List<Symbol>>> boards = new ArrayList<>();

        if (initialRows.size() == size - 1) {
            for (List<Symbol> finalRow : getCombos(size)) {
                List<List<Symbol>> board = new ArrayList<>(initialRows);

                board.add(finalRow);
                boards.add(board);
            }

            return boards;
        }

        for (List<Symbol> nextRow : getCombos(size)) {
            List<List<Symbol>> newInitialRows = new ArrayList<>(initialRows);

            newInitialRows.add(nextRow);

            boards.addAll(getBoards(newInitialRows));
        }

        return boards;
    }


    public static void main(String[] args) {
        int size = 4;
        int limit = 10;

        long start = System.currentTimeMillis();

        List<List<List<Symbol>>> boards = getBoards(size);

        double elapsed = (System.currentTimeMillis() - start) / 1000;

        int resultCount = boards.size();
        int showCount = min(resultCount, limit);

        System.out.println();
        System.out.println("Initial " + showCount + " board(s):");

        for (int i = 0; i < showCount; i++) {
            printBoard(boards.get(i));
        }

        System.out.println();
        System.out.println("Final " + showCount + " board(s):");

        for (int i = resultCount - showCount; i < resultCount; i++) {
            printBoard(boards.get(i));
        }

        System.out.println();
        System.out.println("Found " + resultCount + " possible " + size + " × "
                + size + " board(s) in " + elapsed + " seconds");
    }

    private static void printBoard(List<List<Symbol>> board) {
        System.out.println();

        for (List<Symbol> row : board) {
            for (Symbol symbol : row) {
                System.out.print(symbol);
            }

            System.out.println();
        }
    }
}
