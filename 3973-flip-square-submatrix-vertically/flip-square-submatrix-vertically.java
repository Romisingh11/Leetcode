class Solution {
    public int[][] reverseSubmatrix(int[][] grid, int x, int y, int k) {
        
        // Swap rows inside submatrix
        for (int i = 0; i < k / 2; i++) {
            for (int j = y; j < y + k; j++) {
                
                // Swap elements
                int temp = grid[x + i][j];
                grid[x + i][j] = grid[x + k - 1 - i][j];
                grid[x + k - 1 - i][j] = temp;
            }
        }
        
        return grid;
    }
}