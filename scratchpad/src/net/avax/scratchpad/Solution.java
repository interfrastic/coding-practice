package net.avax.scratchpad;

import java.util.List;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.min;

class Solution {

    // 2018-05-16 techlet easy problem:
    //
    // https://leetcode.com/problems/shortest-distance-to-a-character
    // /description/

    public int[] shortestToChar(String S, char C) {
        int length = S.length();

        int[] targets = new int[length];
        int[] results = new int[length];

        int targetIndex = 0;
        int target = -1;

        while ((target = S.indexOf(C, target)) >= 0) {
            targets[targetIndex++] = target;
            target++;
        }

        int targetsLength = targetIndex;

        targetIndex = 0;

        int nextTarget = targets[targetIndex++];
        int prevTarget = -10000;

        for (int index = 0; index < S.length(); index++) {
            if (index > nextTarget) {
                prevTarget = nextTarget;

                if (targetIndex < targetsLength) {
                    nextTarget = targets[targetIndex++];
                } else {
                    nextTarget = 10000;
                }
            }

            results[index] = min(index - prevTarget, nextTarget - index);
        }

        return results;
    }

    // 2018-05-16 techlet medium problem:
    //
    // https://leetcode.com/problems/binary-tree-inorder-traversal/description/

    public List<Integer> inorderTraversal(TreeNode root) {
        return null;
    }

    // 2018-05-16 techlet hard problem:
    //
    // https://leetcode.com/problems/minimum-window-substring/description/

    public String minWindow(String s, String t) {
        return null;
    }
}
