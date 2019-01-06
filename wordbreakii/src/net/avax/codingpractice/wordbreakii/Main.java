package net.avax.codingpractice.wordbreakii;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        // 14 s with original brute-force implementation.
        // 70 s with first backtracking implementation.
        // 10 s with simple memoization cache.
//        String sentence = "aaaaaaaaaaaaaaaaaaaaaaaa";
//        List<String> dictWords = Stream.of("a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa").collect(Collectors.toList());
        String sentence = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        List<String> dictWords = Stream.of("a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa").collect(Collectors.toList());
//        String sentence = "foobar";
//        List<String> dictWords = Stream.of("foo","bar","foobar").collect(Collectors.toList());
//        String sentence = "a";
//        List<String> dictWords = Collections.emptyList();
//        String sentence = "a";
//        List<String> dictWords = Stream.of("a").collect(Collectors.toList());
//        String sentence = "aaaaaaa";
//        List<String> dictWords = Stream.of("aaaa","aaa").collect(Collectors.toList());

        Solution solution = new Solution();

        System.out.println(solution.wordBreak(sentence, dictWords));
    }
}
