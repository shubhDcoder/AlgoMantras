public class L0091_rec {
  public static void main(String[] args) {
    System.out.println("0001 -> " + numDecodings("0001"));
    System.out.println("0000 -> " + numDecodings("0000"));
    System.out.println("1011 -> " + numDecodings("1011"));
    System.out.println("1101 -> " + numDecodings("1101"));
    System.out.println("1010 -> " + numDecodings("1010"));
    System.out.println("2222 -> " + numDecodings("2222"));
    System.out.println("111111111111111111111111111111111111111111111 -> " + numDecodings("111111111111111111111111111111111111111111111"));
  }

  public static int numDecodings(String s) {
    return numDecodings_V1(s, 0);
  }

  // public static int numDecodings_(String s, int index, String answer) {
  public static int numDecodings_V1(String s, int index) {
    if (index == s.length()) {
      // if (answer.length() != 0) System.out.print(answer + " ");
      return 1;
    }

    int count = 0;
    char curr_char = s.charAt(index);
    if (curr_char != '0') {
      // count += numDecodings_(s, index + 1, answer + (char) (curr_char - '0' + 'a' - 1));
      count += numDecodings_V1(s, index + 1);

      if (index + 1 < s.length()) {
        char next_char = s.charAt(index + 1);
        int number = (curr_char - '0') * 10 + (next_char - '0');
        // if (number <= 26) count += numDecodings_(s, index + 2, answer + (char) (number + 'a' - 1));
        if (number <= 26) count += numDecodings_V1(s, index + 2);
      }
    }
    return count;
  }
}
