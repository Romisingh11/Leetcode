class Solution {
public:
    int numberOfSpecialChars(string word) {
        vector<int> lower(26, 0), upper(26, 0);
        
        // Count lowercase and uppercase letters
        for(char ch : word) {
            if(ch >= 'a' && ch <= 'z')
                lower[ch - 'a'] = 1;
            else
                upper[ch - 'A'] = 1;
        }
        
        int count = 0;
        
        // Check which letters appear in both forms
        for(int i = 0; i < 26; i++) {
            if(lower[i] && upper[i])
                count++;
        }
        
        return count;
    }
};