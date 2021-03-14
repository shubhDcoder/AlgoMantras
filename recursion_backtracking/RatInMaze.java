import java.util.ArrayList;

public class RatInMaze {

  public static void main(String[] args) {
    int[][] m = {{1, 0, 0, 0}, {1, 1, 0, 1}, {1, 1, 0, 0}, {0, 1, 1, 1}};
    int n = 4;

    System.out.println(printPath(m, n));
  }

  public static ArrayList<String> printPath(int[][] m, int n) {
    if (m[0][0] == 0) return new ArrayList<String>();
    return printPath_driver(0, 0, n - 1, n - 1, m);
  }

  public static int[][] directions = {{1, 0}, {0, -1}, {0, 1}, {-1, 0}};
  public static String[] dirMapping = {"D", "L", "R", "U"};

  public static ArrayList<String> printPath_driver(int sr, int sc, int dr, int dc, int[][] m) {
    if (sr == dr && sc == dc) {
      ArrayList<String> ans = new ArrayList<>();
      ans.add("");
      return ans;
    }

    m[sr][sc] = 0;
    ArrayList<String> ans = new ArrayList<>();
    for (int i = 0; i < directions.length; i++) {
      int nr = sr + directions[i][0];
      int nc = sc + directions[i][1];
      if (nr <= dr && nc <= dc && nr >= 0 && nc >= 0 && m[nr][nc] == 1) {
        ArrayList<String> temp = printPath_driver(nr, nc, dr, dc, m);
        for (String path : temp) ans.add(dirMapping[i] + path);
      }
    }

    m[sr][sc] = 1;
    return ans;
  }
}
