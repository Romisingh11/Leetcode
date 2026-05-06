class Solution {
    public char[][] rotateTheBox(char[][] boxGrid) {
        
        int m = boxGrid.length;
        int n = boxGrid[0].length;

        // First simulate gravity on each row
        for (int i = 0; i < m; i++) {

            int empty = n - 1;

            for (int j = n - 1; j >= 0; j--) {

                // Obstacle resets position
                if (boxGrid[i][j] == '*') {
                    empty = j - 1;
                }

                // Move stone to the farthest possible position
                else if (boxGrid[i][j] == '#') {

                    boxGrid[i][j] = '.';
                    boxGrid[i][empty] = '#';
                    empty--;
                }
            }
        }

        // Rotate matrix 90 degree clockwise
        char[][] ans = new char[n][m];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                ans[j][m - 1 - i] = boxGrid[i][j];
            }
        }

        return ans;
    }
}