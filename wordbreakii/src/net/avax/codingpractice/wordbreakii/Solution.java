package net.avax.codingpractice.wordbreakii;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("WeakerAccess")

////////////////////////////////////////////////////////////////////////////////
//
// LeetCode Word Break II problem:
//
// https://leetcode.com/problems/word-break-ii/
//
// First attempt: come up with something that works, regardless of efficiency.
//
// 31 / 39 test cases passed.
// Status: Time Limit Exceeded
//
// https://leetcode.com/submissions/detail/199216935/
//
// Second attempt: optimize to execute within time limit.  Hint: use
// backtracking.
//
// 31 / 39 test cases passed.
// Status: Time Limit Exceeded
//
// https://leetcode.com/submissions/detail/199353852/

class Solution {
    static class Backtracker {
        static class Candidate {
            final public List<Integer> dictWordIndexes;

            public Candidate(List<Integer> dictWordIndexes) {
                this.dictWordIndexes = new ArrayList<>(dictWordIndexes);
            }
        }

        final private String s;
        final private List<String> dictWords;
        final private int maxDictWordIndex;
        private List<String> sentences = new ArrayList<>();

        public Backtracker(String s, List<String> dictWords) {
            this.s = s;
            this.dictWords = new ArrayList<>(dictWords);
            maxDictWordIndex = dictWords.size() - 1;
        }

        private Candidate root() {
            return new Candidate(Collections.emptyList());
        }

        private boolean reject(Candidate c) {
            int maxCharacterCount = s.length();
            String remainder = s;
            int characterCount = 0;

            for (int dictWordIndex : c.dictWordIndexes) {
                String dictWord = dictWords.get(dictWordIndex);

                characterCount += dictWord.length();

                if (characterCount > maxCharacterCount
                        || !remainder.startsWith(dictWord)) {
                    return true;
                }

                remainder = remainder.substring(dictWord.length());
            }

            return false;
        }

        private boolean accept(Candidate c) {
            int characterCount = 0;

            for (int dictWordIndex : c.dictWordIndexes) {
                String dictWord = dictWords.get(dictWordIndex);

                characterCount += dictWord.length();
            }

            return characterCount == s.length();
        }

        private Candidate first(Candidate c) {
            if (maxDictWordIndex < 0) {
                return null;
            }

            List<Integer> dictWordIndexes = new ArrayList<>(c.dictWordIndexes);

            dictWordIndexes.add(0);

            return new Candidate(dictWordIndexes);
        }

        private Candidate next(Candidate s) {
            int lastDictWordIndexIndex = s.dictWordIndexes.size() - 1;

            int lastDictWordIndex
                    = s.dictWordIndexes.get(lastDictWordIndexIndex);

            if (lastDictWordIndex >= maxDictWordIndex) {
                return null;
            }

            List<Integer> dictWordIndexes = new ArrayList<>(s.dictWordIndexes);

            dictWordIndexes.set(lastDictWordIndexIndex, 1 + lastDictWordIndex);

            return new Candidate(dictWordIndexes);
        }

        private void output(Candidate c) {
            StringBuilder sentence = new StringBuilder();
            String sep = null;

            for (int dictWordIndex : c.dictWordIndexes) {
                if (sep == null) {
                    sep = " ";
                } else {
                    sentence.append(sep);
                }

                sentence.append(dictWords.get(dictWordIndex));
            }

            sentences.add(sentence.toString());
        }

        private void bt(Candidate c) {
            if (reject(c)) {
                return;
            }

            if (accept(c)) {
                output(c);
            }

            Candidate s = first(c);

            while (s != null) {
                bt(s);
                s = next(s);
            }
        }

        public List<String> getSentences() {
            this.bt(root());

            return new ArrayList<>(sentences);
        }
    }

    public List<String> wordBreak(String s, List<String> dictWords) {
        Backtracker backtracker = new Backtracker(s, dictWords);

        return backtracker.getSentences();
    }
}
