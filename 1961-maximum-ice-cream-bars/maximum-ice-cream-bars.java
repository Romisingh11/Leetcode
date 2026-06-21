class Solution {
    public int maxIceCream(int[] costs, int coins) {
        int maxCost = 0;

        for (int cost : costs) {
            maxCost = Math.max(maxCost, cost);
        }

        int[] freq = new int[maxCost + 1];

        for (int cost : costs) {
            freq[cost]++;
        }

        int count = 0;

        for (int cost = 1; cost <= maxCost; cost++) {
            if (freq[cost] == 0) continue;

            long totalCost = (long) cost * freq[cost];

            if (coins >= totalCost) {
                coins -= totalCost;
                count += freq[cost];
            } else {
                count += coins / cost;
                break;
            }
        }

        return count;
    }
}