package actividad2;

import actividad1.ExceptionIsEmpty;

public class TestDeque {
    public static void main(String[] args) {
          try {
            DequeLink<Integer> d = new DequeLink<>();

            d.addFirst(12);
            d.addLast(30);
            d.addFirst(10);

            System.out.println("Deque: " + d);
            System.out.println("Primero: " + d.getFirst());
            System.out.println("Último: " + d.getLast());

            d.removeFirst();
            System.out.println("Después de remove el primero: " + d);

            d.removeLast();
            System.out.println("Después de remove el último: " + d);

        } catch (ExceptionIsEmpty e) {
            System.out.println(e.getMessage());
        }
    }
}