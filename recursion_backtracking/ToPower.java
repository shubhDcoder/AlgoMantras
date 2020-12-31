public class ToPower {
  public static void main(String[] args) {
    System.out.println("result of 3 ^ 10 = " + toPower(3, 10));
  }

  public static int toPower(int a, int b) {
    if (b == 0) return 1;
    if (b == 1) return a;
    int commonResult = toPower(a, b / 2);
    if ((b & 1) == 1) return commonResult * commonResult * a;
    return commonResult * commonResult;
  }
}
