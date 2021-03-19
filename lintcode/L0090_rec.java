import java.util.ArrayList;
import java.util.List;

public class L0090_rec {

  static List<List<Integer>> answer;

  public static void main(String[] args) {
    kSumII(new int[] {1, 2, 3, 4}, 2, 5);
    System.out.println(answer);
  }

  public static List<List<Integer>> kSumII(int[] A, int k, int target) {
    answer = new ArrayList<>();
    if (k == 0) return answer;
    kSumII(A, 0, target, new ArrayList<Integer>(), k, 0);
    return answer;
  }

  public static void kSumII(int[] A, int index, int target, List<Integer> traversed, int k, int included) {
    if (included == k) {
      if (target == 0) answer.add(new ArrayList<Integer>(traversed));
      return;
    }

    if (index == A.length) return;

    if (target - A[index] >= 0) {
      traversed.add(A[index]);
      kSumII(A, index + 1, target - A[index], traversed, k, included + 1);
      traversed.remove(traversed.size() - 1);
    }
    kSumII(A, index + 1, target, traversed, k, included);
  }
}
