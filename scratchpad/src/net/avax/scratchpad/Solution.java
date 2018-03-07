package net.avax.scratchpad;

class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        int result = 0;

        for (int i = 0; i < nums.length; i++) {
            long sum = 0;

            for (int j = i; j < nums.length; j++) {
                sum += nums[j];

                if (sum >= s) {
                    int len = j - i + 1;

                    if (len <= result || result == 0) {
                        result = len;
                    }

                    break;
                }
            }
        }

        return result;
    }
}
