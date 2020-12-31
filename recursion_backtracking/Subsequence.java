import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Subsequence {
  public static void main(String[] args) {
    System.out.println("V1 = subsequences of abcd are " + getSubsequence_V1("abcd", 0));
    System.out.print("V2 = all subsequences of 'abcd are ");
    int total = getSubsequence_V2("abcd", "", 0);
    System.out.print(total + "\n");

    System.out.println("V1 = all permutations of 'abcd' are " + permutations_V1("abcd", 0));

    System.out.print("V2 = all permutaions of 'abcd' are -> ");
    total = permutations_V2("abcd", "", 0);
    System.out.print(total + "\n");

    System.out.print("v3 = all permutations of 'abcd' are -> ");
    total = permutaions_V3("abcd", "");
    System.out.print(total + "\n");

    System.out.print("v4 = all permutations of 'abaa' with repetitions are -> ");
    total = permutaions_V3_with_repetition("abaa", "");
    System.out.print(total + "\n");
  }

  // Reach at the top of recursion and then return an arraylist of one blank string.
  // At any level of recursion, prepend the current character with every element of returned arraylist.
  // Finally merge these two arraylist (returned one + we created while prepending character to returne one)
  public static List<String> getSubsequence_V1(String input, int index) {
    if (input.length() == index) {
      List<String> answer = new ArrayList<>();
      answer.add("");
      return answer;
    }
    List<String> answer1 = getSubsequence_V1(input, index + 1);
    List<String> answer2 = answer1.stream().map(e -> input.charAt(index) + e).collect(Collectors.toList());
    return Stream.of(answer1, answer2).flatMap(x -> x.stream()).collect(Collectors.toList());
  }

  // At every point, any character would have two choices. Either it could be part of answer or not.
  // Build recursion tree for same.
  public static int getSubsequence_V2(String input, String answer, int index) {
    if (index == input.length()) {
      System.out.print(answer + " |");
      return 1;
    }

    int count = 0;
    count += getSubsequence_V2(input, answer + input.charAt(index), index + 1);
    count += getSubsequence_V2(input, answer, index + 1);
    return count;
  }

  // Reach at the top of recursion stack(at last element) and return an arraylist containing only that character.
  // At every level, mix-up current charater(character at location index of input) in every possible way to every element of returned arraylist.
  public static List<String> permutations_V1(String input, int index) {
    if (index == input.length() - 1) {
      List<String> answer = new ArrayList<>();
      answer.add(String.valueOf(input.charAt(index)));
      return answer;
    }
    List<String> tempAnswer = permutations_V1(input, index + 1);
    List<String> answer = new ArrayList<>();
    for (int i = 0; i < tempAnswer.size(); i++) {
      String tempStr = tempAnswer.get(i);
      for (int j = 0; j <= tempStr.length(); j++) {
        answer.add(tempStr.substring(0, j) + input.charAt(index) + tempStr.substring(j));
      }
    }
    return answer;
  }

  // Pick any character of input and accept it as answer,
  // Now pick any character of remaining string and try to mix-up this with string already accepted as answer.
  public static int permutations_V2(String input, String answer, int index) {
    if (index == input.length()) {
      System.out.print(answer + " |");
      return 1;
    }

    int count = 0;
    for (int i = 0; i <= answer.length(); i++) {
      count += permutations_V2(input, answer.substring(0, i) + input.charAt(index) + answer.substring(i), index + 1);
    }
    return count;
  }

  // Fix a character first and have faith that all the remaining characters will give you their perutations,
  // When we have our desired result, prepend the fixed character to every permutation we got.
  public static int permutaions_V3(String input, String answer) {
    if (input.length() == 0) {
      System.out.print(answer + " |");
      return 1;
    }

    int count = 0;
    for (int i = 0; i < input.length(); i++) {
      count += permutaions_V3(input.substring(0, i) + input.substring(i + 1), answer + input.charAt(i));
    }
    return count;
  }

  // Approach is same as above function.
  // However, we need to keep track of the characters we already fixed.
  // Fixing again a repetitive character will give us the same result, which we dont want.
  // Thus, taking an integer as visited indicator to prevent recursion tree growing in already visited direction.
  public static int permutaions_V3_with_repetition(String input, String answer) {
    if (input.length() == 0) {
      System.out.print(answer + " |");
      return 1;
    }

    int count = 0;
    int visitedBit = 0;
    for (int i = 0; i < input.length(); i++) {
      if ((visitedBit & (1 << (input.charAt(i) - 'a'))) == 0) {
        visitedBit = (visitedBit | (1 << (input.charAt(i) - 'a')));
        count += permutaions_V3_with_repetition(input.substring(0, i) + input.substring(i + 1), answer + input.charAt(i));
      }
    }
    return count;
  }
}
