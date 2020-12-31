public class RopeCutting {
  public static void main(String[] args) {
    System.out.println("N = 5,  arr - [1,  2,  5] = " + (cutRope(5, new int[] {1, 2, 5})));
    System.out.println("N = 23, arr - [9, 11, 12] = " + (cutRope(23, new int[] {9, 11, 12})));
    System.out.println("N = 17, arr - [10, 11, 3] = " + (cutRope(17, new int[] {10, 11, 3})));
    System.out.println("N = 10, arr - [7, 9, 11] = " + (cutRope(10, new int[] {7, 9, 11})));
  }

  public static int cutRope(int ropeLength, int[] lengths) {
    if (ropeLength == 0) return 0;

    int maxFound = -1;
    for (int index = 0; index < lengths.length; index++) {
      int remainingLength = ropeLength - lengths[index];
      if (remainingLength >= 0) maxFound = Math.max(cutRope(remainingLength, lengths), maxFound);
    }

    return maxFound != -1 ? maxFound + 1 : maxFound;
  }
}
