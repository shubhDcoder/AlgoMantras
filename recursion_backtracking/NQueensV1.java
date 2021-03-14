public class NQueensV1 {

  private static final int TOTAL_QUEENS = 4;
  private static final int ROWS = 4;
  private static final int COLS = 4;
  private static final int TOTAL_ROOMS = ROWS * COLS;
  private static final byte[][] DIRECTIONS = {{0, -1}, {-1, -1}, {-1, 0}, {-1, 1}};
  private static final byte[][] DIRECTIONS8 = {{0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}};

  public static void main(String[] args) {
    byte[][] visited = new byte[ROWS][COLS];
    int answer = nQueensCombinationV1(0, 0, "", visited);
    System.out.println(String.format("V1 Total combinations for placing %d queens in %dX%d matrix is : %d", TOTAL_QUEENS, ROWS, COLS, answer));

    answer = nQueensCombinationV2(0, 0, "", visited);
    System.out.println(String.format("V2 Total combinations for placing %d queens in %dX%d matrix is : %d", TOTAL_QUEENS, ROWS, COLS, answer));

    answer = nQueensPermutationV1(0, "", visited);
    System.out.println(String.format("V1 Total Permutations for placing %d queens in %dX%d matrix is : %d", TOTAL_QUEENS, ROWS, COLS, answer));

    answer = nQueensPermutationV2(0, 0, "", visited);
    System.out.println(String.format("V2 Total Permutations for placing %d queens in %dX%d matrix is : %d", TOTAL_QUEENS, ROWS, COLS, answer));
  }

  public static boolean canBePlacedV1(int x, int y, byte[][] visited) {
    for (byte[] dir : DIRECTIONS) {
      for (int rad = 1; rad < Math.max(ROWS, COLS); rad++) {
        int _x = x + rad * dir[0];
        int _y = y + rad * dir[1];
        if (_x >= 0 && _y >= 0 && _x < ROWS && _y < COLS) {
          if (visited[_x][_y] == 1) return false;
        } else break;
      }
    }
    return true;
  }

  public static boolean canBePlacedV2(int x, int y, byte[][] visited) {
    for (byte[] dir : DIRECTIONS8) {
      for (int rad = 1; rad < Math.max(ROWS, COLS); rad++) {
        int _x = x + rad * dir[0];
        int _y = y + rad * dir[1];
        if (_x >= 0 && _y >= 0 && _x < ROWS && _y < COLS) {
          if (visited[_x][_y] == 1) return false;
        } else break;
      }
    }
    return true;
  }

  public static int nQueensCombinationV2(int queen, int index, String answer, byte[][] visited) {
    if (queen == TOTAL_QUEENS) {
      System.out.println(String.format("[%s]", answer.trim()));
      return 1;
    }

    int x = index / COLS;
    int y = index % COLS;
    if (x < 0 || y < 0 || x >= ROWS || y >= COLS) return 0;

    int count = 0;
    if (canBePlacedV1(x, y, visited)) {
      visited[x][y] = 1;
      count += nQueensCombinationV2(queen + 1, index + 1, answer + String.format("(%s,%s) ", x, y), visited);
      visited[x][y] = 0;
    }
    count += nQueensCombinationV2(queen, index + 1, answer, visited);
    return count;
  }

  public static int nQueensCombinationV1(int queen, int index, String answer, byte[][] visited) {
    if (queen == TOTAL_QUEENS) {
      System.out.println(String.format("[%s]", answer.trim()));
      return 1;
    }

    int count = 0;
    for (int i = index; i < TOTAL_ROOMS; i++) {
      int x = i / COLS;
      int y = i % COLS;
      if (canBePlacedV1(x, y, visited)) {
        visited[x][y] = 1;
        count += nQueensCombinationV1(queen + 1, i + 1, answer + String.format("(%s,%s) ", x, y), visited);
        visited[x][y] = 0;
      }
    }
    return count;
  }

  public static int nQueensPermutationV1(int queen, String answer, byte[][] visited) {
    if (queen == TOTAL_QUEENS) {
      // System.out.println(String.format("[%s]", answer.trim()));
      return 1;
    }

    int count = 0;
    for (int i = 0; i < TOTAL_ROOMS; i++) {
      int x = i / COLS;
      int y = i % COLS;
      if (visited[x][y] == 0 && canBePlacedV2(x, y, visited)) {
        visited[x][y] = 1;
        count += nQueensPermutationV1(queen + 1, answer + String.format("(%s,%s) ", x, y), visited);
        visited[x][y] = 0;
      }
    }
    return count;
  }

  public static int nQueensPermutationV2(int queen, int index, String answer, byte[][] visited) {
    if (queen == TOTAL_QUEENS) {
      // System.out.println(String.format("[%s]", answer.trim()));
      return 1;
    }

    int x = index / COLS;
    int y = index % COLS;
    if (x >= ROWS || y >= COLS) return 0;

    int count = 0;
    if (visited[x][y] == 0 && canBePlacedV2(x, y, visited)) {
      visited[x][y] = 1;
      count += nQueensPermutationV2(queen + 1, 0, answer + String.format("(%s,%s) ", x, y), visited);
      visited[x][y] = 0;
    }
    count += nQueensPermutationV2(queen, index + 1, answer, visited);

    return count;
  }
}
