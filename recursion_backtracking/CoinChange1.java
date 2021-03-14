public class CoinChange1 {
  public static void main(String[] args) {
    int[] coins = {2, 3, 5, 7};
    int target = 10;
    int answer = getPermutationInfiniteSupplyV2(coins, target, "", 0);
    System.out.println("\nPermutations with infinite supply are " + answer);

    answer = getCombinationInfiniteSupplyV2(coins, target, "", 0);
    System.out.println("\nCombinations with infinite supply are " + answer);

    answer = getCombinationFiniteSupplyV2(coins, target, "", 0);
    System.out.println("\nCombinations with finite supply are " + answer);

    answer = getPermutationFiniteSupplyV2(coins, target, "", 0, new byte[coins.length]);
    System.out.println("\nPermutations with finite supply are " + answer);

    answer = getSubsequenceUsingCombinationMethod("abcd", 0, "");
    System.out.println("\nSubsequences using combination method are " + answer);
  }

  public static int getPermutationInfiniteSupplyV2(int[] coins, int target, String ans, int i) {
    if (target == 0) {
      System.out.print(String.format("[%s] ", ans.trim()));
      return 1;
    }
    if (i == coins.length) return 0;

    int count = 0;
    if (target - coins[i] >= 0) count += getPermutationInfiniteSupplyV2(coins, target - coins[i], ans + coins[i] + " ", 0);
    count += getPermutationInfiniteSupplyV2(coins, target, ans, i + 1);
    return count;
  }

  public static int getCombinationInfiniteSupplyV2(int[] coins, int target, String ans, int i) {
    if (target == 0) {
      System.out.print(String.format("[%s] ", ans.trim()));
      return 1;
    }
    if (i == coins.length) return 0;

    int count = 0;
    if (target - coins[i] >= 0) count += getCombinationInfiniteSupplyV2(coins, target - coins[i], ans + coins[i] + " ", i);
    count += getCombinationInfiniteSupplyV2(coins, target, ans, i + 1);

    return count;
  }

  public static int getCombinationFiniteSupplyV2(int[] coins, int target, String ans, int i) {
    if (target == 0) {
      System.out.print(String.format("[%s] ", ans.trim()));
      return 1;
    }
    if (i == coins.length) return 0;

    int count = 0;
    if (target - coins[i] >= 0) count += getCombinationFiniteSupplyV2(coins, target - coins[i], ans + coins[i] + " ", i + 1);
    count += getCombinationFiniteSupplyV2(coins, target, ans, i + 1);

    return count;
  }

  public static int getPermutationFiniteSupplyV2(int[] coins, int target, String ans, int i, byte marking[]) {
    if (target == 0) {
      System.out.print(String.format("[%s] ", ans.trim()));
      return 1;
    }
    if (i == coins.length) return 0;

    int count = 0;
    if (target - coins[i] >= 0 && marking[i] == 0) {
      marking[i] = 1;
      count += getPermutationFiniteSupplyV2(coins, target - coins[i], ans + coins[i] + " ", 0, marking);
      marking[i] = 0;
    }
    count += getPermutationFiniteSupplyV2(coins, target, ans, i + 1, marking);

    return count;
  }

  public static int getSubsequenceUsingCombinationMethod(String input, int index, String answer) {
    int count = 1;
    System.out.print(String.format("[%s] ", answer.trim()));
    for (int from = index; from < input.length(); from++) count += getSubsequenceUsingCombinationMethod(input, from + 1, answer + input.charAt(from) + " ");

    return count;
  }
}
