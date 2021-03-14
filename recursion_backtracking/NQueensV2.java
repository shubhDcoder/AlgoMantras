public class NQueensV2 {

  // Constants for matrix
  private static final int TOTAL_QUEEN = 4;
  private static final int TOTAL_ROW = 4;
  private static final int TOTAL_COL = 4;
  private static final int TOTAL_CELL = TOTAL_ROW * TOTAL_COL;

  // Markers
  private static final int[] rowMarker = new int[TOTAL_ROW];
  private static final int[] colMarker = new int[TOTAL_COL];
  private static final int[] diagMarker = new int[TOTAL_ROW + TOTAL_COL - 1];
  private static final int[] antiDiagMarker = new int[TOTAL_ROW + TOTAL_COL - 1];

  // Integer Markers
  private static int intRowMarker = 0;
  private static int intColMarker = 0;
  private static int intDiagMarker = 0;
  private static int intAntiDiagMarker = 0;

  public static void main(String[] args) {
    System.out.println("total places where queens can sit safely are : " + NQueensOptimizedByArray(0, "", 0));
    System.out.println("total places where queens can sit safely are : " + NQueensOptimizedByInteger(0, "", 0));
  }

  public static int NQueensOptimizedByArray(int queenNumber, String answer, int cell) {
    if (queenNumber == TOTAL_QUEEN) {
      System.out.println(answer.trim());
      return 1;
    }

    int count = 0;
    for (int c = cell; c < TOTAL_CELL; c++) {
      int x = c / TOTAL_COL;
      int y = c % TOTAL_COL;
      if (rowMarker[x] == 0 && colMarker[y] == 0 && diagMarker[x + y] == 0 && antiDiagMarker[x - y + TOTAL_COL - 1] == 0) {
        rowMarker[x] = 1;
        colMarker[y] = 1;
        diagMarker[x + y] = 1;
        antiDiagMarker[x - y + TOTAL_COL - 1] = 1;
        count += NQueensOptimizedByArray(queenNumber + 1, String.format("%s (%d, %d)", answer, x, y), c + 1);
        rowMarker[x] = 0;
        colMarker[y] = 0;
        diagMarker[x + y] = 0;
        antiDiagMarker[x - y + TOTAL_COL - 1] = 0;
      }
    }
    return count;
  }

  public static int NQueensOptimizedByInteger(int queenNumber, String answer, int cell) {
    if (queenNumber == TOTAL_QUEEN) {
      System.out.println(answer.trim());
      return 1;
    }

    int count = 0;
    for (int i = cell; i < TOTAL_CELL; i++) {
      int x = i / TOTAL_COL;
      int y = i % TOTAL_COL;
      if ((intRowMarker & (1 << x)) == 0 && (intColMarker & (1 << y)) == 0 && (intDiagMarker & (1 << (x + y))) == 0 && (intAntiDiagMarker & (1 << (x - y + TOTAL_COL - 1))) == 0) {
        intRowMarker ^= (1 << x);
        intColMarker ^= (1 << y);
        intDiagMarker ^= (1 << (x + y));
        intAntiDiagMarker ^= (1 << (x - y + TOTAL_COL - 1));
        count += NQueensOptimizedByInteger(queenNumber + 1, String.format("%s (%d, %d)", answer, x, y), i + 1);
        intRowMarker ^= (1 << x);
        intColMarker ^= (1 << y);
        intDiagMarker ^= (1 << (x + y));
        intAntiDiagMarker ^= (1 << (x - y + TOTAL_COL - 1));
      }
    }
    return count;
  }
}
