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

    private static List<List<Symbol>> getBoards(int size) {
        int symbolCount = size * size;
        Symbol zeroSymbol = Symbol.values()[0];
        List<Symbol> zeroBoard = new ArrayList<>();

        for (int index = 0; index < symbolCount; index++) {
            zeroBoard.add(zeroSymbol);
        }

        List<List<Symbol>> boards = new ArrayList<>();
        List<Symbol> board = new ArrayList<>(zeroBoard);

        while (true) {
            boards.add(board);

            if ((board = increment(board)).equals(zeroBoard)) {
                break;
            }
        }

        return boards;
    }

    private static List<Symbol> increment(List<Symbol> symbols) {
        List<Symbol> incrementedSymbols = new ArrayList<>(symbols);
        int size = symbols.size();

        for (int index = size - 1; index >= 0; index--) {
            Symbol incrementedSymbol = increment(incrementedSymbols.get
                    (index));

            incrementedSymbols.set(index, incrementedSymbol);

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

        List<List<Symbol>> boards = getBoards(size);

        double elapsed = (System.currentTimeMillis() - start) / 1000;

        int resultCount = boards.size();
        int showCount = min(resultCount, limit);

        System.out.println();
        System.out.println("Initial " + showCount + " board(s):");

        for (int i = 0; i < showCount; i++) {
            printBoard(boards.get(i), size);
        }

        System.out.println();
        System.out.println("Final " + showCount + " board(s):");

        for (int i = resultCount - showCount; i < resultCount; i++) {
            printBoard(boards.get(i), size);
        }

        System.out.println();
        System.out.println("Found " + resultCount + " possible " + size +
                " × "
                + size + " board(s) in " + elapsed + " seconds");
    }

    private static void printBoard(List<Symbol> symbols, int size) {
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
