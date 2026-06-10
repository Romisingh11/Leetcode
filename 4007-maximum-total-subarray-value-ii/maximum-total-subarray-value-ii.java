import java.util.*;

class Solution {
    private int[][] mx;
    private int[][] mn;
    private int[] lg;

    private long value(int l, int r) {
        int k = lg[r - l + 1];

        int mxVal = Math.max(mx[k][l], mx[k][r - (1 << k) + 1]);
        int mnVal = Math.min(mn[k][l], mn[k][r - (1 << k) + 1]);

        return (long) mxVal - mnVal;
    }

    public long maxTotalValue(int[] nums, int k) {
        int n = nums.length;

        lg = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            lg[i] = lg[i >> 1] + 1;
        }

        int m = lg[n] + 1;

        mx = new int[m][n];
        mn = new int[m][n];

        for (int i = 0; i < n; i++) {
            mx[0][i] = nums[i];
            mn[0][i] = nums[i];
        }

        for (int j = 1; j < m; j++) {
            int len = 1 << j;
            int half = len >> 1;

            for (int i = 0; i + len <= n; i++) {
                mx[j][i] = Math.max(mx[j - 1][i],
                                    mx[j - 1][i + half]);

                mn[j][i] = Math.min(mn[j - 1][i],
                                    mn[j - 1][i + half]);
            }
        }

        PriorityQueue<Node> pq =
            new PriorityQueue<>((a, b) -> Long.compare(b.val, a.val));

        for (int l = 0; l < n; l++) {
            pq.offer(new Node(value(l, n - 1), l, n - 1));
        }

        long ans = 0;

        while (k-- > 0) {
            Node cur = pq.poll();

            ans += cur.val;

            if (cur.r > cur.l) {
                int nr = cur.r - 1;
                pq.offer(new Node(value(cur.l, nr), cur.l, nr));
            }
        }

        return ans;
    }

    static class Node {
        long val;
        int l;
        int r;

        Node(long val, int l, int r) {
            this.val = val;
            this.l = l;
            this.r = r;
        }
    }
}