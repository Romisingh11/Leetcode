class Solution {
public:
    vector<bool> getResults(vector<vector<int>>& queries) {
        int mx = 0;
        for (auto &q : queries) mx = max(mx, q[1]);

        vector<int> seg(4 * (mx + 2), 0);

        auto update = [&](auto&& self, int node, int l, int r,
                          int idx, int val) -> void {
            if (l == r) {
                seg[node] = val;
                return;
            }

            int mid = (l + r) >> 1;

            if (idx <= mid)
                self(self, node << 1, l, mid, idx, val);
            else
                self(self, node << 1 | 1, mid + 1, r, idx, val);

            seg[node] = max(seg[node << 1], seg[node << 1 | 1]);
        };

        auto query = [&](auto&& self, int node, int l, int r,
                         int L, int R) -> int {
            if (R < l || r < L) return 0;
            if (L <= l && r <= R) return seg[node];

            int mid = (l + r) >> 1;

            return max(
                self(self, node << 1, l, mid, L, R),
                self(self, node << 1 | 1, mid + 1, r, L, R)
            );
        };

        set<int> obstacles;
        obstacles.insert(0);
        obstacles.insert(mx);

        for (auto &q : queries) {
            if (q[0] == 1) obstacles.insert(q[1]);
        }

        int last = -1;
        for (int x : obstacles) {
            if (last != -1)
                update(update, 1, 0, mx, x, x - last);
            last = x;
        }

        vector<bool> ans;

        for (int i = (int)queries.size() - 1; i >= 0; --i) {
            auto &q = queries[i];

            if (q[0] == 2) {
                int x = q[1];
                int sz = q[2];

                auto it = obstacles.upper_bound(x);
                --it;

                int p = *it;

                int best = query(query, 1, 0, mx, 0, p);
                int tail = x - p;

                ans.push_back(max(best, tail) >= sz);
            } else {
                int pos = q[1];

                auto it = obstacles.find(pos);

                auto prv = std::prev(it);
                auto nxt = std::next(it);

                int left = *prv;
                int right = *nxt;

                update(update, 1, 0, mx, pos, 0);
                update(update, 1, 0, mx, right, right - left);

                obstacles.erase(it);
            }
        }

        reverse(ans.begin(), ans.end());
        return ans;
    }
};