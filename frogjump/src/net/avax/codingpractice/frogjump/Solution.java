package net.avax.codingpractice.frogjump;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@SuppressWarnings("WeakerAccess")

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
//
// Second attempt with memoization:
//
// 39 / 39 test cases passed.
// Status: Accepted
// Runtime: 118 ms, faster than 27.29% of Java online submissions for Frog Jump.
//
// https://leetcode.com/submissions/detail/200150064/

class Solution {
    int finalPosition;
    Set<Integer> stones = new HashSet<>();
    Map<Integer, Map<Integer, Boolean>> startCache = new HashMap<>();

    private boolean canCrossFrom(int start, int prevJump) {
        if (startCache.containsKey(start)) {
            Map<Integer, Boolean> prevJumpCache = startCache.get(start);

            if (prevJumpCache.containsKey(prevJump)) {
                return prevJumpCache.get(prevJump);
            }
        }

        int shorterJump = prevJump - 1;
        int longerJump = prevJump + 1;

        if ((shorterJump > 0 && start + shorterJump == finalPosition)
                || prevJump > 0 && start + prevJump == finalPosition
                || start + longerJump == finalPosition) {
            return true;
        }

        boolean canCross = false;
        int jump = shorterJump;
        int landing = start + jump;

        if (jump > 0 && stones.contains(landing)) {
            canCross = canCrossFrom(landing, jump);
        }

        if (!canCross) {
            jump = prevJump;
            landing = start + jump;

            if (jump > 0 && stones.contains(landing)) {
                canCross = canCrossFrom(landing, jump);
            }
        }

        if (!canCross) {
            jump = longerJump;
            landing = start + jump;

            if (stones.contains(landing)) {
                canCross = canCrossFrom(landing, jump);
            }
        }

        Map<Integer, Boolean> prevJumpCache
                = startCache.computeIfAbsent(start, k -> new HashMap<>());

        prevJumpCache.put(prevJump, canCross);

        return canCross;
    }

    public boolean canCross(int[] stones) {
        finalPosition = stones[stones.length - 1];

        this.stones.clear();

        for (int stone : stones) {
            this.stones.add(stone);
        }

        startCache.clear();

        return canCrossFrom(0, 0);
    }
}
