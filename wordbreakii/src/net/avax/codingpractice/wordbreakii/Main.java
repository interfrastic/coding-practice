package net.avax.codingpractice.wordbreakii;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
//        String sentence = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
//        List<String> dictWords = Stream.of("a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa").collect(Collectors.toList());
        String sentence = "foobar";
        List<String> dictWords = Stream.of("foo","bar","foobar").collect(Collectors.toList());

        Solution solution = new Solution();

        System.out.println(solution.wordBreak(sentence, dictWords));
    }
}
