import java.util.Arrays;

public class CheckPermutation {
  public static void main(String[] args) {
    System.out.println("Check for permutations by sorting " + checkPermutationBySorting("2233", "2332"));
    System.out.println("Check for permutations by sorting " + checkPermutationByCountingChars("2233", "2333"));
  }

  public static boolean checkPermutationBySorting(String s1, String s2) {
    if (s1.length() != s2.length()) return false;

    char[] s1Chars = s1.toCharArray();
    char[] s2Chars = s2.toCharArray();

    Arrays.sort(s1Chars);
    Arrays.sort(s2Chars);

    return new String(s1Chars).equals(new String(s2Chars));
  }

  public static boolean checkPermutationByCountingChars(String s1, String s2) {
    if (s1.length() != s2.length()) return false;

    int[] charCounts = new int[128];
    for (char c : s2.toCharArray()) charCounts[c]++;

    for (char c : s1.toCharArray()) if (--charCounts[c] < 0) return false;

    return true;
  }
}
