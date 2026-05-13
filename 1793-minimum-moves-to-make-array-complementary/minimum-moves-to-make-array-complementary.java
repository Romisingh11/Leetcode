class Solution {
    public int minMoves(int[] nums, int limit) {
        int n = nums.length;

        // Difference array
        int[] diff = new int[2 * limit + 2];

        for (int i = 0; i < n / 2; i++) {
            int a = nums[i];
            int b = nums[n - 1 - i];

            int low = Math.min(a, b);
            int high = Math.max(a, b);

            int sum = a + b;

            /*
                For every pair:
                
                Initially assume 2 moves needed for every target sum.

                Range updates:
                
                [2, low] -> 1 move
                [low+1, high+limit] -> 1 move
                [sum] -> 0 move
            */

            // Start with +2 moves for all sums
            diff[2] += 2;

            // From low+1 to sum-1 => reduce by 1 (2 -> 1)
            diff[low + 1] -= 1;

            // At exact sum => reduce by 1 again (1 -> 0)
            diff[sum] -= 1;

            // After sum => back to 1
            diff[sum + 1] += 1;

            // After high + limit => back to 2
            diff[high + limit + 1] += 1;
        }

        int ans = Integer.MAX_VALUE;
        int current = 0;

        for (int s = 2; s <= 2 * limit; s++) {
            current += diff[s];
            ans = Math.min(ans, current);
        }

        return ans;
    }
}