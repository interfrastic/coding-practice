package net.avax.codingpractice.romantoint;

import java.util.Arrays;

class Main {

    public static void main(String[] args) {
        Solution solution = new Solution();
        for (String input : Arrays.asList(
                "III",
                "IV",
                "IX",
                "LVIII",
                "MCMXCIV"
        )) {
            int output = solution.romanToInt(input);

            System.out.println("Input: " + input + ", output: " + output);
        }
    }
}
