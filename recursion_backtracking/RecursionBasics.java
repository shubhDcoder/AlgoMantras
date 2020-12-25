public class RecursionBasics {

  // As all of the examples builds a recursion tree with every node containing only one child node,
  // Time / Space complexity of all the examples are O(N) which is indeed the height of recursion tree.
  public static void main(String[] args) {
    // Get factorial of a number
    System.out.println("Factorial of 10 is -> " + getFactorial(10));

    // Print number in increasing order
    System.out.println("From 10 - 20 Increasing");
    printIncreasingNumbers(10, 20);
    newLine();

    // Print number in decreasing order
    System.out.println("From 10 - 20 Decreasing");
    printDecreasingNumbers(10, 20);
    newLine();

    // Print number in increasing + decreasing order
    System.out.println("From 10 - 15 IncDecreasing order");
    printIncDecreasingNumbers(10, 15);
    newLine();

    // Print number in increasing + decreasing / odd + even order
    System.out.println("From 10 - 15 OddEvenIncDecreasing order");
    printOddEvenIncDecreasingNumbers(10, 20);
    newLine();

    // Calculate power of a number
    System.out.println(String.format("Power - 2 ^ 10 = %s", calculatePower(2, 10)));
  }

  public static int getFactorial(int number) {
    return number <= 1 ? number : number * getFactorial(number - 1);
  }

  public static void printIncreasingNumbers(int start, int end) {
    if (start > end) return;
    System.out.print(start + " ");
    printIncreasingNumbers(start + 1, end);
  }

  public static void printDecreasingNumbers(int start, int end) {
    if (start > end) return;
    printDecreasingNumbers(start + 1, end);
    System.out.print(start + " ");
  }

  public static void printIncDecreasingNumbers(int start, int end) {
    if (start > end) return;
    System.out.print(start + " up ");
    printIncDecreasingNumbers(start + 1, end);
    System.out.print(start + " DOWN ");
  }

  public static void printOddEvenIncDecreasingNumbers(int start, int end) {
    if (start > end) return;
    if ((start & 1) == 0) {
      System.out.print(start + " ");
      printOddEvenIncDecreasingNumbers(start + 1, end);
    } else {
      printOddEvenIncDecreasingNumbers(start + 1, end);
      System.out.print(start + " ");
    }
  }

  public static int calculatePower(int number, int toPower) {
    if (toPower == 1) return number;
    return number * calculatePower(number, toPower - 1);
  }

  public static void newLine() {
    System.out.println();
  }
}
