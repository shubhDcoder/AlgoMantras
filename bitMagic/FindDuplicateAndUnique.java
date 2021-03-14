public class FindDuplicateAndUnique {
  public static void main(String[] args) {
    int[] input = {1, 2, 3, 4, 5, 6, 7, 3, 8, 9};
    int inputLen = input.length;
    int combinedXor = 0;
    for (int i = 1; i <= inputLen; i++) combinedXor = (combinedXor ^ input[i - 1] ^ i);
    int RMSB = (combinedXor & -combinedXor);
    int first = 0, second = 0;
    for (int i = 1; i <= inputLen; i++) {
      if ((RMSB & input[i - 1]) == 0) first ^= input[i - 1];
      else second ^= input[i - 1];
      if (((RMSB & i) == 0)) first ^= i;
      else second ^= i;
    }

    int duplicateNumber = 0;
    for (int inp : input) {
      if ((first ^ inp) == 0) {
        duplicateNumber = first;
        break;
      }
    }

    if (duplicateNumber != 0) {
      System.out.println("missing   " + second);
      System.out.println("duplicate " + duplicateNumber);
    } else {
      System.out.println("missing   " + first);
      System.out.println("duplicate " + second);
    }
  }
}
