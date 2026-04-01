import java.util.*;

class Solution {

    static class Robot {
        int pos, health, index;
        char dir;

        Robot(int p, int h, char d, int i) {
            pos = p;
            health = h;
            dir = d;
            index = i;
        }
    }

    public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {

        int n = positions.length;
        Robot[] robots = new Robot[n];

        for (int i = 0; i < n; i++) {
            robots[i] = new Robot(positions[i], healths[i], directions.charAt(i), i);
        }

        // Sort by position
        Arrays.sort(robots, (a, b) -> a.pos - b.pos);

        Stack<Robot> stack = new Stack<>();

        for (Robot curr : robots) {

            if (curr.dir == 'R') {
                stack.push(curr);
            } else {

                // Fight with all previous 'R'
                while (!stack.isEmpty() && stack.peek().dir == 'R' && curr.health > 0) {

                    Robot top = stack.peek();

                    if (top.health < curr.health) {
                        stack.pop();
                        curr.health--;   // winner loses 1
                    }
                    else if (top.health > curr.health) {
                        top.health--;
                        curr.health = 0;
                    }
                    else {
                        stack.pop();
                        curr.health = 0;
                        break;
                    }
                }

                // If still alive, push it
                if (curr.health > 0) {
                    stack.push(curr);
                }
            }
        }

        // Collect survivors
        List<Robot> survivors = new ArrayList<>(stack);

        // Sort back to original order
        Collections.sort(survivors, (a, b) -> a.index - b.index);

        List<Integer> ans = new ArrayList<>();
        for (Robot r : survivors) {
            ans.add(r.health);
        }

        return ans;
    }
}