import java.util.Arrays;

public class IsUnique {
  public static void main(String[] args) {
    System.out.println(isUniqueByCompare("hackmeifyoucanbuddy"));
    System.out.println(isUniqueBySorting("hackmeifyoucanbuddy"));

    System.out.println(isUniqueByAdditionalDS("hackmeifyoucanbuddy"));
    System.out.println(isUniqueByByUsingInt("hackmeifyoucanbuddy"));
  }

  // Will take O(N^2) time and O(1) space
  public static boolean isUniqueByCompare(String input) {
    for (int i = 0; i < input.length() - 1; i++) {
      for (int j = i + 1; j < input.length(); j++) {
        if (input.charAt(i) == input.charAt(j)) {
          System.out.println("duplicate at " + input.charAt(j));
          return false;
        }
      }
    }
    return true;
  }

  // Take O(N logN) for sorting and O(N) for checking it linearly.
  // TIme complexity will be dominating term - O(N logN)
  // Space O(1) -- If the sorting algorithm is inplace
  public static boolean isUniqueBySorting(String input) {
    char[] array = input.toCharArray();
    Arrays.sort(array);
    for (int i = 0; i < array.length - 1; i++) {
      if (array[i] == array[i + 1]) {
        System.out.println("duplicate at " + array[i]);
        return false;
      }
    }
    return true;
  }

  // assuming input to be extended ASCII set
  // Time complexity is O(256) == O(min(Total 256 available chars, chars available in input string).
  // Space complexity is O(256) == O(c)
  public static boolean isUniqueByAdditionalDS(String input) {
    if (input.length() > 256) return false;

    byte[] marks = new byte[256];
    for (char c : input.toCharArray()) {
      if (marks[c] == 1) {
        System.out.println("duplicate at " + c);
        return false;
      } else marks[c] = 1;
    }
    return true;
  }

  // If the character set is only from 'a' to 'z', we could use a more optimized solution like below.
  // Time O(n), Space O(1)
  public static boolean isUniqueByByUsingInt(String input) {
    if (input.length() > 26) return false;

    int marks = 0;
    for (char c : input.toCharArray()) {
      int index = c - 'a';
      if ((marks & (1 << index)) > 0) {
        System.out.println("duplicate at " + c);
        return false;
      }
      marks = (marks | (1 << index));
    }
    return true;
  }
}
