import java.util.HashSet;
import java.util.Set;

public class RemoveInvalidParanthesis {
  // https://www.geeksforgeeks.org/remove-invalid-parentheses/

  public static MinimumTracker tracker = new MinimumTracker();

  public static void main(String[] args) {
    // removeInvalidParanthesis("(v)())()", 0, 0, 0, "");
    // removeInvalidParanthesis("()()()", 0, 0, 0, "");
    removeInvalidParanthesis("(abcd)", 0, 0, 0, "");
    System.out.println(tracker.key + " " + tracker.values);
  }

  public static void removeInvalidParanthesis(String input, int index, int opened, int closed, String answer) {
    if (index == input.length()) {
      int diff = input.length() - opened - closed;
      if (tracker.key > diff) {
        tracker.key = diff;
        tracker.values.clear();
        tracker.values.add(answer);
      } else if (tracker.key == diff) tracker.values.add(answer);

    } else if (closed > opened) return;

    while (index < input.length()) {
      if (input.charAt(index) == ')' || input.charAt(index) == '(') break;
      else answer += input.charAt(index++);
    }
    if (index != input.length()) {
      int tempOpened = opened;
      int tempClosed = closed;
      if (input.charAt(index) == '(') tempOpened++;
      else tempClosed++;
      removeInvalidParanthesis(input, index + 1, tempOpened, tempClosed, answer + input.charAt(index));
      removeInvalidParanthesis(input, index + 1, opened, closed, answer);
    }
  }
}

class MinimumTracker {
  public int key = Integer.MAX_VALUE;
  public Set<String> values = new HashSet<>();
}
