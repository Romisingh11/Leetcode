class Solution {
public:
    int minSwaps(vector<vector<int>>& grid) {
        int n = grid.size();
        vector<int> trailing(n, 0);

        // Step 1: count trailing zeros in each row
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = n - 1; j >= 0; j--) {
                if (grid[i][j] == 0) count++;
                else break;
            }
            trailing[i] = count;
        }

        int swaps = 0;

        // Step 2: greedy placement
        for (int i = 0; i < n; i++) {
            int needed = n - 1 - i;
            int j = i;

            // find row with enough trailing zeros
            while (j < n && trailing[j] < needed) j++;

            if (j == n) return -1; // impossible

            // bring row j to position i
            while (j > i) {
                swap(trailing[j], trailing[j - 1]);
                swaps++;
                j--;
            }
        }

        return swaps;
    }
};