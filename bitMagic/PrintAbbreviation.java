public class PrintAbbreviation {
  public static void main(String[] args) {
    String input = "pep";
    printAbbreviation(input);
    System.out.println();
  }

  public static void printAbbreviation(String input) {
    int loopCount = (1 << input.length());
    for (int i = 0; i < loopCount; i++) {
      int carry = 0;
      StringBuilder answer = new StringBuilder();
      for (int j = input.length() - 1; j >= 0; j--) {
        if ((i & (1 << j)) == 0) {
          if (carry != 0) answer.append(carry);
          answer.append(input.charAt(j));
          carry = 0;
        } else {
          carry++;
        }
      }
      if (carry != 0) answer.append(carry);
      System.out.print(answer.toString() + " ");
    }
  }
}
