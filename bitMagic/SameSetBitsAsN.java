public class SameSetBitsAsN {
  public static void main(String[] args) {
    int number = 12;
    System.out.println(getCountOfNumbersHavingSameSetBits(number, countSetBits(number), 31));
  }

  public static int getCountOfNumbersHavingSameSetBits(int number, int setBits, int places) {
    if (places == 0) return 0; // if places reached to 0, it's always the same number.

    int count = 0;
    if ((number & (1 << places)) == 0) {
      return getCountOfNumbersHavingSameSetBits(number, setBits, places - 1);
    } else {
      count += combination(places, setBits);
      count += getCountOfNumbersHavingSameSetBits(number, setBits - 1, places - 1);
    }

    return count;
  }

  public static int countSetBits(int number) {
    int count = 0;
    while (number != 0) {
      number = (number - (number & -number));
      count++;
    }
    return count;
  }

  public static int combination(int n, int r) {
    return fact(n) / (fact(r) * fact(n - r));
  }

  public static int fact(int n) {
    int res = 1;
    for (int i = 2; i <= n; i++) res = res * i;
    return res;
  }
}
