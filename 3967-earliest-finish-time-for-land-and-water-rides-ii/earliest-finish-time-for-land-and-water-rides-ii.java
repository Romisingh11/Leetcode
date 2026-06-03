import java.util.*;

class Solution {
    
    public int earliestFinishTime(int[] landStartTime, int[] landDuration,
                                  int[] waterStartTime, int[] waterDuration) {
        
        int n = landStartTime.length;
        int m = waterStartTime.length;

        // Water rides preprocessing
        int[][] water = new int[m][2];
        for (int i = 0; i < m; i++) {
            water[i][0] = waterStartTime[i];
            water[i][1] = waterDuration[i];
        }

        Arrays.sort(water, (a, b) -> Integer.compare(a[0], b[0]));

        int[] wStart = new int[m];
        int[] wPrefMinDur = new int[m];
        int[] wSuffMinStartPlusDur = new int[m];

        for (int i = 0; i < m; i++) {
            wStart[i] = water[i][0];
        }

        wPrefMinDur[0] = water[0][1];
        for (int i = 1; i < m; i++) {
            wPrefMinDur[i] = Math.min(wPrefMinDur[i - 1], water[i][1]);
        }

        wSuffMinStartPlusDur[m - 1] = water[m - 1][0] + water[m - 1][1];
        for (int i = m - 2; i >= 0; i--) {
            wSuffMinStartPlusDur[i] = Math.min(
                wSuffMinStartPlusDur[i + 1],
                water[i][0] + water[i][1]
            );
        }

        // Land rides preprocessing
        int[][] land = new int[n][2];
        for (int i = 0; i < n; i++) {
            land[i][0] = landStartTime[i];
            land[i][1] = landDuration[i];
        }

        Arrays.sort(land, (a, b) -> Integer.compare(a[0], b[0]));

        int[] lStart = new int[n];
        int[] lPrefMinDur = new int[n];
        int[] lSuffMinStartPlusDur = new int[n];

        for (int i = 0; i < n; i++) {
            lStart[i] = land[i][0];
        }

        lPrefMinDur[0] = land[0][1];
        for (int i = 1; i < n; i++) {
            lPrefMinDur[i] = Math.min(lPrefMinDur[i - 1], land[i][1]);
        }

        lSuffMinStartPlusDur[n - 1] = land[n - 1][0] + land[n - 1][1];
        for (int i = n - 2; i >= 0; i--) {
            lSuffMinStartPlusDur[i] = Math.min(
                lSuffMinStartPlusDur[i + 1],
                land[i][0] + land[i][1]
            );
        }

        int ans = Integer.MAX_VALUE;

        // Land -> Water
        for (int i = 0; i < n; i++) {
            int landFinish = landStartTime[i] + landDuration[i];

            int idx = upperBound(wStart, landFinish);

            int best = Integer.MAX_VALUE;

            if (idx >= 0) {
                best = Math.min(best, landFinish + wPrefMinDur[idx]);
            }

            if (idx + 1 < m) {
                best = Math.min(best, wSuffMinStartPlusDur[idx + 1]);
            }

            ans = Math.min(ans, best);
        }

        // Water -> Land
        for (int j = 0; j < m; j++) {
            int waterFinish = waterStartTime[j] + waterDuration[j];

            int idx = upperBound(lStart, waterFinish);

            int best = Integer.MAX_VALUE;

            if (idx >= 0) {
                best = Math.min(best, waterFinish + lPrefMinDur[idx]);
            }

            if (idx + 1 < n) {
                best = Math.min(best, lSuffMinStartPlusDur[idx + 1]);
            }

            ans = Math.min(ans, best);
        }

        return ans;
    }

    // Returns last index with arr[index] <= target
    private int upperBound(int[] arr, int target) {
        int l = 0, r = arr.length;

        while (l < r) {
            int mid = l + (r - l) / 2;

            if (arr[mid] <= target) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }

        return l - 1;
    }
}