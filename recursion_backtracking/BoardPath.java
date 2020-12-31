import java.util.ArrayList;
import java.util.List;

public class BoardPath {
  public static void main(String[] args) {
    System.out.println("\nWIthout multimove allowed any direction, considering forward direction only");

    System.out.println("\nsource = (0,0), dest = (2, 2) -> " + getBoardPath_V1(0, 0, 2, 2, ""));
    List<String> answer = getBoardPath_V2(0, 0, 2, 2);
    System.out.println(answer);
    System.out.println("source = (0,0), dest = (2, 2) -> " + answer.size());

    System.out.println("\nWIth multimove allowed in HVD direction, considering forward direction only");

    System.out.println("\nsource = (0,0), dest = (2, 2) -> " + getBoardPath_V3(0, 0, 2, 2, ""));
    answer = getBoardPath_V4(0, 0, 2, 2);
    System.out.println(answer);
    System.out.println("source = (0,0), dest = (2, 2) -> " + answer.size());

    System.out.println("\nWIth multimove allowed in HVD forward/backward direction");
  }

  // When only Horizontal/Vertical/Diagoal calls are allowed - approach 1
  public static int getBoardPath_V1(int sr, int sc, int dr, int dc, String answer) {
    if (sr == dr && sc == dc) {
      System.out.print(answer + " ");
      return 1;
    }

    int ways = 0;
    if (sr + 1 <= dr) ways += getBoardPath_V1(sr + 1, sc, dr, dc, answer + "V");
    if (sc + 1 <= dc) ways += getBoardPath_V1(sr, sc + 1, dr, dc, answer + "H");
    if (sc + 1 <= dc && sr + 1 <= dr) ways += getBoardPath_V1(sr + 1, sc + 1, dr, dc, answer + "D");

    return ways;
  }
  // When only Horizontal/Vertical/Diagoal calls are allowed - approach 2
  public static int[][] directions = {{1, 0}, {0, 1}, {1, 1}};

  public static List<String> getBoardPath_V2(int sr, int sc, int dr, int dc) {
    if (sr == dr && sc == dc) {
      List<String> answer = new ArrayList<>();
      answer.add("");
      return answer;
    }

    List<String> answer = new ArrayList<>();
    for (int i = 0; i < directions.length; i++) {
      int nr = sr + directions[i][0];
      int nc = sc + directions[i][1];
      if (nr <= dr && nc <= dc) {
        List<String> answers = getBoardPath_V2(nr, nc, dr, dc);
        char charToAdd;
        if (i == 0) charToAdd = 'V';
        else if (i == 1) charToAdd = 'H';
        else charToAdd = 'D';
        for (String ans : answers) answer.add(charToAdd + ans);
      }
    }
    return answer;
  }

  // When only Horizontal/Vertical/Diagoal calls with JUMPS are allowed - approach 1
  public static int getBoardPath_V3(int sr, int sc, int dr, int dc, String answer) {
    if (sr == dr && sc == dc) {
      System.out.print(String.format("[%s] ", answer.trim()));
      return 1;
    }

    int ways = 0;
    for (int i = 1; i <= Math.max(dr - sr, dc - sc); i++) {
      if (sr + i <= dr) ways += getBoardPath_V3(sr + i, sc, dr, dc, answer + "V" + i + " ");
      if (sc + i <= dc) ways += getBoardPath_V3(sr, sc + i, dr, dc, answer + "H" + i + " ");
      if (sc + i <= dc && sr + i <= dr) ways += getBoardPath_V3(sr + i, sc + i, dr, dc, answer + "D" + i + " ");
    }

    return ways;
  }

  // When only Horizontal/Vertical/Diagoal calls with JUMPS are allowed - approach 2
  public static List<String> getBoardPath_V4(int sr, int sc, int dr, int dc) {
    if (sr == dr && sc == dc) {
      List<String> answer = new ArrayList<>();
      answer.add("");
      return answer;
    }

    List<String> answer = new ArrayList<>();
    for (int jump = 1; jump <= Math.max(dr - sr, dc - sc); jump++) {
      for (int i = 0; i < directions.length; i++) {
        int nr = sr + directions[i][0] * jump;
        int nc = sc + directions[i][1] * jump;
        if (nr <= dr && nc <= dc) {
          List<String> returnedAnswer = getBoardPath_V4(nr, nc, dr, dc);
          char charToAdd;
          if (i == 0) charToAdd = 'V';
          else if (i == 1) charToAdd = 'H';
          else charToAdd = 'D';
          for (String ans : returnedAnswer) answer.add("" + charToAdd + jump + " " + ans);
        }
      }
    }

    return answer;
  }
}
