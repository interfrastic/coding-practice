package net.avax.codingpractice.permutations;

import java.util.ArrayList;
import java.util.List;

public class Main {
    static List<String> permute(String string, int count) {
        List<String> permutations = new ArrayList<>();

        if (string.length() == 1) {
            permutations.add(string);
        } else {
            for (int index = 0; index < string.length(); index++) {
                String letter = string.substring(index, index + 1);

                if (count == 1) {
                    permutations.add(letter);
                } else {
                    String otherLetters = string.substring(0, index)
                            + string.substring(index + 1);

                    for (String otherPermutation
                            : permute(otherLetters, count - 1)) {
                        permutations.add(letter + otherPermutation);
                    }
                }
            }
        }

        return permutations;
    }

    static List<String> permute(String string) {
        List<String> permutations = new ArrayList<>();

        for (int count = 1; count <= string.length(); count++) {
            permutations.addAll(permute(string, count));
        }

        return permutations;
    }

    public static void main(String[] args) {
        System.out.println(permute("help"));
        System.out.println(permute(""));
        System.out.println(permute("a"));
        System.out.println(permute("ab"));
        System.out.println(permute("abc"));
    }
}
