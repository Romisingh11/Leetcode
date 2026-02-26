class Solution {
    public int numSteps(String s) {
        int steps = 0;
        int carry = 0;

        // traverse from rightmost bit to index 1
        for (int i = s.length() - 1; i > 0; i--) {
            int bit = s.charAt(i) - '0';
            int sum = bit + carry;

            if (sum == 1) {
                // odd → add 1 then divide
                steps += 2;
                carry = 1;
            } else {
                // even → just divide
                steps += 1;
            }
        }

        // if carry remains, one extra step
        return steps + carry;
    }
}