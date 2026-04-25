import java.util.*;

class Solution {

    static class Point {
        int x, y;
        long pos;
        Point(int x, int y, long pos) {
            this.x = x;
            this.y = y;
            this.pos = pos;
        }
    }

    Point[] pts;
    int n, k;

    public int maxDistance(int side, int[][] points, int k) {
        this.k = k;
        this.n = points.length;
        pts = new Point[n];

        for (int i = 0; i < n; i++) {
            int x = points[i][0], y = points[i][1];
            long pos;

            if (y == 0) pos = x;
            else if (x == side) pos = side + y;
            else if (y == side) pos = 3L * side - x;
            else pos = 4L * side - y;

            pts[i] = new Point(x, y, pos);
        }

        Arrays.sort(pts, (a, b) -> Long.compare(a.pos, b.pos));

        int low = 0, high = 2 * side, ans = 0;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (can(mid)) {
                ans = mid;
                low = mid + 1;
            } else high = mid - 1;
        }

        return ans;
    }

    private boolean can(int dist) {
        Point[] ext = new Point[2 * n];
        for (int i = 0; i < n; i++) {
            ext[i] = pts[i];
            ext[i + n] = pts[i];
        }

        int[] next = new int[2 * n];
        int j = 1;

        for (int i = 0; i < 2 * n; i++) {
            if (j <= i) j = i + 1;

            while (j < 2 * n && manhattan(ext[i], ext[j]) < dist) {
                j++;
            }
            next[i] = j;
        }

        int LOG = 6; // since k<=25
        int[][] up = new int[LOG][2 * n + 1];

        for (int i = 0; i < 2 * n; i++) up[0][i] = next[i];
        up[0][2 * n] = 2 * n;

        for (int p = 1; p < LOG; p++) {
            for (int i = 0; i <= 2 * n; i++) {
                int mid = up[p - 1][i];
                up[p][i] = (mid <= 2 * n ? up[p - 1][mid] : 2 * n);
            }
        }

        for (int start = 0; start < n; start++) {
            int cur = start;
            int need = k - 1;

            for (int p = 0; p < LOG; p++) {
                if (((need >> p) & 1) == 1) {
                    cur = up[p][cur];
                    if (cur >= start + n) break;
                }
            }

            if (cur >= start + n) continue;

            if (manhattan(ext[start], ext[cur]) >= dist) {
                return true;
            }
        }

        return false;
    }

    private int manhattan(Point a, Point b) {
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }
}