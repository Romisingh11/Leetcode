class Solution {
    public boolean check(int[] nums) {
        int count = 0;
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            // Compare current with next element (circularly)
            if (nums[i] > nums[(i + 1) % n]) {
                count++;
            }
        }

        // Array is valid if there is at most one "drop"
        return count <= 1;
    }
}