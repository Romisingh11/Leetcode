class Solution {
public:
    int minPartitions(string n) {
        int maxDigit = 0;
        
        for(char c : n) {
            maxDigit = max(maxDigit, c - '0');
            if(maxDigit == 9) return 9; // early exit (max possible)
        }
        
        return maxDigit;
    }
};