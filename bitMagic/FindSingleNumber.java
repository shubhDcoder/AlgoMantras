public class FindSingleNumber {
  public static void main(String[] args) {
    int[] input = {1, 2, 3, 4, 3, 2, 1, 4, 55};

    int answer = 0;
    for (int inp : input) answer ^= inp;
    System.out.println("Unique number was > " + answer);
  }
}
