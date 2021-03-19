import java.util.Arrays;

public class CrossWord {
  public static void main(String[] args) {
    // String[] input = {"+-++++++++", "+-++++++++", "+-++++++++", "+-----++++", "+-+++-++++", "+-+++-++++", "+++++-++++", "++------++", "+++++-++++", "+++++-++++"};
    // String words = "LONDON;DELHI;ICELAND;ANKARA";

    // String[] input = {"+-++++++++", "+-++++++++", "+-------++", "+-++++++++", "+-++++++++", "+------+++", "+-+++-++++", "+++++-++++", "+++++-++++", "++++++++++"};
    // String words = "AGRA;NORWAY;ENGLAND;GWALIOR";

    String[] input = {"++++++-+++", "++------++", "++++++-+++", "++++++-+++", "+++------+", "++++++-+-+", "++++++-+-+", "++++++++-+", "++++++++-+", "++++++++-+"};
    String words = "ICELAND;MEXICO;PANAMA;ALMATY";
    crosswordPuzzle(input, words);
  }

  static char[][] grid;
  static String[] lookup;

  static String[] crosswordPuzzle(String[] crossword, String words) {
    grid = new char[10][10];
    for (int i = 0; i < 10; i++) grid[i] = crossword[i].toCharArray();
    lookup = words.split(";");

    solveCrossWord(0);

    String[] answer = new String[10];
    for (int i = 0; i < 10; i++) answer[i] = new String(grid[i]);
    return answer;
  }

  static boolean solveCrossWord(int index) {
    if (index == lookup.length) {
      printGrid();
      return true;
    }
    boolean solved = false;
    String word = lookup[index];
    for (int r = 0; r < 10; r++) {
      for (int c = 0; c < 10; c++) {
        if (grid[r][c] == '-') {
          if (canPlaceHorizonntally(word, r, c)) {
            int placedAt = placeHorizontally(word, r, c);
            solved = solveCrossWord(index + 1);
            if (!solved) unPlaceHorizontally(word, r, c, placedAt);
          }

          if (canPlaceVertically(word, r, c) && !solved) {
            int placedAt = placeVertically(word, r, c);
            solved = solveCrossWord(index + 1);
            if (!solved) unPlaceVertically(word, r, c, placedAt);
          }

          if (solved) return true;
        }
      }
    }
    return false;
  }

  static boolean canPlaceHorizonntally(String word, int r, int c) {
    while (c > 0 && grid[r][c - 1] != '+') c--;
    int j = 0;
    for (int i = c; i < 10 && j < word.length(); i++, j++) {
      if (grid[r][i] != '-' && grid[r][i] != word.charAt(j)) return false;
    }
    if (j != word.length()) return false;
    if (9 - c - word.length() - 1 > 0 && grid[r][c + word.length()] != '+') return false;
    return true;
  }

  static int placeHorizontally(String word, int r, int c) {
    int mask = 0;
    while (c > 0 && grid[r][c - 1] != '+') c--;
    for (int i = c, j = 0; i < c + word.length(); i++, j++) {
      if (grid[r][i] == '-') {
        grid[r][i] = word.charAt(j);
        mask ^= (1 << j);
      }
    }
    return mask;
  }

  static void unPlaceHorizontally(String word, int r, int c, int placedAt) {
    while (c > 0 && grid[r][c - 1] != '+') c--;
    for (int i = c; i < word.length(); i++) {
      if ((placedAt & (1 << i)) != 0) grid[r][i] = '-';
    }
  }

  static boolean canPlaceVertically(String word, int r, int c) {
    while (r > 0 && grid[r - 1][c] != '+') r--;
    int j = 0;
    for (int i = r; i < 10 && j < word.length(); i++, j++) {
      if (grid[i][c] != '-' && grid[i][c] != word.charAt(j)) return false;
    }
    if (j != word.length()) return false;
    if (9 - r - word.length() - 1 > 0 && grid[r + word.length()][c] != '+') return false;
    return true;
  }

  static int placeVertically(String word, int r, int c) {
    int mask = 0;
    while (r > 0 && grid[r - 1][c] != '+') r--;
    for (int i = r, j = 0; i < r + word.length(); i++, j++) {
      if (grid[i][c] == '-') {
        grid[i][c] = word.charAt(j);
        mask ^= (1 << j);
      }
    }
    return mask;
  }

  static void unPlaceVertically(String word, int r, int c, int placedAt) {
    while (r > 0 && grid[r - 1][c] != '+') r--;
    for (int i = r; i < word.length(); i++) {
      if ((placedAt & (1 << i)) != 0) grid[i][c] = '-';
    }
  }

  static void printGrid() {
    for (char[] carr : grid) System.out.println(Arrays.toString(carr));
  }
}
