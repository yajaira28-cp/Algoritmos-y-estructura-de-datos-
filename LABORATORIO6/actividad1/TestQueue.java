package actividad1;

interface Queue<E> {
    void enqueue(E x);
    E dequeue() throws ExceptionisEmpty;
    E front() throws ExceptionisEmpty;
    boolean isEmpty();
}

// Excepción
class ExceptionisEmpty extends Exception {
    public ExceptionisEmpty(String message) {
        super(message);
    }
}

// 🔹 Implementación con arreglo
class QueueArray<E> implements Queue<E> {
    private E[] array;
    private int front;
    private int rear;
    private int size;

    public QueueArray(int n) {
        array = (E[]) new Object[n];
        front = 0;
        rear = -1;
        size = 0;
    }

    // insertar elemento
    public void enqueue(E x) {
        if (isFull()) {
            System.out.println("La cola está llena");
            return;
        }
        rear = (rear + 1) % array.length;
        array[rear] = x;
        size++;
    }

    // eliminar elemento
    public E dequeue() throws ExceptionisEmpty {
        if (isEmpty()) {
            throw new ExceptionisEmpty("La cola está vacía");
        }
        E dato = array[front];
        front = (front + 1) % array.length;
        size--;
        return dato;
    }

    // ver primer elemento
    public E front() throws ExceptionisEmpty {
        if (isEmpty()) {
            throw new ExceptionisEmpty("La cola está vacía");
        }
        return array[front];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == array.length;
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i = front;
        for (int c = 0; c < size; c++) {
            sb.append(array[i]).append(" ");
            i = (i + 1) % array.length;
        }
        return sb.toString();
    }
}

// Clase de prueba
public class TestQueue {
    public static void main(String[] args) {

        try {
            // Cola de Integer
            QueueArray<Integer> colaInt = new QueueArray<>(5);
            colaInt.enqueue(10);
            colaInt.enqueue(20);
            colaInt.enqueue(30);

            System.out.println("Cola Integer: " + colaInt);
            System.out.println("Front: " + colaInt.front());
            colaInt.dequeue();
            System.out.println("Después de dequeue: " + colaInt);

            // Cola de String
            QueueArray<String> colaStr = new QueueArray<>(5);
            colaStr.enqueue("Hola");
            colaStr.enqueue("Melanny");

            System.out.println("Cola String: " + colaStr);
            System.out.println("Front: " + colaStr.front());
            colaStr.dequeue();
            System.out.println("Después de dequeue: " + colaStr);

        } catch (ExceptionisEmpty e) {
            System.out.println(e.getMessage());
        }
    }
}
