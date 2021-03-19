public class LexicoGraphicallySorted {
  public static void main(String[] args) {
    printLexicographicallySorted(1, 1000);
  }

  public static void printLexicographicallySorted(int number, int limit) {
    if (number > limit) return;
    System.out.println(number);
    for (int i = 0; i < 10 && number * 10 + i <= limit; i++) printLexicographicallySorted(number * 10 + i, limit);
    if (number + 1 < 10) printLexicographicallySorted(number + 1, limit);
  }
}
