public class FIndTwoUniqueNumber {
  public static void main(String[] args) {
    int[] numbers = {1, 1, 7, 2, 2, 3, 3, 4, 5, 5};
    getBothUniqueNumber(numbers);
  }

  public static void getBothUniqueNumber(int[] numbers) {
    int mixedNumber = 0;
    for (int number : numbers) mixedNumber ^= number;
    int RMSB = (mixedNumber & -mixedNumber);
    int first = 0, second = 0;
    for (int number : numbers) {
      if ((number & RMSB) == 0) first ^= number;
      else second ^= number;
    }
    System.out.println(first + " " + second);
  }
}
