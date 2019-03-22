package net.avax.codingpractice.generateparentheses;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    private static final char[] OPEN_CHARACTERS = {'{', '[', '('};
    private static final char[] CLOSE_CHARACTERS = {'}', ']', ')'};

    private void permute(int[] toOpenCounts, int[] toCloseCounts,
                         int currentPosition, int currentOpenCharacterIndex,
                         char[] characters, List<String> results) {
        if (currentPosition == characters.length) {
            results.add(String.valueOf(characters));

            return;
        }

        int remainingPositionCount = characters.length - currentPosition;

        for (int toCloseCount : toCloseCounts) {
            remainingPositionCount -= toCloseCount;
        }

        for (int openCharacterIndex = 0;
             openCharacterIndex < OPEN_CHARACTERS.length;
             openCharacterIndex++) {
            if (toOpenCounts[openCharacterIndex] == 0) {
                continue;
            }

            if (toCloseCounts[openCharacterIndex] > remainingPositionCount) {
                continue;
            }

            char openCharacter = OPEN_CHARACTERS[openCharacterIndex];
            int origCurrentOpenCharacterIndex = currentOpenCharacterIndex;
            char origCurrentCharacter = characters[currentPosition];

            toOpenCounts[openCharacterIndex]--;
            toCloseCounts[openCharacterIndex]++;
            currentOpenCharacterIndex = openCharacterIndex;
            characters[currentPosition] = openCharacter;
            currentPosition++;

            permute(toOpenCounts, toCloseCounts, currentPosition,
                    currentOpenCharacterIndex, characters, results);

            currentPosition--;
            characters[currentPosition] = origCurrentCharacter;
            currentOpenCharacterIndex = origCurrentOpenCharacterIndex;
            toCloseCounts[openCharacterIndex]--;
            toOpenCounts[openCharacterIndex]++;
        }

        for (int closeCharacterIndex = 0;
             closeCharacterIndex < CLOSE_CHARACTERS.length;
             closeCharacterIndex++) {
            if (toCloseCounts[closeCharacterIndex] == 0) {
                continue;
            }

            if (currentOpenCharacterIndex >= 0
                    && toCloseCounts[closeCharacterIndex] > 0
                    && closeCharacterIndex != currentOpenCharacterIndex) {
                continue;
            }

            char closeCharacter = CLOSE_CHARACTERS[closeCharacterIndex];
            int origCurrentOpenCharacterIndex = currentOpenCharacterIndex;
            char origCurrentCharacter = characters[currentPosition];

            if (--toCloseCounts[closeCharacterIndex] == 0) {
                currentOpenCharacterIndex = -1;
            }

            characters[currentPosition] = closeCharacter;
            currentPosition++;

            permute(toOpenCounts, toCloseCounts, currentPosition,
                    currentOpenCharacterIndex, characters, results);

            currentPosition--;
            characters[currentPosition] = origCurrentCharacter;
            currentOpenCharacterIndex = origCurrentOpenCharacterIndex;
            toCloseCounts[closeCharacterIndex]++;
        }
    }

    public List<String> generateParenthesis(int n) {
        List<String> results = new ArrayList<>();
        char[] characters = new char[
                n * (OPEN_CHARACTERS.length + CLOSE_CHARACTERS.length)];
        int[] toOpenCounts = new int[OPEN_CHARACTERS.length];
        int[] toCloseCounts = new int[CLOSE_CHARACTERS.length];

        Arrays.fill(toOpenCounts, n);

        permute(toOpenCounts, toCloseCounts, 0, -1, characters, results);

        return results;
    }
}
