class Solution {
public:
    bool canReach(vector<int>& arr, int start) {
        int n = arr.size();
        
        queue<int> q;
        vector<bool> vis(n, false);

        q.push(start);
        vis[start] = true;

        while (!q.empty()) {
            int idx = q.front();
            q.pop();

            // If value is 0, we reached the target
            if (arr[idx] == 0)
                return true;

            int forward = idx + arr[idx];
            int backward = idx - arr[idx];

            // Move forward
            if (forward < n && !vis[forward]) {
                vis[forward] = true;
                q.push(forward);
            }

            // Move backward
            if (backward >= 0 && !vis[backward]) {
                vis[backward] = true;
                q.push(backward);
            }
        }

        return false;
    }
};