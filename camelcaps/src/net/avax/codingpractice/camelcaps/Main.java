package net.avax.codingpractice.camelcaps;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

public class Main {
    private static @NotNull String capitalize(@NotNull String word) {
        if (word.length() == 0) {
            return word;
        }

        return word.substring(0, 1).toUpperCase()
                + word.substring(1).toLowerCase();
    }

    private static @NotNull String getLowerCamelCaps(
            @NotNull List<String> words) {
        int size = words.size();

        if (size == 0) {
            return "";
        }

        return words.get(0) + getUpperCamelCaps(words.subList(1, size));
    }

    private static @NotNull String getUpperCamelCaps(
            @NotNull List<String> words) {
        return words.stream()
                .map(Main::capitalize).collect(Collectors.joining());
    }

    public static void main(String[] args) {
        System.out.println(getLowerCamelCaps(List.of()));
        System.out.println(getLowerCamelCaps(List.of("foo", "bar", "baz")));
        System.out.println(getLowerCamelCaps(List.of("one")));
        System.out.println(getLowerCamelCaps(List.of("one", "", "three")));
        System.out.println(getLowerCamelCaps(List.of("one", "2", "three")));
        System.out.println(getUpperCamelCaps(List.of()));
        System.out.println(getUpperCamelCaps(List.of("foo", "bar", "baz")));
        System.out.println(getUpperCamelCaps(List.of("one")));
        System.out.println(getUpperCamelCaps(List.of("one", "", "three")));
        System.out.println(getUpperCamelCaps(List.of("one", "2", "three")));
    }
}
