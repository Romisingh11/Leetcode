class Solution {
    public int mirrorDistance(int n) {
        int original = n;
        int reversed = 0;
        
        // Step 1: Reverse the number
        while (n > 0) {
            int digit = n % 10;
            reversed = reversed * 10 + digit;
            n /= 10;
        }
        
        // Step 2: Return absolute difference
        return Math.abs(original - reversed);
    }
}