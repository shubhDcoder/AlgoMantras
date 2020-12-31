public class RecursionTree {
  public static void main(String[] args) {
    System.out.println("\noutput is : " + understandRecursion(5, ""));
  }

  public static int understandRecursion(int number, String tabs) {
    if (number <= 1) {
      System.out.println(tabs + "base case " + number);
      return number + 1;
    }

    int count = 0;
    System.out.println(tabs + "pre case " + number);
    count += understandRecursion(number - 1, tabs + "\t");

    System.out.println(tabs + "in case " + number);

    count += understandRecursion(number - 2, tabs + "\t");
    System.out.println(tabs + "post case " + number);

    return count;
  }
}
