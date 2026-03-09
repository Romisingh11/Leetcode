class Solution {

    static final int MOD = 1000000007;
    Integer[][][][] memo;

    public int numberOfStableArrays(int zero, int one, int limit) {

        memo = new Integer[zero + 1][one + 1][2][limit + 1];

        long ans = 0;

        if (zero > 0)
            ans = (ans + dfs(zero - 1, one, 0, 1, limit)) % MOD;

        if (one > 0)
            ans = (ans + dfs(zero, one - 1, 1, 1, limit)) % MOD;

        return (int) ans;
    }

    private int dfs(int z, int o, int last, int cnt, int limit) {

        if (z == 0 && o == 0)
            return 1;

        if (memo[z][o][last][cnt] != null)
            return memo[z][o][last][cnt];

        long ways = 0;

        if (z > 0) {
            if (last == 0) {
                if (cnt < limit)
                    ways = (ways + dfs(z - 1, o, 0, cnt + 1, limit)) % MOD;
            } else {
                ways = (ways + dfs(z - 1, o, 0, 1, limit)) % MOD;
            }
        }

        if (o > 0) {
            if (last == 1) {
                if (cnt < limit)
                    ways = (ways + dfs(z, o - 1, 1, cnt + 1, limit)) % MOD;
            } else {
                ways = (ways + dfs(z, o - 1, 1, 1, limit)) % MOD;
            }
        }

        return memo[z][o][last][cnt] = (int) ways;
    }
}