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
//
// Third attempt with optimized memoization cache:
//
// 39 / 39 test cases passed.
// Status: Accepted
// Runtime: 244 ms, faster than 8.49% of Java online submissions for Frog Jump.
//
// [It seems that this "optimization" had the opposite effect!]
//
// https://leetcode.com/submissions/detail/200155246/
//
// Fourth attempt, backing out cache "optimization" and adding up-front
// impossibility checks:
//
// 39 / 39 test cases passed.
// Status: Accepted
// Runtime: 97 ms, faster than 35.64% of Java online submissions for Frog Jump.
//
// https://leetcode.com/submissions/detail/200167287/

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

        int prevStone = 0;
        int maxJump = 0;

        for (int stone : stones) {
            if (stone - prevStone > maxJump) {
                return false;
            }

            this.stones.add(stone);

            prevStone = stone;
            maxJump++;
        }

        startCache.clear();

        return canCrossFrom(0, 0);
    }
}
