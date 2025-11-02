import java.util.ArrayList;
import java.util.List;

public class Main {
  public static void main(String[] args) {
    System.out.println("Hello, World!");
    System.out.println(fib(200));
    System.out.println(fibRec(20));
  }

  /*
   * this generates the first {limit} fib numbers
   */
  public static List<Integer> fib(int limit) {
    List<Integer> res = new ArrayList<>();
    int a = 0, b = 1, c = 1;
    for (int i = 0; c < limit; c = a + b) {
      a = b;
      b = c;
      res.add(c);
    }
    return res;
  }

  // fib but recursive and make it show all numbers in array
  public static List<Integer> fibRec(int n) {
    List<Integer> res = new ArrayList<>();
    fibRecHelper(n, res);
    return res;
  }

  private static void fibRecHelper(int n, List<Integer> res) {
    if (n <= 0) {
      return;
    }
    if (n == 1) {
      res.add(1);
      return;
    }
    fibRecHelper(n - 1, res);
    int nextFib = (res.size() >= 2) ? res.get(res.size() - 1) + res.get(res.size() - 2) : 1;
    res.add(nextFib);

  }
}
