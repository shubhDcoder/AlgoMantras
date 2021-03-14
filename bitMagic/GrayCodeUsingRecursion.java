import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class GrayCodeUsingRecursion {
  public static void main(String[] args) {
    System.out.println(getGrayCodes(4));
  }

  public static List<String> getGrayCodes(int numberOfBits) {
    if (numberOfBits == 1) {
      List<String> res = new ArrayList<>();
      res.add("0");
      res.add("1");
      return res;
    }

    List<String> tempRes = getGrayCodes(numberOfBits - 1);
    List<String> res = new ArrayList<>();
    Iterator<String> iterator = tempRes.iterator();
    while (iterator.hasNext()) {
      res.add("0" + iterator.next());
    }

    ListIterator lIterator = tempRes.listIterator(tempRes.size());
    while (lIterator.hasPrevious()) {
      res.add("1" + lIterator.previous());
    }

    return res;
  }
}
