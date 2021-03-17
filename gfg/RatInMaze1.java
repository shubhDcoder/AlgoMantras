import java.util.ArrayList;
import java.util.List;

public class RatInMaze1 {
  static byte[][] MOVES = {{1, 0}, {0, -1}, {0, 1}, {-1, 0}};
  static char[] MOVE_MAPPING = {'D', 'L', 'R', 'U'};
  static List<String> answer = new ArrayList<>();

  public static void main(String[] args) {
    // int[][] matrix = {{1, 0, 0, 0}, {1, 1, 0, 1}, {1, 1, 0, 0}, {0, 1, 1, 1}};
    // int N = 4;
    int[][] matrix = {{1, 1}, {1, 1}};
    int N = 2;

    System.out.println(findPath(matrix, N));
  }

  public static List<String> findPath(int[][] m, int n) {
    if (m[n - 1][n - 1] == 0) return answer;
    findPath(m, n, 0, 0, "");
    return answer;
  }

  public static void findPath(int[][] grid, int N, int x, int y, String path) {
    if (x == N - 1 && y == N - 1) {
      answer.add(path);
      return;
    }

    grid[x][y] = 0;
    for (int d = 0; d < MOVES.length; d++) {
      int x_ = x + MOVES[d][0];
      int y_ = y + MOVES[d][1];
      if (x_ >= 0 && y_ >= 0 && x_ < N && y_ < N && grid[x_][y_] == 1) {
        findPath(grid, N, x_, y_, path + MOVE_MAPPING[d]);
      }
    }
    grid[x][y] = 1;
  }
}
