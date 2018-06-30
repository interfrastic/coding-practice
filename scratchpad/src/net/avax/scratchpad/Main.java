package net.avax.scratchpad;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(Arrays.toString(
                solution.shortestToChar("loveleetcode", 'e')));

        System.out.println(Arrays.toString(
                solution.shortestToChar("aaba", 'b')));

        System.out.println(Arrays.toString(
                solution.shortestToChar("abaa", 'b')));

        System.out.println(solution.inorderTraversal(new TreeNode(0)));

        System.out.println(solution.minWindow("ADOBECODEBANC", "ABC"));
    }
}
