package net.avax.codingpractice.frogjump;

////////////////////////////////////////////////////////////////////////////////
//
// LeetCode Frog Jump problem:
//
// https://leetcode.com/problems/frog-jump/
//
// First attempt quick, inelegant solution:
//
// 16 / 39 test cases passed.
// Status: Time Limit Exceeded
//
// https://leetcode.com/submissions/detail/199985323/

class Solution {
    private static boolean isStoneAt(int[] stones, int position) {
        for (int stone : stones) {
            if (stone == position) {
                return true;
            }
        }

        return false;
    }

    private static boolean canCrossFrom(int[] stones, int start, int prevJump) {
        int finalPosition = stones[stones.length - 1];
        int shortJump = prevJump - 1;
        int mediumJump = prevJump;
        int longJump = prevJump + 1;

        if ((shortJump > 0 && start + shortJump == finalPosition)
                || mediumJump > 0 && start + mediumJump == finalPosition
                || start + longJump == finalPosition) {
            return true;
        }

        boolean canCross = false;
        int jump = shortJump;
        int landing = start + jump;

        if (jump > 0 && isStoneAt(stones, landing)) {
            canCross = canCrossFrom(stones, landing, jump);
        }

        if (!canCross) {
            jump = mediumJump;
            landing = start + jump;

            if (jump > 0 && isStoneAt(stones, landing)) {
                canCross = canCrossFrom(stones, landing, jump);
            }
        }

        if (!canCross) {
            jump = longJump;
            landing = start + jump;

            if (isStoneAt(stones, landing)) {
                canCross = canCrossFrom(stones, landing, jump);
            }
        }

        return canCross;
    }

    public boolean canCross(int[] stones) {
        return canCrossFrom(stones, 0, 0);
    }
}
