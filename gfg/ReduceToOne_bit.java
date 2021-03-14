public class ReduceToOne_bit {
  public static void main(String[] args) {
    int intInput = 15;

    int count = 0;
    long input = intInput;
    while (input != 1) {
      count++;
      if ((input & 1) != 1) input = (input >> 1);
      else if (input == 3) input--;
      else if ((input & 3) == 3) input++;
      else input--;
    }
    System.out.println(count);
  }
}
