public class BoardPathV1 {
  public static void main(String[] args) {
    int rows = 3, cols = 3;
    byte[][] visited = new byte[rows][cols];
    System.out.println("Get Longest path length when HVD in all directions allowed with jump V1 => " + getLongestPathV1(0, 0, rows - 1, cols - 1, visited));
    System.out.println("Get Longest path length when HVD in all directions allowed with jump V2 => " + getLongestPathV2(0, 0, rows - 1, cols - 1, 0, visited));
    System.out.println("Get Shortest path length when HVD in all directions allowed with jump V1 => " + getShortestPathV1(0, 0, rows - 1, cols - 1, visited));
    System.out.println("Get Shortest path length when HVD in all directions allowed without jump V2 => " + getShortestPathV2(0, 0, rows - 1, cols - 1, visited));
    System.out.println("Get ALl paths length with trace when HVD in all directions allowed with jump => ");
    System.out.println(getALlPathsWithTrace(0, 0, rows - 1, cols - 1, "", visited));
  }

  public static int directions[][] = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, 1}, {-1, 1}, {1, -1}, {-1, -1}};
  public static char[] dirMapping = {'D', 'F', 'U', 'B', 'S', 'E', 'W', 'N'};

  public static int getLongestPathV1(int sr, int sc, int dr, int dc, byte[][] visited) {
    if (sr == dr && sc == dc) {
      return 0;
    }

    visited[sr][sc] = 1;
    int maxLen = 0;
    for (int d = 0; d < directions.length; d++) {
      int radius = Math.max(Math.max(dr - sr, sr - 0), Math.max(dc - sc, sc - 0));
      for (int i = 1; i < radius; i++) {
        int nr = sr + i * directions[d][0];
        int nc = sc + i * directions[d][1];
        if (nr <= dr && nc <= dc && nr >= 0 && nc >= 0 && visited[nr][nc] == 0) maxLen = Math.max(maxLen, getLongestPathV1(nr, nc, dr, dc, visited));
      }
    }

    visited[sr][sc] = 0;
    return maxLen + 1;
  }

  public static int getLongestPathV2(int sr, int sc, int dr, int dc, int lenSoFar, byte[][] visited) {
    if (sr == dr && sc == dc) {
      return lenSoFar;
    }

    visited[sr][sc] = 1;
    int maxLen = 0;
    for (int d = 0; d < directions.length; d++) {
      int maxRadius = Math.max(Math.max(dr - sr, sr - 0), Math.max(dc - sc, sc - 0));
      for (int rad = 1; rad <= maxRadius; rad++) {
        int nr = sr + rad * directions[d][0];
        int nc = sc + rad * directions[d][1];
        if (nr <= dr && nc <= dc && nr >= 0 && nc >= 0 && visited[nr][nc] == 0) maxLen = Math.max(maxLen, getLongestPathV2(nr, nc, dr, dc, lenSoFar + 1, visited));
      }
    }
    visited[sr][sc] = 0;
    return maxLen;
  }

  // With Jump , shortest path turns out to be 1 only. That is one jump in diagonal with the largest possible radius.
  public static int getShortestPathV1(int sr, int sc, int dr, int dc, byte[][] visited) {
    if (sr == dr && sc == dc) {
      return 0;
    }

    visited[sr][sc] = 1;
    int minLength = visited[0].length * visited.length;
    for (int d = 0; d < directions.length; d++) {
      int maxRadius = Math.max(Math.max(dr - sr, sr - 0), Math.max(dc - sc, sc - 0));
      for (int rad = 1; rad <= maxRadius; rad++) {
        int nr = sr + rad * directions[d][0];
        int nc = sc + rad * directions[d][1];
        if (nr <= dr && nc <= dc && nr >= 0 && nc >= 0 && visited[nr][nc] == 0) minLength = Math.min(minLength, getShortestPathV1(nr, nc, dr, dc, visited));
      }
    }
    visited[sr][sc] = 0;
    return minLength + 1;
  }

  // Without Jump.
  public static int getShortestPathV2(int sr, int sc, int dr, int dc, byte[][] visited) {
    if (sr == dr && sc == dc) {
      return 0;
    }

    visited[sr][sc] = 1;
    int minLength = visited[0].length * visited.length;
    for (int d = 0; d < directions.length; d++) {
      {
        int nr = sr + directions[d][0];
        int nc = sc + directions[d][1];
        if (nr <= dr && nc <= dc && nr >= 0 && nc >= 0 && visited[nr][nc] == 0) minLength = Math.min(minLength, getShortestPathV1(nr, nc, dr, dc, visited));
      }
    }
    visited[sr][sc] = 0;
    return minLength + 1;
  }

  public static Trace getALlPathsWithTrace(int sr, int sc, int dr, int dc, String answer, byte[][] visited) {
    if (sr == dr && sc == dc) {
      return new Trace(0, 0, answer, answer);
    }

    visited[sr][sc] = 1;
    Trace trace = new Trace(0, visited[0].length * visited.length, "", "");
    for (int d = 0; d < directions.length; d++) {
      int maxRadius = Math.max(Math.max(dr - sr, sr - 0), Math.max(dc - sc, sc - 0));
      for (int rad = 1; rad <= maxRadius; rad++) {
        int nr = sr + rad * directions[d][0];
        int nc = sc + rad * directions[d][1];
        if (nr >= 0 && nc >= 0 && nr <= dr && nc <= dc && visited[nr][nc] == 0) {
          Trace tempTrace = getALlPathsWithTrace(nr, nc, dr, dc, String.format("%s %s%d", answer, dirMapping[d], rad), visited);
          if (tempTrace.longestPathLen >= trace.longestPathLen) {
            trace.longestPath = tempTrace.longestPath;
            trace.longestPathLen = tempTrace.longestPathLen;
          }

          if (tempTrace.shortestPathLen < trace.shortestPathLen) {
            trace.shortestPath = tempTrace.shortestPath;
            trace.shortestPathLen = tempTrace.shortestPathLen;
          }
        }
      }
    }
    visited[sr][sc] = 0;
    trace.shortestPathLen++;
    trace.longestPathLen++;
    return trace;
  }
}

class Trace {
  public int longestPathLen, shortestPathLen;
  public String longestPath, shortestPath;

  public Trace(int longestPathLen, int shortestPathLen, String longestPath, String shortestPath) {
    this.longestPath = longestPath;
    this.longestPathLen = longestPathLen;
    this.shortestPath = shortestPath;
    this.shortestPathLen = shortestPathLen;
  }

  @Override
  public String toString() {
    return String.format("[shortestPath: %s, shortestPathLen: %d]\n[longestPath: %s, longestPathLen: %d]", shortestPath, shortestPathLen, longestPath, longestPathLen);
  }
}
