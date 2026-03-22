class Solution {
    public boolean findRotation(int[][] mat, int[][] target) {
        int n = mat.length;

        boolean rot0 = true;
        boolean rot90 = true;
        boolean rot180 = true;
        boolean rot270 = true;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                if (mat[i][j] != target[i][j]) {
                    rot0 = false;
                }

                if (mat[n - j - 1][i] != target[i][j]) {
                    rot90 = false;
                }

                if (mat[n - i - 1][n - j - 1] != target[i][j]) {
                    rot180 = false;
                }

                if (mat[j][n - i - 1] != target[i][j]) {
                    rot270 = false;
                }
            }
        }

        return rot0 || rot90 || rot180 || rot270;
    }
}