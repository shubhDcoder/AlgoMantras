public class XorOfSumOfPairs {
  public static void main(String[] args) {
    int input[] = {1, 2, 3, 4, 5, 6, 7};
    int output = 0;
    for (int num : input) output ^= num;
    System.out.println(output);
  }
}
