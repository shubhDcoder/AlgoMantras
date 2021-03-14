public class BitsBasics {
  public static void main(String[] args) {
    int number = 1239;
    int number2 = 1232;
    int number3 = 1024;
    System.out.println("number         -> " + Integer.toBinaryString(number));

    System.out.println("3rd bit on     -> " + Integer.toBinaryString(setAbit(number, 3)));
    System.out.println("2nd bit off    -> " + Integer.toBinaryString(unsetAbit(number, 2)));
    System.out.println("2nd bit toggle -> " + Integer.toBinaryString(toggleAbit(number, 2)));
    System.out.println("3rd bit toggle -> " + Integer.toBinaryString(toggleAbit(number, 3)));

    System.out.println("number2        -> " + Integer.toBinaryString(number2));
    System.out.println("RMSB           -> " + Integer.toBinaryString(findRightMostSetBit(number2)));
    System.out.println("If power of 2  -> " + ifPowerOfTwo(number2));
    System.out.println("If power of 2  -> " + ifPowerOfTwo(number3));

    System.out.println("Count set bits V1 --------------------- ");
    System.out.println("Set bits are   -> " + getSetBitsV1(number2));
    System.out.println("Set bits are   -> " + getSetBitsV1(number3));
    System.out.println("Set bits are   -> " + getSetBitsV1(-1));

    System.out.println("Count set bits V2 --------------------- ");
    System.out.println("Set bits are   -> " + getSetBitsV2(number2));
    System.out.println("Set bits are   -> " + getSetBitsV2(number3));
    System.out.println("Set bits are   -> " + getSetBitsV2(-1));

    System.out.println("Count set bits V3 --------------------- ");
    System.out.println("Set bits are   -> " + getSetBitsV3(number2));
    System.out.println("Set bits are   -> " + getSetBitsV3(number3));
    System.out.println("Set bits are   -> " + getSetBitsV3(-1));
  }

  public static int setAbit(int number, int kthBit) {
    return ((1 << kthBit) | number);
  }

  public static int unsetAbit(int number, int kthBit) {
    return (~(1 << kthBit)) & number;
  }

  public static int toggleAbit(int number, int kthBit) {
    return (1 << kthBit) ^ number;
  }

  public static int findRightMostSetBit(int number) {
    return number & -number;
  }

  public static boolean ifPowerOfTwo(int number) {
    return number > 0 && ((number & (number - 1)) == 0);
  }

  // Count number of set bits
  public static int getSetBitsV1(int number) {
    int count = 0;
    for (int i = 0; i < 32; i++) {
      if ((number & (1 << i)) != 0) count++;
    }
    return count;
  }

  // Count number of set bits Kernighan Algo
  public static int getSetBitsV2(int number) {
    int count = 0;
    while (number != 0) {
      number = (number & (~(number & (-number))));
      count++;
    }
    return count;
  }

  public static int getSetBitsV3(int number) {
    int count = 0;
    while (number != 0) {
      number = (number - (number & (-number)));
      count++;
    }
    return count;
  }
}
