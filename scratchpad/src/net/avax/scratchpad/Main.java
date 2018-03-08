package net.avax.scratchpad;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.minSubArrayLen(
                7,
                new int[]{2, 3, 1, 2, 4, 3}
        ));

        for (String input : List.of(
                "aab",
                "aaab",
                "vvvlo"
        )) {
            System.out.println(input + ": \""
                    + solution.reorganizeString(input) + "\"");

        }
    }
}
