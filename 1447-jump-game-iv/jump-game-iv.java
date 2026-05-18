import java.util.*;

class Solution {
    public int minJumps(int[] arr) {
        int n = arr.length;

        // If already at last index
        if (n == 1) return 0;

        // Store indices having same value
        HashMap<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);
        }

        // BFS
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[n];

        q.offer(0);
        visited[0] = true;

        int steps = 0;

        while (!q.isEmpty()) {
            int size = q.size();

            for (int s = 0; s < size; s++) {
                int curr = q.poll();

                // Reached last index
                if (curr == n - 1) {
                    return steps;
                }

                // Move to curr - 1
                if (curr - 1 >= 0 && !visited[curr - 1]) {
                    visited[curr - 1] = true;
                    q.offer(curr - 1);
                }

                // Move to curr + 1
                if (curr + 1 < n && !visited[curr + 1]) {
                    visited[curr + 1] = true;
                    q.offer(curr + 1);
                }

                // Move to all same-value indices
                if (map.containsKey(arr[curr])) {
                    for (int next : map.get(arr[curr])) {
                        if (!visited[next]) {
                            visited[next] = true;
                            q.offer(next);
                        }
                    }

                    // Clear list to avoid repeated processing
                    map.remove(arr[curr]);
                }
            }

            steps++;
        }

        return -1;
    }
}