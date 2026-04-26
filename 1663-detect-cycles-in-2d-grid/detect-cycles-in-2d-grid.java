class Solution {
    
    int[] dr = {-1, 1, 0, 0};
    int[] dc = {0, 0, -1, 1};
    
    public boolean containsCycle(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        boolean[][] vis = new boolean[m][n];
        
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                
                if(!vis[i][j]) {
                    if(dfs(grid, i, j, -1, -1, vis))
                        return true;
                }
            }
        }
        
        return false;
    }
    
    private boolean dfs(char[][] grid, int r, int c, int pr, int pc, boolean[][] vis) {
        vis[r][c] = true;
        
        for(int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            
            // boundary + same character check
            if(nr >= 0 && nr < grid.length && nc >= 0 && nc < grid[0].length
               && grid[nr][nc] == grid[r][c]) {
                
                // ignore parent
                if(nr == pr && nc == pc) continue;
                
                // already visited and not parent => cycle
                if(vis[nr][nc]) return true;
                
                if(dfs(grid, nr, nc, r, c, vis))
                    return true;
            }
        }
        
        return false;
    }
}