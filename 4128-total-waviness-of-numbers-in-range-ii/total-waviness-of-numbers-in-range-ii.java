class Solution {

    static class Node {
        long cnt;     // number of valid numbers
        long wavy;    // total waviness sum

        Node(long cnt, long wavy) {
            this.cnt = cnt;
            this.wavy = wavy;
        }
    }

    private char[] digits;
    private Node[][][][][] memo;

    public long totalWaviness(long num1, long num2) {
        return solve(num2) - solve(num1 - 1);
    }

    private long solve(long n) {
        if (n < 0) return 0;

        digits = Long.toString(n).toCharArray();

        int m = digits.length;
        memo = new Node[m + 1][3][11][11][1];

        return dfs(0, true, 0, -1, -1).wavy;
    }

    private Node dfs(int pos, boolean tight, int lenState, int prev2, int prev1) {
        if (pos == digits.length) {
            return new Node(1, 0);
        }

        if (!tight) {
            Node saved = memo[pos][lenState][prev2 + 1][prev1 + 1][0];
            if (saved != null) return saved;
        }

        int limit = tight ? digits[pos] - '0' : 9;

        long totalCnt = 0;
        long totalWavy = 0;

        for (int d = 0; d <= limit; d++) {
            boolean nextTight = tight && (d == limit);

            if (lenState == 0) {
                // Still skipping leading zeros
                if (d == 0) {
                    Node child = dfs(pos + 1, nextTight, 0, -1, -1);
                    totalCnt += child.cnt;
                    totalWavy += child.wavy;
                } else {
                    Node child = dfs(pos + 1, nextTight, 1, -1, d);
                    totalCnt += child.cnt;
                    totalWavy += child.wavy;
                }
            } else if (lenState == 1) {
                Node child = dfs(pos + 1, nextTight, 2, prev1, d);
                totalCnt += child.cnt;
                totalWavy += child.wavy;
            } else {
                int add = ((prev1 > prev2 && prev1 > d) ||
                           (prev1 < prev2 && prev1 < d)) ? 1 : 0;

                Node child = dfs(pos + 1, nextTight, 2, prev1, d);

                totalCnt += child.cnt;
                totalWavy += child.wavy + add * child.cnt;
            }
        }

        Node ans = new Node(totalCnt, totalWavy);

        if (!tight) {
            memo[pos][lenState][prev2 + 1][prev1 + 1][0] = ans;
        }

        return ans;
    }
}