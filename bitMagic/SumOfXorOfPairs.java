public class SumOfXorOfPairs {
  public static void main(String[] args) {
    int[] input = {5, 9, 7, 6};
    System.out.println(getSumOfXor(input, 2, 0, 0));
    System.out.println(getSumOfXor_usingBits(input));
  }

  public static int getSumOfXor_usingBits(int[] input) {
    int result = 0;
    for (int i = 0; i < 32; i++) {
      int ones = 0, zeros = 0;
      for (int number : input) {
        // count ones and zeros
        if ((number & (1 << i)) == 0) zeros++;
        else ones++;
      }
      // add their contribution to answer
      result += (ones * zeros * (int) Math.pow(2, i));
    }
    return result;
  }

  // O(n2) using recursion
  public static int getSumOfXor(int[] input, int remaining, int index, int result) {
    if (remaining == 0) {
      return result;
    }
    if (index == input.length) {
      return 0;
    }

    int tempResult = 0;
    tempResult = (tempResult + getSumOfXor(input, remaining - 1, index + 1, result ^ input[index]));
    tempResult = (tempResult + getSumOfXor(input, remaining, index + 1, result));
    return tempResult;
  }
}
