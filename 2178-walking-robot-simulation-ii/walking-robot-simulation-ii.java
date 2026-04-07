class Robot {

    int width, height;
    int x, y;
    int dir; // 0=East, 1=North, 2=West, 3=South

    int[][] dirs = {{1,0}, {0,1}, {-1,0}, {0,-1}};
    String[] dirNames = {"East", "North", "West", "South"};

    int cycle;

    public Robot(int width, int height) {
        this.width = width;
        this.height = height;
        this.x = 0;
        this.y = 0;
        this.dir = 0; // East
        this.cycle = 2 * (width + height - 2);
    }

    public void step(int num) {
        num %= cycle;

        // special case: full cycle
        if (num == 0) {
            if (x == 0 && y == 0) {
                dir = 3; // South
            }
            return;
        }

        while (num > 0) {
            int nx = x + dirs[dir][0];
            int ny = y + dirs[dir][1];

            // if out of bounds → turn CCW
            if (nx < 0 || nx >= width || ny < 0 || ny >= height) {
                dir = (dir + 1) % 4;
            } else {
                x = nx;
                y = ny;
                num--;
            }
        }
    }

    public int[] getPos() {
        return new int[]{x, y};
    }

    public String getDir() {
        return dirNames[dir];
    }
}