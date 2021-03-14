public class Sudoku {
  private static int[] rowMarker = new int[9];
  private static int[] colMarker = new int[9];
  private static int boxMarker[][] = new int[3][3];
  private static final int ROWS = 9;
  private static final int COLS = 9;
  private static final int TOTAL_CELLS = ROWS * COLS;
  private static int[][] grid;

  public static void main(String[] args) {
    String[][] board = {
      {"5", "3", ".", ".", "7", ".", ".", ".", "."},
      {"6", ".", ".", "1", "9", "5", ".", ".", "."},
      {".", "9", "8", ".", ".", ".", ".", "6", "."},
      {"8", ".", ".", ".", "6", ".", ".", ".", "3"},
      {"4", ".", ".", "8", ".", "3", ".", ".", "1"},
      {"7", ".", ".", ".", "2", ".", ".", ".", "6"},
      {".", "6", ".", ".", ".", ".", "2", "8", "."},
      {".", ".", ".", "4", "1", "9", ".", ".", "5"},
      {".", ".", ".", ".", "8", ".", ".", "7", "9"}
    };

    grid = new int[9][9];
    for (int i = 0; i < 9; i++)
      for (int j = 0; j < 9; j++)
        if (board[i][j].equals(".")) grid[i][j] = 0;
        else grid[i][j] = Integer.parseInt(board[i][j]);

    // fill row marker
    for (int i = 0; i < 9; i++) for (int j = 0; j < 9; j++) if (grid[i][j] != 0) rowMarker[i] ^= (1 << grid[i][j]);

    // fill col marker
    for (int i = 0; i < 9; i++) for (int j = 0; j < 9; j++) if (grid[j][i] != 0) colMarker[i] ^= (1 << grid[j][i]);

    // fill box marker
    for (int i = 0; i < 9; i++) for (int j = 0; j < 9; j++) if (grid[i][j] != 0) boxMarker[i / 3][j / 3] ^= (1 << grid[i][j]);

    int answer = solveSudoku(0);
    System.out.println("total solution for sudoku are " + answer);
  }

  public static int solveSudoku(int cell) {
    if (cell == TOTAL_CELLS) {
      printGrid();
      return 1;
    }

    int count = 0;
    int x = cell / COLS;
    int y = cell % COLS;
    if (grid[x][y] == 0) {
      int gridX = x / 3;
      int gridY = y / 3;
      for (int number = 1; number <= 9; number++) {
        if (((rowMarker[x] & (1 << number)) == 0) && ((colMarker[y] & (1 << number)) == 0) && ((boxMarker[gridX][gridY] & (1 << number)) == 0)) {
          grid[x][y] = number;
          rowMarker[x] ^= (1 << number);
          colMarker[y] ^= (1 << number);
          boxMarker[gridX][gridY] ^= (1 << number);
          count += solveSudoku(cell + 1);
          rowMarker[x] ^= (1 << number);
          colMarker[y] ^= (1 << number);
          boxMarker[gridX][gridY] ^= (1 << number);
          grid[x][y] = 0;
        }
      }
    } else {
      count = solveSudoku(cell + 1);
    }
    return count;
  }

  private static void printGrid() {
    for (int[] row : grid) {
      for (int val : row) System.out.printf("%-4d", val);
      System.out.println();
    }
  }
}
