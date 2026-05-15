class Solution {
    public int findMin(int[] nums) {
        
        int left = 0;
        int right = nums.length - 1;
        
        // If array is already sorted
        if (nums[left] <= nums[right]) {
            return nums[left];
        }
        
        while (left <= right) {
            
            int mid = left + (right - left) / 2;
            
            // Check if mid element is minimum
            if (mid < right && nums[mid] > nums[mid + 1]) {
                return nums[mid + 1];
            }
            
            // Check if mid itself is minimum
            if (mid > left && nums[mid] < nums[mid - 1]) {
                return nums[mid];
            }
            
            // Decide which half to search
            if (nums[mid] >= nums[left]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return -1;
    }
}