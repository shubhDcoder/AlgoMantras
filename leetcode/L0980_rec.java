public class L0980_rec {
  public static void main(String[] args) {
    // int[][] input = {{1, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 2, -1}};

    // int[][] input = {{1, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 2}};

    int[][] input = {{0, 1}, {2, 0}};
    System.out.println(uniquePathsIII(input));
  }

  public static int directions[][] = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

  public static int uniquePathsIII(int[][] grid) {
    int noOfEmptyCells = 0;
    int sr = 0, sc = 0;
    for (int row = 0; row < grid.length; row++)
      for (int col = 0; col < grid[0].length; col++) {
        if (grid[row][col] == 0) noOfEmptyCells++;
        else if (grid[row][col] == 1) {
          sr = row;
          sc = col;
        }
      }
    noOfEmptyCells += 2;
    return uniquePathsIII(sr, sc, grid, noOfEmptyCells, 1);
  }

  public static int uniquePathsIII(int sr, int sc, int[][] grid, int noOfEmptyCells, int lenSoFar) {
    if (grid[sr][sc] == 2) {
      if (lenSoFar == noOfEmptyCells) return 1;
      return 0;
    }

    grid[sr][sc] = -1;
    int ways = 0;
    for (int[] dir : directions) {
      int nr = sr + dir[0];
      int nc = sc + dir[1];
      if (nr >= 0 && nc >= 0 && nr < grid.length && nc < grid[0].length && grid[nr][nc] != -1) {
        int wayFound = uniquePathsIII(nr, nc, grid, noOfEmptyCells, lenSoFar + 1);
        if (wayFound > 0) ways += wayFound;
      }
    }
    grid[sr][sc] = 0;
    return ways;
  }
}
