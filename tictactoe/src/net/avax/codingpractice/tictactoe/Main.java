package net.avax.codingpractice.tictactoe;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Main {
    enum Symbol {
        EMPTY("."),
        X("X"),
        O("O");

        private String string;

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

                for (Symbol symbol : finalCombo) {

                    combo.add(symbol);
                }

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
                List<List<Symbol>> board = new ArrayList<>();

                board.addAll(initialRows);
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


    public static void main(String[] args) throws FileNotFoundException {
        System.setOut(new PrintStream(new FileOutputStream("output.log")));

        long startTime = System.currentTimeMillis();

        int size = 3;

        List<List<List<Symbol>>> boards = getBoards(size);

        for (List<List<Symbol>> board : boards) {
            printBoard(board);

            for (int i = 0; i < size; i++) {
                System.out.print("-");
            }
            System.out.println();
        }

        long endTime = System.currentTimeMillis();

        double seconds = (endTime - startTime) / 1000;

        System.out.println("Found " + boards.size() + " possible " + size
                + " × " + size + " board(s) in " + seconds + " second(s)");
    }

    private static void printBoard(List<List<Symbol>> board) {
        for (List<Symbol> row : board) {
            for (Symbol symbol : row) {
                System.out.print(symbol);
            }

            System.out.println();
        }
    }
}
