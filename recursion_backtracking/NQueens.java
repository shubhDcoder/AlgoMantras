public class NQueens {

  public static final int roomsAvailable = 5;
  public static final int queensAvailable = 3;
  public static final int queensAvailableFor2D = 4;
  public static final int row2D = 4;
  public static final int col2D = 4;

  public static void main(String[] args) {
    int answer = nQueenCombination(0, 0, "");
    System.out.println(String.format("\nCombinations of placing %d queens in %d rooms are %d", queensAvailable, roomsAvailable, answer));

    answer = nQueenCombinationV1(0, "", 0);
    System.out.println(String.format("\nCombinations of placing %d queens in %d rooms are %d", queensAvailable, roomsAvailable, answer));

    answer = nQueenPermutation(new byte[roomsAvailable], 0, 0, "");
    System.out.println(String.format("\nPermutations of placing %d queens in %d rooms are %d", queensAvailable, roomsAvailable, answer));

    answer = nQueenPermutationV1(new byte[roomsAvailable], 0, "");
    System.out.println(String.format("\nPermutations of placing %d queens in %d rooms are %d", queensAvailable, roomsAvailable, answer));

    answer = nQueenCombination2DArray(0, "", 0);
    System.out.println(String.format("\nCombinations of placing %d queens in row %d, col %d rooms are %d", queensAvailableFor2D, row2D, col2D, answer));

    answer = nQueenPermutation2DArray("", 0, new byte[row2D * col2D]);
    System.out.println(String.format("\nPermutations of placing %d queens in row %d, col %d rooms are %d", queensAvailableFor2D, row2D, col2D, answer));
  }

  public static int nQueenCombination(int room, int queen, String answer) {
    if (queen == queensAvailable) {
      // System.out.print(String.format("[%s] ", answer.trim()));
      return 1;
    }
    if (room == roomsAvailable) return 0;
    int count = 0;
    count += nQueenCombination(room + 1, queen + 1, String.format(answer + " R%dQ%d", room, queen));
    count += nQueenCombination(room + 1, queen, answer);
    return count;
  }

  public static int nQueenCombinationV1(int queen, String answer, int roomAt) {
    if (queen == queensAvailable) {
      // System.out.print(String.format("[%s] ", answer.trim()));
      return 1;
    }

    int count = 0;
    for (int r = roomAt; r < roomsAvailable; r++) {
      if (roomsAvailable - r >= queensAvailable - queen) count += nQueenCombinationV1(queen + 1, String.format(answer + " R%dQ%d", r, queen), r + 1);
    }
    return count;
  }

  public static int nQueenPermutation(byte[] visited, int queen, int room, String answer) {
    if (queen == queensAvailable) {
      // System.out.print(String.format("[%s] ", answer.trim()));
      return 1;
    }
    if (room == roomsAvailable) return 0;

    int count = 0;
    if (visited[room] == 0) {
      visited[room] = 1;
      count += nQueenPermutation(visited, queen + 1, 0, String.format(answer + " R%dQ%d", room, queen));
      visited[room] = 0;
    }
    count += nQueenPermutation(visited, queen, room + 1, answer);

    return count;
  }

  public static int nQueenPermutationV1(byte[] visited, int queen, String answer) {
    if (queen == queensAvailable) {
      // System.out.print(String.format("[%s] ", answer.trim()));
      return 1;
    }

    int count = 0;
    for (int r = 0; r < roomsAvailable; r++) {
      if (visited[r] == 0) {
        visited[r] = 1;
        count += nQueenPermutationV1(visited, queen + 1, String.format(answer + " R%dQ%d", r, queen));
        visited[r] = 0;
      }
    }
    return count;
  }

  public static int nQueenCombination2DArray(int index, String answer, int queen) {
    if (queen == queensAvailableFor2D) {
      // System.out.print(String.format("[%s] ", answer.trim()));
      return 1;
    }

    int count = 0;
    int totalRooms = row2D * col2D;
    for (int i = index; i < totalRooms; i++) {
      int x = i / col2D;
      int y = i % col2D;
      if (totalRooms >= queensAvailableFor2D - queen) count += nQueenCombination2DArray(i + 1, String.format(answer + " (%d %d)", x, y), queen + 1);
    }
    return count;
  }

  public static int nQueenPermutation2DArray(String answer, int queen, byte[] visited) {
    if (queen == queensAvailableFor2D) {
      // System.out.print(String.format("[%s] ", answer.trim()));
      return 1;
    }

    int count = 0;
    int totalRooms = row2D * col2D;
    for (int i = 0; i < totalRooms; i++) {
      if (visited[i] == 0) {
        int x = i / col2D;
        int y = i % col2D;
        visited[i] = 1;
        count += nQueenPermutation2DArray(String.format(answer + " (%d %d)", x, y), queen + 1, visited);
        visited[i] = 0;
      }
    }
    return count;
  }
}
