public class SegregateSets {
  public static void main(String[] args) {
    int[] input = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
    int answer = segregateSets_2(input, 0, 0, 0, "", "", false);
    System.out.println("unique sets are " + answer);
  }

  public static int segregateSets(int[] input, int index, int sum_a, int sum_b, String list_a, String list_b) {
    if (index == input.length) {
      if (sum_a == sum_b) {
        System.out.println(list_a.trim() + " = " + list_b.trim());
        return 1;
      } else return 0;
    }
    int count = 0;
    count += segregateSets(input, index + 1, sum_a + input[index], sum_b, list_a + " " + input[index], list_b);
    count += segregateSets(input, index + 1, sum_a, sum_b + input[index], list_a, list_b + " " + input[index]);
    return count;
  }

  public static int segregateSets_2(int[] input, int index, int sum_a, int sum_b, String list_a, String list_b, boolean isIncluded) {
    int count = 0;
    if (sum_a == sum_b && sum_a != 0 && isIncluded) {
      System.out.println(list_a.trim() + " = " + list_b.trim());
      count = 1;
    }

    if (index == input.length) return count;

    count += segregateSets_2(input, index + 1, sum_a + input[index], sum_b, list_a + " " + input[index], list_b, true);
    count += segregateSets_2(input, index + 1, sum_a, sum_b + input[index], list_a, list_b + " " + input[index], true);
    count += segregateSets_2(input, index + 1, sum_a, sum_b, list_a, list_b, false);
    return count;
  }
}
