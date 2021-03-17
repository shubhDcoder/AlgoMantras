import java.util.ArrayList;
import java.util.List;

public class L0216_rec {

  static List<List<Integer>> answer;

  public static void main(String[] args) {
    System.out.println(combinationSum3(3, 7));
    System.out.println(combinationSum3(3, 9));
  }

  public static List<List<Integer>> combinationSum3(int k, int n) {
    answer = new ArrayList<>();
    combinationSum3(k, n, 1, new ArrayList<Integer>(), 0);
    return answer;
  }

  public static void combinationSum3(int k, int sum, int index, List<Integer> traversed, int processed) {
    if (processed == k) {
      if (sum == 0) answer.add(new ArrayList<>(traversed));
      return;
    }

    for (int i = index; i <= 9; i++) {
      if (sum - i >= 0) {
        traversed.add(i);
        combinationSum3(k, sum - i, i + 1, traversed, processed + 1);
        traversed.remove(traversed.size() - 1);
      }
    }
  }
}
