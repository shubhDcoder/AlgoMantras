import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class L0040_rec {
  List<List<Integer>> globalAnswer;

  public static void main(String[] args) {
    int[] array = new int[] {10, 1, 2, 7, 6, 1, 5};
    Arrays.sort(array);
    List<List<Integer>> ans = new L0040_rec().combinationSum2(array, 8);
    for (List<Integer> list : ans) System.out.println(list);
  }

  public List<List<Integer>> combinationSum2(int[] candidates, int target) {
    globalAnswer = new ArrayList<>();
    combinationSum2(candidates, target, 0, new ArrayList<Integer>());
    return globalAnswer;
  }

  public void combinationSum2(int[] candidates, int target, int index, List<Integer> answer) {
    if (target == 0) {
      if (answer.size() > 0) globalAnswer.add(new ArrayList<Integer>(answer));
      return;
    }

    int prev = -1;
    for (int i = index; i < candidates.length; i++) {
      if (target >= candidates[i] && prev != candidates[i]) {
        answer.add(candidates[i]);
        combinationSum2(candidates, target - candidates[i], i + 1, answer);
        answer.remove(answer.size() - 1);
        prev = candidates[i];
      }
    }
  }
}
