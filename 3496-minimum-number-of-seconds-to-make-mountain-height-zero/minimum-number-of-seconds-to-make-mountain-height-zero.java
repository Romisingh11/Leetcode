class Solution {

    public long minNumberOfSeconds(int mountainHeight, int[] workerTimes) {

        long left = 0;
        long right = (long)1e18;

        while (left < right) {
            long mid = left + (right - left) / 2;

            if (canFinish(mid, mountainHeight, workerTimes)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private boolean canFinish(long time, int height, int[] workers) {

        long total = 0;

        for (int t : workers) {

            long low = 0, high = (long)1e6;

            while (low <= high) {
                long mid = (low + high) / 2;

                long needed = t * mid * (mid + 1) / 2;

                if (needed <= time) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }

            total += high;

            if (total >= height) return true;
        }

        return false;
    }
}