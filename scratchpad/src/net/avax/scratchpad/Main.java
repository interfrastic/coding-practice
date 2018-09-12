package net.avax.scratchpad;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.isPalindrome(121));
        System.out.println(solution.isPalindrome(-121));
        System.out.println(solution.isPalindrome(10));
        System.out.println(solution.isPalindrome(java.lang.Integer.MIN_VALUE));
        System.out.println(solution.isPalindrome(java.lang.Integer.MAX_VALUE));
        System.out.println(solution.isPalindrome(1234554321));
    }
}
