class Solution {
    public int numberOfSubmatrices(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        int[][] prefixX = new int[n][m];
        int[][] prefixY = new int[n][m];

        // Build prefix sums
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                int x = (grid[i][j] == 'X') ? 1 : 0;
                int y = (grid[i][j] == 'Y') ? 1 : 0;

                prefixX[i][j] = x;
                prefixY[i][j] = y;

                if (i > 0) {
                    prefixX[i][j] += prefixX[i - 1][j];
                    prefixY[i][j] += prefixY[i - 1][j];
                }
                if (j > 0) {
                    prefixX[i][j] += prefixX[i][j - 1];
                    prefixY[i][j] += prefixY[i][j - 1];
                }
                if (i > 0 && j > 0) {
                    prefixX[i][j] -= prefixX[i - 1][j - 1];
                    prefixY[i][j] -= prefixY[i - 1][j - 1];
                }
            }
        }

        int count = 0;

        // Check all (0,0) → (i,j)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                int x = prefixX[i][j];
                int y = prefixY[i][j];

                if (x == y && x > 0) {
                    count++;
                }
            }
        }

        return count;
    }
}