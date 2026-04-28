import java.util.*;

class Solution {
    public int minOperations(int[][] grid, int x) {
        ArrayList<Integer> list = new ArrayList<>();
        
        int rem = grid[0][0] % x;
        
        // Step 1: Flatten and check possibility
        for (int[] row : grid) {
            for (int num : row) {
                if (num % x != rem) {
                    return -1;
                }
                list.add(num);
            }
        }
        
        // Step 2: Sort
        Collections.sort(list);
        
        // Step 3: Find median
        int median = list.get(list.size() / 2);
        
        // Step 4: Count operations
        int operations = 0;
        
        for (int num : list) {
            operations += Math.abs(num - median) / x;
        }
        
        return operations;
    }
}