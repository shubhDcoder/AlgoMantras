import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecursionWithArrays {
  public static void main(String[] args) {
    int[] arr = new int[] {1, 2, 12, 4, 5, 6, 2, 3};
    System.out.println("Multiplication of array is " + multiplyElements(arr, 0));
    System.out.println("Maximum of array is " + findMax(arr, 0));
    System.out.println("Minimum of array is " + findMin(arr, 0));
    System.out.println("check if 12 present " + checkPresence(arr, 12, 0));
    System.out.println("check if 0 present  " + checkPresence(arr, 0, 0));
    System.out.println("first index of 2 is " + firstIndex(arr, 2, 0));
    System.out.println("last index of 2 is  " + lastIndex(arr, 2, 0));

    List<Integer> allIndexes = new ArrayList<>();
    allIndex(arr, 2, 0, allIndexes);
    System.out.println("all indexes of 2 are " + allIndexes);

    System.out.println("V2 - all indexes of 2 are " + Arrays.toString(allIndexV2(arr, 2, 0, 0)));
  }

  // We count total number of element present while going up (pre area) in recursion,
  // From the very top, we create an array of totalCount size and return it.
  // In post area of recursion, we place the indexes inside the returned resultant array
  public static int[] allIndexV2(int[] arr, int data, int index, int totalFound) {
    if (index == arr.length) return new int[totalFound];
    boolean found = false;
    if (arr[index] == data) found = true;
    int[] returnArray = allIndexV2(arr, data, index + 1, found ? totalFound + 1 : totalFound);
    if (found) returnArray[totalFound] = index;
    return returnArray;
  }

  // Generic solution, pass a list as an argument and add every matching element.
  public static void allIndex(int[] arr, int data, int index, List<Integer> answer) {
    if (index == arr.length) return;
    if (arr[index] == data) answer.add(index);
    allIndex(arr, data, index + 1, answer);
  }

  // We could reverse the logic of firstIndex function
  // If we traverse array from end, the first matching element would be the last matching one from starting.
  public static int lastIndex(int[] arr, int data, int index) {
    if (index == arr.length) return -1;
    int result = lastIndex(arr, data, index + 1);
    return result != -1 ? result : arr[index] == data ? index : -1;
  }

  public static int firstIndex(int[] arr, int data, int index) {
    if (index == arr.length) return -1;
    return arr[index] == data ? index : firstIndex(arr, data, index + 1);
  }

  public static boolean checkPresence(int arr[], int data, int index) {
    if (index == arr.length) return false;
    return data == arr[index] ? true : checkPresence(arr, data, index + 1);
  }

  public static int findMin(int[] arr, int index) {
    if (index == arr.length - 1) return arr[index];
    return Math.min(arr[index], findMin(arr, index + 1));
  }

  public static int findMax(int[] arr, int index) {
    if (index == arr.length - 1) return arr[index];
    return Math.max(arr[index], findMax(arr, index + 1));
  }

  public static int multiplyElements(int[] arr, int index) {
    if (index == arr.length) return 1;
    return multiplyElements(arr, index + 1) * arr[index];
  }
}
