class Solution {

    public int[][] rotateGrid(int[][] grid, int k) {

        int m = grid.length;
        int n = grid[0].length;

        int layers = Math.min(m, n) / 2;

        for (int layer = 0; layer < layers; layer++) {

            int top = layer;
            int left = layer;
            int bottom = m - layer - 1;
            int right = n - layer - 1;

            // Store layer elements
            java.util.List<Integer> arr = new java.util.ArrayList<>();

            // top row
            for (int j = left; j <= right; j++) {
                arr.add(grid[top][j]);
            }

            // right column
            for (int i = top + 1; i <= bottom - 1; i++) {
                arr.add(grid[i][right]);
            }

            // bottom row
            for (int j = right; j >= left; j--) {
                arr.add(grid[bottom][j]);
            }

            // left column
            for (int i = bottom - 1; i >= top + 1; i--) {
                arr.add(grid[i][left]);
            }

            int len = arr.size();

            // Effective rotations
            int rot = k % len;

            // Rotate counter-clockwise
            java.util.List<Integer> rotated = new java.util.ArrayList<>();

            for (int i = 0; i < len; i++) {
                rotated.add(arr.get((i + rot) % len));
            }

            int idx = 0;

            // Put back top row
            for (int j = left; j <= right; j++) {
                grid[top][j] = rotated.get(idx++);
            }

            // Put back right column
            for (int i = top + 1; i <= bottom - 1; i++) {
                grid[i][right] = rotated.get(idx++);
            }

            // Put back bottom row
            for (int j = right; j >= left; j--) {
                grid[bottom][j] = rotated.get(idx++);
            }

            // Put back left column
            for (int i = bottom - 1; i >= top + 1; i--) {
                grid[i][left] = rotated.get(idx++);
            }
        }

        return grid;
    }
}