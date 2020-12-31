public class MapWord {
  public static void main(String[] args) {
    System.out.println("\nmapping of 1123 can be " + getWords("1123", 0, ""));
  }

  private static String characters = "abcdefghijklmnopqrstuvwxyz";

  public static int getWords(String input, int index, String answer) {
    if (index == input.length()) {
      System.out.print(answer + ", ");
      return 1;
    }

    int count = getWords(input, index + 1, answer + characters.charAt(input.charAt(index) - '0'));

    if (index + 1 < input.length()) {
      int number = (input.charAt(index) - '0') * 10 + (input.charAt(index + 1) - '0');
      if (number < 27) count += getWords(input, index + 2, answer + characters.charAt(number));
    }

    return count;
  }
}
