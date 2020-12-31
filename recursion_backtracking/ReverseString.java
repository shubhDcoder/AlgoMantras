public class ReverseString {
  public static void main(String[] args) {
    System.out.println("reverse of bitterbit is : " + reverseString("bitterbit", 0));
  }

  public static String reverseString(String str, int index) {
    if (index == str.length()) return "";
    return reverseString(str, index + 1) + str.charAt(index);
  }
}
