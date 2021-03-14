public class L1219_rec {

  public static void main(String[] args) {
    int[][] arg = {{1, 0, 7}, {2, 0, 6}, {3, 4, 5}, {0, 3, 0}, {9, 0, 20}};
    System.out.println(getMaximumGold(arg));
  }

  public static int directions[][] = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

  public static int getMaximumGold(int[][] grid) {
    int maxCollected = 0;
    for (int row = 0; row < grid.length; row++) {
      for (int col = 0; col < grid[0].length; col++) {
        if (grid[row][col] != 0) {
          maxCollected = Math.max(maxCollected, getMaximumGold(row, col, grid));
        }
      }
    }
    return maxCollected;
  }

  public static int getMaximumGold(int sr, int sc, int[][] grid) {
    int collected = 0;
    int tempSaved = grid[sr][sc];
    grid[sr][sc] = 0;
    for (int[] dir : directions) {
      int nr = sr + dir[0];
      int nc = sc + dir[1];
      if (nr >= 0 && nc >= 0 && nr < grid.length && nc < grid[0].length && grid[nr][nc] != 0) {
        collected = Math.max(collected, getMaximumGold(nr, nc, grid));
      }
    }
    grid[sr][sc] = tempSaved;
    return tempSaved + collected;
  }
}
