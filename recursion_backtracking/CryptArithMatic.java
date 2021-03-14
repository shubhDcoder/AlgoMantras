public class CryptArithMatic {
  public static String first;
  public static String second;
  public static String third;
  public static String unique;
  public static int numberUsed;
  public static int[] encodedInts;

  public static void main(String[] args) {
    first = "send";
    second = "more";
    third = "money";
    encodedInts = new int[26];
    callForPermutation();
  }

  public static void callForPermutation() {
    int uniques = 0;
    String joinedString = first + second + third;
    for (int i = 0; i < joinedString.length(); i++) uniques |= (1 << (joinedString.charAt(i) - 'a'));
    unique = "";
    for (int i = 0; i < 26; i++) if ((uniques & (1 << i)) != 0) unique += ((char) (i + 'a'));
    System.out.println(getTotalPermutations(0));
  }

  public static int getTotalPermutations(int index) {
    if (index == unique.length()) {
      if (encodedInts[third.charAt(0) - 'a'] == 0) return 0;
      int num1 = getMeNumber(first);
      int num2 = getMeNumber(second);
      int num3 = getMeNumber(third);
      if (num1 + num2 == num3) {
        for (char c : unique.toCharArray()) System.out.printf("[%c %d]", c - 32, encodedInts[c - 'a']);
        System.out.println();
        return 1;
      }
      return 0;
    }
    int count = 0;
    for (int number = 0; number < 10; number++) {
      int mask = (1 << number);
      if ((numberUsed & mask) == 0) {
        numberUsed ^= mask;
        encodedInts[unique.charAt(index) - 'a'] = number;
        count += getTotalPermutations(index + 1);
        numberUsed ^= mask;
        encodedInts[unique.charAt(index) - 'a'] = 0;
      }
    }

    return count;
  }

  public static int getMeNumber(String str) {
    String answer = "";
    for (int i = 0; i < str.length(); i++) answer += encodedInts[str.charAt(i) - 'a'];
    return Integer.parseInt(answer);
  }
}
