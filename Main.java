public class Main {
  public static void main(String[] args) {
    for (int i = 0; i < 100; i++) {
      var b = new Ball("red", 5.0);
      System.out.println(b);
    }
  }
}

class Ball {
  private String color;
  private double radius;

  public Ball(String color, double radius) {
    this.color = color;
    this.radius = radius;
  }

  public String getColor() {
    return color;
  }

  public double getRadius() {
    return radius;
  }

  @Override
  public String toString() {
    return "Ball{" +
        "color='" + color + '\'' +
        ", radius=" + radius +
        '}';
  }
}
