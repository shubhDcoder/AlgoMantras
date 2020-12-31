public class JosephusExecution {
  public static void main(String[] args) {
    System.out.println("N = 7, K = 2 : survivor is " + josephus(7, 2));
    System.out.println("N = 7, K = 3 : survivor is " + josephus(7, 3));
    System.out.println("N = 5, K = 3 : survivor is " + josephus(5, 3));
    System.out.println("N = 8, K = 4 : survivor is " + josephus(8, 4));
  }

  public static int josephus(int totalRemains, int killAtIndex) {
    if (totalRemains == 1) return 0;
    return (josephus(totalRemains - 1, killAtIndex) + killAtIndex) % totalRemains;
  }
}
