public class CoinChange {
  public static int[] coins = {2, 3, 5, 7};

  public static void main(String[] args) {
    int answer = getPermutationInfiniteSupplyV1(10, "");
    System.out.println("\nNo of permutations with infinite supply of coins are " + answer);

    answer = getCombinationInfiniteSupplyV1(10, "", 0);
    System.out.println("\nNo of combinations with infinite supply of coins are " + answer);

    answer = getCombinationFiniteSupplyV1(10, "", 0);
    System.out.println("\nNO of combinatinos with finite supply of coins are " + answer);

    answer = getPermutationFiniteSupplyV1(10, "", new byte[coins.length]);
    System.out.println("\nNo of permutations with finite supply of coins are " + answer);
  }

  public static int getPermutationInfiniteSupplyV1(int target, String answer) {
    if (target == 0) {
      System.out.print(String.format("[%s] ", answer.trim()));
      return 1;
    }

    int count = 0;
    for (int i = 0; i < coins.length; i++) {
      if (target - coins[i] >= 0) {
        count += getPermutationInfiniteSupplyV1(target - coins[i], answer + coins[i] + " ");
      }
    }
    return count;
  }

  public static int getCombinationInfiniteSupplyV1(int target, String answer, int index) {
    if (target == 0) {
      System.out.print(String.format("[%s] ", answer.trim()));
      return 1;
    }

    int count = 0;
    for (int i = index; i < coins.length; i++) {
      if (target - coins[i] >= 0) {
        count += getCombinationInfiniteSupplyV1(target - coins[i], answer + coins[i] + " ", i);
      }
    }
    return count;
  }

  public static int getCombinationFiniteSupplyV1(int target, String answer, int index) {
    if (target == 0) {
      System.out.print(String.format("[%s] ", answer.trim()));
      return 1;
    }

    int count = 0;
    for (int i = index; i < coins.length; i++) {
      if (target - coins[i] >= 0) {
        count += getCombinationFiniteSupplyV1(target - coins[i], answer + coins[i] + " ", i + 1);
      }
    }
    return count;
  }

  public static int getPermutationFiniteSupplyV1(int target, String answer, byte[] visited) {
    if (target == 0) {
      System.out.print(String.format("[%s] ", answer.trim()));
      return 1;
    }

    int count = 0;
    for (int i = 0; i < coins.length; i++) {
      if (visited[i] == 0 && target - coins[i] >= 0) {
        visited[i] = 1;
        count += getPermutationFiniteSupplyV1(target - coins[i], answer + coins[i] + " ", visited);
        visited[i] = 0;
      }
    }
    return count;
  }
}
