package net.avax.codingpractice.expressionaddoperators;

// 2018-04-06 Rupert's "LeetCode problems for rainy days," bonus problem:
//
// https://leetcode.com/problems/expression-add-operators/description/
//
// 3 / 20 test cases passed.
// Status: Time Limit Exceeded
// Last executed input:
// "3456237490"
// 9191
//
// https://leetcode.com/submissions/detail/149570235/

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> addOperators(String num, int target) {
        List<String> matches = new ArrayList<>();

        for (Combo combo : getCombos(num)) {
            String expression = combo.toString();
            BigInteger value = new BigInteger(combo.collapse().toString());

            if (value.equals(BigInteger.valueOf(target))) {
                matches.add(expression);
            }
        }

        return matches;
    }

    private enum Operator {
        TIMES("*"),
        PLUS("+"),
        MINUS("-");

        private final String name;

        Operator(String name) {
            this.name = name;
        }

        public String toString() {
            return name;
        }
    }

    private static class Combo {
        String num;
        Operator operator;
        Combo next;

        public Combo(String num) {
            this.num = num;
        }

        public Combo collapse() {
            for (Operator operator : Operator.values()) {
                Combo combo = this;
                Combo prevCombo = combo;

                while ((combo = combo.next) != null) {
                    if (prevCombo.operator == operator) {
                        BigInteger result = new BigInteger(prevCombo.num);
                        BigInteger operand = new BigInteger(combo.num);

                        switch (prevCombo.operator) {
                            case PLUS:
                                result = result.add(operand);
                                break;
                            case MINUS:
                                result = result.subtract(operand);
                                break;
                            case TIMES:
                                result = result.multiply(operand);
                                break;
                        }

                        prevCombo.num = result.toString();
                        prevCombo.next = combo.next;

                        if (prevCombo.next == null) {
                            prevCombo.operator = null;
                        } else {
                            prevCombo.operator = combo.operator;
                        }

                        combo = prevCombo;
                    }

                    prevCombo = combo;
                }
            }

            return this;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            Combo combo = this;
            Combo prevCombo = combo;

            sb.append(combo.num);

            while ((combo = combo.next) != null) {
                sb.append(prevCombo.operator);
                sb.append(combo.num);
                prevCombo = combo;
            }

            return sb.toString();
        }
    }

    private static List<Combo> getCombos(String num) {
        List<Combo> combos = new ArrayList<>();
        int maxSplitIndex = num.length();

        for (int splitIndex = 1; splitIndex <= maxSplitIndex; splitIndex++) {
            String num1 = num.substring(0, splitIndex);
            String num2 = num.substring(splitIndex);

            if (num1.matches("^0+([^0]|0$)")) {
                continue;
            }

            if (num2.length() == 0) {
                Combo combo = new Combo(num1);

                combos.add(combo);
            } else {
                for (Operator operator : Operator.values()) {
                    for (Combo combo2 : getCombos(num2)) {
                        Combo combo1 = new Combo(num1);

                        combo1.operator = operator;
                        combo1.next = combo2;

                        combos.add(combo1);
                    }
                }
            }
        }

        return combos;
    }
}
