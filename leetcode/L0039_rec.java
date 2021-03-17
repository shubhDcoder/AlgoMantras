import java.util.ArrayList;
import java.util.List;

public class L0039_rec {
  public static List<List<Integer>> answer;

  public static void main(String[] args) {
    System.out.println(combinationSum(new int[] {2, 3, 6, 7}, 7));
    System.out.println(combinationSum(new int[] {2, 3, 5}, 8));
    System.out.println(combinationSum(new int[] {1}, 2));
  }

  public static List<List<Integer>> combinationSum(int[] candidates, int target) {
    answer = new ArrayList<>();
    combinationSum(candidates, target, 0, new ArrayList<Integer>());
    return answer;
  }

  public static void combinationSum(int[] candidates, int target, int index, List<Integer> path) {
    if (target == 0) {
      answer.add(new ArrayList<>(path));
      return;
    }

    for (int i = index; i < candidates.length; i++) {
      if (target - candidates[i] >= 0) {
        path.add(candidates[i]);
        combinationSum(candidates, target - candidates[i], i, path);
        path.remove(path.size() - 1);
      }
    }
  }
}
