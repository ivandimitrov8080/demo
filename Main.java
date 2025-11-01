import java.util.Arrays;

public class Main {
  public static void main(String[] args) {
    System.out.println(Arrays.toString(args));
    // copilot r u here
    System.out.println("Hello, World!");
  }
}

// TODO: Implement a very simple stack

class SimpleStack<T> {
  private Object[] elements;
  private int size;
  private static final int DEFAULT_CAPACITY = 10;

  public SimpleStack() {
    elements = new Object[DEFAULT_CAPACITY];
    size = 0;
  }

  public void push(T item) {
    if (size == elements.length) {
      resize();
    }
    elements[size++] = item;
  }

  public T pop() {
    if (isEmpty()) {
      throw new IllegalStateException("Stack is empty");
    }
    T item = (T) elements[--size];
    elements[size] = null; // Avoid memory leak
    return item;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  private void resize() {
    int newCapacity = elements.length * 2;
    elements = Arrays.copyOf(elements, newCapacity);
  }
}
