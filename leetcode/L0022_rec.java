import java.util.ArrayList;
import java.util.List;

public class L0022_rec {
  static List<String> answer;

  public static void main(String[] args) {
    System.out.println(generateParenthesis(3));
  }

  public static List<String> generateParenthesis(int n) {
    answer = new ArrayList<>();
    generateParenthesis(n, n, new char[1 << n], 0);
    return answer;
  }

  public static void generateParenthesis(int opened, int closed, char[] traversed, int till) {
    if (opened > closed) return;
    if (opened == 0) {
      while (closed-- != 0) traversed[till++] = ')';
      answer.add(new String(traversed));
      return;
    }
    traversed[till] = '(';
    generateParenthesis(opened - 1, closed, traversed, till + 1);
    traversed[till] = ')';
    generateParenthesis(opened, closed - 1, traversed, till + 1);
  }
}
