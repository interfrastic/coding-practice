package net.avax.codingpractice.integertoenglishwords;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.numberToWords(0));
        System.out.println(solution.numberToWords(1));
        System.out.println(solution.numberToWords(10));
        System.out.println(solution.numberToWords(11));
        System.out.println(solution.numberToWords(100));
        System.out.println(solution.numberToWords(101));
        System.out.println(solution.numberToWords(999));
        System.out.println(solution.numberToWords(1_000));
        System.out.println(solution.numberToWords(1_000_000));
        System.out.println(solution.numberToWords(1_001_000));
        System.out.println(solution.numberToWords(1_000_000_000));
        System.out.println(solution.numberToWords(1_000_000_001));
        System.out.println(solution.numberToWords(1_000_001_000));
        System.out.println(solution.numberToWords(1_001_000_000));
        System.out.println(solution.numberToWords(Integer.MAX_VALUE));
    }
}
