public class L1079_rec {

  public static void main(String[] args) {
    System.out.println(numTilePossibilities("V"));
  }

  public static int numTilePossibilities(String tiles) {
    return numTilePossibilities(tiles, 0);
  }

  public static int numTilePossibilities(String tiles, int visited) {
    int count = 0;
    int levelVisited = 0;
    for (int i = 0; i < tiles.length(); i++) {
      if ((levelVisited & (1 << (tiles.charAt(i) - 'A'))) == 0 && (visited & (1 << i)) == 0) {
        visited ^= (1 << i);
        levelVisited ^= (1 << (tiles.charAt(i) - 'A'));
        count += numTilePossibilities(tiles, visited) + 1;
        visited ^= (1 << i);
      }
    }

    return count;
  }
}
