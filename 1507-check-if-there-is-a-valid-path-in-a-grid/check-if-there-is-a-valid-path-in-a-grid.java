import java.util.*;

class Solution {
    public boolean hasValidPath(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][][] dirs = {
            {},
            {{0,-1},{0,1}},      // type 1
            {{-1,0},{1,0}},      // type 2
            {{0,-1},{1,0}},      // type 3
            {{0,1},{1,0}},       // type 4
            {{0,-1},{-1,0}},     // type 5
            {{0,1},{-1,0}}       // type 6
        };

        boolean[][] vis = new boolean[m][n];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0,0});
        vis[0][0] = true;

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0], c = cur[1];

            if(r == m-1 && c == n-1) return true;

            int type = grid[r][c];

            for(int[] d : dirs[type]) {
                int nr = r + d[0];
                int nc = c + d[1];

                if(nr < 0 || nc < 0 || nr >= m || nc >= n || vis[nr][nc])
                    continue;

                // check reverse connection from neighbor
                if(isConnected(grid[nr][nc], -d[0], -d[1], dirs)) {
                    vis[nr][nc] = true;
                    q.offer(new int[]{nr,nc});
                }
            }
        }

        return false;
    }

    private boolean isConnected(int type, int dr, int dc, int[][][] dirs) {
        for(int[] d : dirs[type]) {
            if(d[0] == dr && d[1] == dc)
                return true;
        }
        return false;
    }
}