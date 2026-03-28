import java.util.*;

class Solution {
    public String findTheString(int[][] lcp) {
        int n = lcp.length;

        // Step 1: Check diagonal
        for (int i = 0; i < n; i++) {
            if (lcp[i][i] != n - i) return "";
        }

        char[] word = new char[n];
        Arrays.fill(word, '#');

        char ch = 'a';

        // Step 2: Build string
        for (int i = 0; i < n; i++) {
            if (word[i] == '#') {
                if (ch > 'z') return ""; // more than 26 groups
                word[i] = ch;

                // assign same char where lcp[i][j] > 0
                for (int j = i + 1; j < n; j++) {
                    if (lcp[i][j] > 0) {
                        word[j] = ch;
                    }
                }
                ch++;
            }
        }

        // Step 3: Validate by recomputing LCP
        int[][] check = new int[n][n];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (word[i] == word[j]) {
                    if (i + 1 < n && j + 1 < n) {
                        check[i][j] = 1 + check[i + 1][j + 1];
                    } else {
                        check[i][j] = 1;
                    }
                } else {
                    check[i][j] = 0;
                }

                if (check[i][j] != lcp[i][j]) {
                    return "";
                }
            }
        }

        return new String(word);
    }
}