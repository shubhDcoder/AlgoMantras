import java.util.ArrayList;
import java.util.List;

public class KeyPad {
  public static void main(String[] args) {
    System.out.println("\nnumber of combinations for keys 456 are -> " + getMeTheTypedWord_V1("456", 0, ""));

    System.out.println("\nnumber of combinations for keys 1011 are -> " + getMeTheTypedWord_V3("1011", 0, ""));

    List<String> answer = getMeTheTypedWord_V2("456");
    System.out.println(answer);
    System.out.println("number of combinations for keys 456 are -> " + answer.size());
  }

  private static String[] mapping = {".-/", "abc", "def", "ghi", "jkl", "mno", "pqr", "stu", "vwx", "yz", "!@#", "$%^"};

  private static int getMeTheTypedWord_V1(String input, int index, String answer) {
    if (index == input.length()) {
      System.out.print(answer + ", ");
      return 1;
    }

    int count = 0;
    String mappedKeys = mapping[input.charAt(index) - '0'];
    for (int i = 0; i < mappedKeys.length(); i++) {
      count += getMeTheTypedWord_V1(input, index + 1, answer + mappedKeys.charAt(i));
    }

    return count;
  }

  private static List<String> getMeTheTypedWord_V2(String keys) {
    if (keys.length() == 0) {
      List<String> answer = new ArrayList<>();
      answer.add("");
      return answer;
    }

    char charAtThisStack = keys.charAt(0);
    String keyForNextStack = keys.substring(1);

    List<String> input = getMeTheTypedWord_V2(keyForNextStack);
    List<String> output = new ArrayList<>();

    String charsToMixUp = mapping[charAtThisStack - '0'];
    for (char c : charsToMixUp.toCharArray()) {
      for (String sentAnswer : input) {
        output.add(c + sentAnswer);
      }
    }

    return output;
  }

  public static int getMeTheTypedWord_V3(String input, int index, String answer) {
    if (index == input.length()) {
      System.out.print(answer + ", ");
      return 1;
    }

    int count = 0;
    String keys = mapping[input.charAt(index) - '0'];
    for (char c : keys.toCharArray()) {
      count += getMeTheTypedWord_V3(input, index + 1, answer + c);
    }

    if (index + 1 < input.length()) {
      int numberFormed = ((input.charAt(index) - '0') * 10) + (input.charAt(index + 1) - '0');
      if (numberFormed == 10 || numberFormed == 11) {
        keys = mapping[numberFormed];
        for (char c : keys.toCharArray()) {
          count += getMeTheTypedWord_V3(input, index + 2, answer + c);
        }
      }
    }

    return count;
  }
}
