public class SudokuBasic {
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
      for (int number = 1; number <= 9; number++) {
        if (isOkToPlace(number, x, y)) {
          grid[x][y] = number;
          count += solveSudoku(cell + 1);
          grid[x][y] = 0;
        }
      }
    } else count = solveSudoku(cell + 1);
    return count;
  }

  private static boolean isOkToPlace(int number, int x, int y) {
    // For checking the number is valid for row
    for (int i = 0; i < 9; i++) if (grid[x][i] == number) return false;
    // For checking the number is valid for col
    for (int i = 0; i < 9; i++) if (grid[i][y] == number) return false;
    // For checking the number is valid for ThreeSquaredMatrix
    int gridX = x - (x % 3);
    int gridY = y - (y % 3);
    for (int i = gridX; i < gridX + 3; i++) for (int j = gridY; j < gridY + 3; j++) if (grid[i][j] == number) return false;

    return true;
  }

  private static void printGrid() {
    for (int[] row : grid) {
      for (int val : row) System.out.printf("%-4d", val);
      System.out.println();
    }
    System.out.println(String.format("%33s", " ").replace(" ", "-"));
  }
}
