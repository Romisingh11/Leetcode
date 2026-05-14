import java.util.*;

class Solution {
    public boolean isGood(int[] nums) {

        int n = nums.length;

        // Expected numbers: 1 to n-1
        int[] freq = new int[n];

        for (int num : nums) {

            // Number should be between 1 and n-1
            if (num < 1 || num > n - 1) {
                return false;
            }

            freq[num]++;
        }

        // 1 to n-2 should appear exactly once
        for (int i = 1; i <= n - 2; i++) {
            if (freq[i] != 1) {
                return false;
            }
        }

        // n-1 should appear exactly twice
        return freq[n - 1] == 2;
    }
}