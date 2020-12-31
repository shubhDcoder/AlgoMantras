public class RecursionBasics3 {
  public static void main(String[] args) {
    System.out.println("Sum of numbers of 253 is -> " + getSumOfDigits(253));
    System.out.println("Sum of numbers of 100 is -> " + getSumOfDigits(100));
  }

  // Find sum of digits in number
  public static int getSumOfDigits(int number) {
    if (number % 10 == number) return number;
    return getSumOfDigits(number / 10) + (number % 10);
  }
}
