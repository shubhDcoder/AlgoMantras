import java.util.HashSet;
import java.util.Set;

public class WordBreak {
  public static void main(String[] args) {
    String inputs[] = {"i", "like", "sam", "sung", "samsung", "ilikes", "ilike", "likes"};
    Set<String> dict = new HashSet<>();
    int maxLen = 0;
    for (String inp : inputs) {
      dict.add(inp);
      if (inp.length() > maxLen) maxLen = inp.length();
    }
    System.out.println(getWordBreaks("ilikesamsung", 0, dict, "", maxLen));
  }

  public static int getWordBreaks(String input, int start, Set<String> dict, String answer, int maxLen) {
    if (start == input.length()) {
      System.out.println(answer.trim());
      return 1;
    }
    int count = 0;
    int end = start + 1;
    while (end <= start + maxLen && end <= input.length()) {
      if (dict.contains(input.substring(start, end))) count += getWordBreaks(input, end, dict, answer + " " + input.substring(start, end), maxLen);
      end++;
    }
    return count;
  }
}
