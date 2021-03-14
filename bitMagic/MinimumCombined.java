import java.util.ArrayList;
import java.util.List;

public class MinimumCombined {
  public static void main(String[] args) {
    int[] persons = {Integer.parseInt("111100", 2), Integer.parseInt("111001", 2), Integer.parseInt("010101", 2), Integer.parseInt("101010", 2)};
    getMinimumToCombine(new ArrayList<Integer>(), 0, persons.length, 0, persons);
    System.out.println(answer);
  }

  public static List<Integer> answer = new ArrayList<>();

  public static void getMinimumToCombine(List<Integer> currentAnswer, int personAt, int totalPerson, int currentBits, int[] persons) {
    if (personAt == totalPerson) {
      if (currentBits == ((1 << 6) - 1) && (answer.size() > currentAnswer.size() || answer.size() == 0)) {
        answer = new ArrayList<>(currentAnswer);
      }
      return;
    }

    currentAnswer.add(personAt);
    getMinimumToCombine(currentAnswer, personAt + 1, totalPerson, (currentBits | persons[personAt]), persons);
    currentAnswer.remove(currentAnswer.size() - 1);

    getMinimumToCombine(currentAnswer, personAt + 1, totalPerson, currentBits, persons);
  }
}
