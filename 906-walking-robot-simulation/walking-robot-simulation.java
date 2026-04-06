import java.util.*;

class Solution {
    public int robotSim(int[] commands, int[][] obstacles) {

        // Store obstacles
        Set<String> set = new HashSet<>();
        for (int[] obs : obstacles) {
            set.add(obs[0] + "," + obs[1]);
        }

        // Directions: North, East, South, West
        int[][] dirs = {
            {0, 1},   // North
            {1, 0},   // East
            {0, -1},  // South
            {-1, 0}   // West
        };

        int x = 0, y = 0;
        int dir = 0; // start facing North
        int maxDist = 0;

        for (int cmd : commands) {

            if (cmd == -1) {
                dir = (dir + 1) % 4; // turn right
            } 
            else if (cmd == -2) {
                dir = (dir + 3) % 4; // turn left
            } 
            else {
                // move step by step
                for (int i = 0; i < cmd; i++) {
                    int nx = x + dirs[dir][0];
                    int ny = y + dirs[dir][1];

                    // check obstacle
                    if (set.contains(nx + "," + ny)) {
                        break;
                    }

                    x = nx;
                    y = ny;

                    maxDist = Math.max(maxDist, x * x + y * y);
                }
            }
        }

        return maxDist;
    }
}