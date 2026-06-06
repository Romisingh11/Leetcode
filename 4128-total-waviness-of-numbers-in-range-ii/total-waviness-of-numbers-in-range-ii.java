class Solution {

    static class Node {
        long cnt;   // number of numbers
        long wav;   // total waviness

        Node(long cnt, long wav) {
            this.cnt = cnt;
            this.wav = wav;
        }
    }

    private char[] digits;
    private Node[][][][] memo;

    public long totalWaviness(long num1, long num2) {
        return solve(num2) - solve(num1 - 1);
    }

    private long solve(long n) {
        if (n <= 0) return 0;

        digits = Long.toString(n).toCharArray();

        int len = digits.length;
        memo = new Node[len][11][11][2];

        return dfs(0, -1, -1, false, true).wav;
    }

    private Node dfs(int pos, int prev2, int prev1,
                     boolean started, boolean tight) {

        if (pos == digits.length) {
            return new Node(1, 0);
        }

        if (!tight) {
            Node saved = memo[pos][prev2 + 1][prev1 + 1][started ? 1 : 0];
            if (saved != null) return saved;
        }

        int limit = tight ? digits[pos] - '0' : 9;

        long totalCnt = 0;
        long totalWav = 0;

        for (int d = 0; d <= limit; d++) {

            boolean nextTight = tight && (d == limit);

            if (!started && d == 0) {
                Node child = dfs(pos + 1, -1, -1, false, nextTight);

                totalCnt += child.cnt;
                totalWav += child.wav;
            } else {

                boolean nextStarted = true;

                int add = 0;

                if (prev2 != -1) {
                    if ((prev1 > prev2 && prev1 > d) ||
                        (prev1 < prev2 && prev1 < d)) {
                        add = 1;
                    }
                }

                int newPrev2 = prev1;
                int newPrev1 = d;

                Node child = dfs(
                        pos + 1,
                        newPrev2,
                        newPrev1,
                        nextStarted,
                        nextTight
                );

                totalCnt += child.cnt;
                totalWav += child.wav + add * child.cnt;
            }
        }

        Node ans = new Node(totalCnt, totalWav);

        if (!tight) {
            memo[pos][prev2 + 1][prev1 + 1][started ? 1 : 0] = ans;
        }

        return ans;
    }
}