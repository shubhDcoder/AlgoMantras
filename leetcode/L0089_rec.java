import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class L0089_rec {
  public static void main(String[] args) {
    System.out.println(grayCodeVersion1(4));
    System.out.println(grayCodeVersion2(4));
    System.out.println(grayCodeVersion3(4));
    System.out.println(grayCodeVersion4(4));
  }

  // Version 4 Super fast using bits

  public static List<Integer> grayCodeVersion4(int number) {
    int totalNumbers = (int) Math.pow(2, number);
    Integer[] grayCodes = new Integer[totalNumbers];
    grayCodes[0] = 0;
    grayCodes[1] = 1;
    int atBit = 1;
    int counter = 2;
    while (number-- > 1) {
      for (int i = (1 << atBit) - 1; i >= 0; i--) {
        grayCodes[counter++] = (grayCodes[i] | (1 << atBit));
      }
      atBit++;
    }
    return Arrays.asList(grayCodes);
  }

  // Version 3 Using divde and conquer
  public static List<Integer> grayCodeVersion3(int number) {
    int totalNumbers = (int) Math.pow(2, number);
    Integer[] grayCodes = new Integer[totalNumbers];
    for (int i = 0; i < totalNumbers; i++) grayCodes[i] = i;
    divideAndConquer(grayCodes, 0, totalNumbers - 1);
    return Arrays.asList(grayCodes);
  }

  public static void divideAndConquer(Integer[] grayCodes, int start, int end) {
    if (start < end) {
      int mid = (end + start) / 2;
      divideAndConquer(grayCodes, start, mid);
      divideAndConquer(grayCodes, mid + 1, end);
      reverseLastHalf(grayCodes, mid + 1, end);
    }
  }

  public static void reverseLastHalf(Integer[] array, int start, int end) {
    while (start < end) {
      int temp = array[end];
      array[end] = array[start];
      array[start] = temp;
      start++;
      end--;
    }
  }

  // Version 3 Using bits
  public static List<Integer> grayCodeVersion2(int number) {
    int totalNumbers = (int) Math.pow(2, number);
    Integer[] grayCodes = new Integer[totalNumbers];
    grayCodes[0] = 0;
    grayCodes[1] = 1;

    int atBit = 1;
    int counter = 2;
    while (atBit < number) {
      for (int i = (1 << atBit) - 1; i >= 0; i--) grayCodes[counter++] = (grayCodes[i] + (1 << atBit));
      atBit++;
    }
    return Arrays.asList(grayCodes);
  }

  // Version 1 Using Recursion only.
  public static List<Integer> grayCodeVersion1(int number) {
    List<String> grayCodsStrings = grayCodeVersion1_driver(number);
    List<Integer> grayCodeIntegers = new ArrayList<>();
    for (String s : grayCodsStrings) grayCodeIntegers.add(Integer.parseInt(s, 2));
    return grayCodeIntegers;
  }

  public static List<String> grayCodeVersion1_driver(int number) {
    if (number == 1) {
      List<String> res = new ArrayList<>();
      res.add("0");
      res.add("1");
      return res;
    }

    List<String> tempRes = grayCodeVersion1_driver(number - 1);
    List<String> res = new ArrayList<>();
    for (String s : tempRes) res.add("0" + s);
    for (int i = tempRes.size() - 1; i >= 0; i--) res.add("1" + tempRes.get(i));
    return res;
  }
}
