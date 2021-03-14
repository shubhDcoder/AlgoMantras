import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class L1178_bits {
  public static void main(String[] args) {
    // String[] words = {"aaaa", "asas", "able", "ability", "actt", "actor", "access"};
    // String[] puzzles = {"aboveyz", "abrodyz", "abslute", "absoryz", "actresz", "gaswxyz"};

    String[] words = {"apple", "pleas", "please"};
    String[] puzzles = {"aelwxyz", "aelpxyz", "aelpsxy", "saelpxy", "xaelpsy"};

    System.out.println(findNumOfValidWords(words, puzzles));
    System.out.println(findNumOfValidWords1(words, puzzles));
  }

  public static List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
    Map<Integer, List<Integer>> cache = new HashMap<>();
    for (String word : words) {
      int number = getIntFromString(word);
      int tempNumber = number;
      while (tempNumber != 0) {
        int RMSB = (tempNumber & -tempNumber);
        if (cache.get(RMSB) == null) cache.put(RMSB, new ArrayList<Integer>());
        cache.get(RMSB).add(number);
        tempNumber = (tempNumber - RMSB);
      }
    }

    cache.forEach((K, V) -> System.out.println(Integer.toBinaryString(K) + ", value : " + V));

    List<Integer> answer = new ArrayList<>();
    for (int i = 0; i < puzzles.length; i++) answer.add(0);
    for (int index = 0; index < puzzles.length; index++) {
      List<Integer> wordsMapped = cache.get((1 << (puzzles[index].charAt(0) - 'a')));
      if (wordsMapped != null) {
        int puzzleIntVal = getIntFromString(puzzles[index]);
        for (Integer word : wordsMapped) {
          if ((puzzleIntVal & word) == word) answer.set(index, answer.get(index) + 1);
        }
      }
    }
    return answer;
  }

  public static int getIntFromString(String input) {
    int output = 0;
    for (char c : input.toCharArray()) output |= (1 << (c - 'a'));
    return output;
  }

  // Better solution

  public static List<Integer> findNumOfValidWords1(String[] words, String[] puzzles) {
    Map<Integer, Integer> cache = new HashMap<>();
    for (String word : words) {
      int wordMask = getIntFromString(word);
      if (cache.get(wordMask) == null) cache.put(wordMask, 1);
      else cache.put(wordMask, cache.get(wordMask) + 1);
    }

    List<Integer> puzzleToWordCount = new ArrayList<>();
    for (String puzzle : puzzles) {
      int count = 0;
      int puzzleMask = getIntFromString(puzzle);
      int tempMask = puzzleMask;
      while (tempMask != 0) {
        if (((tempMask & (1 << (puzzle.charAt(0) - 'a'))) != 0) && cache.containsKey(tempMask)) {
          count += cache.get(tempMask);
        }
        tempMask = ((tempMask - 1) & puzzleMask);
      }
      puzzleToWordCount.add(count);
    }
    return puzzleToWordCount;
  }
}
