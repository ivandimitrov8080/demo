import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
  public static void main(String[] args) {
    System.out.println("Hello, World!");
    System.out.println(fib(200));
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
}
