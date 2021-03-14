public class KnightsTour {
  public static void main(String[] args) {
    int[][] board = new int[8][8];
    for (int i = 0; i < board.length; i++) for (int j = 0; j < board[0].length; j++) board[i][j] = -1;
    board[0][0] = 0;
    printKnightTour(0, 0, board, 1);
  }

  public static final byte[][] MOVES = {{2, 1}, {1, 2}, {-1, 2}, {-2, 1}, {-2, -1}, {-1, -2}, {1, -2}, {2, -1}};

  public static boolean printKnightTour(int x, int y, int[][] board, int covered) {
    if (covered == 64) {
      printBoard(board);
      return true;
    }

    boolean result = false;
    for (int d = 0; d < MOVES.length && !result; d++) {
      int _x = x + MOVES[d][0];
      int _y = y + MOVES[d][1];
      if (_x >= 0 && _y >= 0 && _x < 8 && _y < 8 && board[_x][_y] == -1) {
        board[_x][_y] = covered;
        result = printKnightTour(_x, _y, board, covered + 1);
        board[_x][_y] = -1;
      }
    }

    return result;
  }

  public static void printBoard(int[][] board) {
    for (int[] row : board) {
      for (int move : row) {
        System.out.print(String.format("%-4d", move));
      }
      System.out.println();
    }
  }
}
