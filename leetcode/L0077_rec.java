import java.util.ArrayList;
import java.util.List;

public class L0077_rec {
  static List<List<Integer>> answer;

  public static void main(String[] args) {
    System.out.println(combine(4, 2));
  }

  public static List<List<Integer>> combine(int n, int k) {
    answer = new ArrayList<>();
    combine(n, k, 0, new ArrayList<Integer>(), 1);
    return answer;
  }

  public static void combine(int N, int K, int processed, List<Integer> traversed, int index) {
    if (processed == K) {
      answer.add(new ArrayList<Integer>(traversed));
      return;
    }
    for (int i = index; i <= N; i++) {
      traversed.add(i);
      combine(N, K, processed + 1, traversed, i + 1);
      traversed.remove(traversed.size() - 1);
    }
  }
}
