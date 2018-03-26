package net.avax.codingpractice.tictactoe;

import java.util.Arrays;

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

    private static Symbol[][] getBoards(int size) {
        if (size == 0) {
            return new Symbol[0][];
        }

        Symbol[] symbols = Symbol.values();
        Symbol zeroSymbol = symbols[0];
        int symbolCount = symbols.length;
        int boardSpaceCount = size * size;
        int boardCount = (int) Math.pow(symbolCount, boardSpaceCount);

        Symbol[] zeroBoard = new Symbol[boardSpaceCount];

        for (int i = 0; i < boardSpaceCount; i++) {
            zeroBoard[i] = zeroSymbol;
        }

        Symbol[][] boards = new Symbol[boardCount][];
        Symbol[] board = Arrays.copyOf(zeroBoard, zeroBoard.length);

        for (int i = 0; i < boardCount; i++) {
            boards[i] = board;

            board = increment(board);
        }

        return boards;
    }

    private static Symbol[] increment(Symbol[] symbols) {
        int size = symbols.length;
        Symbol[] incrementedSymbols = Arrays.copyOf(symbols, symbols.length);

        for (int i = size - 1; i >= 0; i--) {
            Symbol incrementedSymbol = increment(incrementedSymbols[i]);

            incrementedSymbols[i] = incrementedSymbol;

            if (incrementedSymbol.ordinal() % size != 0) {
                break;
            }
        }

        return incrementedSymbols;
    }

    private static Symbol increment(Symbol symbol) {
        Symbol[] values = Symbol.values();

        return values[(symbol.ordinal() + 1) % values.length];
    }

    public static void main(String[] args) {
        int size = 4;
        int limit = 10;

        long start = System.currentTimeMillis();

        Symbol[][] boards = getBoards(size);

        double elapsed = (System.currentTimeMillis() - start) / 1000;

        int resultCount = boards.length;
        int showCount = min(resultCount, limit);

        System.out.println();
        System.out.println("Initial " + showCount + " board(s):");

        for (int i = 0; i < showCount; i++) {
            printBoard(boards[i], size);
        }

        System.out.println();
        System.out.println("Final " + showCount + " board(s):");

        for (int i = resultCount - showCount; i < resultCount; i++) {
            printBoard(boards[i], size);
        }

        System.out.println();
        System.out.println("Found " + resultCount + " possible " + size +
                " × "
                + size + " board(s) in " + elapsed + " seconds");
    }

    private static void printBoard(Symbol[] symbols, int size) {
        System.out.println();

        int i = 0;

        for (Symbol symbol : symbols) {
            System.out.print(symbol);

            if (++i % size == 0) {
                System.out.println();
            }
        }
    }
}
