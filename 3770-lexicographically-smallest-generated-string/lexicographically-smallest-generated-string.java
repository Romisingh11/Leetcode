class Solution {
    public String generateString(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        
        char[] res = new char[n + m - 1];
        boolean[] fixed = new boolean[n + m - 1];
        
        // Step 1: fill with '?'
        for (int i = 0; i < res.length; i++) {
            res[i] = '?';
        }
        
        // Step 2: apply 'T'
        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) == 'T') {
                for (int j = 0; j < m; j++) {
                    if (res[i + j] == '?' || res[i + j] == str2.charAt(j)) {
                        res[i + j] = str2.charAt(j);
                        fixed[i + j] = true; // lock it
                    } else {
                        return "";
                    }
                }
            }
        }
        
        // Step 3: fill remaining with 'a'
        for (int i = 0; i < res.length; i++) {
            if (res[i] == '?') res[i] = 'a';
        }
        
        // Step 4: handle 'F'
        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) == 'F') {
                
                boolean match = true;
                for (int j = 0; j < m; j++) {
                    if (res[i + j] != str2.charAt(j)) {
                        match = false;
                        break;
                    }
                }
                
                if (match) {
                    boolean changed = false;
                    
                    // try from rightmost
                    for (int j = m - 1; j >= 0; j--) {
                        int idx = i + j;
                        
                        if (fixed[idx]) continue; // ❗ skip locked positions
                        
                        for (char c = 'a'; c <= 'z'; c++) {
                            if (c != str2.charAt(j)) {
                                res[idx] = c;
                                changed = true;
                                break;
                            }
                        }
                        
                        if (changed) break;
                    }
                    
                    if (!changed) return "";
                }
            }
        }
        
        return new String(res);
    }
}