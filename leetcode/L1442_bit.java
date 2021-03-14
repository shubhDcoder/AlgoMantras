public class L1442_bit {
  public int countTriplets(int[] arr) {
    int countOfTriplets = 0;
    for (int i = 0; i < arr.length - 1; i++) {
      int currentXor = arr[i];
      for (int k = i + 1; k < arr.length; k++) {
        currentXor ^= arr[k];
        if (currentXor == 0) countOfTriplets += (k - i);
      }
    }
    return countOfTriplets;
  }
}
